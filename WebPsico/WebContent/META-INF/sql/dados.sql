INSERT INTO CONTATO (ID,CPF,DATANASCIMENTO,DESATIVADO,EMAIL,ESCOLARIDADE,ESTADOCIVIL,NACIONALIDADE,NATURALIDADE,NOME,OBS,PARENTESCO,PROFISSAO,RELIGIAO,SEXO,ENDERECO_IDENDERECO) VALUES (1,'11111111',{d '1945-09-21'},0,'venina@gmail.com','MEDIO_COMPLETO','CASADO','Brasileiro','RJ','Venina Val Porto','Ligar na parte da tarde.','Mãe','Do lar','Espírita','FEMININO',1);
INSERT INTO CONTATO (ID,CPF,DATANASCIMENTO,DESATIVADO,EMAIL,ESCOLARIDADE,ESTADOCIVIL,NACIONALIDADE,NATURALIDADE,NOME,OBS,PARENTESCO,PROFISSAO,RELIGIAO,SEXO,ENDERECO_IDENDERECO) VALUES (2,'22222222222',{d '1952-04-16'},0,'luiz@gmail.com','MEDIO_COMPLETO','VIUVO','Brasileiro','RJ','Luiz Augusto Andrade de Moraes',null,'Pai','Aposentado','Católico','MASCULINO',2);
INSERT INTO CONTATO_TELEFONE (ID_CONTATO,ID_TELEFONE) VALUES (1,1);
INSERT INTO CONTATO_TELEFONE (ID_CONTATO,ID_TELEFONE) VALUES (2,2);
INSERT INTO ENDERECO (IDENDERECO,BAIRRO,CEP,CIDADE,COMPLEMENTO,LOGRADOURO,NUMERO,UF) VALUES (1,'JPA','22000-000','Rio de Janeiro','Jardim Clarice','Estrada Curipós','521','RJ');
INSERT INTO ENDERECO (IDENDERECO,BAIRRO,CEP,CIDADE,COMPLEMENTO,LOGRADOURO,NUMERO,UF) VALUES (2,'Ilha do Governador','21920-321','Rio de Janeiro','Jardim Guanabara','Rua Primeiros Sonhos','123','RJ');
INSERT INTO ENDERECO (IDENDERECO,BAIRRO,CEP,CIDADE,COMPLEMENTO,LOGRADOURO,NUMERO,UF) VALUES (3,'Ilha do Governador','21920-000','Rio de Janeiro','Tauá','Estrada do Dendê','542','RJ');
INSERT INTO INDICACAO (IDINDICACAO,NOME,PROFISSAO) VALUES (1,'Regina Lucy','Médica');
INSERT INTO PACIENTE (ID,CPF,DATAINICIO,DATANASCIMENTO,DATAUTIMASESSAO,DESATIVADO,EMAIL,ESCOLARIDADE,ESTADOCIVIL,FREQUENCIA,NACIONALIDADE,NATURALIDADE,NOME,OBS,PRECO,PREFERENCIATURNO,PROFISSAO,SEXO,INDICACAO_IDINDICACAO,ENDERECO_IDENDERECO) VALUES (1,'123456789',{d '2008-02-01'},{d '2005-06-11'},{ts '2015-06-11 00:00:00.'},0,'lucas@gmail.com','FUNDAMENTAL_INCOMPLETO','SOLTEIRO','15 em 15 dias','Brasileiro','RJ','Lucas Val Porto',null,320.0,'TARDE','Estudante','MASCULINO',1,3);
INSERT INTO PACIENTE_TELEFONE (ID_PACIENTE,ID_TELEFONE) VALUES (1,3);
INSERT INTO TELEFONE (IDTELEFONE,DDD,TELEFONE,TIPOTELEFONE) VALUES (1,'21','24477654','RESIDENCIAL');
INSERT INTO TELEFONE (IDTELEFONE,DDD,TELEFONE,TIPOTELEFONE) VALUES (2,'21','988972975','CELULAR');
INSERT INTO TELEFONE (IDTELEFONE,DDD,TELEFONE,TIPOTELEFONE) VALUES (3,'21','33932975','RESIDENCIAL');
INSERT INTO USUARIO (ID,DESATIVADO,EMAIL,LOGIN,NOMECOMPLETO,PERFIL,SENHA) VALUES (1,0,'hexemeister@gmail.com','Renato','Renato Moraes','ADMINISTRADOR','123');
INSERT INTO USUARIO (ID,DESATIVADO,EMAIL,LOGIN,NOMECOMPLETO,PERFIL,SENHA) VALUES (2,0,'rosevalporto@gmail.com','Viviane','Viviane Rose Val Porto','PSICOLOGA','123');
INSERT INTO USUARIO (ID,DESATIVADO,EMAIL,LOGIN,NOMECOMPLETO,PERFIL,SENHA) VALUES (3,0,'brunofitzner1973@gmail.com','Bruno','Bruno Fitzner','ATENDENTE','123');
INSERT INTO USUARIO (ID,DESATIVADO,EMAIL,LOGIN,NOMECOMPLETO,PERFIL,SENHA) VALUES (4,1,'pablo@gmail.com','Pablo','Pablo Rangel','ATENDENTE','123');