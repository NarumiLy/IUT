class GenerateurProduit extends Program {

    /*
     * Fonction qui transforme un fichier texte en une liste de valeurs
     * @param txt : le fichier texte
     * @return : la liste de valeurs
     */
    String [] getValeurstoArray(String txt) {
        String [] tab = new String [5];
        int i = 0;
        int debutIndice = 0;
        int nvlLigne = 0;
        while (i < txt.length()) {
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

    /* Fonction qui transforme un fichier texte en fichier html
    * @param txt : le fichier texte
    * @return : le fichier html
    */
    String txtToHtml(String [] tab) {
        String result = "";
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
                        result = result + "\n" + tab[4];
                    // On regarde si c'est une balise <h1>
                    } else if(equals(substring(html, i-3, i+1), "<h1>")) {
                        result = result + tab[0] + " (" + tab[2] + ")";
                    // On regarde si c'est une balise <h2>
                    } else if(equals(substring(html, i-3, i+1), "<h2>")) {
                        result = result + tab[3] + " (Sortie en " + tab[1] + ")";
                    // On regarde si c'est une balise <title>
                    } else if(equals(substring(html, i-6, i+1), "<title>")){
                        result = result + tab[0];
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
        print(txtToHtml(getValeurstoArray(content)));
    }
}