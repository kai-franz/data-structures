import java.util.ArrayList;

/**
 * Class for Phrase objects, which contain multiple tokens
 *
 * @author Kai
 * @version June 1, 2018
 */
public class Phrase
{
        private ArrayList<Token> tokens;

    /**
     * Constructs a phrase object
     */
    public Phrase()
        {
            tokens = new ArrayList<Token>();
        }

    /**
     * Adds a token to the current list of tokens in the phrase
     * @param token the token to be added
     */
    public void addToken(Token token)
        {
            tokens.add(token);
        }

    /**
     * Copies the list of tokens into a new list
     * @return a copy of the current list of tokens
     */
    public ArrayList copy()
        {
            ArrayList<Token> copy = new ArrayList();
            for (Token t : tokens)
            {
                copy.add(t);
            }
            return copy;
        }

    /**
     * Returns the String form ot the phrase
     * @return the phrase as a string
     */
    @Override
    public String toString()
        {
            String word = new String();
            for (Token t : tokens)
            {
                word = word + t.toString() + " ";
            }
            return word;
        }
}
