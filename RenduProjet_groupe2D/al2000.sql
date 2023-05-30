/*
Code utilisé seulement lorsqu'il y avait nécessité de
reboot la BD.

DROP TRIGGER FilmPhysique.lotofdamaged;
DROP TRIGGER HistoriqueLocationPhysique.OodCreditHisto;
DROP TRIGGER HistoriqueLocationDemat.OodCreditHisto2;
DROP TRIGGER FilmPhysique.refunddamaged;

DROP table HistoriqueCredit;
DROP table FilmsPhysiquesDemandes;
DROP table Technicien;
DROP table HistoriqueLocationDemat;
DROP table HistoriqueLocationPhysique;
DROP table StockFilmsPhysiques;
DROP table AL2000s;
DROP table Administrateur;
DROP table Abonne;
DROP table Clients;
DROP table TagBloque;
DROP table TagPreferences;
DROP table FilmPhysique;
DROP table LesFilms;*/

create table LesFilms
(
ide INT PRIMARY KEY NOT NULL,
title VARCHAR(400),
realisateur VARCHAR(1000),
acteurs VARCHAR(4000),
descript VARCHAR(3000),
anneesortie INT,
affiche VARCHAR(1000),
genre_un VARCHAR(40),
genre_deux VARCHAR(40),
genre_trois VARCHAR(40),
genre_quatre VARCHAR(40),
genre_cinq VARCHAR(40),
genre_six VARCHAR(40),
genre_sept VARCHAR(40)
);


create table FilmPhysique
(
ide INT PRIMARY KEY NOT NULL,
etat VARCHAR(20),
id_film INT,
FOREIGN KEY(id_film) references LesFilms(ide)
);

create table TagBloque
(
ide INT not null,
tag VARCHAR(40),
FOREIGN KEY (ide) references Abonne(ide)
);

create table Clients
(
ide INT PRIMARY KEY NOT NULL,
empreinte_bancaire INT,
name VARCHAR(40),
adress VARCHAR(70)
);

create table Abonne
(
ide INT PRIMARY KEY NOT NULL,
hashed_card_id INT,
hashed_password INT,
id_client INT,
montant_carte_abonnement INT,
FOREIGN KEY (id_client) references Clients(ide)
);

create table AL2000s
(
ide INT PRIMARY KEY NOT NULL,
localisation VARCHAR(100)
);

create table StockFilmsPhysiques
(
nmr_stock INT not null,
ide_film_physique INT,
FOREIGN KEY (nmr_stock) references AL2000s(ide),
FOREIGN KEY (ide_film_physique) references FilmPhysique(ide)
);

create table HistoriqueLocationDemat
(
ide INT PRIMARY KEY NOT NULL,
id_abonne INT,
id_film INT,
ddloc DATE,
dfloc DATE,
price INT,
FOREIGN KEY (id_abonne) references Abonne(ide),
FOREIGN KEY (id_film) references LesFilms(ide)
);

create table HistoriqueLocationPhysique
(
ide INT PRIMARY KEY NOT NULL,
id_abonne INT,
id_film_physique INT,
ddloc DATE,
dfloc DATE,
price INT,
FOREIGN KEY (id_abonne) references Abonne(ide),
FOREIGN KEY (id_film_physique) references FilmPhysique(ide)
);

create table Technicien
(
ide INT PRIMARY KEY NOT NULL,
hashed_login VARCHAR(20),
hashed_password INT
);

create table Administrateur
(
ide INT PRIMARY KEY NOT NULL,
hashed_login VARCHAR(20),
hashed_password INT
);

create table FilmsPhysiquesDemandes
(
idcommand INT PRIMARY KEY NOT NULL,
id_al2000 INT,
id_film INT,
FOREIGN KEY (id_al2000) references AL2000s(ide),
FOREIGN KEY (id_film) references LesFilms(ide)
);

create table HistoriqueCredit
(
id_credit INT PRIMARY KEY NOT NULL,
id_abonne INT,
montant INT,
date_credit DATE,
FOREIGN KEY (id_abonne) references Abonne(ide)
);

create or replace TRIGGER lotofdamaged
AFTER UPDATE ON FilmPhysique
DECLARE
    NB NUMBER;
BEGIN
    SELECT COUNT(*) INTO NB FROM FilmPhysique, StockFilmsPhysiques
    WHERE (ETAT='DAMAGED' OR ETAT='UNVERIFIED') AND FilmPhysique.ide=StockFilmsPhysiques.ide_film_physique;
    IF(NB>10) THEN
        raise_application_error(-20111,'Appel technicien');
    END IF;
END;

create or replace TRIGGER OodCreditHisto
AFTER INSERT ON HistoriqueLocationPhysique
FOR EACH ROW
BEGIN
    DELETE FROM HistoriqueCredit
    WHERE date_credit < SYSDATE - INTERVAL '1' YEAR;
END;

create or replace TRIGGER OodCreditHisto2
AFTER INSERT ON HistoriqueLocationDemat
FOR EACH ROW
BEGIN
    DELETE FROM HistoriqueCredit
    WHERE date_credit < SYSDATE - INTERVAL '1' YEAR;
END;

create or replace TRIGGER refunddamaged
AFTER UPDATE ON FilmPhysique
FOR EACH ROW
DECLARE 
    searchid INTEGER;
    montant INTEGER;
BEGIN
    SELECT id_abonne INTO searchid FROM HistoriqueLocationPhysique WHERE id_film_physique=:NEW.ide AND dfloc= (select max(datedate) from (SELECT ddloc as datedate FROM HistoriqueLocationPhysique WHERE id_film_physique=:NEW.ide));
    SELECT price INTO montant FROM HistoriqueLocationPhysique WHERE id_abonne=searchid AND id_film_physique=:NEW.ide;
    IF (:NEW.etat='DAMAGED') THEN 
        UPDATE Abonne
        SET montant_carte_abonnement = montant_carte_abonnement + montant
        WHERE ide = searchid;
    END IF;
END;


insert into Clients values (1,1234,'Guests','noadress');
insert into Abonne values (1, null, null, 1, null, null);