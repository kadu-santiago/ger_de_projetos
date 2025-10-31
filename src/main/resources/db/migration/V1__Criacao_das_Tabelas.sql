CREATE TABLE projeto
(
    id           INT NOT NULL AUTO_INCREMENT,
    nome         VARCHAR(100),
    descricao    VARCHAR(200),
    data_criacao TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE tarefa
(
    id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    descricao   VARCHAR(200),
    data_limite TIMESTAMP,
    status      VARCHAR(20),
    id_projeto INT,
    FOREIGN KEY (id_projeto) REFERENCES projeto(id)
);