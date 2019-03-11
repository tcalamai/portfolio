package game_of_life;

/** 
 * @author Tom Calamaï and Vianney Dardonville
 */
public class cellule {
    
    //Etat de la cellule : true = vivante, false = morte
    private boolean Vivant;
    
    /**
     * @param vie
     */
    public cellule()
    {
        this.Vivant = false; //Par defaut la cellule n'est pas vivante
    }
    
    //Constructeur qui permet de donner l'état de la cellule
    public cellule(boolean vie)
    {
        this.Vivant = vie;
    }
    
    //Retourne l'état de la cellule
    public boolean EstVivant()
    {
        return Vivant;
    }  
    
    //met la cellule dans l'état vivant
    public void Vivant()
    {
        Vivant = true;
    }
    
    //met la cellule dans l'état mort
    public void Mort()
    {
        Vivant = false;
    }
    
}
