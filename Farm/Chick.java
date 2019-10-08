/**
 * Class for chick that extends the Chicken class
 *
 * @author Naveen/Kai
 * @version October 18, 2017
 */
public class Chick extends Chicken
{
    /**
     * Default constructor for chicks.
     * calls the Chicken constructor with the type as Chick
     */
    public Chick()
    {
        super("Chick");
    }

    /**
     * Constructs a chick of the given chickType
     * calls the Chicken constructor with a parameter of the one passed into this constructor
     * @param chickType, this is the type of the chicken
     */
    public Chick(String chickType)
    {
        super(chickType);
    }

    /**
     * Speaks the sound of a chick.
     * @return a string containing "peep"
     */
    public String speak()
    {
        return "peep";
    }
}
