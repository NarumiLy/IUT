semaine 1 (20 octobre): pré-traitements de fichiers textuels et mécanisme de template pour l’injection de header, footer, etc...;

But du livrable:

on dispose de dichiers décrivant des produits, à raison d’un fichier par produit. Ce sont les fichiers produit1.txt, produit2.txt, … produit5.txt. La structure de ces
fichiers est identique et constituée d’un ensemble de clés/valeurs permettant de caractériser un
produit.

Un produit est donc caractérisé par plusieurs clés :
  - nom : le nom du produit
  - date : la date de mise sur le marché
  - entreprise : l’entreprise ayant fabriqué ce produit
  - prix : le prix d’achat du produit à l’époque de commercialisation et une équivalence de ce
que cela coûterait de nos jours
  - description : une courte description des éléments caractéris#ques de ce produit.
Tous les fichiers produitX.txt respectent strictement ce format

Sachant que nos fichiers html automatiquement générés doivent avoir une structure similaire à: 
```html
<!DOCTYPE html>
<html lang="fr">
  <head>
    <title>{valeur de la clé nom}</title>
    <meta charset="utf-8">
  </head>
  <body>
    <h1>{valeur de la clé nom} ({valeur de la clé entreprise})</h1>
    <h2>{valeur de la clé prix} ({valeur de la clé date})</h2>
    <p>
{valeur de la clé description}
    </p>
  </body>
</html>
```
