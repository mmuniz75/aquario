create table PH (
    id int PRIMARY KEY,
    name VARCHAR(10),
    minPH numeric,
    maxPh numeric
    )
;
INSERT INTO ph (id, name, minph, maxph) VALUES(1, 'Acido', 6.2, 6.8);
INSERT INTO ph (id, name, minph) VALUES (2, 'Neutro', 7.0, 7.0);
INSERT INTO ph (id, name, minph, maxph) VALUES(3, 'Alcalino', 7.2, 7.4);
