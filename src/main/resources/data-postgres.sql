insert into klinika (ime, adresa, opis, prosecna_ocena) values ('MC Klinika', 'Bulevar Cara Lazara 72', '', 0);

insert into korisnik (ime, prezime, email, username, password, grad, drzava, jmbg, adresa, telefon, datum_rodjenja, uloga, aktivan, aktiviran) values ('Petar', 'Petrovic', 'petar.pertovic@gmail.com','njegos', 'njegos', 'Beograd', 'Srbija', '2607426985147', 'Njegoseva 38', 114587698, '1972-4-5', 4, TRUE, TRUE);
insert into korisnik (ime, prezime, email, username, password, grad, drzava, jmbg, adresa, telefon, datum_rodjenja, uloga, aktivan, aktiviran) values ('Steva', 'Stevic', 'steva.stevic@gmail.com','steva', 'steva', 'Novi Sad', 'Srbija', '1452663358994', 'Steviceva 8', 634152698, '1980-7-16', 4, FALSE, FALSE);
insert into korisnik (ime, prezime, email, username, password, grad, drzava, jmbg, adresa, telefon, datum_rodjenja, uloga, aktivan, aktiviran) values ('Marko', 'Markovic', 'marko.markovic@gmail.com','maki', 'maki', 'Novi Sad', 'Srbija', '145874126547', 'Markovljeva 8', 64152677, '1989-6-16', 0, FALSE, FALSE);
insert into korisnik (ime, prezime, email, username, password, grad, drzava, jmbg, adresa, telefon, datum_rodjenja, uloga, aktivan, aktiviran) values ('Adam', 'Adminic', 'adam.adminic@gmail.com','adam', 'adam', 'Novi Sad', 'Srbija', '4152887563945', 'Adamoviceva 7', 415263254, '1990-7-16', 1, FALSE, FALSE);
insert into korisnik (ime, prezime, email, username, password, grad, drzava, jmbg, adresa, telefon, datum_rodjenja, uloga, aktivan, aktiviran) values ('Borisav', 'Petkovic', 'bora.peka@gmail.com','bora', 'bora', 'Novi Sad', 'Srbija', '1412984800147', 'Perkoviceva 7', 637412458, '1984-12-14', 4, TRUE, TRUE);

insert into pacijent (id_korisnik, visina, tezina, dioptrija, klinika_id) values (1, 168, 70, -4.0, 1);
insert into pacijent (id_korisnik, visina, tezina, dioptrija, klinika_id) values (2, 174, 88, 0.5, 1);

insert into administrator_klinike(id_korisnik, klinika_id) values (4, 1);

insert into administrator_klinickog_centra(id_korisnik) values (3);

insert into doktor (id_korisnik, specijalizacija, prosecna_ocena, klinika_id) values (5, 'Lekar opste prakse', 0, 1);

insert into sala (klinika_id, ime, opis) values (1, 'Sala 1', 'Sala za opste preglede');

insert into lek(sifra, ime, opis) values ('J01CA04', 'Amoksicilin', 'Lek na bazi penicilina');
insert into lek(sifra, ime, opis) values ('J01CR02', 'Panklav', 'Lek na bayi amoksicilina i klavulinske kiseline');

insert into dijagnoza(sifra, ime, opis) values ('I95', 'Nizak krvni pritisak', 'Gornji ispod 100');
insert into dijagnoza(sifra, ime, opis) values ('J01', 'Akutno zapaljenje sinusa', '');
insert into dijagnoza(sifra, ime, opis) values ('J20', 'Akutni bronhitis', 'Akutna upala disajnih puteva');

insert into recept (naziv, opis, status, datum_ispisa, sifra_lek, lek_ime, pacijent_id) values ('Recept diklofenakDUO', '2x1 na dan', 0, '2019-12-01', 'dikDUO', 'DiklofenakDUO', 1);
insert into recept (naziv, opis, status, datum_ispisa, sifra_lek, lek_ime, pacijent_id) values ('Recept levopront', '3x1 na dan', 0, '2019-12-01', 'levo1256', 'Levopront sirup', 1);
