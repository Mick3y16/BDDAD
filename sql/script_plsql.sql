/*
	Declaração dos tipos de dados extra.
*/
CREATE OR REPLACE PACKAGE variaveis_adicionais
AS 
  TYPE lista_numerica IS TABLE OF NUMBER; 
END variaveis_adicionais;

/*
	Ponto 1
*/
DROP SEQUENCE seq_ficharegistos;
CREATE SEQUENCE seq_ficharegistos MINVALUE 0 MAXVALUE 99 START WITH 7 INCREMENT BY 1;

DROP SEQUENCE seq_tratamentos;
CREATE SEQUENCE seq_tratamentos MINVALUE 0 MAXVALUE 99 START WITH 9 INCREMENT BY 1;

CREATE OR REPLACE PROCEDURE CriarFichaDeRegistos(
	medico medico.id%TYPE,
	paciente paciente.id%TYPE,
	tipo_pagamento tipo_pagamento.id%TYPE,
	servicos variaveis_adicionais.lista_numerica
)
IS
id_orcamento orcamento.id%TYPE;
custo_servico orcamento.valor_total%TYPE;
BEGIN
  -- Atribuir o id do orçamento que se vai inserir a uma variável
  SELECT seq_ficharegistos.NEXTVAL INTO id_orcamento FROM dual;

	-- Inserir o orçamento/ficha de serviços
	INSERT INTO orcamento(id, medico, paciente, tipo_pagamentoid, valor_total)
	VALUES(id_orcamento, medico, paciente, tipo_pagamento, 0);

	-- Percorrer todos os elementos da lista
	FOR i IN servicos.FIRST .. servicos.LAST LOOP
		-- e adicionar cada um dos serviços à ficha
		INSERT INTO tratamento(id, orcamento, servico, consulta)
		VALUES(id_orcamento, seq_ficharegistos.CURRVAL, i, NULL);
		
		-- Selecionar o custo do serviço e atualizar o valor total do orçamento
		SELECT valor INTO custo_servico FROM servico WHERE id = i;
		UPDATE orcamento SET valor_total = valor_total + custo_servico WHERE id = id_orcamento;	
  END LOOP;
END CriarFichaDeRegistos;

/*
	Ponto 2
*/
DROP SEQUENCE seq_reservas;
CREATE SEQUENCE seq_reservas MINVALUE 0 MAXVALUE 99 START WITH 2 INCREMENT BY 1;

CREATE OR REPLACE PROCEDURE CriarReserva(
		gabinete gabinete.id%TYPE,
		data reserva.data%TYPE,
		hora_inicio reserva.hora_inicio%TYPE,
		hora_fim reserva.hora_fim%TYPE,
		equipamentos variaveis_adicionais.lista_numerica
)
IS
  id_reserva reserva.id%TYPE;
  -- equip reserva_equipamentos.equipamento_substituicao%TYPE;
BEGIN
  -- Atribuir o id da reserva que se vai inserir a uma variável.
  SELECT seq_reservas.NEXTVAL INTO id_reserva FROM dual;

  -- Inserir a reserva
  INSERT INTO reserva(id, gabinete, data, hora_inicio, hora_fim, estado)
	VALUES(id_reserva, gabinete, data, hora_inicio, hora_fim, 3);
  
  -- Inserir cada um dos equipamentos
  FOR i IN equipamentos.FIRST .. equipamentos.LAST LOOP
    INSERT INTO reserva_equipamentos(reservaid, equipamento_substituicao) VALUES(id_reserva, i);
  END LOOP;
  /* Alternativa com While
  equip := equipamentos.FIRST;
  WHILE equip IS NOT NULL LOOP
    INSERT INTO reserva_equipamentos(reservaid, equipamento_substituicao) VALUES(id_reserva, equip);
    equip := equipamentos.NEXT(equip);
  END LOOP;
  */
END CriarReserva;

/*
	Ponto 3
*/
CREATE OR REPLACE PROCEDURE getClientesEmDivida
IS
  CURSOR c IS 
    SELECT paciente
    FROM (SELECT paciente.nome_completo as paciente,
            SUM(CASE WHEN pago = 1 THEN valor ELSE 0 END) as valorPago,
            SUM(CASE WHEN (pago = 0 AND TO_DATE(sysdate, 'yy-mm-dd') - data_vencimento >= 0) THEN valor ELSE 0 END) +
            SUM(CASE WHEN (pago = 0 AND TO_DATE(sysdate, 'yy-mm-dd') - data_vencimento < 0) THEN (valor * ((0.25 / 100) + 1)) ELSE 0 END) as ValorPorPagar
          FROM paciente, orcamento, parcela_pagamento
          WHERE paciente.id = orcamento.paciente
            AND orcamento.id = parcela_pagamento.orcamentoid
          GROUP BY paciente.nome_completo)
    WHERE valorPorPagar > 10 * valorPago;
    rc c%ROWTYPE;
BEGIN
  OPEN c;

  LOOP
    FETCH c INTO rc;
    EXIT WHEN c%NOTFOUND;
    -- Imprimir cada um dos pacientes.
    DBMS_OUTPUT.put_line(rc.paciente);
  END LOOP;

  CLOSE c;
END getClientesEmDivida;

/*
	Ponto 4
*/
CREATE OR REPLACE FUNCTION getTotalPagarPaciente(
  cliente paciente.id%TYPE
)
RETURN SYS_REFCURSOR
AS apontador SYS_REFCURSOR;
BEGIN
  OPEN apontador FOR SELECT medico.nome, SUM(medico.preco_consulta) + (0.2 * SUM(servico.valor)) as total
                     FROM paciente, orcamento, tratamento, servico, consulta, medico
                     WHERE paciente.id = cliente
                       AND paciente.id = orcamento.paciente
                       AND orcamento.id = tratamento.orcamento
                       AND tratamento.consulta = consulta.id
                       AND consulta.medico = medico.id
                       AND tratamento.servico = servico.id
                     GROUP BY medico.nome;
  RETURN apontador;
END getTotalPagarPaciente;

/*
	Ponto 5
*/
CREATE OR REPLACE TRIGGER trg_atualizar_reservas
AFTER UPDATE ON equipamento_substituicao
FOR EACH ROW
WHEN (NEW.disponivel = 1)
DECLARE
  maior_prioridade NUMBER := -1;
  reserva_alterar NUMBER;
BEGIN
	-- Verificar todas as reservas em espera, onde o equipamento se encontra requisitado.
	FOR i IN (SELECT DISTINCT reserva.id as id_reserva, gabinete.prioridade as prioridade
            FROM reserva, gabinete, reserva_equipamentos
            WHERE reserva.gabinete = gabinete.id
              AND reserva.id = reserva_equipamentos.reservaid
              AND reserva_equipamentos.equipamento_substituicao = :NEW.equipamentoid)
  LOOP
    -- Determinar o id da reserva com maior prioridade sobre o equipamento.
    IF (i.prioridade > maior_prioridade) THEN
      maior_prioridade := i.prioridade;
      reserva_alterar := i.id_reserva;
    END IF;
  END LOOP;
	
  -- Se tiver sido encontrada uma reserva em espera, que necessite do equipamento, altera-se o estado.
	IF (maior_prioridade != -1) THEN
    UPDATE reserva SET estado = 0 WHERE reserva.id = reserva_alterar;
  END IF;
END;

/*
	Ponto 6
*/
CREATE OR REPLACE TRIGGER trg_penalizacao_prioridade
AFTER UPDATE ON reserva
FOR EACH ROW
WHEN (NEW.estado = 2)
BEGIN
  IF (:NEW.data - TO_DATE(sysdate, 'yy-mm-dd') < 0) OR ((:NEW.data - TO_DATE(sysdate, 'yy-mm-dd') = 0) AND (:NEW.hora_inicio - to_number(to_char(sysdate, 'hh24')) < -1)) THEN
    -- Mais de uma hora depois da data/hora vale cinco faltas.
    UPDATE gabinete SET faltas_reservas = faltas_reservas + 5 WHERE gabinete.id = :NEW.gabinete;
  ELSIF (:NEW.data - TO_DATE(sysdate, 'yy-mm-dd') = 0 AND :NEW.hora_inicio - to_number(to_char(sysdate, 'hh24')) < 1) THEN
    -- Até uma hora depois vale três faltas.
    UPDATE gabinete SET faltas_reservas = faltas_reservas + 3 WHERE gabinete.id = :NEW.gabinete;
  ELSIF (:NEW.data - TO_DATE(sysdate, 'yy-mm-dd') = 0 AND :NEW.hora_inicio - to_number(to_char(sysdate, 'hh24')) < 2) THEN
    -- Até duas horas antes vale uma falta.
    UPDATE gabinete SET faltas_reservas = faltas_reservas + 1 WHERE id = :NEW.gabinete;
  END IF;
END;
