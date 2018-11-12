CREATE DATABASE IF NOT EXISTS so;
USE so;

-- Tabela de Prioridade
CREATE TABLE Prioridade(
id int not null auto_increment,
nome varchar(255) NULL,
PRIMARY KEY (id))
ENGINE INNODB
DEFAULT CHARSET= UTF8;

INSERT INTO Prioridade VALUES (DEFAULT, 'Baixa');
INSERT INTO Prioridade VALUES (DEFAULT, 'Média');
INSERT INTO Prioridade VALUES (DEFAULT, 'Alta');

-- SELECT * FROM Prioridade;

-- Tabela de Estado
CREATE TABLE Estado(
id int not null auto_increment,
nome varchar(255) NULL,
PRIMARY KEY (id))
ENGINE INNODB
DEFAULT CHARSET= UTF8;

INSERT INTO Estado VALUES (DEFAULT, 'Novo');
INSERT INTO Estado VALUES (DEFAULT, 'Pronto');
INSERT INTO Estado VALUES (DEFAULT, 'Executando');
INSERT INTO Estado VALUES (DEFAULT, 'Bloqueado');
INSERT INTO Estado VALUES (DEFAULT, 'Suspenso');
INSERT INTO Estado VALUES (DEFAULT, 'Finalizado');

-- SELECT * FROM Estado;

-- Tabela de Processo
CREATE TABLE IF NOT EXISTS Processo(
id int not null auto_increment,
nome varchar(255) null,
duracao int null,
duracao_atual int null,
id_prioridade int null,
h_inicio datetime null,
h_fim datetime null,
id_estado int null,
PRIMARY KEY (id),
FOREIGN KEY(id_prioridade) REFERENCES Prioridade(id),
FOREIGN KEY(id_estado) REFERENCES Estado(id))
ENGINE INNODB
DEFAULT CHARSET= UTF8;

-- SELECT * FROM Processo;

-- DROP TABLE Processo;

-- TRUNCATE TABLE Processo;

-- UPDATE Processo SET id_estado=3 WHERE id = 1;

-- ALTER TABLE Processo DROP COLUMN tamanho;

CREATE TABLE IF NOT EXISTS Memoria(
id int not null auto_increment,
max_memory int,
atual_memory int,
ultima_memory int,
PRIMARY KEY (id))
ENGINE INNODB
DEFAULT CHARSET = UTF8;

INSERT INTO Memoria VALUES (DEFAULT, 100, 100, 0);
-- DROP TABLE Memoria;

-- SELECT * FROM Memoria;

-- TRUNCATE TABLE Memoria;
-- UPDATE Memoria SET atual_memory=100 WHERE id=1;

CREATE TABLE IF NOT EXISTS Particao(
id int not null auto_increment,
tamanho int,
disponibilidade bit,
id_processo int,
ordem int,
PRIMARY KEY(id),
FOREIGN KEY (id_processo) REFERENCES Processo(id))
ENGINE INNODB
DEFAULT CHARSET = UTF8;

INSERT INTO Particao VALUES (DEFAULT, 100, 1, NULL, 0);
-- TRUNCATE TABLE Particao;
-- UPDATE Particao SET disponibilidade = 1 WHERE id = 2;

-- SELECT * FROM Particao order by ordem asc, id desc;

-- DROP TABLE Particao;

CREATE TABLE IF NOT EXISTS Escalonador(
id int not null auto_increment,
tamanho int,
ultimaParticao int,
PRIMARY KEY (id))
ENGINE INNODB
DEFAULT CHARSET= UTF8;

-- DROP TABLE Escalonador;

-- TRUNCATE TABLE Escalonador;

-- SELECT * FROM Escalonador;

INSERT INTO Escalonador VALUES (DEFAULT, 100, 0);