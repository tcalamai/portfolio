/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threading;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Tom Calamaï  et Vianney Dardonville
 */
public class Threading {

    /**
     * @param args the command line arguments
     */
    
    public static void main (String[] args) throws InterruptedException 
    {
        int n = 100000000; //Nombre de lancé par quart
        System.out.println("Calcul de pi sans threads : ");
        long start = System.nanoTime();//mesure du temps de calcul
        QuartDeCercleNoThread(n); // 1er quart
        QuartDeCercleNoThread(n); // 2eme quart
        QuartDeCercleNoThread(n); // 3eme quart
        QuartDeCercleNoThread(n); // 4eme quart
        long end = System.nanoTime();//mesure du temps de calcul
        System.out.println("Fin des calculs : " + (end-start)/1e9 + " sec");

        System.out.println("Calcul de pi avec threads : ");
        //Variables en commun sont dans database
        Database db = new Database(0);
        
        //Création de threads :
        Thread Q1 = new Thread(new Calcul(n, db));
        Thread Q2 = new Thread(new Calcul(n, db));
        Thread Q3 = new Thread(new Calcul(n, db));
        Thread Q4 = new Thread(new Calcul(n, db));
        
        start = System.nanoTime();//mesure du temps de calcul
        
        Q1.start();
        Q2.start();
        Q3.start();
        Q4.start();
        
        //On attend que les threads aient fini leur calculs
        Q1.join();
        Q2.join();
        Q3.join();
        Q4.join();
    
        end = System.nanoTime();//mesure du temps de calcul
        System.out.println("Fin des calculs : " + (end-start)/1e9 + " sec");
        
        int nombre_final = db.getNombreDePoint(); //Récupération du résultat
        System.out.println("Resultat : " + ((float)nombre_final/(float)n));
    }  
    
    //Calcul d'un quart sans thread :
    public static void QuartDeCercleNoThread(int n) {
        int NombreDePointDansLeQuart = 0;
        
        //Récupération du random local pour avoir une génération de point aléatoire plus efficace
        ThreadLocalRandom R = ThreadLocalRandom.current();
        
        //Coord des points
        double x,y;
        
        for (int i = 0; i < n; i++)
        {
            //Lancé d'un point
            x = R.nextDouble();
            y = R.nextDouble();
            
            //Le point appartient-il au cercle ?
            if(x*x + y*y <= 1)
                NombreDePointDansLeQuart++;
        }
        
        System.out.println("Fin des calculs : " + NombreDePointDansLeQuart);
    }
}