-- Crie a sequência
CREATE SEQUENCE character_id_seq START 1;

-- Crie a tabela 'characters' com uma coluna 'ID' que usa a sequência criada
CREATE TABLE characters (
    ID INTEGER DEFAULT nextval('character_id_seq') PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    AGE INTEGER,
    bornAt VARCHAR(255) NOT NULL
);
