
drop table if exists ClientNonAbo;
drop table if exists EnfantAbonne;
drop table if exists Films;
drop table if exists Favoris;
drop table if exists Location;
drop table if exists FilmsPhysique;
drop table if exists Acteurs;
drop table if exists Producteurs;
drop table if exists CarteAbonne;
drop table if exists Categories;
drop view if exists Historique;
drop table if exists ClientAbonne;


CREATE TABLE CarteAbonne(
    CarteId INTEGER PRIMARY KEY AUTOINCREMENT,
    ClientId INTEGER,
    FOREIGN KEY (ClientId) REFERENCES ClientAbonne(ClientId)
);


CREATE TABLE ClientAbonne (
    ClientId  INTEGER  PRIMARY KEY AUTOINCREMENT,
    Nom VARCHAR(255) NOT NULL, 
    Prenom VARCHAR(255) NOT NULL,
    DateNaissance DATE NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE, 
    PassW VARCHAR(255) NOT NULL,
    Adresse VARCHAR(255) NOT NULL,
    CarteBleu VARCHAR(255) NOT NULL,
	  Solde DOUBLE NOT NULL
);


CREATE TABLE ClientNonAbo(
	ClientNonAboId INTEGER PRIMARY KEY AUTOINCREMENT,
	CarteBleu VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE EnfantAbonne (
    CarteId_p INTEGER , 
    CarteId_e INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    Age INT NOT NULL,
    FOREIGN KEY (CarteId_p) REFERENCES CarteAbonne(CarteId)
);


CREATE TABLE Films(
    FilmId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    Titre VARCHAR(255),
    Descr VARCHAR(255),
    Annee_sortie INT,
    Img VARCHAR(255),
    AgeMin INT,
    Duree INT
);
-- CREATE TABLE Favoris(
--     ClientId INT ,
--     FilmId INT,
--     Solde INT,
--     FOREIGN KEY (ClientId) REFERENCES ClientAbonne(ClientId),
--     FOREIGN KEY (FilmId) REFERENCES Films(FilmId),
-- 	PRIMARY KEY (CarteId, FilmId)
-- );


CREATE TABLE Location(
	LocId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	Carte STRING,
	FilmId INT ,
    TypeFilm STRING,
	Date_loc DATE,
	Prix DECIMAL,
	FOREIGN KEY (FilmId)REFERENCES Films(FilmId)
);


CREATE TABLE FilmsPhysique(
	BlurayId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	FilmId INT, 
    Etat VARCHAR(255),
	FOREIGN KEY (FilmId) REFERENCES Films(FilmId)
);


CREATE TABLE Acteurs(
	FilmId INT ,
	Nom VARCHAR(255),
	Prenom VARCHAR(255),
	Date_naissance VARCHAR(255),
	Nationalite VARCHAR(255),
	FOREIGN KEY (FilmId) REFERENCES Films(FilmId)
);


CREATE TABLE Producteurs(
	FilmId INT ,
	Nom VARCHAR(255),
	Prenom VARCHAR(255),
	Date_naissance VARCHAR(255),
	Nationalite VARCHAR(255),
	PRIMARY KEY (Nom,Prenom),
	FOREIGN KEY (FilmId) REFERENCES Films(FilmId)
);

CREATE TABLE Categories(
	FilmId INT ,
    Categories VARCHAR(255),
	FOREIGN KEY (FilmId) REFERENCES Films(FilmId)
);

-- #TODO Historique vu

CREATE VIEW Historique AS
    SELECT Titre , Carte, Date_loc
    FROM Location
    GROUP BY Carte 
    ORDER BY Date_loc DESC 
    LIMIT 20
;

CREATE TRIGGER limit_locations_per_client
BEFORE INSERT ON Location
FOR EACH ROW
WHEN
    (SELECT COUNT(*) FROM Location WHERE Carte = NEW.Carte) >= 5
BEGIN
    SELECT RAISE(ABORT, 'Nombre maximal de locations atteint pour ce client');
END;

CREATE TRIGGER abonnement
BEFORE INSERT ON CarteAbonne
FOR EACH ROW
WHEN(
    SELECT Solde 
    FROM ClientAbonne
    WHERE ClientId=NEW.ClientId
)<15
BEGIN
    SELECT RAISE(ABORT, 'le solde minimum de la carte abonné à la création est 15 euros');
END;

CREATE TRIGGER create_carte_abonne
AFTER INSERT ON ClientAbonne
BEGIN
    INSERT INTO CarteAbonne (ClientId) VALUES (NEW.ClientId);
END;
 
-- CREATE TRIGGER bonus
-- AFTER INSERT ON Location
-- FOR EACH ROW
-- WHEN(
--     SELECT COUNT(*) 
--     FROM Location
--     WHERE Carte=NEW.Carte
-- )%20=0
-- BEGIN
--     UPDATE CarteAbonne
--     SET Solde=Solde+10
--     WHERE CarteId=NEW.Carte
-- END;




INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Trolls Band Together", "When Branch's brother, Floyd, is kidnapped for his musical talents by a pair of nefarious pop-star villains, Branch and Poppy embark on a harrowing and emotional journey to reunite the other brothers and rescue Floyd from a fate even worse than pop-culture obscurity.", 2023, "0_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("George Jones: Country Legends Live", "George Jones and the Jones Boys played hits for the crowd at Church Street Station in Orlando, in 1984. Performances include I Always Get Luck With You, Who's Gonna Fill Their Shoes, Bartender's Blues, The One I Loved Back Then and He Stopped Loving Her Today.", 2007, "1_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("The Treasures of Saxony: How August III Built His Collection", "Year 1763, the Seven Years' War is about to end. August III, Elector of Saxony and King of Poland, has died, leaving empty the royal treasury and an extraordinary collection of paintings, sculptures, jewelry and goldsmith masterpieces, which he considered a symbol of his greatness, and that of Dresden, one of the European capitals of Baroque art.", 2021, "2_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Tchaikovsky’s Wife", "Antonina Miliukova is a beautiful and bright young woman, born in the aristocracy of 19th century Russia. She could have anything she'd want, and yet her only obsession is to marry Pyotr Tchaikovsky, with whom she falls in love from the very moment she hears his music. The composer finally accepts this union, but after blaming her for his misfortunes and breakdowns, his attempts to get rid of his wife are brutal. Consumed by her feelings for him, Antonina decides to endure and do whatever it takes to stay with him. Humiliated, disgraced and discarded, she is slowly driven to madness.", 2022, "3_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Dhummas", "In this movie humanity has been portrayed in a moral and superior manner, and climax will keep the audience on the edge and curious. One should not think defeated till the last breathe because table can turn at any moment in life. A person should be law abiding citizen and work accordingly.", 2021, "4_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("The Stagecoach Driver and the Girl", "Silent western starring Tom Mix", 1915, "5_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Twisted Trails", "Ranch foreman Tom Snow is being hounded by sheriff Luke Fisher and his deputy, Brad Foster. The pair are really cattle rustlers, and they're trying to pin the blame on Snow. Snow escapes from them and leads them on a death-defying escape over a chasm.", 1916, "6_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Summoning Bloody Mary", "A group of friends reuniting soon learn the deadly curse of - The Bloody Mary.", 2021, "7_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Up and Going", "In Up and Going, based on Mix's own story, Arctic Trails, the star played a titled, polo playing Northwest Mounted Police officer. From an elderly woman, Tom learns that childhood girlfriend Jackie McNabb is being kept prisoner by evil Basil Du Bois.", 1922, "8_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("For Big Stakes", "Clean-Up Sudden is a drifter restoring law and order to a small farming community run by a corrupt sheriff in this silent Western.", 1922, "9_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Do and Dare", "When Henry Boone hears his grandfather's stories of his youth as a pioneer and scout, he is gripped by the fires of romance and decides to hunt adventure. Boone finds himself in an airplane carrying a military message to a leader of a revolution in a South American country. He is arrested as a spy but escapes and saves the ruler's daughter from the revolutionaries.", 1922, "10_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Doughnut", "Things turn sour when an amateur improv class meet for the first time after one of the group misjudges the situation.
 https://shortoftheweek.com/2021/11/22/doughnut", 2021, "11_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("A Shore Away", "Newly employed in an emergency shelter for people experiencing homelessness, Geneviève is shaken to meet a young woman there whom she believed to have succeeded in reintegrating when she was her social worker.", 2022, "12_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Along the One Way", "One day, my mother asked me to come home. When he got home, he asked everyone, including me, to vote for a Muslim mayoral candidate. I turned it down because I didn't agree. Therefore, the mother forced to choose based on religious arguments. Until finally the regional head election was over, I didn't vote for anyone.", 2016, "13_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Fishing Dreams", "Someone tells of a dream he had, which was to go fishing and get lots of fish, this kind of dream is often associated with a symbol of good luck. But he was afraid that the dream would happen again and again, from generation to generation.", 2021, "14_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("CSI Sanatan", "When a buisness tycoon Vikram Chakravarthi gets murdered, CSI Sanatan known for his genius crime-solving abilities is appointed to investigate. Sanatan shortlists five employees as suspects but soon the case takes a turn that shocks the nation.", 2023, "15_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Forbidden", "In one of the bustling cities of Kerala, a migrant idol-maker from Bengal, desperately needing help with his uncle's cremation, and a tough Tamil midwife, who moonlights as a funeral priest, forge a fragile bond that is threatened when she is accused of a heinous crime.", 2022, "16_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Coronameron", "Three theater students who are self-isolating at home during the COVID-19 pandemic record their daily lives on their cellphones. Students having an online meeting with their professor are rehearsing a play adapted for the stage from Decameron, which tells the story of a medieval plague. When a student's home becomes a stage, their lives become a play. The distinction between fiction and reality is becoming increasingly blurred. Life and drama merge in a new reality.", 2021, "17_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Unlocked", "Charlotte lives a very mundane and regular life. Juggling between school works and social life her day-to-day couldn't have been more routinely. But that is about to change when she forgets to lock her door one day.", 2021, "18_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Chithirai Sevvaanam", "Muthupandi is in search of Aishwarya, his teenage daughter, who goes missing after her private video gets leaked online. Can the innocent father trace his daughter, and identify the culprits responsible for putting him in this situation?", 2021, "19_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Nabab LLB", "A girl named Shuvra becomes a victim of raping and fights for justice. A so called lawyer named Nabab comes to help her and give her justice.", 2020, "20_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Rose", "Follows two sisters, Inger and Ellen, and how their relationship is challenged on an anticipated coach trip to Paris.", 2022, "21_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("As the Sprouts Grow", "The efforts of Silvester, an artist who is also a civil servant at the East Flores Regency Tourism Office, in taking part in a cultural festival in his area, Lewolema, Indonesia. The Lewolema indigenous people for almost half a century have not recognized their space for cultural expression. Festivals, he believes, are celebrations where cultural egalitarian spaces are recreated. This film also portrays how to place artistic work in an effort to maintain heritage and traditions with tourism which has a different way.", 2020, "22_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Winterabenteuer mit Pettersson und Findus", "Findus promises the chickens to build a feather care machine.", 2022, "23_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("The Enigma of HeDonism", "The Enigma of Hedonism are profile documentaries that tell the life of Heri Dono. His attitude and view as an artist that transcends canvas and time has had an important impact on artists and artists in other fields in their work. Not only that, his exploration of the various and types of working mediums and the experience of participating in various prestigious exhibitions in the world has made him dubbed as the greatest artist of Indonesia today.", 2021, "24_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Farewell, My Lovely", "", 2021, "25_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Miss Viborg", "In a small Danish town, we meet two generations of women. 61-year-old Solvej and the 17-year-old Kate. Their meeting kickstarts an unlikely friendship, as the wounds of the past are revealed and the seeds of hope are sown.", 2022, "26_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Veerapandiyapuram", "An orphan murders his girlfriend's father and his brothers. What made him seek out this bloody revenge?", 2022, "27_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Queen of Speed", "A documentary on the life of rally driver Michèle Mouton.", 2021, "28_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Resident Evil  Wesker's Report", "", 2001, "29_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Sivudu", "A rural entertainer laced with action, drama.", 2022, "30_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Cum Breack", "", 2017, "31_poster.jpg", 12, 120);
INSERT INTO Films(Titre, Descr, Annee_sortie, Img, AgeMin, Duree) VALUES("Siah Baz", "A few young people, after having many problems in their personal lives, instead of trying to solve these problems with the right decisions, draw a dangerous plan that leads to unfortunate events and ...", 2021, "32_poster.jpg", 12, 120);

INSERT INTO Acteurs VALUES(1, "Anna", "Kendrick", "1985-08-09", "USA");
INSERT INTO Acteurs VALUES(1, "Justin", "Timberlake", "1981-01-31", "USA");
INSERT INTO Acteurs VALUES(2, "George", "Jones", "1931-09-12", "USA");
INSERT INTO Acteurs VALUES(3, "August", "III", "1696-10-17", "Poland");
INSERT INTO Acteurs VALUES(4, "Pyotr", "Tchaikovsky", "1840-05-07", "Russia");
INSERT INTO Acteurs VALUES(5, "Sukhdeep", "Singh", "1996-01-01", "India");
INSERT INTO Acteurs VALUES(6, "Tom", "Mix", "1880-01-06", "USA");
INSERT INTO Acteurs VALUES(7, "Tom", "Mix", "1880-01-06", "USA");
INSERT INTO Acteurs VALUES(8, "Sara", "Fletcher", "1983-01-12", "USA");

INSERT INTO Producteurs VALUES(1, "Walt", "Dohrn", "1970-12-21", "USA");
INSERT INTO Producteurs VALUES(2, "George", "Jones", "1931-09-12", "USA");
INSERT INTO Producteurs VALUES(3, "August", "III", "1696-10-17", "Poland");
INSERT INTO Producteurs VALUES(4, "Pyotr", "Tchaikovsky", "1840-05-07", "Russia");

INSERT INTO Categories VALUES(1, "Animation");
INSERT INTO Categories VALUES(1, "Comedy");
INSERT INTO Categories VALUES(2, "Music");
INSERT INTO Categories VALUES(3, "Documentary");
INSERT INTO Categories VALUES(4, "Drama");
INSERT INTO Categories VALUES(5, "Action");
INSERT INTO Categories VALUES(6, "Adventure");
INSERT INTO Categories VALUES(7, "Crime");
INSERT INTO Categories VALUES(8, "Family");
INSERT INTO Categories VALUES(9, "Fantasy");
INSERT INTO Categories VALUES(10, "Horror");
INSERT INTO Categories VALUES(11, "Mystery");
INSERT INTO Categories VALUES(12, "Romance");
INSERT INTO Categories VALUES(13, "Sci-Fi");
INSERT INTO Categories VALUES(14, "Thriller");
INSERT INTO Categories VALUES(15, "War");