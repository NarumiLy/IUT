# Semaine 3 : Généralisation du nombre de produits

## Contexte et informations générales

Maintenant que vous avez un premier site fonctionnel, votre encadrante vous informe qu'il y a bien plus de produits en réalité et qu'il va falloir modifier le programme afin qu'il puisse traiter un nombre quelconque de produits. Avec cette augmentation se pose la question de la navigation parmi l'ensemble des produits. Actuellement, c'était une navigation similaire sur l'ensemble des pages, avec en plus un nom générique non significatif pour les différents produits. Pour cette itération, on souhaite donc pouvoir traiter plusieurs dizaines de produits et disposer d'une navigation basée sur le nom des produits en proposant à chaque fois le retour à l'accueil et les 5 produits actuels de la liste en fonction du produit actuellement sélectionné.

[Figure 1 : nouvelle navigation pour la page d'accueil (gpaslesliens)](https://www.youtube.com/watch?v=NZrS9KZtqiw)

[Figure 2 : adaptation de la navigation si l'on est sur la page ENIAC : on peut maintenant accéder aux 5 produits suivants, mais toujours revenir à l'accueil (gtoujourspaslelien)](https://www.youtube.com/watch?v=711i7D3sUyk)

## Consignes et fonctionnalités à implémenter

Afin de pouvoir gérer un grand nombre de produits et surtout pouvoir accéder facilement à leurs noms pour générer les blocs de navigation qui vont maintenant tous être différents pour chacune des pages du site, votre encadrante vous indique qu'il est nécessaire d'introduire une structure de données pour gérer les produits. Cette structure de données permettra d'accéder à toutes les informations souhaitées lors de la génération du site, contrairement au traitement réalisé produit par produit lors du livrable précédent. Cela sera particulièrement pratique pour générer les blocs de navigation afin d'accéder au nom des produits pour pouvoir remplacer les étiquettes génériques ProduitX par le nom du produit.

### Indications techniques

Votre encadrante vous demande de définir la structure de données (SDD) des produits à l'aide d'un tableau à 2 dimensions de chaînes de caractères, qui contiendra 5 colonnes (une pour chaque champ caractérisant un produit : nom, date, entreprise, prix, description) et autant de lignes qu'il y a de produits dans le répertoire data. Elle vous partage un exemple de visualisation d'un extrait de l'organisation demandée pour la SDD des produits :

| NOM | DATE | ENTREPRISE | PRIX | DESCRIPTION |
| --- | --- | --- | --- | --- |
| Altair 8800 | 1975 | MITS | 439 dollars (environ 2 100 dollars ajustés à l'inflation en 2023) | L'Altair 8800 est souvent considéré comme le premier micro-ordinateur. Il était basé sur le processeur Intel 8080, disposait de 256 octets de mémoire et était livré en kit à assembler ... |
| NeXT Computer | 1988 | NeXT Inc. | 6 500 dollars (environ 13 700 dollars ajustés à l'inflation en 2023) | Le NeXT Computer, créé par Steve Jobs après son départ d'Apple, était reconnu pour sa puissance et son système d'exploitation révolutionnaire appelé NeXTSTEP. ... |

De plus, cette semaine, votre encadrante est plus directive sur l'implémentation de cette nouvelle fonctionnalité et vous demande d'ajouter une fonction chargée de la création de la structure de données des produits en respectant cette spécification :

```java
/**
* La fonction chargerProduits parcourt les fichiers 'repertoire/prefixeX.txt'
* et retourne un tableau à deux dimensions de chaînes de caractères contenant
* l'ensemble des produits. Le tableau comporte 5 colonnes, la première pour
* le NOM du produit, la deuxième pour la DATE du produit, la troisième pour
* l'ENTREPRISE du produit, la quatrième pour le PRIX du produit et la
* cinquième pour la DESCRIPTION du produit. Le tableau comporte donc autant
* de lignes qu'il y a de fichiers produits présents dans le répertoire 'data'.
*/
String[][] chargerProduits(String repertoire, String prefixe) {}
```

Pour obtenir la liste de tous les 1chiers présents dans un répertoire, vous pouvez u#liser la
fonc#on suivante. Il peut être u#le cependant de 1ltrer les 1chiers a1n de ne décompter que ceux
correspondant à des produits :

```java
/**
* La fonction getAllFilesFromDirectory(String repertoire) retourne un tableau
* à une dimension de chaînes de caractères dont chaque case contient le nom
d’un fichier présent dans le répertoire indiqué en paramètre.
*/
String[] getAllFilesFromDirectory(String repertoire) {
```

A1n d’avoir un code lisible, elle vous indique aussi qu’il faudra créer des constantes globales pour
accéder à chacun des champs caractérisant le produit dans la table, c’est-à-dire aux indices des
diOérentes colonnes (IDX_NOM, IDX_DATE, IDX_ENTREPRISE, IDX_PRIX et
IDX_DESCRIPTION).
Elle vous conseille aussi d’ajouter une fonc#on String toString(String[][]
produits) pour visualiser le contenu de votre structure de données, ce qui peut se révéler être
très pra#que en cas de débogage.

```java
/**
* La fonction toString retourne une représentation sous forme de chaîne de
* caractères du tableau de produits à des fins de débogage. Ainsi, la
* fonction ne retourne qu'un sous-ensemble des données : NOM (DATE) - PRIX
* - DESCRIPTION. Exemple de chaîne produite : Apple II (Avril 1977) - 1 298
* dollars (environ 5 600 dollars ajustés à l'inflation en 2023) - L'Apple II
* était l'un des premiers ordinateurs personnels largement adoptés par
* le grand public. Il était équipé d'un microprocesseur MOS Technology 6502,
* de 4 Ko de RAM (extensible à 48 Ko) et d'une couleur graphique. L'Apple II
* a été très populaire dans les écoles et les foyers.
*/
String toString(String[][] produits) {}
```

Au niveau de la naviga#on entre les pages, votre encadrante vous précise l’évolution majeure à apporter lors de ce livrable en partageant ce schéma:


|  |  || ***Navigation pour le livrable 2 (5 produits)*** ||||
|---|---|---|---|---|---|---|
| | Accueil | Produit1 | Produit2 | Produit3 | Produit4 | Produit5 |

|  |  || ***Navigation pour le livrable 3 (plusieurs dizaines de produits)*** ||||
|---|---|---|---|---|---|---|
| Accueil | ProduitX | Produit(X+1) | Produit(X+2) | Produit(X+3) | Produit(X+4) |

Ainsi, chacune des pages aura son propre bloc de navigation, cela induira quelques changement en terme de paramétrage sur la créa#on de ces éléments HTML ...