# Clinical Center

Projekat iz predmeta Internet softverske arhitekture i Projektovanje softvera za školsku 2019/2020 godinu.

Za pokretanje projekta neophodno je imati instalirano:
   1) [Spring Tool Suite 3](https://spring.io/tools3/sts/all) okruženje
   2) [PostgreSQL](https://www.postgresql.org/download/) bazu podataka

## Pokretanje programa

Projekat je neophodno klonorati ili preuzeti sa [GitHub](https://github.com/MilenaVujicic/ClinicalCenter)-a

Neophodno je **importovati** projekat u workspace STS-a.
Potrebni podaci za bazu 
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/ClinicalCenter
spring.datasource.username=postgres
spring.datasource.password=admin
```
Program se pokreće desnim klikom na ime projekta: **Run As > Spring Boot App** program se posle pokretanja dalje može videti na [localhost](http://localhost:8080)-u.

Testiranje se pokreću tako što se prvo pokrene program kao što je prethodno opisan i onda ponovo desnim klikom na ime projekta: **Run As > JUnit Test**

Tokom pokretanja programa savetuje se da se isključi **Antivirus** zbog uspešnog slanja mejlova i uspešnog rada samog programa.

## Autori
Projekat su radili:
1) *Filip-Postin Vozarević* **(RA 173/2016)**
2) *Milena Vujičić* **(RA 5/2016)**
3) *Andrea Mendrei* **(RA 10/2016)**
