/**
 * Class for animals that have a common name and a latin name and can speak.
 *
 * @author Naveen/Kai
 * @version October 18, 2017
 */
public abstract class Animal implements Comparable<Animal>
{
    String latinName;
    String commonName;

    /**
     * Constructor for animal objects.
     * @param latinName the latin name of the animal
     * @param commonName the common name of the animal
     */
    public Animal(String latinName, String commonName)
    {
        this.latinName = latinName;
        this.commonName = commonName;
    }

    /**
     * Gets the animal's latin name
     * @return the animal's latin name
     */
    public String getLatinName()
    {
        return latinName;
    }

    /**
     * Sets the animal's latin name
     * @param latin the animal's new latin name
     */
    public void setLatinName(String latin)
    {
        latinName = latin;
    }

    /**
     * Sets the animal's common name
     * @param common the animal's new common name
     */
    public void setCommonName(String common)
    {
        commonName = common;
    }

    /**
     * Gets the animal's common name
     * @return the animal's common name
     */
    public String getCommonName()
    {
        return commonName;
    }

    /**
     * Speaks. To be implemented in classes that extend it.
     * @return a string containing the animal speaking
     */
    abstract String speak();

    /**
     * Compares this animal to another using their common names.
     * @precondition other is not null
     * @param other
     * @return a value < 0 if this is less than other
     *         a value = 0 if this is equal to other
     *         a value > 0 if this is greater than other
     */
    public int compareTo(Animal other)
    {
        if(getCommonName()==null)
        {
            throw new NullPointerException("common name is null");
        }

        if (!(other instanceof Animal))
        {
            throw new IllegalArgumentException("given object is not an instanceof (seldom used) Animal");
        }

        Animal ot = (Animal) other;

        String o = ot.getCommonName();

        return o.compareTo(commonName);
    }
}
