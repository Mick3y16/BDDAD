INSERT INTO cidade VALUES (0,'Porto');
INSERT INTO cidade VALUES (1,'Lisboa');
INSERT INTO cidade VALUES (2,'Barcelona');

INSERT INTO pais VALUES (0,'Portugal');
INSERT INTO pais VALUES (1,'Espanha');

INSERT INTO sistema_saude VALUES (0,'SNS');
INSERT INTO sistema_saude VALUES (1,'ADM');
INSERT INTO sistema_saude VALUES (2,'ADSE');

INSERT INTO medico VALUES (0, 'Dra. Renata Conceição', 50, 10);
INSERT INTO medico VALUES (1, 'Dr. Armando Freitas', 60, 10);
INSERT INTO medico VALUES (2, 'Dr. Pinto da Costa',70, 10);
INSERT INTO medico VALUES (3, 'Dra. Cêcê Magalhães', 70, 10);
INSERT INTO medico VALUES (4, 'Dr. Diogo Braga', 70, 10); 

INSERT INTO clinica VALUES (0, 60, 8, 0.25, 60);

INSERT INTO paciente VALUES (0, '111111110AA1', 111111111, 'Ana Maria', 'Rua Principal', 0, 0, '99-9999999', '1980-01-02', 0);
INSERT INTO paciente VALUES (1, '111111110BB1', 222222222, 'Joao Carlos', 'Rua Qualquer', 2, 1, '99-9999988', '1975-10-25', 1); 
INSERT INTO paciente VALUES (2, '111111110CC1', 333333333, 'Manuel Fonseca', 'Rua Qualquer 2', 1, 0, '99-9999977', '1965-12-06', 2); 
INSERT INTO paciente VALUES (3, '111111110DD1', 225522222, 'Maria Madalena', 'Rua Qualquer3', 2, 1, '99-9999966', '1975-10-25', 3); 
INSERT INTO paciente VALUES (4, '111111110EE1', 333553333, 'Joana Maria', 'Rua Qualquer 4', 1, 0, '99-9999955', '1965-12-06', 4); 

INSERT INTO consulta VALUES (0, 0, 0, '2015-12-12', 15.00, 16.00, 0); 
INSERT INTO consulta VALUES (1, 1, 1, '2016-02-07', 14.00, 15.00, 0); 
INSERT INTO consulta VALUES (2, 2, 2, '2016-10-03', 17.00, 18.00, 0); 
INSERT INTO consulta VALUES (3, 3, 3, '2015-11-30', 16.00, 17.00, 0);
INSERT INTO consulta VALUES (4, 3, 2, '2015-12-02', 15.00, 16.00, 0);
INSERT INTO consulta VALUES (5, 4, 4, '2015-12-10', 10.00, 11.00, 0);

INSERT INTO tipo_pagamento VALUES (0, 'a pronto');
INSERT INTO tipo_pagamento VALUES (1, '30 dias');
INSERT INTO tipo_pagamento VALUES (2, '60 dias');

INSERT INTO servico VALUES (0, 'Serviço 1', 50);
INSERT INTO servico VALUES (1, 'Serviço 2', 60);
INSERT INTO servico VALUES (2, 'Serviço 3', 70);
INSERT INTO servico VALUES (3, 'Serviço 4', 60);
INSERT INTO servico VALUES (4, 'Serviço 5', 35);
INSERT INTO servico VALUES (5, 'Serviço 6', 40);
INSERT INTO servico VALUES (6, 'Serviço 7', 67);
INSERT INTO servico VALUES (7, 'Serviço 8', 46);
INSERT INTO servico VALUES (8, 'Serviço 9', 55);

INSERT INTO material VALUES (0, 'Gaze');
INSERT INTO material VALUES (1, 'Luvas');
INSERT INTO material VALUES (2, 'Pasta de Moldar');
INSERT INTO material VALUES (3, 'Anestesia');

INSERT INTO lista_material VALUES (0, 0);
INSERT INTO lista_material VALUES (0, 1);
INSERT INTO lista_material VALUES (0, 2);
INSERT INTO lista_material VALUES (0, 3);
INSERT INTO lista_material VALUES (1, 0);
INSERT INTO lista_material VALUES (1, 1);
INSERT INTO lista_material VALUES (1, 2);
INSERT INTO lista_material VALUES (1, 3);
INSERT INTO lista_material VALUES (2, 2);
INSERT INTO lista_material VALUES (2, 3);
INSERT INTO lista_material VALUES (3, 2);
INSERT INTO lista_material VALUES (3, 3);
INSERT INTO lista_material VALUES (4, 2);
INSERT INTO lista_material VALUES (4, 3);
INSERT INTO lista_material VALUES (5, 0);
INSERT INTO lista_material VALUES (5, 1);
INSERT INTO lista_material VALUES (5, 2);
INSERT INTO lista_material VALUES (6, 0);
INSERT INTO lista_material VALUES (6, 1);
INSERT INTO lista_material VALUES (6, 2);
INSERT INTO lista_material VALUES (7, 0);
INSERT INTO lista_material VALUES (7, 1);
INSERT INTO lista_material VALUES (7, 2);
INSERT INTO lista_material VALUES (8, 0);
INSERT INTO lista_material VALUES (8, 1);
INSERT INTO lista_material VALUES (8, 2); 

INSERT INTO funcionario VALUES (0, 'Funcionario 1');
INSERT INTO funcionario VALUES (1, 'Funcionario 2');
INSERT INTO funcionario VALUES (2, 'Funcionario 3');

INSERT INTO tarefa VALUES (0, 'Limpar gabinete');
INSERT INTO tarefa VALUES (1, 'Preparar gabinete');
INSERT INTO tarefa VALUES (2, 'Verificar gabinete');

INSERT INTO prioridade_gabinete VALUES (0, 'Mínima');
INSERT INTO prioridade_gabinete VALUES (1, 'Abaixo da Média');
INSERT INTO prioridade_gabinete VALUES (2, 'Média');
INSERT INTO prioridade_gabinete VALUES (3, 'Acima da Média');
INSERT INTO prioridade_gabinete VALUES (4, 'Máxima');

INSERT INTO equipamento VALUES (0, 'Aspirador');
INSERT INTO equipamento VALUES (1, 'Broca');
INSERT INTO equipamento VALUES (2, 'Equipamento1'); 
INSERT INTO equipamento VALUES (3, 'Equipamento2'); 
INSERT INTO equipamento VALUES (4, 'Equipamento3'); 

INSERT INTO equipamento_substituicao VALUES (0, 1); 
INSERT INTO equipamento_substituicao VALUES (1, 1); 
INSERT INTO equipamento_substituicao VALUES (2, 1); 
INSERT INTO equipamento_substituicao VALUES (3, 1); 
INSERT INTO equipamento_substituicao VALUES (4, 1); 

INSERT INTO estado_reserva VALUES (0, 'Ativa');
INSERT INTO estado_reserva VALUES (1, 'Satisfeita');
INSERT INTO estado_reserva VALUES (2, 'Cancelada');
INSERT INTO estado_reserva VALUES (3, 'Em Espera');
INSERT INTO estado_reserva VALUES (4, 'Esquecida');

INSERT INTO orcamento VALUES (0, 0, 0, 2, 70); 
INSERT INTO orcamento VALUES (1, 1, 1, 1, 60); 
INSERT INTO orcamento VALUES (2, 2, 2, 2, 70); 
INSERT INTO orcamento VALUES (3, 2, 2, 0, 60); 
INSERT INTO orcamento VALUES (4, 3, 3, 0, 70); 
INSERT INTO orcamento VALUES (5, 2, 3, 0, 70); 
INSERT INTO orcamento VALUES (6, 4, 4, 0, 70); 

INSERT INTO gabinete VALUES (0, 15, 0, 0, 0, 4, 0); 
INSERT INTO gabinete VALUES (1, 15, 2, 2, 1, 2, 1); 
INSERT INTO gabinete VALUES (2, 15, 1, 0, 0, 2, 0); 

INSERT INTO medico_horario VALUES (0, 0, '2015-12-12', 10.00, 16.00, 0); 
INSERT INTO medico_horario VALUES (1, 1, '2016-02-07', 9.00, 15.00, 1); 
INSERT INTO medico_horario VALUES (2, 2, '2016-10-03', 14.00, 18.00, 0); 
INSERT INTO medico_horario VALUES (3, 2, '2015-11-30', 16.00, 17.00, 2); 
INSERT INTO medico_horario VALUES (4, 2, '2015-12-02', 15.00, 16.00, 1);
INSERT INTO medico_horario VALUES (5, 4, '2015-12-10', 10.00, 11.00, 0); 

INSERT INTO equipamento_gabinete VALUES (0, 0);
INSERT INTO equipamento_gabinete VALUES (0, 1);
INSERT INTO equipamento_gabinete VALUES (0, 2);  
INSERT INTO equipamento_gabinete VALUES (1, 0);
INSERT INTO equipamento_gabinete VALUES (1, 1);
INSERT INTO equipamento_gabinete VALUES (1, 2); 
INSERT INTO equipamento_gabinete VALUES (2, 0);
INSERT INTO equipamento_gabinete VALUES (2, 1);
INSERT INTO equipamento_gabinete VALUES (2, 2);
INSERT INTO equipamento_gabinete VALUES (3, 0);
INSERT INTO equipamento_gabinete VALUES (3, 1);
INSERT INTO equipamento_gabinete VALUES (3, 2);
INSERT INTO equipamento_gabinete VALUES (4, 0);
INSERT INTO equipamento_gabinete VALUES (4, 1);
INSERT INTO equipamento_gabinete VALUES (4, 2);

INSERT INTO nota_reparacao VALUES (0, 0);
INSERT INTO nota_reparacao VALUES (1, 1);

INSERT INTO equipamento_avariado VALUES (0, 1, 0); 
INSERT INTO equipamento_avariado VALUES (1, 2, 0); 

INSERT INTO reserva VALUES (0, 0, '2015-12-12', 10.00, 16.00, 0); 
INSERT INTO reserva VALUES (1, 0, '2016-10-03', 14.00, 18.00, 0); 

INSERT INTO reserva_equipamentos VALUES (0, 1);
INSERT INTO reserva_equipamentos VALUES (1, 2);

INSERT INTO tratamento VALUES (0, 0, 0, 0);
INSERT INTO tratamento VALUES (1, 1, 0, 0);
INSERT INTO tratamento VALUES (2, 2, 1, 1); 
INSERT INTO tratamento VALUES (3, 2, 1, 1); 
INSERT INTO tratamento VALUES (4, 3, 1, 2); 
INSERT INTO tratamento VALUES (5, 4, 2, 3); 
INSERT INTO tratamento VALUES (6, 5, 3, 4); 
INSERT INTO tratamento VALUES (7, 5, 3, 4); 
INSERT INTO tratamento VALUES (8, 5, 3, 5);
INSERT INTO tratamento VALUES (9, null,  0, 0);
INSERT INTO tratamento VALUES(10, null, 0, 1);

INSERT INTO parcela_pagamento VALUES (0, 0, 0, '2015-11-10', '2016-01-10', 10, 1111111111, 1);
INSERT INTO parcela_pagamento VALUES (1, 0, 0, '2015-11-10', '2016-01-11', 10, 1111111111, 1);
INSERT INTO parcela_pagamento VALUES (2, 0, 0, '2015-11-10', '2016-01-20', 50, 1111111111, 1);
