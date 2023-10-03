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
     *         à la place de la balise <p> et le titre du fichier texte 
     *         à la place de la balise <title>
     */
    String txtToHtml(String content, String html) {
        String result = "";

        // On parcourt le fichier html
        for(int i = 0; i<length(html)-1; i = i+1) {
                result = result + charAt(html, i);

                // Si on trouve une balise fermante
                if (charAt(html, i) == '>') {
                    // On regarde si c'est une balise <title> ou <p>
                    if (equals(substring(html, i-6, i+1), "<title>")) {
                        result = result + " Zen of Python ";
                    } else if (equals(substring(html, i-2, i+1), "<p>")) {
                        result = result + "\n" + content;
                    }
                }
        }
        return result;
    }
    // Fonction principale
    void algorithm() {
        String content = fileAsString("./data/Zen of Python.txt");
        String html = fileAsString("./data/html.txt");
        print(txtToHtml(content, html));
    }
}