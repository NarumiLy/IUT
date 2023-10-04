class Generateur extends Program {
    
    // Récupère le contenu d'un fichier pour le mettre dans la variable content.
    String fileAsString(String filename) {
        extensions.File f = new extensions.File(filename);
        String content = "";
        while (ready(f)) {
        content = content + readLine(f) + '\n';
        }
        return content;
    }

    /* 
     * Transforme le contenu d'un fichier texte en un fichier html.
     * @param content le contenu du fichier texte
     * @param html le contenu du fichier des balises html
     * @return le contenu du fichier html avec le contenu du fichier texte
     *         à la place de la balise <p>.
     * Ca peut être considéré comme une fonction inutile,
     * mais elle sera utilisable pour les prochains livrables.
     * C'est un gain de temps personnel.
     */
    String txtToHtml(String content, String html) {
        String result = "";

        // On parcourt le fichier html
        for(int i = 0; i != length(html); i = i+1) {
                result = result + charAt(html, i);

                // Si on trouve une balise fermante
                if (charAt(html, i) == '>') {
                    // On regarde si c'est une balise <p>
                    if (equals(substring(html, i-2, i+1), "<p>")) {
                        result = result + "\n" + content + "\n";
                    }
                }
        }
        return result;
    }
    // Fonction principale
    void algorithm() {
        String content = fileAsString("./data/Zen of Python.txt");
        String html = "<!DOCTYPE html>"
                    + "\n"
                    + "<html lang=\"fr\">"
                    + "\n"
                    + "  <head>"
                    + "\n"
                    + "    <title> Zen of Python </title>"
                    + "\n"
                    + "    <meta charset=\"utf-8\">"
                    + "\n"
                    + "  </head>"
                    + "\n"
                    + "  <body>"
                    + "\n"
                    + "    <p>"
                    + "    </p>"
                    + "\n"
                    + "  </body>"
                    + "\n"
                    + "</html>";
        print(txtToHtml(content, html));
    }
}

/* ToDo: faire les espaces depuis txtToHtml, pas difficile
* et me fera probablement gagner du temps pour les prochains livrables
* (à voir à quoi ressemblera le md)
*/ 