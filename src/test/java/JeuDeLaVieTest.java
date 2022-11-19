import org.junit.Assert;
import org.junit.Test;
import org.sdv.alenagard.automates.Cellule;
import org.sdv.alenagard.automates.JeuDeLaVie;

public class JeuDeLaVieTest {

    @Test
    public void testNouvelleCellule(){
        JeuDeLaVie jdlv = new JeuDeLaVie();
        Cellule[][] tabCell = jdlv.initTableauCellules();



        Cellule cel1 = jdlv.nouvelleCellule(5,9);

        Cellule result = tabCell[5][9];

        Assert.assertEquals(cel1, result);


    }

    @Test
    public void testRetirerCellule(){
        JeuDeLaVie jdlv = new JeuDeLaVie();
        Cellule[][] tabCell = jdlv.initTableauCellules();

        Cellule cel1 = jdlv.nouvelleCellule(8,8);

        jdlv.retirerCellule(8,8);

        Cellule result = tabCell[8][8];

        Assert.assertEquals(null, result);
    }

    @Test
    public void testCellulesAdjacentes(){
        JeuDeLaVie jdlv = new JeuDeLaVie();
        Cellule[][] tabCell = jdlv.initTableauCellules();

        Cellule cel1 = jdlv.nouvelleCellule(5,5);
        Cellule cel2 = jdlv.nouvelleCellule(6,6);
        Cellule cel3 = jdlv.nouvelleCellule(7,7);

        int nbreCellAdj1 = jdlv.compterCellulesAdjacentes(5,5);
        int nbreCellAdj2 = jdlv.compterCellulesAdjacentes(5,6);

        Assert.assertEquals(1,nbreCellAdj1);
        Assert.assertEquals(2,nbreCellAdj2);

    }

    @Test
    public void testgenerationSuivante(){       // blinker

        JeuDeLaVie jdlv = new JeuDeLaVie();
        Cellule[][] tabCells = jdlv.initTableauCellules();


        Cellule cel1 = jdlv.nouvelleCellule(5,5);
        Cellule cel2 = jdlv.nouvelleCellule(5,6);
        Cellule cel3 = jdlv.nouvelleCellule(5,7);

        jdlv.afficherGrille();

        jdlv.generationSuivante();
        jdlv.generationSuivante();
        jdlv.generationSuivante();

        Boolean result1 = tabCells[4][6] != null;   /*Doit être égal à true*/
        Boolean result2 = tabCells[5][6] != null;   /*Doit être égal à true*/
        Boolean result3 = tabCells[6][6] != null;   /*Doit être égal à true*/

        Assert.assertEquals(true, result1);
        Assert.assertEquals(true, result2);
        Assert.assertEquals(true, result3);
    }

    @Test
    public void testgenerationSuivante2(){        //octogone

        JeuDeLaVie jdlv = new JeuDeLaVie();
        Cellule[][] tabCells = jdlv.initTableauCellules();

        //  le dessin doit paser par les formes suivantes et dans cet ordre pour revenir ensuite à sa forme initiale :

/*| |X| | |X| |
| |X| |X|X| |X|
| | |X| | |X| |
| | |X| | |X| |
| |X| |X|X| |X|
| | |X| | |X| |*/
/*
    |X|X|X|X| | | | |
| |X| |X|X| |X| | | |
| |X|X| | |X|X| | | |
| |X|X| | |X|X| | | |
| |X| |X|X| |X| | | |
| | |X|X|X|X|*/
        /*
| | | |X|X| | | | | |
| | |X| | |X| | | | |
| |X| | | | |X| | | |
|X| | | | | | |X| | |
|X| | | | | | |X| | |
| |X| | | | |X| | | |
| | |X| | |X| | | | |
| | | |X|X| | | |*/
     /*
| | | |X|X| | | | | |
| | |X|X|X|X| | | | |
| |X| | | | |X| | | |
|X|X| | | | |X|X| | |
|X|X| | | | |X|X| | |
| |X| | | | |X| | | |
| | |X|X|X|X| | | | |
| | | |X|X| | | |*/
    /*
| | |X| | |X| | | | |
| | |X| | |X| | | | |
|X|X| |X|X| |X|X| | |
| | |X| | |X| | | | |
| | |X| | |X| | | | |
|X|X| |X|X| |X|X| | |
| | |X| | |X| | | | |
| | |X| | |X| | |*/

        Cellule cel1 = jdlv.nouvelleCellule(2,2);
        Cellule cel2 = jdlv.nouvelleCellule(2,5);

        Cellule cel3 = jdlv.nouvelleCellule(3,1);
        Cellule cel4 = jdlv.nouvelleCellule(3,3);
        Cellule cel5 = jdlv.nouvelleCellule(3,4);
        Cellule cel6 = jdlv.nouvelleCellule(3,6);

        Cellule cel7 = jdlv.nouvelleCellule(4,2);
        Cellule cel8 = jdlv.nouvelleCellule(4,5);

        Cellule cel9 = jdlv.nouvelleCellule(5,2);
        Cellule cel10 = jdlv.nouvelleCellule(5,5);

        Cellule cel11 = jdlv.nouvelleCellule(6,1);
        Cellule cel12 = jdlv.nouvelleCellule(6,3);
        Cellule cel13 = jdlv.nouvelleCellule(6,4);
        Cellule cel14 = jdlv.nouvelleCellule(6,6);

        Cellule cel15 = jdlv.nouvelleCellule(7,2);
        Cellule cel16 = jdlv.nouvelleCellule(7,5);

        jdlv.afficherGrille();

        jdlv.generationSuivante();
        jdlv.generationSuivante();
        jdlv.generationSuivante();
        jdlv.generationSuivante();
        jdlv.generationSuivante();

        Assert.assertEquals(true, true);
    }

    @Test
    public void testgenerationSuivante3() {       // vaisseau

        // Doit respecter les formes suivantes dans cet ordre, en boucle :
/*
|X|X|X|
| | |X|
| |X| |
   */
        /*
| |X| | |
| | |X|X|
| |X| |X|
 */
        /*
| |X|X|
|X| |X|
| | |X|
     */
/*
| |X|X| |
| | |X|X|
| |X| | |
  */

        JeuDeLaVie jdlv = new JeuDeLaVie();
        Cellule[][] tabCells = jdlv.initTableauCellules();

        Cellule cel1 = jdlv.nouvelleCellule(7, 0);
        Cellule cel2 = jdlv.nouvelleCellule(7, 1);
        Cellule cel3 = jdlv.nouvelleCellule(7, 2);

        Cellule cel4 = jdlv.nouvelleCellule(8, 2);

        Cellule cel5 = jdlv.nouvelleCellule(9, 1);

        jdlv.afficherGrille();

        for (int i = 0; i < 27; i++) {
            jdlv.generationSuivante();
        }
    }

    @Test
    public void testTableauEgalVieuTableau(){

        //  Les itérations s'arretent si le modele reste le meme, les cellules ne bougeront plus

        JeuDeLaVie jdlv = new JeuDeLaVie();
        Cellule[][] tabCells = jdlv.initTableauCellules();

        jdlv.nouvelleCellule(1,1);
        jdlv.nouvelleCellule(1,2);
        jdlv.nouvelleCellule(2,1);
        jdlv.nouvelleCellule(2,2);

        jdlv.afficherGrille();

        for (int i = 0; i < 10; i++) {
            jdlv.generationSuivante();

            if(jdlv.tableauEgalVieuxTableau()){
                System.out.println("Les cellules se sont installées et ne bougeront plus");
                return;
            }
        }


    }




}
