insert into klinika (ime, adresa, opis, prosecna_ocena) values ('MC Klinika', 'Bulevar Cara Lazara 72', '', 0);

insert into korisnik (ime, prezime, email, username, password, grad, drzava, jmbg, adresa, telefon, datum_rodjenja, uloga) values ('Petar', 'Petrovic', 'petar.pertovic@gmail.com','njegos', 'njegos', 'Beograd', 'Srbija', '2607426985147', 'Njegoseva 38', 114587698, '1972-4-5', 4);
insert into korisnik (ime, prezime, email, username, password, grad, drzava, jmbg, adresa, telefon, datum_rodjenja, uloga) values ('Steva', 'Stevic', 'steva.stevic@gmail.com','steva', 'steva', 'Novi Sad', 'Srbija', '1452663358994', 'Steviceva 8', 634152698, '1980-7-16', 4);
insert into korisnik (ime, prezime, email, username, password, grad, drzava, jmbg, adresa, telefon, datum_rodjenja, uloga) values ('Marko', 'Markovic', 'marko.markovic@gmail.com','maki', 'maki', 'Novi Sad', 'Srbija', '145874126547', 'Markovljeva 8', 64152677, '1989-6-16', 0);
insert into korisnik (ime, prezime, email, username, password, grad, drzava, jmbg, adresa, telefon, datum_rodjenja, uloga) values ('Adam', 'Adminic', 'adam.adminic@gmail.com','adam', 'adam', 'Novi Sad', 'Srbija', '4152887563945', 'Adamoviceva 7', 415263254, '1990-7-16', 1);

insert into pacijent (id_korisnik, visina, tezina, dioptrija, klinika_id) values (1, 168, 70, -4.0, 1);
insert into pacijent (id_korisnik, visina, tezina, dioptrija, klinika_id) values (2, 174, 88, 0.5, 1);

insert into administrator_klinike(id_korisnik, klinika_id) values (4, 1);

insert into administrator_klinickog_centra(id_korisnik) values (3);
