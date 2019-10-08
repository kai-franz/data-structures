import java.io.*;
import java.util.*;

/**
 * Models hurricane information, works with Hurricane class
 * and the user to manipulate an array of hurricane data. 
 *
 * @author Montek Kalsi and Kai 
 * @version February 27, 2018
 */
public class HurricaneOrganizerArrayName
{
    private Hurricane [] hurricanes;

    /**
     * Initializes a HurricaneOrganizerArrayName based on the filename.
     * 
     * @param filename the file with the data of the hurricanes
     * 
     * @throws IOException  if file with the hurricane information cannot be found
     */
    public HurricaneOrganizerArrayName(String filename)throws IOException
    {
        readFile(filename);   
    }

    /**
     * Determines and outputs the length of the file
     * @param filename the file whose length is determined
     * @return an integer which is the length of the file
     * @throws IOException  if file with the hurricane information cannot be found
     */
    private static int determineFileLength(String filename) throws IOException
    {
        Scanner inFile = new Scanner(new File(filename));
        int counter = 0;

        while(inFile.hasNextLine())
        {
            counter++;
            inFile.nextLine();
        }
        inFile.close();
        return counter;
    }

    /**
     * Reads and processes the information in the file filename
     * @param filename the file being read
     * @throws IOException  if file with the hurricane information cannot be found
     */
    public void readFile(String filename) throws IOException
    {
        hurricanes = new Hurricane [determineFileLength(filename)];
        int hurYear, hurPressure, hurSpeed;
        String hurName, hurMonth;
        Scanner inFile = new Scanner(new File(filename));

        for(int i = 0; i < hurricanes.length; i++)
        {
            hurYear = inFile.nextInt();
            hurMonth = inFile.next();
            hurPressure = inFile.nextInt();
            hurSpeed = inFile.nextInt();
            String tempName = inFile.nextLine();
            hurName = "";
            for(int k = 0; k < tempName.length(); k++)
            {
                char c = tempName.charAt(k);
                if(('a' <= c && c <= 'z') || ('A' <= c && c <='Z'))
                    hurName += c;
            }
            Hurricane h = new Hurricane(hurYear, hurMonth, hurPressure, hurSpeed, hurName);
            hurricanes [i] = h;
        }
        inFile.close();
    }

    /**
     * Searches the array of hurricanes for the one with the largest
     * windspeed and returns the speed
     * @return the maximum windspeed in hurricanes
     */
    public int findMaxWindSpeed( )
    {
        if (hurricanes.length == 0)
            return 0;
        int max = hurricanes[0].getSpeed();
        for (Hurricane x : hurricanes)
            max = Math.max(max, x.getSpeed());
        return max;
    }

    /**
     * Determines the maximum pressure in the array of hurricanes and returns it
     * @return the maximum pressure in hurricanes
     */
    public int findMaxPressure( )
    {
        if (hurricanes.length == 0)
            return 0;
        int max = hurricanes[0].getPressure();
        for (Hurricane x : hurricanes)
            max = Math.max(max, x.getPressure());
        return max;
    }

    /**
     * Searches the array of hurricanes for the one with the smallest
     * windspeed and returns the speed
     * @return the minimum windspeed in hurricanes
     */
    public int findMinWindSpeed( )
    {
        if (hurricanes.length == 0)
            return 0;
        int min = hurricanes[0].getSpeed();
        for (Hurricane x : hurricanes)
            min = Math.min(min, x.getSpeed());
        return min;
    }

    /**
     * Determines the minimum pressure in the array of hurricanes and returns it
     * @return the minimum pressure in hurricanes
     */
    public int findMinPressure( )
    {
        if (hurricanes.length == 0)
            return 0;
        int min = hurricanes[0].getPressure();
        for (Hurricane x : hurricanes)
            min = Math.min(min, x.getPressure());
        return min;
    }

    /**
     * Ouputs the average of all of the wind speeds of the hurricanes
     * @return the average wind speed of the hurricanes
     */
    public double calculateAverageWindSpeed( )
    {
        if (hurricanes.length == 0)
            return 0;
        double sum = 0.0;
        for (Hurricane x : hurricanes)
            sum+=x.getSpeed();
        return sum/hurricanes.length;
    }

    /**
     * Determines the average pressure of the hurricanes and outputs it
     * @return the average pressure of hurricanes
     */
    public double calculateAveragePressure( )
    {
        if (hurricanes.length == 0)
            return 0;
        double sum = 0.0;
        for (Hurricane x : hurricanes)
            sum+=x.getPressure();
        return sum/hurricanes.length;
    }

    /**
     * Determines the average category of the hurricanes and outputs it
     * @return the average category of hurricanes
     */
    public double calculateAverageCategory( )
    {
        if (hurricanes.length == 0)
            return 0;
        double sum = 0.0;
        for (Hurricane x : hurricanes)
            sum+=x.getCategory();
        return sum/hurricanes.length;
    }

    /**
     * Sorts ascending based upon the hurricanes' years,
     * The algorithm is selection sort.
     */
    public void sortYears()
    {
        for (int i=0; i<hurricanes.length; i++)
        {
            int minIndex = i;
            for (int j=i+1; j<hurricanes.length; j++)
            {
                if (hurricanes[j].getYear() < hurricanes[minIndex].getYear())
                    minIndex=j;
            }
            Hurricane temp = hurricanes[i];
            hurricanes[i] = hurricanes[minIndex];
            hurricanes[minIndex] = temp;
        }
    }

    /**
     * Lexicographically sorts hurricanes based on the hurricanes' name, 
     * using insertion sort.
     */
    public void sortNames()
    {
        for (int i=1; i<hurricanes.length; i++)
        {
            Hurricane h = hurricanes[i];
            String name = h.getName();
            int j=i-1;
            while (j>=0 && name.compareTo(hurricanes[j].getName())<0)
            {
                hurricanes[j+1]=hurricanes[j];
                j--;
            }
            hurricanes[j+1]=h;
        }
    }

    /**
     * Sorts descending based upon the hurricanes' categories,
     * using selection sort.
     */
    public void sortCategories()
    {
        for (int i=0; i<hurricanes.length; i++)
        {
            int maxIndex = i;
            for (int j=i+1; j<hurricanes.length; j++)
            {
                if (hurricanes[j].getCategory() > hurricanes[maxIndex].getCategory())
                    maxIndex=j;
            }
            Hurricane temp = hurricanes[i];
            hurricanes[i] = hurricanes[maxIndex];
            hurricanes[maxIndex] = temp;
        }
    }  

    /**
     * Sorts descending based upon pressures using a non-recursive selection sort.
     */
    public void sortPressures()
    {
        int len = hurricanes.length;
        int mid = len/2;
        sortPressuresHelper(0, mid);
        sortPressuresHelper(mid, len);
        Hurricane[] merged = new Hurricane[len];
        int findex = 0;
        int sindex = mid;
        for(int index = 0; index<len; index++)
        {
            if (findex>=mid)
            {
                merged[index]=hurricanes[sindex];
                sindex++;
            }
            else if (sindex>=len)
            {
                merged[index] = hurricanes[findex];
                findex++;
            }
            else if (hurricanes[findex].comparePressureTo(hurricanes[sindex])>=0)
            {
                merged[index] = hurricanes[findex];
                findex++;
            }
            else
            {
                merged[index] = hurricanes[sindex];
                sindex++;
            }
        }
        hurricanes = merged;
    }
    
    /**
     * Sorts descending a portion of array based upon pressure, 
     * using merge.
     * 
     * @param   start   the first index to start the sort
     * @param   end     one past the last index to sort; hence, end position
     *                  is excluded in the sort
     */
    private void sortPressuresHelper (int start, int end)
    {
        for (int outer = start; outer<end; outer++)
        {
            int mindex = outer;
            for (int inner = outer + 1; inner<end; inner++)
            {
                if (hurricanes[inner].comparePressureTo(hurricanes[mindex])>0)
                {
                    mindex = inner;
                }
            }
            Hurricane temp = hurricanes[outer];
            hurricanes[outer] = hurricanes[mindex];
            hurricanes[mindex] = temp;
        }
    }

    /**
     * Sorts ascending based upon wind speeds using a recursive merge sort.
     * @param low the smallest wind speed
     * @param high the largest wind speed
     */
    public void sortWindSpeeds(int low, int high)
    {
        if (high==low)
        {
            return;
        }
        int mid = (high+low)/2;
        sortWindSpeeds(low, mid);
        sortWindSpeeds(mid+1, high);
        mergeWindSpeedsSortHelper(low, mid+1, high);
    }

    /**
     * Merges two consecutive parts of an array, using wind speed as a criteria
     * and a temporary array.  The merge results in an ascending sort between
     * the two given indices.
     * 
     * @precondition the two parts are sorted ascending based upon wind speed
     * 
     * @param low   the starting index of one part of the array.
     *              This index is included in the first half.
     * @param mid   the starting index of the second part of the array.
     *              This index is included in the second half.
     * @param high  the ending index of the second part of the array.  
     *              This index is included in the merge.
     */
    private void mergeWindSpeedsSortHelper(int low, int mid, int high)
    {
        Hurricane[] merged = new Hurricane[high-low+1];
        int findex = low;
        int sindex = mid;
        for(int index = 0; index<merged.length; index++)
        {
            if (findex>=mid)
            {
                merged[index]=hurricanes[sindex];
                sindex++;
            }
            else if (sindex>high)
            {
                merged[index] = hurricanes[findex];
                findex++;
            }
            else if (hurricanes[findex].compareSpeedTo(hurricanes[sindex])<=0)
            {
                merged[index] = hurricanes[findex];
                findex++;
            }
            else
            {
                merged[index] = hurricanes[sindex];
                sindex++;
            }
        }
        for(int index = 0; index<merged.length; index++)
        {
            hurricanes[low+index] = merged[index];
        }
    }

    /**
     * Sequential search for all the hurricanes in a given year.
     * 
     * @param   year the year being searched for
     * @return  an array of objects in Hurricane that occured in
     *          the parameter year
     */
    public Hurricane [] searchYear(int year)
    {
        int counter = 0;
        for (Hurricane x : hurricanes)
            if (x.getYear() == year)
                counter++;
        Hurricane[] h = new Hurricane[counter];
        int index = 0;
        for (Hurricane x : hurricanes)
            if (x.getYear() == year)
            {
                h[index] = x;
                index++;
            }
        return h;
    }     
    
    /**
     * Binary search for a hurricane name.
     * 
     * @param  name   hurricane name being search
     * @return a Hurricane array of all objects in hurricanes with specified name. 
     *         Returns null if there are no matches
     */
    public Hurricane[ ] searchHurricaneName(String name)
    {
        sortNames();
        return searchHurricaneNameHelper(name, 0, hurricanes.length - 1);
    }

    /**
     * Recursive binary search for a hurricane name.  This is the helper
     * for searchHurricaneName.
     * 
     * @precondition  the array must be presorted by the hurricane names
     * 
     * @param   name  hurricane name to search for
     * @param   low   the smallest index that needs to be checked
     * @param   high  the highest index that needs to be checked
     * @return  a Hurricane array of all Hurricane objects with a specified name. 
     *          Returns null if there are no matches
     */
    private Hurricane[ ] searchHurricaneNameHelper(String name, int low , int high)
    {
        if (low>high)
        {
            return null;
        }

        int mid = (low+high)/2;
        if (hurricanes[mid].getName().equals(name))
        {
            return retrieveMatchedNames(name, mid);
        }
        if (hurricanes[mid].getName().compareTo(name)>0)
        {
            return searchHurricaneNameHelper(name, low, mid-1);
        }
        return searchHurricaneNameHelper(name, mid+1, high);
    }

    /**
     * Supports Binary Search method to get the full range of matches.
     * 
     * @precondition  the array must be presorted by the hurricane names
     * 
     * @param   name hurricane name being search for
     * @param   index  the index where a match was found
     * @return  a Hurricane array with objects from hurricanes with specified name. 
     *          Returns null if there are no matches
     */
    private Hurricane[ ] retrieveMatchedNames (String name, int index)
    {
        int findex = index-1;
        while(findex>=0 && hurricanes[findex].getName().equals(name))
        {
            findex--;
        }
        findex++;
        int lindex = index+1;
        while(lindex<hurricanes.length && hurricanes[lindex].getName().equals(name))
        {
            lindex++;
        }
        lindex--;
        Hurricane[] matches = new Hurricane[lindex-findex+1];
        for (int x = 0; x<matches.length; x++)
        {
            matches[x] = hurricanes[findex+x];
        }
        return matches;
    }

    /**
     * Prints the header for a visual representation of a table
     */
    public void printHeader()
    {
        System.out.println("\n\n");
        System.out.printf("%-4s %-5s %-15s %-5s %-5s %-5s \n", 
            "Year", "Mon.", "Name", "Cat.", "Knots", "Pressure");
    }

    /**
     * Displays the data of the hurricanes for the user to see
     */
    public void printHurricanes()
    {
        printHurricanes(hurricanes);
    }

    /**
     * Displays the data of the hurricanes for the user to see
     * @param hurs the hurricanes being printed
     */
    public void printHurricanes(Hurricane [] hurs)
    {
        if(hurs == null || hurs.length == 0)
        {
            System.out.println("\nVoid of hurricane data.");
            return;
        }
        printHeader();
        for(Hurricane h: hurs)
        {
            System.out.println(h);
        }
    }

    /**
     * Displays the menu for the user to see and use to interact with the program
     */
    public void printMenu()
    {
        System.out.println("\n\nEnter option: ");
        System.out.println("\t 1 - Print all hurricane data \n" +
            "\t 2 - Print maximum and minimum data \n" +
            "\t 3 - Print averages \n" +
            "\t 4 - Sort hurricanes by year \n" +
            "\t 5 - Sort hurricanes by name \n" +
            "\t 6 - Sort hurricanes by category, descending \n" +
            "\t 7 - Sort hurricanes by pressure, descending \n" +
            "\t 8 - Sort hurricanes by speed \n" + 
            "\t 9 - Search for hurricanes for a given year \n" +
            "\t10 - Search for a given hurricane by name \n" +
            "\t11 - Quit \n");
    }

    /**
     * Outputs the maximum and minimum windspeeds and pressures
     */
    public void printMaxAndMin( )
    {
        System.out.println("Maximum wind speed is " + 
            findMaxWindSpeed( ) +
            " knots and minimum wind speed is " + 
            findMinWindSpeed( ) + " knots.");
        System.out.println("Maximum pressure is " + 
            findMaxPressure( ) +
            " and minimum pressure is " + 
            findMinPressure( ) + ".");
    }

    /**
     * Displays all of the averages of the hurricanes
     * e.g. the average wind speeds, pressures, and categories
     */
    public void printAverages( )
    {
        System.out.printf("Average wind speed is %5.2f knots. \n" , 
            calculateAverageWindSpeed( ));
        System.out.printf("Average pressure is %5.2f. \n" , 
            calculateAveragePressure( ));
        System.out.printf("Average category is %5.2f. \n" , 
            calculateAverageCategory( ));
    }

    /**
     * Determines what to output based on the user's input
     * @return true if the user is done and chooses 11
     */
    public boolean interactWithUser( )
    {
        Scanner in = new Scanner(System.in);
        boolean done = false;
        printMenu();
        int choice = in.nextInt();
        // clear the input buffer
        in.nextLine();

        if(choice == 1)
        {
            printHurricanes( ); 
        }
        else if (choice == 2)
        {
            printMaxAndMin( );
        }
        else if (choice == 3)
        {
            printAverages( );
        }
        else if(choice == 4)
        {
            sortYears();
            printHurricanes( );
        }
        else if(choice == 5)
        {
            sortNames();
            printHurricanes( );
        }
        else if(choice == 6)
        {
            sortCategories();
            printHurricanes( );
        }
        else if(choice == 7)
        {
            sortPressures();
            printHurricanes( );
        }
        else if(choice == 8)
        {
            sortWindSpeeds(0, hurricanes.length - 1);
            printHurricanes( );
        }
        else if(choice == 9)
        {
            System.out.print("\n\tWhich year do you want to search for?\n\t");
            int year = in.nextInt();
            printHurricanes(searchYear(year));
        }
        else if(choice == 10)
        {
            System.out.print("\n\tWhich name do you want to search for?\n\t");
            String name = in.next();
            printHurricanes(searchHurricaneName(name));
        }
        else if (choice == 11)
        {
            done = true;
        }  
        return done;
    }

    /**
     * Constructs a new HurricaneOrganizerArrayNam and allows the user
     * to interact with the data using the given inputs.
     * 
     * @param args  user's information from the command line
     * 
     * @throws IOException  if file with the hurricane information cannot be found
     */
    public static void main (String [] args) throws IOException
    {
        HurricaneOrganizerArrayName cane = new HurricaneOrganizerArrayName("/Users/Kai/IdeaProjects/hurricane/src/hurricanedata.txt");
        boolean areWeDoneYet = false;
        while ( ! areWeDoneYet)
        {
            areWeDoneYet = cane.interactWithUser( );    
        }
    }
}
