create table PH (
    id int PRIMARY KEY,
    name VARCHAR(20),
    minPH numeric,
    maxPh numeric
    )
;
INSERT INTO ph (id, name, minph, maxph) VALUES(1, 'Extremamente Acido', 4.0, 4.9);
INSERT INTO ph (id, name, minph, maxph) VALUES(2, 'Muito Acido', 5.0, 6.0);
INSERT INTO ph (id, name, minph, maxph) VALUES(3, 'Acido', 6.2, 6.4);
INSERT INTO ph (id, name, minph, maxph) VALUES(4, 'Ligeiramento Acido ', 6.6, 6.8);
INSERT INTO ph (id, name, minph, maxph) VALUES (5, 'Neutro', 7.0, 7.0);
INSERT INTO ph (id, name, minph, maxph) VALUES(6, 'Alcalino', 7.2, 7.4);
INSERT INTO ph (id, name, minph, maxph) VALUES(7, 'Muito Alcalino', 7.5, 8.0);
