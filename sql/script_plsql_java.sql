-- Função para obter a lista de cidades.
CREATE OR REPLACE FUNCTION getCidades
RETURN SYS_REFCURSOR
AS apontador SYS_REFCURSOR;
BEGIN
  OPEN apontador FOR SELECT * FROM cidade;
  RETURN apontador;
END getCidades;

-- Função para obter a lista de paises.
CREATE OR REPLACE FUNCTION getPaises
RETURN SYS_REFCURSOR
AS apontador SYS_REFCURSOR;
BEGIN
  OPEN apontador FOR SELECT * FROM pais;
  RETURN apontador;
END getPaises;

-- Função para obter a lista de sistemas de saúde.
CREATE OR REPLACE FUNCTION getSistemasSaude
RETURN SYS_REFCURSOR
AS apontador SYS_REFCURSOR;
BEGIN
  OPEN apontador FOR SELECT * FROM sistema_saude;
  RETURN apontador;
END getSistemasSaude;

-- Função para obter a lista de médicos.
CREATE OR REPLACE FUNCTION getMedicos
RETURN SYS_REFCURSOR
AS apontador SYS_REFCURSOR;
BEGIN
  OPEN apontador FOR SELECT * FROM medico;
  RETURN apontador;
END getMedicos;

-- Função para obter a lista de pacientes. (Passível de melhoria, seria bom retornar também os respetivos sistemas de saúde de cada paciente...)
CREATE OR REPLACE FUNCTION getPacientes
RETURN SYS_REFCURSOR
AS apontador SYS_REFCURSOR;
BEGIN
  OPEN apontador FOR SELECT * FROM paciente;
  RETURN apontador;
END getPacientes;

-- Função para obter a lista de serviços.
CREATE OR REPLACE FUNCTION getServicos
RETURN SYS_REFCURSOR
AS apontador SYS_REFCURSOR;
BEGIN
  OPEN apontador FOR SELECT * FROM servico;
  RETURN apontador;
END getServicos;

-- Função que devolve a lista de materiais.
CREATE OR REPLACE FUNCTION getMateriais
RETURN SYS_REFCURSOR
AS apontador SYS_REFCURSOR;
BEGIN
  OPEN apontador FOR SELECT * FROM material;
  RETURN apontador;
END getMateriais;

-- 1º
-- Procedimento de registo de um paciente.
DROP SEQUENCE seq_paciente;
CREATE SEQUENCE seq_paciente MINVALUE 1 MAXVALUE 99 START WITH 10 INCREMENT BY 1;

CREATE OR REPLACE PROCEDURE registarPaciente(
  bi paciente.bi%TYPE,
  nif paciente.nc%TYPE,
  nome_completo paciente.nome_completo%TYPE,
  endereco paciente.endereco%TYPE,
  cidade paciente.cidade%TYPE,
  pais paciente.pais%TYPE,
  telefone paciente.telefone%TYPE,
  data_nascimento paciente.data_nascimento%TYPE,
  medico_por_omissao paciente.medico_por_omissao%TYPE
)
AS
BEGIN
	-- Inserir o paciente na base de dados.
  INSERT INTO paciente (id, bi, nc, nome_completo, endereco, cidade, pais, telefone, data_nascimento, medico_por_omissao)
  VALUES(seq_paciente.NEXTVAL, bi, nif, nome_completo, endereco, cidade, pais, telefone, data_nascimento, medico_por_omissao);
END registarPaciente;

-- 2º
-- Função que devolve todas as consultas de um paciente.
CREATE OR REPLACE FUNCTION getConsultasPaciente(
  p_id consulta.paciente%TYPE
)
RETURN SYS_REFCURSOR
AS apontador SYS_REFCURSOR;
BEGIN
  OPEN apontador FOR SELECT consulta.*
                     FROM consulta, paciente
                     WHERE p_id = paciente.id
                       AND paciente.id = consulta.paciente;
  RETURN apontador;
END getConsultasPaciente;

-- Função que devolve todos os tratamentos por realizar de um dado paciente.
CREATE OR REPLACE FUNCTION getTratamentosPacientePorR(
  p_id paciente.id%TYPE
)
RETURN SYS_REFCURSOR
AS apontador SYS_REFCURSOR;
BEGIN
	OPEN apontador FOR SELECT tratamento.*
                       FROM paciente, orcamento, tratamento
                      WHERE p_id = paciente.id
                        AND paciente.id = orcamento.paciente
                        AND orcamento.id = tratamento.orcamento
                        AND tratamento.consulta IS NULL;
	RETURN apontador;
END getTratamentosPacientePorR;

-- Função que devolve todos os tratamentos realizados numa consulta.
CREATE OR REPLACE FUNCTION getTratamentosPacienteConsulta(
  p_id paciente.id%TYPE,
  c_id consulta.id%TYPE
)
RETURN SYS_REFCURSOR
AS apontador SYS_REFCURSOR;
BEGIN
	OPEN apontador FOR SELECT tratamento.*
                   FROM paciente, consulta, tratamento
                  WHERE p_id = paciente.id 
                    AND c_id = consulta.id
                    AND paciente.id = consulta.paciente
                    AND consulta.id = tratamento.consulta;	
	RETURN apontador;
END getTratamentosPacienteConsulta;

-- Procedimento que permite registar um serviço executado por um médico numa dada consulta.
CREATE OR REPLACE PROCEDURE registarTratamento(
	c_id tratamento.consulta%TYPE,
  t_id tratamento.id%TYPE
)
AS
BEGIN
	UPDATE tratamento
     SET tratamento.consulta = c_id
   WHERE tratamento.id = t_id;
END registarTratamento;

-- 3º
-- Função que devolve todas as consultas de um médico num dado mês.
CREATE OR REPLACE FUNCTION getAgendaConsultasMedicoMes(
  medico medico.id%TYPE,
  mes NUMBER
)
RETURN SYS_REFCURSOR
AS apontador SYS_REFCURSOR;
BEGIN
  OPEN apontador FOR SELECT consulta.*
                     FROM medico, consulta, paciente
                     WHERE medico.id = medico
                       AND medico.id = consulta.medico
                       AND EXTRACT(MONTH FROM consulta.data) = mes
                       AND consulta.estado = 0
                       AND consulta.paciente = paciente.id;
  RETURN apontador;
END getAgendaConsultasMedicoMes;

-- 4º
-- Função que devlve uma lista de médicos, os serviços realizados por esses médicos e os materiais utilizados em cada serviço.
CREATE OR REPLACE FUNCTION getListaMedicosServicoMaterial(
  data_inicio consulta.data%TYPE,
  data_fim consulta.data%TYPE
)
RETURN SYS_REFCURSOR
AS apontador SYS_REFCURSOR;
BEGIN
	OPEN apontador FOR SELECT medico.*, servico.id as servico_id, servico.designacao, servico.valor, material.id as material_id, material.designacao as material_designacao
                       FROM medico, consulta, tratamento, servico, lista_material, material
                      WHERE medico.id = consulta.medico
                        AND consulta.id = tratamento.consulta 
                        AND consulta.data >= data_inicio
                        AND consulta.data <= data_fim
                        AND tratamento.servico = servico.id
                        AND servico.id = lista_material.servico
                        AND lista_material.material = material.id
                      ORDER BY medico.nome, servico.id, material.designacao;
	RETURN apontador;
END getListaMedicosServicoMaterial;

-- 5º
-- Sair

-- 6º
-- Procedimento que atualiza os dados de um paciente na base de dados
CREATE OR REPLACE PROCEDURE atualizarPaciente(
  p_id paciente.id%TYPE,
  p_bi paciente.bi%TYPE,
  p_nif paciente.nc%TYPE,
  p_nome_completo paciente.nome_completo%TYPE,
  p_endereco paciente.endereco%TYPE,
  p_cidade paciente.cidade%TYPE,
  p_pais paciente.pais%TYPE,
  p_telefone paciente.telefone%TYPE,
  p_data_nascimento paciente.data_nascimento%TYPE,
  p_medico_por_omissao paciente.medico_por_omissao%TYPE
)
AS
BEGIN
  -- Atualizar o paciente na base de dados.
  UPDATE paciente 
  SET paciente.bi = p_bi,
      paciente.nc = p_nif,
      paciente.nome_completo = p_nome_completo,
      paciente.endereco = p_endereco,
      paciente.cidade = p_cidade,
      paciente.pais = p_pais,
      paciente.telefone = p_telefone,
      paciente.data_nascimento = p_data_nascimento,
      paciente.medico_por_omissao = p_medico_por_omissao
  WHERE paciente.id = p_id;
END atualizarPaciente;
