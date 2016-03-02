DROP TABLE reserva_equipamentos CASCADE CONSTRAINTS;
DROP TABLE reserva CASCADE CONSTRAINTS;
DROP TABLE estado_reserva CASCADE CONSTRAINTS;
DROP TABLE equipamento_substituicao CASCADE CONSTRAINTS;
DROP TABLE equipamento_avariado CASCADE CONSTRAINTS;
DROP TABLE equipamento_gabinete CASCADE CONSTRAINTS;
DROP TABLE equipamento CASCADE CONSTRAINTS;
DROP TABLE nota_reparacao CASCADE CONSTRAINTS;
DROP TABLE material_gabinete CASCADE CONSTRAINTS;
DROP TABLE parcela_pagamento CASCADE CONSTRAINTS;
DROP TABLE lista_material CASCADE CONSTRAINTS;
DROP TABLE material CASCADE CONSTRAINTS;
DROP TABLE tratamento CASCADE CONSTRAINTS;
DROP TABLE orcamento CASCADE CONSTRAINTS;
DROP TABLE tipo_pagamento CASCADE CONSTRAINTS;
DROP TABLE servico CASCADE CONSTRAINTS;
DROP TABLE medico_horario CASCADE CONSTRAINTS;
DROP TABLE funcionarios_gabinete CASCADE CONSTRAINTS;
DROP TABLE gabinete CASCADE CONSTRAINTS;
DROP TABLE prioridade_gabinete CASCADE CONSTRAINTS;
DROP TABLE agenda CASCADE CONSTRAINTS;
DROP TABLE tarefa CASCADE CONSTRAINTS;
DROP TABLE funcionario CASCADE CONSTRAINTS;
DROP TABLE consulta CASCADE CONSTRAINTS;
DROP TABLE clinica CASCADE CONSTRAINTS;
DROP TABLE paciente_sistemas_saude CASCADE CONSTRAINTS;
DROP TABLE paciente CASCADE CONSTRAINTS;
DROP TABLE sistema_saude CASCADE CONSTRAINTS;
DROP TABLE cidade CASCADE CONSTRAINTS;
DROP TABLE pais CASCADE CONSTRAINTS;
DROP TABLE medico CASCADE CONSTRAINTS;

CREATE TABLE medico (
	id             integer, 
	nome		   varchar(64) NOT NULL,
	preco_consulta number(5,2) NOT NULL, 
	cota_clinica   number(5,2) NOT NULL, 
	CONSTRAINT PK_medico PRIMARY KEY (id),
	CONSTRAINT CK_medico_preco_consulta CHECK (preco_consulta > 0),
	CONSTRAINT CK_medico_cota_clinica CHECK (cota_clinica > 0));

CREATE TABLE pais (
	id   integer, 
	nome varchar2(32) NOT NULL UNIQUE, 
	CONSTRAINT PK_pais PRIMARY KEY (id));

CREATE TABLE cidade (
	id   integer, 
	nome varchar2(32) NOT NULL UNIQUE, 
	CONSTRAINT PK_cidade PRIMARY KEY (id));

CREATE TABLE sistema_saude (
	id         integer, 
	designacao varchar2(16) NOT NULL UNIQUE, 
	CONSTRAINT PK_sistema_saude PRIMARY KEY (id));

CREATE TABLE paciente (
	id                 integer,
	bi                 varchar2(13) NOT NULL UNIQUE, 
	nc                 number(9) NOT NULL UNIQUE, 
	nome_completo      varchar2(64) NOT NULL, 
	endereco           varchar2(64) NOT NULL, 
	cidade             integer NOT NULL, 
	pais               integer NOT NULL, 
	telefone           char(10) NOT NULL, 
	data_nascimento    date NOT NULL, 
	medico_por_omissao integer NOT NULL, 
	CONSTRAINT PK_paciente PRIMARY KEY (id),
	CONSTRAINT FK_paciente_cidade FOREIGN KEY (cidade) REFERENCES cidade (id),
	CONSTRAINT FK_paciente_pais FOREIGN KEY (pais) REFERENCES pais (id),
	CONSTRAINT FK_paciente_medico FOREIGN KEY (medico_por_omissao) REFERENCES medico (id),
	CONSTRAINT CK_paciente_bi CHECK (REGEXP_LIKE(bi,'[0-9]{9}[A-Za-z]{2}[0-9]')),
	CONSTRAINT CK_paciente_nc CHECK (REGEXP_LIKE(nc,'[0-9]{9}')),
	CONSTRAINT CK_paciente_telefone CHECK (REGEXP_LIKE(telefone,'[9|2][0-9]-[0-9]{7}')));

CREATE TABLE paciente_sistemas_saude (
	paciente      integer, 
	sistema_saude integer, 
	CONSTRAINT PK_paciente_sistemas_saude PRIMARY KEY (paciente, sistema_saude),
	CONSTRAINT FK_paciente_sistemas_saude_p FOREIGN KEY (paciente) REFERENCES paciente (id),
	CONSTRAINT FK_paciente_sistemas_saude_s FOREIGN KEY (sistema_saude) REFERENCES sistema_saude (id));

CREATE TABLE clinica (
	diretor_clinica         integer, 
	duracao_consulta        number(2) NOT NULL, 
	max_consultas_medico    number(2) NOT NULL, 
	taxa_juro_incumprimento number(5,2) NOT NULL, 
	duracao_reserva         number(2) NOT NULL,
	CONSTRAINT FK_medico FOREIGN KEY (diretor_clinica) REFERENCES medico (id),
	CONSTRAINT CK_clinica_consulta CHECK (duracao_consulta > 0),
	CONSTRAINT CK_clinica_max_consultas CHECK (max_consultas_medico > 0),
	CONSTRAINT CK_clinica_taxa_juro CHECK (taxa_juro_incumprimento  between 0 and 100),
	CONSTRAINT CK_clinica_duracao CHECK (duracao_reserva > 0));

CREATE TABLE consulta (
	id          integer, 
	paciente  integer NOT NULL, 
	medico    integer NOT NULL, 
	data        date NOT NULL, 
	hora_inicio number(4,2) NOT NULL, 
	hora_fim    number(4,2) NOT NULL, 
	estado      number(1) NOT NULL, 
	CONSTRAINT PK_consulta PRIMARY KEY (id),
	CONSTRAINT FK_consulta_paciente FOREIGN KEY (paciente) REFERENCES paciente (id),
	CONSTRAINT FK_consulta_medico FOREIGN KEY (medico) REFERENCES medico (id),
	CONSTRAINT CK_consulta_hora_inicio CHECK (hora_inicio between 8 and 20),
	CONSTRAINT CK_consulta_hora_fim CHECK (hora_inicio between 9 and 21),
	CONSTRAINT CK_consulta_estado CHECK (estado between 0 and 1));

CREATE TABLE funcionario (
	id   integer, 
	nome varchar(32) NOT NULL,
	CONSTRAINT PK_funcionario PRIMARY KEY (id));

CREATE TABLE tarefa (
	id         integer, 
	designacao varchar2(32) NOT NULL UNIQUE, 
	CONSTRAINT PK_tarefa PRIMARY KEY (id));

CREATE TABLE agenda (
	funcionario integer, 
	tarefa      integer, 
	CONSTRAINT PK_agenda PRIMARY KEY (funcionario, tarefa),
	CONSTRAINT FK_agenda_funcionario FOREIGN KEY (funcionario) REFERENCES funcionario (id),
	CONSTRAINT FK_agenda_tarefa FOREIGN KEY (tarefa) REFERENCES tarefa (id));

CREATE TABLE prioridade_gabinete (
	id		   integer, 
	designacao varchar2(16) NOT NULL UNIQUE, 
	CONSTRAINT PK_prioridade_gabinete PRIMARY KEY (id));

CREATE TABLE gabinete (
	id                     integer, 
	dimensao               number(5) NOT NULL, 
	alteracoes_prioridade  number(10) NOT NULL,
	faltas_reservas        number(1) NOT NULL, 
	levantamentos_reservas number(1) NOT NULL, 
	prioridade             integer NOT NULL, 
	responsavel            integer NOT NULL, 
	CONSTRAINT PK_gabinete PRIMARY KEY (id),
	CONSTRAINT FK_gabinete_funcionario FOREIGN KEY (responsavel) REFERENCES funcionario (id),
	CONSTRAINT FK_gabinete_prioridade_gab FOREIGN KEY (prioridade) REFERENCES prioridade_gabinete (id),
	CONSTRAINT CK_gabinete_dimensao CHECK (dimensao > 0),
	CONSTRAINT CK_gabinete_faltas CHECK (faltas_reservas between 0 and 5),
	CONSTRAINT CK_gabinete_reservas CHECK (levantamentos_reservas between 0 and 2));

CREATE TABLE funcionarios_gabinete (
	funcionario integer, 
	gabinete    integer, 
	CONSTRAINT PK_funcionarios_gabinete PRIMARY KEY (funcionario, gabinete),
	CONSTRAINT FK_funcionarios_gabinete_func FOREIGN KEY (funcionario) REFERENCES funcionario (id),
	CONSTRAINT FK_funcionarios_gabinete_gab FOREIGN KEY (gabinete) REFERENCES gabinete (id));

CREATE TABLE medico_horario (
	id          integer, 
	medico      integer NOT NULL, 
	data        date NOT NULL, 
	hora_inicio number(4,2) NOT NULL, 
	hora_fim    number(4,2) NOT NULL, 
	gabinete    integer NOT NULL, 
	CONSTRAINT PK_medico_horario PRIMARY KEY (id),
	CONSTRAINT FK_medico_horario_medico FOREIGN KEY (medico) REFERENCES medico (id),
	CONSTRAINT FK_medico_horario_gabinete FOREIGN KEY (gabinete) REFERENCES gabinete (id),
	CONSTRAINT CK_medico_horario_h_inicio CHECK (hora_inicio between 8 and 20),
	CONSTRAINT CK_medico_horario_h_fim CHECK (hora_inicio between 9 and 21));

CREATE TABLE tipo_pagamento (
	id         integer, 
	designacao varchar2(16) NOT NULL UNIQUE, 
	CONSTRAINT PK_tipo_pagamento PRIMARY KEY (id));

CREATE TABLE servico (
	id         integer, 
	designacao varchar2(32) NOT NULL UNIQUE, 
	valor      number(10,2) NOT NULL, 
	CONSTRAINT PK_servico PRIMARY KEY (id),
	CONSTRAINT CK_servico_valor CHECK (valor > 0));

CREATE TABLE orcamento (
	id               integer, 
	medico           integer NOT NULL, 
	paciente         integer NOT NULL, 
	tipo_pagamentoid integer NOT NULL, 
	valor_total      number(10,2) NOT NULL, 
	CONSTRAINT PK_orcamento PRIMARY KEY (id),
	CONSTRAINT FK_orcamento_medico FOREIGN KEY (medico) REFERENCES medico (id),
	CONSTRAINT FK_orcamento_paciente FOREIGN KEY (paciente) REFERENCES paciente (id),
	CONSTRAINT FK_orcamento_tipo_pagamento FOREIGN KEY (tipo_pagamentoid) REFERENCES tipo_pagamento (id),
	CONSTRAINT CK_orcamento_valor_total CHECK (valor_total > 0));

CREATE TABLE tratamento (
	id            integer, 
	consulta	  integer,
	orcamento     integer NOT NULL, 
	servico       integer NOT NULL, 
	CONSTRAINT PK_tratamento PRIMARY KEY (id),
	CONSTRAINT FK_tratamento_consulta FOREIGN KEY (consulta) REFERENCES consulta (id),
	CONSTRAINT FK_tratamento_orcamento FOREIGN KEY (orcamento) REFERENCES orcamento (id),
	CONSTRAINT FK_tratamento_servico FOREIGN KEY (servico) REFERENCES servico (id));

CREATE TABLE material (
	id         integer, 
	designacao varchar2(16) NOT NULL UNIQUE, 
	CONSTRAINT PK_material PRIMARY KEY (id));

CREATE TABLE lista_material (
	servico	   integer, 
	material   integer, 
	CONSTRAINT PK_lista_material PRIMARY KEY (servico, material),
	CONSTRAINT FK_lista_material_tratamento FOREIGN KEY (servico) REFERENCES servico (id),
	CONSTRAINT FK_lista_material_material FOREIGN KEY (material) REFERENCES material (id));

CREATE TABLE parcela_pagamento (
	id              integer, 
	tratamentoid    integer NOT NULL, 
	orcamentoid     integer NOT NULL, 
	data_vencimento date NOT NULL, 
	data_pagamento	date NOT NULL, 
	valor           number(10,2) NOT NULL, 
	numero_cheque   number(10) NOT NULL, 
	pago            number(1) NOT NULL,
	CONSTRAINT PK_parcela_pagamaneto PRIMARY KEY (id),
	CONSTRAINT FK_parcela_pagamento_trat FOREIGN KEY (tratamentoid) REFERENCES tratamento (id),
	CONSTRAINT FK_parcela_pagamento_orc FOREIGN KEY (orcamentoid) REFERENCES orcamento (id),
	CONSTRAINT CK_parcela_pagamento_val CHECK (valor > 0),
	CONSTRAINT CK_parcela_pagamento_num CHECK (REGEXP_LIKE(numero_cheque,'[0-9]{10}')),
	CONSTRAINT CK_parcela_pagamento_est CHECK (pago between 0 and 1));

CREATE TABLE material_gabinete (
	gabineteid integer, 
	materialid integer, 
	quantidade number(10) NOT NULL, 
	CONSTRAINT PK_material_gabinete PRIMARY KEY (gabineteid, materialid),
	CONSTRAINT FK_material_gabinete_gabinete FOREIGN KEY (gabineteid) REFERENCES gabinete (id),
	CONSTRAINT FK_material_gabinete_material FOREIGN KEY (materialid) REFERENCES material (id),
	CONSTRAINT CK_material_gabinete_quant CHECK (quantidade > -1));

CREATE TABLE nota_reparacao (
	id            integer, 
	funcionarioid integer NOT NULL, 
	CONSTRAINT PK_nota_reparacao PRIMARY KEY (id),
	CONSTRAINT FK_nota_reparacao_funcionario FOREIGN KEY (funcionarioid) REFERENCES funcionario (id));

CREATE TABLE equipamento (
	id         integer, 
	designacao varchar2(16) NOT NULL UNIQUE, 
	CONSTRAINT PK_equipamento PRIMARY KEY (id));

CREATE TABLE equipamento_gabinete (
	equipamentoid integer, 
	gabineteid    integer, 
	CONSTRAINT PK_equipamento_gabinete PRIMARY KEY (equipamentoid, gabineteid),
	CONSTRAINT FK_equipamento_gabinete_equip FOREIGN KEY (equipamentoid) REFERENCES equipamento (id),
	CONSTRAINT FK_equipamento_gabinete_gab FOREIGN KEY (gabineteid) REFERENCES gabinete (id));

CREATE TABLE equipamento_avariado (
	nota_reparacaoid integer, 
	equipamentoid    integer, 
	gabineteid       integer, 
	CONSTRAINT PK_equipamento_avariado PRIMARY KEY (nota_reparacaoid, equipamentoid, gabineteid),
	CONSTRAINT FK_equipamento_avariado_nota FOREIGN KEY (nota_reparacaoid) REFERENCES nota_reparacao (id),
	CONSTRAINT FK_equipamento_avariado_equip FOREIGN KEY (equipamentoid, gabineteid) REFERENCES equipamento_gabinete (equipamentoid, gabineteid));

CREATE TABLE equipamento_substituicao (
	equipamentoid integer,
	disponivel 	  number(1),
	CONSTRAINT PK_equipamento_sbustituicao PRIMARY KEY (equipamentoid),
	CONSTRAINT FK_equipamento_substituicao_eq FOREIGN KEY (equipamentoid) REFERENCES equipamento (id));

CREATE TABLE estado_reserva (
	id			integer, 
	designacao	varchar2(16) NOT NULL UNIQUE, 
	CONSTRAINT PK_estado_reserva PRIMARY KEY (id));

CREATE TABLE reserva (
	id          integer, 
	gabinete    integer NOT NULL, 
	data        date NOT NULL, 
	hora_inicio number(4,2) NOT NULL, 
	hora_fim    number(4,2) NOT NULL, 
	estado      number(1) NOT NULL, 
	CONSTRAINT PK_reserva PRIMARY KEY (id),
	CONSTRAINT FK_reserva_gabinete FOREIGN KEY (gabinete) REFERENCES gabinete (id),
	CONSTRAINT FK_reserva_estado_reserva FOREIGN KEY (estado) REFERENCES estado_reserva (id),
	CONSTRAINT CK_reserva_hora_inicio CHECK (hora_inicio between 8 and 20),
	CONSTRAINT CK_reserva_hora_fim CHECK (hora_inicio between 9 and 21));

CREATE TABLE reserva_equipamentos (
	reservaid                integer, 
	equipamento_substituicao integer, 
	PRIMARY KEY (reservaid, equipamento_substituicao),
	CONSTRAINT FK_reserva FOREIGN KEY (reservaid) REFERENCES reserva (id),
	CONSTRAINT FK_reserva_equipamentos_equip FOREIGN KEY (equipamento_substituicao) REFERENCES equipamento_substituicao (equipamentoid));
