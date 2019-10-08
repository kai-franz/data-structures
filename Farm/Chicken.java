/**
 * Class for a chicken that extends the Animal abstract class
 * and implements the speak method.
 *
 * @author Naveen/Kai
 * @version October 18, 2017
 */
public class Chicken extends Animal{


    /**
     * overrides the default constructor for a Chicken
     * Defines a chicken with no specific type
     * makes a call to the constructor with the chicken type as chicken
     */
    public Chicken()
    {
        this("chicken");
    }

    /**
     * constructs a new chicken with a given Chicken type as a parameter.
     * makes a call to the super class (Animal) constructor with the Latin name
     * of a chicken and the given chicken type
     * @param chickenType, this is the type of the chicken
     */
    public Chicken(String chickenType)
    {
        super("Gallus Gallus Domesticus",chickenType);
    }

    /**
     * implements the speak method from the abstract super class
     * @return "bawk" , the noise a chicken makes
     */
    public String speak(){
        return "bawk";
    }
}
