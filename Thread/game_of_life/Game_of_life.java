package game_of_life;

import java.util.ArrayList;

/**
 *
 * @author Tom Calamaï and Vianney Dardonville
 */
public class Game_of_life {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception 
    {        
        int l = 4;
        cellule[][] plateau = new cellule[l][l];
        
        //Génération du plateau aléatoirement :
        for(int i = 0; i < l; i++)
            for(int j = 0; j < l; j++)
                if(Math.random() > 0.6) //Probabilité d'avoir une cellule vivante 40%
                    plateau[i][j] = new cellule(true);
                else
                    plateau[i][j] = new cellule();
        
        //On effectue 5 tours
        for(int i = 0; i < 5; i++)
        {
            System.out.println("tour " + i);
            afficher(plateau); //Affichage du plateau
            plateau = mise_a_jour(l, l, plateau); //Mise à jour du plateau
        }
        
    }
    
    //Fonction de mise à jour du plateau
    public static cellule[][] mise_a_jour(int colonnes, int lignes, cellule[][] plateau) throws Exception
    {
        //Liste de l'ensemble des threads :
        ArrayList<Thread> threads = new ArrayList<>();
        //Création du nouveau plateau :
        cellule[][] nouveauPlateau = new cellule[plateau.length][plateau[0].length];
        
        //Verificatiion de toutes les cases :
        for(int l = 0; l < lignes; l++)
        {
            for(int c = 0; c < colonnes; c++)
            {
                //Créer la nouvelle cellule
                nouveauPlateau[l][c] = new cellule();
                Integer loc_c = c;
                Integer loc_l = l;
                
                //Création du thread qui s'occupe de cette case :
                Thread verification_cellule = new Thread(new Jeu(plateau, nouveauPlateau[loc_l][loc_c], loc_c, loc_l));
                verification_cellule.start();
                threads.add(verification_cellule); //On l'ajoute à notre liste de threads;
            }
        }
        
        //attendre que tous les threads finissent
        for(Thread t : threads)
             t.join();
        
        return nouveauPlateau;//On retourne le nouveau plateau de jeu mis à jour
    }
    
    //Fonctiond d'affichage du plateau
    public static void afficher(cellule[][] plateau)
    {     
        for(int l = 0; l < plateau.length; l++)
        {
            //Affichage ligne par ligne :
            String ligne = "";
            for(int c = 0; c < plateau[0].length; c++)
            {
                //Affichage de chaque case : selon son état
                if(plateau[l][c].EstVivant())
                    ligne += "0";
                else
                    ligne += ".";
            }
            System.out.println(ligne);
        }
        
    }
    
}
