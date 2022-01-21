create Table Fish (
   id int PRIMARY KEY,
   name VARCHAR(50),
   size int,
   maxTemperature int,
   minTemperature int,
   minNumber int,
   widthTank int,
   lengthTank int
)
;

create table fish_ph (
   fish_id int,
   ph_id int,
   PRIMARY KEY (fish_id,ph_id),
   FOREIGN KEY (fish_id) REFERENCES fish(id),
   FOREIGN KEY (ph_id) REFERENCES ph(id)
)
;

create table fish_dh (
   fish_id int,
   dh_id int,
   PRIMARY KEY (fish_id,dh_id),
   FOREIGN KEY (fish_id) REFERENCES fish(id),
   FOREIGN KEY (dh_id) REFERENCES dh(id)
)
;
create table fish_compatility (
   fish_id int,
   compatible_fish_id int,
   PRIMARY KEY (fish_id,compatible_fish_id),
   FOREIGN KEY (fish_id) REFERENCES fish(id),
   FOREIGN KEY (compatible_fish_id) REFERENCES fish(id)
)
;

INSERT INTO fish (id, "name", "size", maxtemperature, mintemperature, minnumber, widthtank, lengthtank)
VALUES(1, 'Betta', 5, 28, 26, 1, 30, 15);

INSERT INTO fish (id, "name", "size", maxtemperature, mintemperature, minnumber, widthtank, lengthtank)
VALUES(2, 'Borboleta', 5, 28, 26, 8, 80, 30);

INSERT INTO fish (id, "name", "size", maxtemperature, mintemperature, minnumber, widthtank, lengthtank)
VALUES(3, 'Acara bandeira', 5, 28, 26, 6, 100, 40);

INSERT INTO fish_ph (fish_id, ph_id) VALUES(1, 2);
INSERT INTO fish_dh (fish_id, dh_id) VALUES(1, 2);
INSERT INTO fish_dh (fish_id, dh_id) VALUES(1, 3);
INSERT INTO fish_dh (fish_id, dh_id) VALUES(1, 4);

INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(1, 2);
INSERT INTO fish_compatility (fish_id, compatible_fish_id) VALUES(1, 3);

