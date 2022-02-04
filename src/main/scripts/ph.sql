create table PH (
    id int PRIMARY KEY,
    name VARCHAR(10),
    minPH numeric,
    maxPh numeric
    )
;
INSERT INTO ph (id, name, minph, maxph) VALUES(0, 'Muito Acido', 4.0, 6.0);
INSERT INTO ph (id, name, minph, maxph) VALUES(1, 'Acido', 6.2, 6.8);
INSERT INTO ph (id, name, minph) VALUES (2, 'Neutro', 7.0, 7.0);
INSERT INTO ph (id, name, minph, maxph) VALUES(3, 'Alcalino', 7.2, 7.4);
INSERT INTO ph (id, name, minph, maxph) VALUES(4, "Muito Alcalino', 7.5, 8.0);
