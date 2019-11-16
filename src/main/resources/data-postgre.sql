insert into korisnik (ime, prezime, email, username, password, grad, drzava, jmbg, adresa, telefon, datum_rodjenja, uloga) values ('Petar', 'Petrovic', 'njegos', 'njegos', 'Beograd', 'Srbija', '0607426985147', 'Njegoseva 38', 114587698, '1972-4-5', PACIJENT);

insert into korisnik (ime, prezime, email, username, password, grad, drzava, jmbg, adresa, telefon, datum_rodjenja, uloga) values ('Steva', 'Stevic', 'steva', 'steva', 'Novi Sad', 'Srbija', '4152663358994', 'Steviceva 8', 634152698, '1980-7-16', PACIJENT);

insert into pacijent (id_korisnik, visina, tezina, dioptrija, klinika_id) values (1, 168, 70, -4.0, 1);
insert into pacijent (id_korisnik, visina, tezina, dioptrija, klinika_id) values (2, 174, 88, 0.5, 1);
insert into pacijent (id_korisnik, visina, tezina, dioptrija, klinika_id) values (3, 197, 98, 0.0, 1);