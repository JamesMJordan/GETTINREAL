# --- !Ups

insert into pricing values (1, "banners", 1, 2.75);
insert into pricing values (2, "businesscards", 250, 10.00);
insert into pricing values (3, "businesscards", 500, 15.00);
insert into pricing values (4, "businesscards", 1000, 20.00);
insert into pricing values (5, "businesscards", 2500, 30.00);
insert into pricing values (6, "coroplast", 1, 80.00);


# --- !Downs

DROP TABLE pricing;
