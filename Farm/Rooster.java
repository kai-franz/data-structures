/**
 * Class for a Rooster that extends chicken and says "cock-a-doodle-do"
 *
 * @author Naveen/Kai
 * @version October 18, 2017
 */
public class Rooster extends Chicken
{
    /**
     * Default constructor for rooster objects,
     * makes a call to the Chicken super class constructor with the
     * type of chicken as Rooster
     */
    public Rooster()
    {
        super("Rooster");
    }


    /**
     * Speaks the noise that a Rooster makes.
     * @return a string containing "cock-a-doodle-do"
     */
    public String speak()
    {
        return "cock-a-doodle-do";
    }
}
