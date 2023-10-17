class GenerateurProduit extends Program {

    /*
     * Fonction qui transforme un fichier texte en une liste de valeurs
     * @param txt : le fichier texte
     * @return : la liste de valeurs
     */
    /* 
    String [] getValeurstoArray(String txt) {
        String [] tab = new String [5];
        int i = 0;
        int debutIndice = 0;
        int nvlLigne = 0;
        while (i < length(txt)) {
            // Si on trouve un : on récupère l'indice
            if(txt.charAt(i) == ':') {
                // On récupère l'indice et on rajoute 2 car on ne veut pas le :, ni l'espace juste après
                debutIndice = i+2;
            }
            // Si on trouve un \n on récupère la valeur et on l'ajoute au tableau
            if (txt.charAt(i) == '\n') {
                tab[nvlLigne] = substring(txt, debutIndice, i);
                // On ajoute une case au tableau
                nvlLigne = nvlLigne + 1;
            }
            // Si on a récupéré toutes les valeurs on sort de la boucle, on ne veut pas récupérer les valeurs suivantes puisqu'il n'y en a pas normalement.
            if(nvlLigne > 4) {
                break;
            }
            i = i + 1;
        }
        return tab;
    }
    */
    /* Fonction qui transforme un fichier texte en fichier html
    * @param txt : le fichier texte
    * @return : le fichier html
    */

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

    String txtToHtml(String txt) {
        String result = "";
        String nom = getValeursByName(txt, "nom");
        String date = getValeursByName(txt, "date");
        String description = getValeursByName(txt, "description");
        String entreprise = getValeursByName(txt, "entreprise");
        String prix = getValeursByName(txt, "prix");
        String html = "<!DOCTYPE html>"
                + "\n"
                + "<html lang=\"fr\">"
                + "\n"
                + "  <head>"
                + "\n"
                + "    <title></title>"
                + "\n"
                + "    <meta charset=\"utf-8\">"
                + "\n"
                + "  </head>"
                + "\n"
                + "  <body>"
                + "\n"
                + "    <h1></h1>"
                + "\n"
                + "    <h2></h2>"
                + "\n"
                + "    <p>"
                + "\n    </p>"
                + "\n"
                + "  </body>"
                + "\n"
                + "</html>"
                + "\n";
        for(int i = 0; i<length(html); i++) {
                        result = result + charAt(html, i);

                // Si on trouve une balise fermante
                if (charAt(html, i) == '>') {
                    // On regarde si c'est une balise <p>
                    if (equals(substring(html, i-2, i+1), "<p>")) {
                        result = result + "\n" + description;
                    // On regarde si c'est une balise <h1>
                    } else if(equals(substring(html, i-3, i+1), "<h1>")) {
                        result = result + nom + " (" + entreprise + ")";
                    // On regarde si c'est une balise <h2>
                    } else if(equals(substring(html, i-3, i+1), "<h2>")) {
                        result = result + prix + " (Sortie en " + date + ")";
                    // On regarde si c'est une balise <title>
                    } else if(equals(substring(html, i-6, i+1), "<title>")){
                        result = result + nom;
                    }
                }
        }
        return result;
    }
    // Fonction principale
    void algorithm() {
        String nomFichier = argument(0);
        if(!fileExist(nomFichier)) {
            error(nomFichier + " n'existe pas");
            return;
        }
        String content = fileAsString(nomFichier);
        print(txtToHtml(content));
    }
}