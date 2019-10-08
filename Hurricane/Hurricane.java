import java.io.*;

/**
 * Models hurricane information, including categories.  
 * Works with HurricaneOrganizer, provides object and comparison skeletons.
 * 
 * @author Susan King, Kai Franz
 * @version January 10, 2010
 */
public class Hurricane
{
    private int year;
    private String month;
    private int pressure;
    private int speed;
    private String name;
    private int category;

    /**
     * Initializes a Hurricane object with no information.
     */
    public Hurricane( )
    {
        this.year = 0;
        this.month = "";
        this.pressure = 0;
        this.speed = 0;
        this.name = "";
        this.category = 0;
    }

    /**
     * Initializes a Hurricane object with historical information.
     * 
     * @param year      year the hurricane took place
     * @param month     month in String format
     * @param pressure  hurricane's pressure
     * @param speed     hurricane's speed in knots
     * @param name      hurricane's name
     */
    public Hurricane(int year, String month, 
    int pressure, int speed, String name)
    {
        this.year = year;
        this.month = month;
        this.pressure = pressure;
        this.speed = speed;
        this.name = name;
        this.category = determineCategory(speed);
    }

    /**
     * Based upon Saffir/Simpson Hurricane Scale, figures out
     * the category using wind speed in knots.
     * 
     * @param knots     wind speed in knots
     * @return Saffir/Simpson Hurricane Scale category
     */
    public int determineCategory(int knots)
    {
        if (knots >= 137) return 5;
        if (knots >= 113) return 4;
        if (knots >= 96) return 3;
        if (knots >= 83) return 2;
        if (knots >= 64) return 1;
        return 0;
    }

    //Getters

    /**
     * Gets the name of the hurricane.
     * @return the name of the hurricane
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the month of the hurricane
     * @return the month that the hurricane occurred
     */
    public String getMonth()
    {
        return month;
    }

    /**
     * Gets the pressure of the hurricane
     * @return the hurricane's pressure
     */
    public int getPressure()
    {
        return pressure;
    }

    /**
     * Gets the speed of the hurricane.
     * @return the hurricane's speed
     */
    public int getSpeed()
    {
        return speed;
    }

    /**
     * Gets the year of the hurricane.
     * @return the year the hurricane occurred.
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Gets the category of the hurricane.
     * @return the category of the hurricane, on a scale of 1-5,
     *          based on the Saffir-Simpson Hurricane Wind Scale.
     */
    public int getCategory()
    {
        return category;
    }

    /**
     * Prints out the year, month, name, category, speed, and pressure of the hurricane.
     */
    public void print()
    {
        System.out.println(toString());
    }

    /**
     * Returns a string with the hurricane's information.
     * @return a string with the year, month, name, category, speed, and pressure of the hurricane
     */
    @Override
    public String toString()
    {
        return String.format("%-4d %-5s %-15s %-5d %5d %5d ",
               year, month, name, category, speed, pressure);
    }

    /**
     * Compares the hurricane's year to the other hurricane's year.
     * @return a negative integer, zero, or a positive integer as this hurricane's year
     *         is less than, equal to, or greater than the the other hurricane's year.
     */
    public int compareYearTo(Hurricane h)
    {
        return Integer.compare(this.year, h.year);
    }

    /**
     * Compares the hurricane's year to the other hurricane's name.
     * @return a negative integer, zero, or a positive integer as this hurricane's name
     *         is less than, equal to, or greater than the the other hurricane's name.
     */
    public int compareNameTo(Hurricane h)
    {
        return name.compareTo(h.name);
    }

    /**
     * Compares the hurricane's year to the other hurricane's pressure.
     * @return a negative integer, zero, or a positive integer as this hurricane's pressure
     *         is less than, equal to, or greater than the the other hurricane's pressure.
     */
    public int comparePressureTo(Hurricane h)
    {
        return Integer.compare(pressure, h.pressure);
    }

    /**
     * Compares the hurricane's year to the other hurricane's speed.
     * @return a negative integer, zero, or a positive integer as this hurricane's speed
     *         is less than, equal to, or greater than the the other hurricane's speed.
     */
    public int compareSpeedTo(Hurricane h)
    {
        return Integer.compare(speed, h.speed);
    }

    /**
     * Compares the hurricane's year to the other hurricane's category.
     * @return a negative integer, zero, or a positive integer as this hurricane's category
     *         is less than, equal to, or greater than the the other hurricane's category.
     */
    public int compareCategoryTo(Hurricane h)
    {
        return Integer.compare(category, h.category);
    }
}
