import java.util.ArrayList;

/**
 * Class for sentences
 * @author Kai
 * @version June 1, 2018
 */
public class Sentence
{

    private ArrayList<Phrase> phrases;

    /**
     * Constructor for Sentence objects
     */
    public Sentence()
    {
        phrases = new ArrayList<Phrase>();
    }

    /**
     * Adds a phrase to the sentence
     * @param p the phrase to be added
     */
    public void addPhrase(Phrase p)
    {
        phrases.add(p);
    }

    /**
     * Copies the array list of phrases
     * @return a copy of the array list of phrases
     */
    public ArrayList copy()
    {
        ArrayList<Phrase> copy = new ArrayList<Phrase>();
        for (Phrase p : phrases)
        {
            ArrayList<Token> tokens = p.copy();
            Phrase a = new Phrase();
            for (Token t : tokens)
            {
                a.addToken(t);
            }
            copy.add(a);
        }
        return copy;
    }

    /**
     * Returns the to String form of the sentence
     * @return the string form of this sentence
     */
    @Override
    public String toString()
    {
        String answer = "";
        for (int i = 0; i < phrases.size(); i++)
        {
            answer = answer + phrases.get(i).toString();
        }
        return answer;
    }
}