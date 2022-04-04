delete from fish_compatility;
delete from fish_ph;
delete from fish_dh;
delete from fish;
delete from ph;
delete from dh;

-- PH
INSERT INTO ph (id, name, minph, maxph) VALUES(1, 'Extremamente Acido', 4.0, 4.9);
INSERT INTO ph (id, name, minph, maxph) VALUES(2, 'Muito Acido', 5.0, 6.0);
INSERT INTO ph (id, name, minph, maxph) VALUES(3, 'Acido', 6.2, 6.4);
INSERT INTO ph (id, name, minph, maxph) VALUES(4, 'Ligeiramento Acido ', 6.6, 6.8);
INSERT INTO ph (id, name, minph, maxph) VALUES (5, 'Neutro', 7.0, 7.0);
INSERT INTO ph (id, name, minph, maxph) VALUES(6, 'Alcalino', 7.2, 7.4);
INSERT INTO ph (id, name, minph, maxph) VALUES(7, 'Muito Alcalino', 7.5, 8.0);

-- DH
INSERT INTO dh (id, name, mindh, maxdh) VALUES(1, 'Muito mole', 0, 4);
INSERT INTO dh (id, name, mindh, maxdh) VALUES(2, 'Mole', 5, 8);
INSERT INTO dh (id, name, mindh, maxdh) VALUES(3, 'Media', 9, 12);
INSERT INTO dh (id, name, mindh, maxdh) VALUES(4, 'Dura', 13, 18);
INSERT INTO dh (id, name, mindh, maxdh) VALUES(5, 'Muito dura', 19, 30);


-- Fish
INSERT INTO fish (id, name, size, maxtemperature, mintemperature, minnumber, widthtank, lengthtank, imageurl)
VALUES(1, 'Neon', 3, 28, 26, 10, 60, 30, 'http://www.aquarismopaulista.com/wp-content/uploads/Paracheirodon-axelrodi1.jpg');
INSERT INTO fish_ph (fish_id, ph_id) VALUES(1, 3);
INSERT INTO fish_ph (fish_id, ph_id) VALUES(1, 4);
INSERT INTO fish_dh (fish_id, dh_id) VALUES(1, 1);
INSERT INTO fish_dh (fish_id, dh_id) VALUES(1, 2);

INSERT INTO fish (id, name, size, maxtemperature, mintemperature, minnumber, widthtank, lengthtank, imageurl)
VALUES(2, 'Mato grosso', 3, 28, 26, 6, 60, 30 , 'http://www.aquarismopaulista.com/wp-content/uploads/2015/06/Hyphessobrycon-eques.jpg');
INSERT INTO fish_ph (fish_id, ph_id) VALUES(2, 3);
INSERT INTO fish_ph (fish_id, ph_id) VALUES(2, 4);
INSERT INTO fish_dh (fish_id, dh_id) VALUES(2, 3);

INSERT INTO fish (id, name, size, maxtemperature, mintemperature, minnumber, widthtank, lengthtank, imageurl)
VALUES(3, 'Tetra Negro', 5, 28, 26, 6, 60, 30 , 'http://www.aquarismopaulista.com/wp-content/uploads/2015/06/Gymnocorymbus-ternetzi1.jpg');
INSERT INTO fish_ph (fish_id, ph_id) VALUES(3, 3);
INSERT INTO fish_ph (fish_id, ph_id) VALUES(3, 4);
INSERT INTO fish_dh (fish_id, dh_id) VALUES(3, 3);

INSERT INTO fish (id, name, size, maxtemperature, mintemperature, minnumber, widthtank, lengthtank, imageurl)
VALUES(4, 'Barbus Ouro', 5, 28, 26, 6, 80, 30, 'http://www.aquarismopaulista.com/wp-content/uploads/2014/08/Puntius-semifasciolatus1.jpg');
INSERT INTO fish_ph (fish_id, ph_id) VALUES(4, 3);
INSERT INTO fish_ph (fish_id, ph_id) VALUES(4, 4);
INSERT INTO fish_ph (fish_id, ph_id) VALUES(4, 5);
INSERT INTO fish_dh (fish_id, dh_id) VALUES(4, 3);

INSERT INTO fish (id, name, size, maxtemperature, mintemperature, minnumber, widthtank, lengthtank, imageurl)
VALUES(5, 'Ramirezi', 5, 28, 26, 1, 60, 30 , 'http://www.aquarismopaulista.com/wp-content/uploads/2014/09/Mikrogeophagus-ramirezi1.jpg');
INSERT INTO fish_ph (fish_id, ph_id) VALUES(5, 3);
INSERT INTO fish_ph (fish_id, ph_id) VALUES(5, 4);
INSERT INTO fish_dh (fish_id, dh_id) VALUES(5, 3);

INSERT INTO fish (id, name, size, maxtemperature, mintemperature, minnumber, widthtank, lengthtank, imageurl)
VALUES(6, 'Colisa', 5, 28, 26, 1, 80, 30, 'http://www.aquarismopaulista.com/wp-content/uploads/2014/11/Trichogaster-lalius.jpg');
INSERT INTO fish_ph (fish_id, ph_id) VALUES(6, 3);
INSERT INTO fish_ph (fish_id, ph_id) VALUES(6, 4);
INSERT INTO fish_dh (fish_id, dh_id) VALUES(6, 3);

INSERT INTO fish (id, name, size, maxtemperature, mintemperature, minnumber, widthtank, lengthtank, imageurl)
VALUES(7, 'Tricogaster', 5, 28, 26, 1, 80, 30 , 'http://www.aquarismopaulista.com/wp-content/uploads/Trichogaster-trichopterus.jpg');
INSERT INTO fish_ph (fish_id, ph_id) VALUES(7, 3);
INSERT INTO fish_ph (fish_id, ph_id) VALUES(7, 4);
INSERT INTO fish_dh (fish_id, dh_id) VALUES(7, 3);

INSERT INTO fish (id, name, size, minnumber, mintemperature, maxtemperature, widthtank, lengthtank,initialspace, imageurl)
VALUES(21, 'Kinguio', 30, 1, 10, 28, 80, 40, 90, 'http://www.aquarismopaulista.com/wp-content/uploads/2015/12/Carassius-auratus.jpg');
INSERT INTO fish_ph (fish_id, ph_id) VALUES(21, 5);
INSERT INTO fish_dh (fish_id, dh_id) VALUES(21, 2);
INSERT INTO fish_dh (fish_id, dh_id) VALUES(21, 3);
INSERT INTO fish_dh (fish_id, dh_id) VALUES(21, 4);

INSERT INTO fish (id, name, size, minnumber, mintemperature, maxtemperature, widthtank, lengthtank,imageurl)
VALUES(26, 'Killifish', 5, 1, 18, 22, 30, 20,'http://www.aquarismopaulista.com/wp-content/uploads/2018/04/Aphyosemion-ogoense.jpg');
INSERT INTO fish_ph (fish_id, ph_id) VALUES(26, 3);
INSERT INTO fish_ph (fish_id, ph_id) VALUES(26, 4);
INSERT INTO fish_dh (fish_id, dh_id) VALUES(26, 1);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(26, 3); -- Tetra Negro

-- Compatibility

-- Neon
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(1, 1);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(1, 2);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(1, 3);

-- Mato grosso
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(2, 1);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(2, 2);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(2, 3);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(2, 4);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(2, 5);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(2, 6);

-- Tetra negro
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(3, 1);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(3, 2);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(3, 3);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(3, 4);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(3, 5);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(3, 6);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(3,26);

-- Barbus ouro
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(4, 7);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(4, 2);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(4, 3);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(4, 4);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(4, 5);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(4, 6);


--Ramirezi
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(5, 7);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(5, 2);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(5, 3);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(5, 4);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(5, 5);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(5, 6);


--Coliza
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(6, 7);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(6, 2);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(6, 3);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(6, 4);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(6, 5);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(6, 6);

--Tricogaster
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(7, 7);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(7, 4);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(7, 5);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(7, 6);