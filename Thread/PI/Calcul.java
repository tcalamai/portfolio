/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threading;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Tom Calamaï
 */
public class Calcul implements Runnable {

    int m_n;
    Database m_db;

    public Calcul(int m_n, Database db) {
        this.m_n = m_n;
        m_db = db;
    }
    
    @Override
    public void run() {
       QuartDeCercle(m_n);
    }
    
    private void QuartDeCercle(int n) {
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
        //Ajout des points aux variables en commun
        m_db.AddPoints(NombreDePointDansLeQuart);
    }
    
    
    
}
