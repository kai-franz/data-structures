// imports the ArrayList class so that the animals can be stored in an arraylist
import java.util.ArrayList;

/**
 * The OldMacDonalsFarm class tests the various classes of animals by creating
 * an array list of animals, and then printing the lyrics of Old Macdonald,
 * testing the methods within each object
 *
 * @author Naveen/Kai
 * @version October 18, 2017
 */
public class OldMacDonaldsFarm
{
    // instance variable farmerName; String that keeps the name of the farmer that owns the farm
    String farmerName;
    // instance variable farmAnimals; ArrayList that contains the animals on the farm
    ArrayList<Animal> farmAnimals;

    // constructs a new OldMacDonaldsFarm with a farmerName of Old Macdonald
    // and an empty array list of animals
    public OldMacDonaldsFarm()
    {
        farmerName = "Old MacDonald";
        farmAnimals = new ArrayList<Animal>( );
    }

    /**
     * Creates a new instance of an OldMacDonaldsGarm and then adds all of the animals to that farm,
     * each time an animal is added, the old macdonald verse is sung
     */
    public static void main (String [] args)
    {
        OldMacDonaldsFarm singer = new OldMacDonaldsFarm( );
        singer.farmAnimals.add(new Chicken( ));
        singer.singVerse( );
        singer.farmAnimals.add(new Chick());
        singer.singVerse( );
        singer.farmAnimals.add(new Rooster( ));
        singer.singVerse( );
        singer.farmAnimals.add(new Pig( ));
        singer.singVerse( );
    }

    /**
     * Prints the Old MacDonald verse, first stating which new animal was added, then going through
     * the array list of animals and printing their respective noises.
     */
    public void singVerse()
    {
        String phrase1 = farmerName + " had a farm," ;
        String ei = " E-I-E-I-O.";
        System.out.println(phrase1 + ei + " and on his farm he had some " + farmAnimals.get(farmAnimals.size()-1).getCommonName() + "," + ei);
        for (int i = farmAnimals.size() - 1; i >= 0; i--)
        {
            Animal a = farmAnimals.get(i);
            String s = a.speak();
            System.out.println("With a " + s + "-" + s + " here, and a " + s + "-" + s + " there, ");
            System.out.println("Here a " + s + ", there a " + s + ", everywhere a " + s + "-" + s + ",");
        }
        System.out.println(phrase1 + ei);
        System.out.println("\n");
    }
}
