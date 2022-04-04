create table IF NOT EXISTS dh (
    id int PRIMARY KEY,
    name VARCHAR(10),
    mindh int,
    maxdh int
    )
;
create table IF NOT EXISTS  ph (
    id int PRIMARY KEY,
    name VARCHAR(20),
    minph numeric,
    maxph numeric
    )
;

create Table IF NOT EXISTS fish (
   id int PRIMARY KEY,
   name VARCHAR(50),
   size int,
   maxtemperature int,
   mintemperature int,
   minNumber int,
   widthTank int,
   lengthTank int,
   initialspace int default 0,
   imageurl VARCHAR(255)
)
;

create table IF NOT EXISTS fish_ph (
   fish_id int,
   ph_id int,
   PRIMARY KEY (fish_id,ph_id),
   FOREIGN KEY (fish_id) REFERENCES fish(id),
   FOREIGN KEY (ph_id) REFERENCES ph(id)
)
;

create table IF NOT EXISTS fish_dh (
   fish_id int,
   dh_id int,
   PRIMARY KEY (fish_id,dh_id),
   FOREIGN KEY (fish_id) REFERENCES fish(id),
   FOREIGN KEY (dh_id) REFERENCES dh(id)
)
;
create table IF NOT EXISTS fish_compatility (
   fish_id int,
   compatible_fish_id int,
   PRIMARY KEY (fish_id,compatible_fish_id),
   FOREIGN KEY (fish_id) REFERENCES fish(id),
   FOREIGN KEY (compatible_fish_id) REFERENCES fish(id)
)
;