/**
 * The pig object extends Animal and says "oink"
 *
 * @author Naveen/Kai
 * @version October 18, 2017
 */
public class Pig extends Animal
{
    /**
     * Default constructor for pig objects
     * calls the super class (Animal) constructor with two parameters
     * both parameters of the call to super are "pig",
     * so both the latin and common name are pig
     */
    public Pig()
    {
        super("Pig", "Pig");
    }

    /**
     * Speaks the noise that a pig would make
     * @return "oink", the sound of a pig
     */
    public String speak()
    {
        return "oink";
    }
}
