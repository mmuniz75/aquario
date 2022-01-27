INSERT INTO ph (id, name, minph, maxph) VALUES(1, 'Acido', 6.2, 6.8);
INSERT INTO ph (id, name, minph, maxph) VALUES (2, 'Neutro', 7.0, 7.0);
INSERT INTO ph (id, name, minph, maxph) VALUES(3, 'Alcalino', 7.2, 7.4);

INSERT INTO dh (id, name, mindh, maxdh) VALUES(1, 'Muito mole', 0, 4);
INSERT INTO dh (id, name, mindh, maxdh) VALUES(2, 'Mole', 5, 8);
INSERT INTO dh (id, name, mindh, maxdh) VALUES(3, 'Media', 9, 12);
INSERT INTO dh (id, name, mindh, maxdh) VALUES(4, 'Dura', 13, 18);
INSERT INTO dh (id, name, mindh, maxdh) VALUES(5, 'Muito dura', 19, 30);

INSERT INTO fish (id, name, size, maxtemperature, mintemperature, minnumber, widthtank, lengthtank)
VALUES(1, 'Betta', 5, 28, 26, 1, 30, 15);
INSERT INTO fish_ph (fish_id, ph_id) VALUES(1, 2);
INSERT INTO fish_dh (fish_id, dh_id) VALUES(1, 2);
INSERT INTO fish_dh (fish_id, dh_id) VALUES(1, 3);
INSERT INTO fish_dh (fish_id, dh_id) VALUES(1, 4);


INSERT INTO fish (id, name, size, maxtemperature, mintemperature, minnumber, widthtank, lengthtank)
VALUES(2, 'Borboleta', 5, 28, 26, 8, 80, 30);

INSERT INTO fish (id, name, size, maxtemperature, mintemperature, minnumber, widthtank, lengthtank)
VALUES(3, 'Acara bandeira', 5, 28, 26, 6, 100, 40);

INSERT INTO fish_ph (fish_id, ph_id) VALUES(3, 1);
INSERT INTO fish_ph (fish_id, ph_id) VALUES(3, 2);

INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(1, 2);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(1, 3);