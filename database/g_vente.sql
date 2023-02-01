# Create table with name Article_Prix
# containing the following columns:
# - codeArt: Int (PK)
# - nomArt: varchar(20)
# - descArt: varchar(200)
# - prixArt: Int(11)
USE g_vente;

CREATE TABLE Article_Prix (
    codeArt INT(11) NOT NULL AUTO_INCREMENT,
    nomArt VARCHAR(20) NOT NULL,
    descArt VARCHAR(200) NOT NULL,
    prixArt INT(11) NOT NULL,
    PRIMARY KEY (codeArt)
);

# Create table with name Commandes
# containing the following columns:
# - codeCmd: Int (PK)
# - client: varchar(20)
# - codeArt: Int (FK)
# - qteCmd: Int
# - dateCmd: Date
CREATE TABLE Commandes (
    codeCmd INT(11) NOT NULL AUTO_INCREMENT,
    client VARCHAR(20) NOT NULL,
    codeArt INT(11) NOT NULL,
    qteCmd INT(11) NOT NULL,
    dateCmd DATE NOT NULL,
    PRIMARY KEY (codeCmd),
    FOREIGN KEY (codeArt) REFERENCES Article_Prix(codeArt)
);

# Create table with name Users
# containing the following columns:
# - codeUser: Int (PK)
# - login: varchar(20)
# - password: varchar(20)
CREATE TABLE Users (
    codeUser INT(11) NOT NULL AUTO_INCREMENT,
    login VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(20) NOT NULL,
    PRIMARY KEY (codeUser)
);