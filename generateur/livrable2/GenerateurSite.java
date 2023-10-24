class GenerateurSite extends Program {
            final String html = "<!DOCTYPE html>\n" +
                      "<html lang=\"fr\">\n" +
                      "  <head>\n" +
                      "    <title>Ordinateurs mythiques</title>\n" +
                      "    <meta charset=\"utf-8\">\n" +
                      "    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n" +
                      "  </head>\n" +
                      "  <body>\n" +
                      "    <header>\n" +
                      "      <h1>Ordinateurs mythiques</h1>\n" +
                      "    </header>\n" +
                      "    <nav>\n" +
                      "      <ul>\n" +
                      "        <li><a href=\"index.html\">Accueil</a></li>\n" +
                      "      </ul>\n" +
                      "    </nav>\n" +
                      "    <main>\n" +
                      "      <section>\n" +
                      "        <h2></h2>\n" +
                      "          <p>\n          </p>\n" +
                      "      </section>\n" +
                      "    </main>\n" +
                      "  </body>\n" +
                      "</html>\n";
    /*
     * Fonction qui récupère la valeur d'une ligne en fonction de son nom
     * @param txt : le fichier texte
     * @param name : le nom de la valeur recherchée
     * @return : la valeur recherchée
     */
    String getValeursByName(String txt, String name) {
        String result = "";
        int i = 0;
        int debutIndice = 0;

        // Tant qu'on a pas trouvé la valeur recherchée et qu'on est pas à la fin du fichier. Normalement on ne devrait pas avoir à parcourir tout le fichier, mais je rajoute la condition par sécurité.
        while (equals(result, "") && i < length(txt)) {
            // On enlève 2 car on ne veut pas le :, ni l'espace juste avant, j'ai pas trouvé mieux en nom...
            int iSansEspace = i-1;
            // Si on trouve un : on récupère l'indice et on regarde s'il i est > à la longueur du nom pour éviter d'avoir une erreur si le nom est plus long que la valeur recherchée.
            if(txt.charAt(i) == ':' && length(name) <= iSansEspace) {
                // On check si le nom est bien le bon et on récupère l'indice en rajoutant 2 car on ne veut pas le :, ni l'espace juste après
                if(equals(substring(txt, iSansEspace-length(name), iSansEspace), name)) {
                debutIndice = i+2;
                }
            }
            // Si on trouve un \n on récupère la valeur
            if (txt.charAt(i) == '\n' && debutIndice != 0) {
                    result = substring(txt, debutIndice, i);
            }
            i = i + 1;
        }
        return result;
    }

    /*
     * Fonction qui transforme un fichier texte en fichier html
     * @param txt : le fichier texte
     * @return : le fichier html
     */
    String txtToHtml(String txt) {
        String result = "";
        String nom = getValeursByName(txt, "nom");
        String date = getValeursByName(txt, "date");
        String description = getValeursByName(txt, "description");
        String entreprise = getValeursByName(txt, "entreprise");
        String prix = getValeursByName(txt, "prix");

        for(int i = 0; i<length(html); i++) {
                result = result + charAt(html, i);
                
                // Si on trouve une balise ouvrante
                if(charAt(html, i) == '<') {
                    // On regarde si c'est une balise <p>
                    if (equals(substring(html, i, i+3), "<p>")) {
                        // On enlève 2 espaces avant <p>, raison expliquée dans algorithm()
                        result = substring(result, 0, result.length() - 3) + "<";
                        // On regarde si c'est une balise </p>
                    } else if(equals(substring(html, i, i+4), "</p>")) {
                        // On enlève 2 espaces avant </p>, raison expliquée dans algorithm()
                        result = substring(result, 0, result.length() - 3) + "<";
                    }
                }   
                // Si on trouve une balise fermante
                if (charAt(html, i) == '>') {
                    // On regarde si c'est une balise <p>
                    if (equals(substring(html, i-2, i+1), "<p>")) {
                        result = result + "\n" + description;
                    // On regarde si c'est une balise <h1>
                    } else if(equals(substring(html, i-3, i+1), "<h2>")) {
                        result = result + nom + " (" + entreprise + ")";
                    // On regarde si c'est une balise <h2>
                    } else if(equals(substring(html, i-4, i+1), "</h2>")) {
                        result = result + "\n        <h3>" + prix + " (Sortie en " + date + ")</h3>";
                    }
                }
        }
        return result;
    }

    /*
     * Fonction qui crée la page index.html
     * @param nbrFichier : le nombre de fichier à ajouter
     * @return : la page index.html
     */
    String indexPage(int nbrFichier) {
        String res = "";

        for(int i = 0; i<length(html); i++) {
            // On ajoute tous les caractères de la page html
            res = res + charAt(html, i);
            // Si on trouve une balise fermante
            if (charAt(html, i) == '>') {
                    // si c'est égal à <h2> on ajoute son contenu
                if(equals(substring(html, i-3, i+1), "<h2>")) {
                    // On ajoute le titre de la page
                    res = res + "Tout ce que vous avez toujours voulu savoir sur les vieux ordis sans jamais avoir osé le demander !";
                    // si c'est égal à <p> on ajoute son contenu
                } else if(equals(substring(html, i-2, i+1), "<p>")) {
                    // On ajoute le contenu de la page
                    res = res + "\nBienvenue dans le musée virtuel d'ordinateurs mythiques de l'histoire de l'informatique. Vous trouverez ici des éléments sur quelques machines qui ont marqué l'histoire de l'informatique que cela soit par leurs caractéristiques techniques ou l'impact commercial qu'elles ont eu et qui ont contribué au développement du secteur informatique.";
                }
            }
        }
        return res;
    }

    String ajouterLitoHtml(int nbrFichier, String page) {
        String res = "";
        // On ajoute le nombre de fichier à notre page, length() - 2 car fileAsString rajoute une newline automatiquement... 
        for(int i = 0; i<page.length()-2; i++) {
            // On ajoute tous les caractères de la page html
            res = res + charAt(page, i);
            // Si on trouve une balise fermante
            if (charAt(page, i) == '>') {
                // si c'est égal à </li> on ajoute un <li> avec les fichiers
                if(equals(substring(page, i-4, i+1), "</li>")) {
                    // On ajoute les fichiers dont on connait déjà le nombre avec notre paramètre nbrFichier précédemment calculé, on commence à 1 car le produit 0 n"existe pas
                    for(int j = 1; j<nbrFichier; j++) {
                        // On ajoute un <li> avec le lien vers le fichier à chaque produit
                        res = res + "\n        <li><a href=\"produit" + j + ".html\">Produit " + j + "</a></li>";
                    }
                }
            }
        }
        res += ">";
        return res;
    }
    // Fonction principale
    void algorithm() {
        String nomFichier = "./data/produit";
        int nbrFichier = 1;
        // Tant qu'il y a un fichier avec le nom produit + un nombre + .txt on le transforme en html
        while(fileExist(nomFichier + nbrFichier + ".txt") && !fileExist("./output/produit" + nbrFichier + ".html")) {
            // On récupère le contenu du fichier
            String content = fileAsString(nomFichier + nbrFichier + ".txt");
            // On transforme le contenu du fichier en html
            String html = txtToHtml(content);
            // On crée le fichier html
            stringAsFile("./output/" + "produit" + nbrFichier + ".html", html);
            // On incrémente le nombre de fichier pour passer au suivant
            nbrFichier = nbrFichier + 1;
        }

        // Ajouter Li à nos fichiers html
        /* On aurait pu éviter de refaire une boucle mais ça va honnêtement me rendre folle ce TP avec 0 explication convenable... 
         * Des balises <p> qui ne sont pas à la même place en fonction de l'index et du produit
         * Bref nsm j'ai pas envie de me prendre + la tête
        */
        for(int i = 1; i<nbrFichier; i++) {
            // On récupère le contenu du fichier
            String content = fileAsString("./output/" + "produit" + i + ".html");
            // On transforme le contenu du fichier en html
            String html = ajouterLitoHtml(nbrFichier, content);
            // On crée le fichier html
            stringAsFile("./output/" + "produit" + i + ".html", html);
        }
        // On crée la page index.html
        String index = indexPage(nbrFichier);
        // On ajoute les li à la page index.html et un newline pcq la fonction fileAsString() rajoute une nl automatiquement...
        String indexLi = ajouterLitoHtml(nbrFichier, index) + "\n";
        // On crée le fichier index.html
        stringAsFile("./output/index.html", indexLi);

    }
}