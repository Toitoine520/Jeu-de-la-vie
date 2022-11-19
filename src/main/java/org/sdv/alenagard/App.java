package org.sdv.alenagard;

import org.sdv.alenagard.automates.JeuDeLaVie;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        JeuDeLaVie jdlv = new JeuDeLaVie();

        jdlv.initTableauCellules();

        final int NBRE_CELLULES = 100;
        final int NBRE_ITERATIONS = 15;

        jdlv.genererNCellulesAleatoires( NBRE_CELLULES );

        jdlv.afficherGrille();

        jdlv.iterationsDeNGenerations( NBRE_ITERATIONS );



    }
}
