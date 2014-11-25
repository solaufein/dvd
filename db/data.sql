INSERT INTO genre VALUES (default,'dramat');
INSERT INTO genre VALUES (default,'komedia');
INSERT INTO genre VALUES (default,'horror');
INSERT INTO genre VALUES (default,'fantasy');
INSERT INTO genre VALUES (default,'s-f');
INSERT INTO genre VALUES (default,'thriller');
INSERT INTO genre VALUES (default,'obyczajowy');
INSERT INTO genre VALUES (default,'musical');
INSERT INTO genre VALUES (default,'akcja');

INSERT INTO actors VALUES (default,'brad','pitt');
INSERT INTO actors VALUES (default,'will','smith');
INSERT INTO actors VALUES (default,'mike','tyson');
INSERT INTO actors VALUES (default,'tom','cruise');
INSERT INTO actors VALUES (default,'anthony','hopkins');

INSERT INTO promotion VALUES(default,'Hit',9.99,6);
INSERT INTO promotion VALUES(default,'Mega Hit',7.99,5);
INSERT INTO promotion VALUES(default,'Super Hit',5.99,3);

INSERT INTO employee VALUES(default,'Adam','Nowak','505505505','nowak@op.pl',MD5('nowak1'),default,default);
INSERT INTO employee VALUES(default,'Julia','Szpak','601602603','szpak@op.pl',MD5('szpak1'),default,default);
INSERT INTO employee VALUES(default,'Radek','Wit','505226107','radoslawwitek@gmail.com',MD5('rad'),default,default);
INSERT INTO employee VALUES(default,'Stefan','Oso','595226127','raslawwitek@gmail.com',MD5('zzz'),default,default);
INSERT INTO employee VALUES(default,'Ziut','Ksow','525126107','ratitek@gmail.com',MD5('ssrs'),default,default);
INSERT INTO employee VALUES(default,'Ferdek','Etet','535226117','ferdo@gmail.com',MD5('e2ee'),default,default);
INSERT INTO employee VALUES(default,'Guto','Kajet','565727107','kajeto@gmail.com',MD5('oowso'),default,default);
INSERT INTO employee VALUES(default,'Riko','Zipes','585286187','rikozip@gmail.com',MD5('ttft'),default,default);
INSERT INTO employee VALUES(default,'Yerbo','Kreco','511226117','yerbaa@gmail.com',MD5('zzswe'),default,default);
INSERT INTO employee VALUES(default,'Nolas','Yusi','522215167','nosa@gmail.com',MD5('z265we'),default,default);
INSERT INTO employee VALUES(default,'Jhok','Rok','721125117','jhos@gmail.com',MD5('sgr3'),default,default);
INSERT INTO employee VALUES(default,'Susi','Jonns','616263167','jokkos@gmail.com',MD5('gfde2'),default,default);

INSERT INTO roles VALUES(default,'ADMIN');
INSERT INTO roles VALUES(default,'USER');

INSERT INTO employee_roles VALUES(1,1);
INSERT INTO employee_roles VALUES(1,2);
INSERT INTO employee_roles VALUES(2,2);
INSERT INTO employee_roles VALUES(3,1);
INSERT INTO employee_roles VALUES(3,2);
INSERT INTO employee_roles VALUES(4,1);
INSERT INTO employee_roles VALUES(5,1);
INSERT INTO employee_roles VALUES(6,1);
INSERT INTO employee_roles VALUES(7,1);
INSERT INTO employee_roles VALUES(8,1);
INSERT INTO employee_roles VALUES(9,1);

INSERT INTO client VALUES(default,'Stefan','Kora','88122233322','Krakow','Dobrego Pasterza 1','603603032','kora@op.pl');
INSERT INTO client VALUES(default,'Zofia','Piec','84122236312','Krakow','Wielicka 2','503203032','piec@op.pl');
INSERT INTO client VALUES(default,'Ziuta','Ziemia','83122533622','Krakow','Szewska 3','601783232','ziemia@op.pl');
INSERT INTO client VALUES(default,'Natalia','Zonk','85113533622','Krakow','Szewska 7','612783232','zonk@op.pl');
INSERT INTO client VALUES(default,'Karolina','Gleba','87123533322','Krakow','Opolska 1','621722232','gleba@op.pl');
INSERT INTO client VALUES(default,'Oliwia','Hola','82222533662','Krakow','Balicka 5','622783222','hola@op.pl');
INSERT INTO client VALUES(default,'Michal','Wiadro','81124443622','Krakow','Krucza 2','611183232','wiadro@op.pl');
INSERT INTO client VALUES(default,'Jan','Loko','83662536662','Krakow','Solarna 3','601888832','loko@op.pl');
INSERT INTO client VALUES(default,'Jurek','Zito','83555556221','Krakow','Klasztorna 3','68883232','zito@op.pl');
INSERT INTO client VALUES(default,'Olga','Przek','86162563622','Krakow','Sokola 8','633383232','przek@op.pl');

INSERT INTO receipt VALUES(default,40.30,'2013-06-12 12:23:44','213ga');
INSERT INTO receipt VALUES(default,10.31,'2013-06-12 12:23:44','613ha');
INSERT INTO receipt VALUES(default,70.33,'2013-05-11 12:23:44','1d5a');
INSERT INTO receipt VALUES(default,40.35,'2013-05-19 12:23:44','31g4a');
INSERT INTO receipt VALUES(default,24.11,'2013-07-12 12:23:44','1fda');
INSERT INTO receipt VALUES(default,28.65,'2013-07-14 12:23:44','221jja');
INSERT INTO receipt VALUES(default,56.22,'2013-08-15 12:23:44','12a');
INSERT INTO receipt VALUES(default,20.45,'2013-08-17 12:23:44','3a');
INSERT INTO receipt VALUES(default,23.23,'2013-09-18 12:23:44','jfa');
INSERT INTO receipt VALUES(default,25.42,'2013-09-19 12:23:44','65sa');
INSERT INTO receipt VALUES(default,35.12,'2013-09-20 12:23:44','66da');
INSERT INTO receipt VALUES(default,49.42,'2013-10-19 12:23:44','j5a');
INSERT INTO receipt VALUES(default,26.22,'2013-10-21 12:23:44','7s2');
INSERT INTO receipt VALUES(default,48.32,'2013-11-24 12:23:44','22sds');
INSERT INTO receipt VALUES(default,37.42,'2013-11-25 12:23:44','1213s');

INSERT INTO movie VALUES(default,5,1,'Superman','Woody Allen','2011',default);
INSERT INTO movie VALUES(default,5,1,'Sniper','Koxx','2010',default);
INSERT INTO movie VALUES(default,5,2,'Hulk','Stanley Kubrick','2010',default);
INSERT INTO movie VALUES(default,5,3,'Wolverine','Tim Burton','2012',default);
INSERT INTO movie VALUES(default,3,1,'Pila','Tim Burton','2013',default);
INSERT INTO movie VALUES(default,1,2,'Gladiator','Ingmar Bergman','2009',default);
INSERT INTO movie VALUES(default,2,3,'Rzym','Christopher Nolan','2012',default);

INSERT INTO starring VALUES(1,1);
INSERT INTO starring VALUES(1,7);
INSERT INTO starring VALUES(4,1);
INSERT INTO starring VALUES(4,2);
INSERT INTO starring VALUES(3,2);
INSERT INTO starring VALUES(3,3);
INSERT INTO starring VALUES(2,1);
INSERT INTO starring VALUES(2,3);
INSERT INTO starring VALUES(4,4);
INSERT INTO starring VALUES(1,5);
INSERT INTO starring VALUES(1,6);
INSERT INTO starring VALUES(4,6);
INSERT INTO starring VALUES(1,2);

INSERT INTO movie_copy VALUES(default,1,'A203','copy ok',1);
INSERT INTO movie_copy VALUES(default,1,'A204','copy ok',1);
INSERT INTO movie_copy VALUES(default,1,'A205','copy ok',1);
INSERT INTO movie_copy VALUES(default,2,'B203','copy ok',1);
INSERT INTO movie_copy VALUES(default,2,'B303','copy ok',1);
INSERT INTO movie_copy VALUES(default,3,'C207','copy ok',1);
INSERT INTO movie_copy VALUES(default,3,'C208','copy ok',1);
INSERT INTO movie_copy VALUES(default,4,'D203','copy ok',1);
INSERT INTO movie_copy VALUES(default,4,'D205','copy ok',1);
INSERT INTO movie_copy VALUES(default,5,'E204','copy ok',1);
INSERT INTO movie_copy VALUES(default,6,'F203','copy ok',1);
INSERT INTO movie_copy VALUES(default,6,'F204','copy ok',1);
INSERT INTO movie_copy VALUES(default,7,'G104','copy ok',1);
INSERT INTO movie_copy VALUES(default,7,'G106','copy ok',1);
INSERT INTO movie_copy VALUES(default,7,'G107','copy ok',1);

INSERT INTO renting_registry VALUES(default,1,2,2,2,'2013-05-10 12:23:44','2013-05-13 12:23:44','everything ok');
INSERT INTO renting_registry VALUES(default,1,3,3,3,'2013-05-12 12:23:44','2013-05-14 12:23:44','everything ok');
INSERT INTO renting_registry VALUES(default,1,4,4,4,'2013-06-09 12:23:44','2013-06-12 12:23:44','everything ok');
INSERT INTO renting_registry VALUES(default,2,5,5,5,'2013-06-10 12:23:44','2013-06-14 12:23:44','everything ok');
INSERT INTO renting_registry VALUES(default,2,6,6,6,'2013-06-12 12:23:44','2013-06-19 12:23:44','everything ok');
INSERT INTO renting_registry VALUES(default,2,7,7,7,'2013-07-12 12:23:44','2013-07-19 12:23:44','everything ok');
INSERT INTO renting_registry VALUES(default,2,8,8,8,'2013-07-13 12:23:44','2013-07-21 12:23:44','everything ok');
INSERT INTO renting_registry VALUES(default,2,9,9,9,'2013-08-11 12:23:44','2013-08-17 12:23:44','everything ok');
INSERT INTO renting_registry VALUES(default,2,10,10,10,'2013-08-12 12:23:44','2013-08-19 12:33:44','everything ok');
INSERT INTO renting_registry VALUES(default,1,1,11,11,'2013-09-10 12:23:44','2013-09-16 12:23:44','everything ok');
INSERT INTO renting_registry VALUES(default,1,2,12,12,'2013-09-11 12:23:44','2013-09-17 12:23:44','everything ok');
INSERT INTO renting_registry VALUES(default,2,3,13,13,'2013-09-13 12:23:44','2013-09-18 12:23:44','everything ok');
INSERT INTO renting_registry VALUES(default,2,4,14,14,'2013-10-11 12:23:44','2013-10-17 12:23:44','everything ok');
INSERT INTO renting_registry VALUES(default,1,5,15,15,'2013-10-12 12:23:44','2013-10-19 12:23:44','everything ok');
INSERT INTO renting_registry(employee_id, client_id, receipt_id, movie_copy_id, rent_date, return_date)
VALUES(1,1,1,1,'2013-06-10 12:23:44','2013-06-21 11:33:24');

