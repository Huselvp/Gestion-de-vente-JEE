# Create table with name Articles_Stock
# containing the following columns:
# - codeArt: int
# - qteArt: int
# - nomArt: varchar(20)
# - descArt: varchar(200)
# - prixArt: int(11)
CREATE TABLE Articles_Stock (
    codeArt INT,
    qteArt INT,
    nomArt VARCHAR(20),
    descArt VARCHAR(200),
    prixArt INT(11)
);

# Create table with name Articles_Approvisionnement
# containing the following columns:
# - codeArt: int
# - qteCommande: int
# - datePrevueLivraison: date
CREATE TABLE Articles_Approvisionnement (
    codeArt INT,
    qteCommande INT,
    datePrevueLivraison DATE
);