insert into klinika (ime, adresa, opis, prosecna_ocena) values ('MC Klinika', 'Bulevar Cara Lazara 72', '', 0);

insert into korisnik (ime, prezime, email, password, grad, drzava, jmbg, adresa, telefon) values ('Petar', 'Petrovic', 'petar.pertovic@gmail.com', 'njegos', 'Beograd', 'Srbija', '2607426985147', 'Njegoseva 38', 114587698);
insert into korisnik (ime, prezime, email, password, grad, drzava, jmbg, adresa, telefon) values ('Steva', 'Stevic', 'steva.stevic@gmail.com', 'steva', 'Novi Sad', 'Srbija', '1452663358994', 'Steviceva 8', 634152698);

insert into pacijent (id_korisnik, visina, tezina, dioptrija, klinika_id) values (1, 168, 70, -4.0, 1);
insert into pacijent (id_korisnik, visina, tezina, dioptrija, klinika_id) values (2, 174, 88, 0.5, 1);

INSERT INTO uloga (name) VALUES ('USER');
INSERT INTO uloga (name) VALUES ('ADMIN');
INSERT INTO uloga (name) VALUES ('PACIJENT');

INSERT INTO uloga_korisnik (user_id, uloga_id) VALUES (1, 3);
INSERT INTO uloga_korisnik (user_id, uloga_id) VALUES (2, 3);
INSERT INTO uloga_korisnik (user_id, uloga_id) VALUES (2, 2);