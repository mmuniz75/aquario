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

INSERT INTO public.fish
(id, "name", "size", maxtemperature, mintemperature, minnumber, widthtank, lengthtank)
VALUES(1, 'Betta', 5, 28, 26, 1, 30, 15);