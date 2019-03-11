/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threading;

/**
 * Variables communes
 * @author Tom Calamaï
 */
public class Database {
    int nombreDePoint; //Nombre total de point dans le cercle

    public Database(int nombreDePoint) {
        this.nombreDePoint = nombreDePoint; //Initialisation
    }
    
    //Ajout de point, on synchronise pour qu'un seul thread à la fois ajoute des
    //point au nombre total
    synchronized void AddPoints(int Points) {
        nombreDePoint += Points; 
        System.out.println("Ajout de " + Points + " points = " + nombreDePoint);
    }
    
    //Récupération du nombre total de point
    synchronized int getNombreDePoint(){
        return nombreDePoint;
    }
}
