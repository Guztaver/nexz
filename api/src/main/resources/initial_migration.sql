CREATE SEQUENCE rpg_character_id_seq START 1;
CREATE TABLE rpg_character (
    ID INTEGER DEFAULT nextval('rpg_character_id_seq') PRIMARY KEY,
    CHARACTER_NAME VARCHAR(255),
    AGE INTEGER
);