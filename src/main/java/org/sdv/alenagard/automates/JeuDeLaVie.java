package org.sdv.alenagard.automates;


import java.util.Arrays;
import java.util.Random;

public class JeuDeLaVie {


    private final int NB_LIGNES_GRILLE = 10;
    private final int NB_COLONNES_GRILLE = 10;
    private final int NB_CELLULES = 10;

    private final Cellule[][] tableauCellule2D = new Cellule[NB_LIGNES_GRILLE][NB_COLONNES_GRILLE];
    private final Cellule[][] vieuxTableauCellule2D = new Cellule[NB_LIGNES_GRILLE][NB_COLONNES_GRILLE];

    private int cellulesAdjacentes = 0;

//    private Boolean celluHG = false;


//    private Cellule cel2D = null;

    public Cellule[][] initTableauCellules(){


        for (int i = 0; i < tableauCellule2D.length; i++) { //ligne
            for (int j = 0; j < tableauCellule2D[i].length; j++) { //colonne

                vieuxTableauCellule2D[i][j] = tableauCellule2D[i][j];

            }
        }

        return tableauCellule2D ;

    }

    /**
     * Permet d'afficher l'état actuel de la grille
     */
    public void afficherGrille(){

        for (int ligne = 0; ligne < NB_LIGNES_GRILLE; ligne++) {

            for (int colonne = 0; colonne < NB_COLONNES_GRILLE; colonne++) {

                if( tableauCellule2D[ligne][colonne] != null ){

                    System.out.print("|X");

                }else{

                    System.out.print("| ");

                }
            }
            System.out.println("|");
        }

        System.out.println(  "==========================="  );
        System.out.println(  "tableau imprimé"  );
        System.out.println(  "==========================="  );
    }

    /**
     * Permet de créer une cellule à partir d'un entier x et d'un entier y
     * @param ligne
     * @param colonne
     * @return
     */
    public Cellule nouvelleCellule(int ligne, int colonne){
        if(ligne > NB_LIGNES_GRILLE || colonne > NB_COLONNES_GRILLE){
            throw new RuntimeException("La cellule doit se trouver dans le tableau");
        }

        Cellule cel = new Cellule(ligne, colonne);

        this.tableauCellule2D[ligne][colonne] = cel;

        return cel;
    }

    /**
     * Permet de compter les cellules adjacentes à chaque case du tableau, contenant une cellule ou non
     * @param colonne, entier, coordonnee x de la cellule
     * @param ligne, entier, coordonnee y de la cellule
     * @return entier, retourne le nombre de cellules adjacentes
     */
    public int compterCellulesAdjacentes(int colonne, int ligne){
        int cellulesAdj = 0;

     /* |O| | |
        | |X| |
        | | | |*/
        if(ligne > 0 && colonne > 0) {
            if (this.vieuxTableauCellule2D[ligne - 1][colonne - 1] != null) {
                cellulesAdj++;
            }
        }

     /* | | | |
        |O|X| |
        | | | |*/
        if(colonne > 0) {
            if (this.vieuxTableauCellule2D[ligne][colonne - 1] != null) {
                cellulesAdj++;
            }
        }

     /* | | | |
        | |X| |
        |O| | |*/
        if(colonne > 0 && ligne < tableauCellule2D.length-1) {
            if (this.vieuxTableauCellule2D[ligne + 1][colonne - 1] != null) {
                cellulesAdj++;
            }
        }

     /* | |O| |
        | |X| |
        | | | |*/
        if(ligne > 0) {
            if (this.vieuxTableauCellule2D[ligne - 1][colonne] != null) {
                cellulesAdj++;
            }
        }

     /* | | | |
        | |X| |
        | |O| |*/
        if(ligne < tableauCellule2D.length-1) {
            if (this.vieuxTableauCellule2D[ligne + 1][colonne] != null) {
                cellulesAdj++;
            }
        }



     /* | | |O|
        | |X| |
        | | | |*/
        if(ligne > 0 && colonne < tableauCellule2D[ligne].length-1) {
            if (this.vieuxTableauCellule2D[ligne - 1][colonne + 1] != null) {
                cellulesAdj++;
            }
        }

     /* | | | |
        | |X|O|
        | | | |*/
        if(colonne < tableauCellule2D[ligne].length-1) {
            if (this.vieuxTableauCellule2D[ligne][colonne + 1] != null) {
                cellulesAdj++;
            }
        }

     /* | | | |
        | |X| |
        | | |O|*/
        if(ligne < tableauCellule2D.length-1 && colonne < tableauCellule2D[ligne].length-1) {
            if (this.vieuxTableauCellule2D[ligne + 1][colonne + 1] != null) {
                cellulesAdj++;
            }
        }

        return cellulesAdj;
    }

    /**
     *
     * @param posx, entier position x de la cellule à supprimer
     * @param posy, entier position y de la cellule à supprimer
     */
    public void retirerCellule(int posx, int posy){
        this.tableauCellule2D[posx][posy] = null;
    }

    /**
     * Permet de passer à la génération suivante, comprenant toute l'analyse du nombre de cellules adjacentes
     */
    public void generationSuivante(){

        dupliquerTableauDansOld();

        for (int ligne = 0; ligne < NB_LIGNES_GRILLE; ligne++) {

            for (int colonne = 0; colonne < NB_COLONNES_GRILLE; colonne++) {

                int cellulesAdjacentes = compterCellulesAdjacentes(colonne,  ligne);

                if(tableauCellule2D[ligne][colonne] != null){

                    if( cellulesAdjacentes < 2 ){

                        this.tableauCellule2D[ligne][colonne] = null;

                    }else if( cellulesAdjacentes >= 4){

                        this.tableauCellule2D[ligne][colonne] = null;

                    }

                }else{

                    if(cellulesAdjacentes == 3){
                        Cellule newCel = nouvelleCellule(ligne, colonne);
                        this.tableauCellule2D[ligne][colonne] = newCel;
                    }
                }
            }
        }


        afficherGrille();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * créé une copie du tableau 2D dans un tableau le représentant à la génération précédente
     */
    public void dupliquerTableauDansOld(){

        for (int i = 0; i < this.tableauCellule2D.length; i++) { //ligne

            for (int j = 0; j < this.tableauCellule2D[i].length; j++) { //colonne

                this.vieuxTableauCellule2D[i][j] = this.tableauCellule2D[i][j];

            }
        }

    }

    /**
     *
     * @return Boolean, vérifie si le tableau est le même qu'a l'étape précédente
     */
    public Boolean tableauEgalVieuxTableau(){

        Boolean isTabSame = false;

        for (int i = 0; i < NB_LIGNES_GRILLE; i++) {

            if (Arrays.deepEquals(tableauCellule2D, vieuxTableauCellule2D) && Arrays.equals(tableauCellule2D[i], vieuxTableauCellule2D[i])) {
                isTabSame = true;
            }
            else {
                isTabSame = false;
            }
        }

        return isTabSame;
    }

    /**
     *
     * @param nbGenerations entier définissant le nombre d'étapes du jeu de la vie
     */
    public void iterationsDeNGenerations(int nbGenerations){

        afficherGrille();
        for (int i = 0; i < nbGenerations; i++) {

            generationSuivante();

            if( tableauEgalVieuxTableau() ){

                System.out.println("Les cellules se sont installées et ne bougeront plus, ou toutes les cellules sont mortes");
                return;
            }
        }
    }

    /**
     *
     * @param nbCellules entier inférieur au nombre de cases du tableau
     */
    public void genererNCellulesAleatoires(int nbCellules){

        if(nbCellules > NB_LIGNES_GRILLE * NB_COLONNES_GRILLE){
            throw new RuntimeException("Le nombre de cellules ne peut excéder plus de "+ NB_LIGNES_GRILLE * NB_COLONNES_GRILLE +" cellules");
        }

        for (int i = 0; i < nbCellules; i++) {
            int randNum1 = new Random().nextInt(10);
            int randNum2 = new Random().nextInt(10);

            nouvelleCellule(randNum1, randNum2);
        }

    }

}
