package game_of_life;

/**
 *
 * @author Tom Calamaï and Vianney Dardonville
 */
public class Jeu implements Runnable{
    
    cellule[][] plateau;
    cellule nouvelleCell;
    int colonnes, lignes;
    int colonne, ligne;

    //Initialisation de l'ensemble des variables necessaire pour la mise à jour de la cellule
    public Jeu(cellule[][] plateau, cellule nouvelleCell, int colonne, int ligne) {     
        this.plateau = plateau;
        this.colonnes = plateau[0].length;
        this.lignes = plateau.length;  
        this.colonne = colonne;
        this.ligne = ligne;
        this.nouvelleCell = nouvelleCell;
    }    

    //Lancement l'opération réalisé dans le thread
    @Override
    public void run() {
        UpdateCell();
    }
    
    //Fonction de verification et de mise à jour :
    private void UpdateCell()
    {
        int voisins = 0;
        cellule cell = plateau[ligne][colonne];
        //On compte le nombre de voisin
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                //On ne traite pas la case elle même mais ses voisins
                if(i == 0 && j == 0)
                    continue;
                
                //Vérification des conditions aux limites (bordure du plateau)
                if(ligne + i < 0 || ligne + i >= lignes)
                    continue;
                if(colonne + j < 0 || colonne + j >= colonnes)
                    continue;
                
                //Ajout du voisin
                if(plateau[ligne + i][colonne  + j].EstVivant())
                    voisins++;
            }
        }
        
        //On met à jour la case selon les règles
        if(cell.EstVivant() && (voisins == 2 || voisins == 3))
            nouvelleCell.Vivant();
        else if(!cell.EstVivant() && voisins == 3 )
            nouvelleCell.Vivant();
        else
            nouvelleCell.Mort();
    }
    
}
