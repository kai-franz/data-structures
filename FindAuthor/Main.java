import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Finds the author of the five mystery texts.
 *
 * @author Kai
 * @version June 1, 2018
 */
public class Main {
    /**
     * Opens the five mystery documents and compares their statistics to those of known authors
     * to guess which author wrote each mystery document.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        ArrayList<String> statFiles = new ArrayList<String>();
        statFiles.add("src/agatha.christie.stats");
        statFiles.add("src/alexandre.dumas.stats");
        statFiles.add("src/brothers.grim.stats");
        statFiles.add("src/charles.dickens.stats");
        statFiles.add("src/douglas.adams.stats");
        statFiles.add("src/emily.bronte.stats");
        statFiles.add("src/fyodor.dostoevsky.stats");
        statFiles.add("src/james.joyce.stats");
        statFiles.add("src/jane.austen.stats");
        statFiles.add("src/lewis.caroll.stats");
        statFiles.add("src/mark.twain.stats");
        statFiles.add("src/sir.arthur.conan.doyle.stats");
        statFiles.add("src/william.shakespeare.stats");


        FindAuthor f1 = new FindAuthor("src/mystery1.txt", statFiles);
        FindAuthor f2 = new FindAuthor("src/mystery2.txt", statFiles);
        FindAuthor f3 = new FindAuthor("src/mystery3.txt", statFiles);
        FindAuthor f4 = new FindAuthor("src/mystery4.txt", statFiles);
        FindAuthor f5 = new FindAuthor("src/mystery5.txt", statFiles);


        System.out.println("The Author of 1 is " + f1.findAuthor());
        System.out.println("The Author of 2 is " + f2.findAuthor());
        System.out.println("The Author of 3 is " + f3.findAuthor());
        System.out.println("The Author of 4 is " + f4.findAuthor());
        System.out.println("The Author of 5 is " + f5.findAuthor());
    }
}
