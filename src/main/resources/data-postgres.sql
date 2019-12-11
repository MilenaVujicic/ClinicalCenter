insert into klinika (ime, adresa, opis, prosecna_ocena) values ('MC Klinika', 'Bulevar Cara Lazara 72', 'Radi 24/7', 3.5);
insert into klinika (ime, adresa, opis, prosecna_ocena) values ('Klinika za ortopedsku hirurgiju i traumatologiju', 'Futoska 76', 'Radi 24/7', 5.0);
insert into klinika (ime, adresa, opis, prosecna_ocena) values ('Poliklinika Atina', 'Bulevar Evrope 22', 'Radi 24/7', 4.5);
insert into klinika (ime, adresa, opis, prosecna_ocena) values ('Nova klinika', 'Strazilovska 5', 'Tek otvorena', 0);


insert into korisnik (ime, prezime, email, username, password, grad, drzava, jmbg, adresa, telefon, datum_rodjenja, uloga, aktivan, aktiviran) values ('Petar', 'Petrovic', 'petar.pertovic@gmail.com','njegos', 'njegos', 'Beograd', 'Srbija', '2607426985147', 'Njegoseva 38', 114587698, '1972-4-5', 4, TRUE, TRUE);
insert into korisnik (ime, prezime, email, username, password, grad, drzava, jmbg, adresa, telefon, datum_rodjenja, uloga, aktivan, aktiviran) values ('Steva', 'Stevic', 'steva.stevic@gmail.com','steva', 'steva', 'Novi Sad', 'Srbija', '1452663358994', 'Steviceva 8', 634152698, '1980-7-16', 4, FALSE, FALSE);
insert into korisnik (ime, prezime, email, username, password, grad, drzava, jmbg, adresa, telefon, datum_rodjenja, uloga, aktivan, aktiviran) values ('Marko', 'Markovic', 'marko.markovic@gmail.com','maki', 'maki', 'Novi Sad', 'Srbija', '145874126547', 'Markovljeva 8', 64152677, '1989-6-16', 0, FALSE, FALSE);
insert into korisnik (ime, prezime, email, username, password, grad, drzava, jmbg, adresa, telefon, datum_rodjenja, uloga, aktivan, aktiviran) values ('Adam', 'Adminic', 'adam.adminic@gmail.com','adam', 'adam', 'Novi Sad', 'Srbija', '4152887563945', 'Adamoviceva 7', 415263254, '1990-7-16', 1, FALSE, FALSE);

insert into korisnik (ime, prezime, email, username, password, grad, drzava, jmbg, adresa, telefon, datum_rodjenja, uloga, aktivan, aktiviran) values ('Petar', 'Korisnik', 'petar.k@gmail.com','petk', 'petk', 'Beograd', 'Srbija', '2607426985147', 'Njegoseva 38', 114587698, '1972-4-5', 4, TRUE, TRUE);
insert into korisnik (ime, prezime, email, username, password, grad, drzava, jmbg, adresa, telefon, datum_rodjenja, uloga, aktivan, aktiviran) values ('Petar', 'Pacijent', 'petar.p@gmail.com','petp', 'petp', 'Beograd', 'Srbija', '2607426985147', 'Njegoseva 38', 114587698, '1972-4-5', 4, TRUE, TRUE);

insert into korisnik (ime, prezime, email, username, password, grad, drzava, jmbg, adresa, telefon, datum_rodjenja, uloga, aktivan, aktiviran) values ('Borisav', 'Petkovic', 'bora.peka@gmail.com','bora', 'bora', 'Novi Sad', 'Srbija', '1412984800147', 'Perkoviceva 7', 637412458, '1984-12-14', 2, TRUE, TRUE);
insert into korisnik (ime, prezime, email, username, password, grad, drzava, jmbg, adresa, telefon, datum_rodjenja, uloga, aktivan, aktiviran) values ('Lekar', 'Lekarovic', 'lekar.lekarovic@gmail.com','lekar', 'lekar', 'Novi Sad', 'Srbija', '1412123800147', 'Lekarova 7', 631232321, '1985-12-14',2, TRUE, TRUE);


insert into pacijent (id_korisnik, visina, tezina, dioptrija, klinika_id) values (1, 168, 70, -4.0, 1);
insert into pacijent (id_korisnik, visina, tezina, dioptrija, klinika_id) values (2, 174, 88, 0.5, 1);
insert into pacijent (id_korisnik, visina, tezina, dioptrija, klinika_id) values (6, 174, 88, 0.5, 1);
insert into administrator_klinike(id_korisnik, klinika_id) values (4, 1);

insert into administrator_klinickog_centra(id_korisnik) values (3);

insert into sala(ime, opis, klinika_id) values('Sala Op', 'Sala za opercaije', 1);
insert into sala(ime, opis, klinika_id) values('Sala 2', 'Sala za preglede', 1);
insert into sala(ime, opis, klinika_id) values('Sala 1', 'Sala 1 u novoj klinici', 2);

insert into termin(datum, slobodan, sala_id) values ('2020-1-30 9:00', true, 1);

insert into doktor (id_korisnik, specijalizacija, prosecna_ocena, klinika_id) values (5, 'Lekar opste prakse', 0, 1);
insert into doktor (id_korisnik, specijalizacija, prosecna_ocena, klinika_id) values (6, 'Hirurg', 5, 1);

insert into sala (klinika_id, ime, opis) values (1, 'Sala 1', 'Sala za opste preglede');

insert into lek(sifra, ime, opis) values ('J01CA04', 'Amoksicilin', 'Lek na bazi penicilina');
insert into lek(sifra, ime, opis) values ('J01CR02', 'Panklav', 'Lek na bayi amoksicilina i klavulinske kiseline');

insert into dijagnoza(sifra, ime, opis) values ('I95', 'Nizak krvni pritisak', 'Gornji ispod 100');
insert into dijagnoza(sifra, ime, opis) values ('J01', 'Akutno zapaljenje sinusa', '');
insert into dijagnoza(sifra, ime, opis) values ('J20', 'Akutni bronhitis', 'Akutna upala disajnih puteva');

insert into recept (naziv, opis, status, datum_ispisa, sifra_lek, lek_ime, pacijent_id) values ('Recept Amoksicilin', '2x1 na dan', 0, '2019-12-01', 'J01CA04', 'Amoksicilin', 1);
insert into recept (naziv, opis, status, datum_ispisa, sifra_lek, lek_ime, pacijent_id) values ('Recept Panklav', '3x1 na dan', 0, '2019-12-01', 'J01CR02', 'Panklav', 1);