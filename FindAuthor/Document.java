import java.util.ArrayList;

/**
 * The Document class stores the mystery document as sentences.
 *
 * @author Kai
 * @version June 1, 2018
 */
public class Document
{
    private ArrayList<Sentence> sentences;
    private Scanner in;
    private Token currentToken;

    /**
     * Constructor for Document objects
     * @param in the scanner to read the document with
     */
    public Document(Scanner in)
    {
        sentences = new ArrayList<Sentence>();
        this.in = in;
        getNextToken();
    }

    /**
     * Gets the next token in the input
     */
    public void getNextToken()
    {
        currentToken = in.nextToken();
    }

    /**
     * Gets the next token
     */
    public void eat(Token t)
    {
            getNextToken();
    }

    /**
     * Parses a phrase into words
     * @param t the current token
     * @return the parsed phrase as a Phrase object
     */
    public Phrase parsePhrase(Token t)
    {
        Phrase p = new Phrase();
        while (!t.getType().equals(Scanner.TOKEN_TYPE.END_OF_PHRASE)
                && !t.getType().equals(Scanner.TOKEN_TYPE.END_OF_SENTENCE)
                && !t.getType().equals(Scanner.TOKEN_TYPE.END_OF_FILE))
        {
            if (t.getType().equals(Scanner.TOKEN_TYPE.WORD))
            {
                p.addToken(t);
            }
            eat(t);
            t = currentToken;
        }
        return p;

    }

    /**
     * Parses a sentence into phrases
     * @return the parsed sentence as a Sentence object
     */
    public Sentence parseSentence()
    {
        Sentence s = new Sentence();
        while (!currentToken.getType().equals(
                Scanner.TOKEN_TYPE.END_OF_SENTENCE)
                && !currentToken.getType().equals(
                Scanner.TOKEN_TYPE.END_OF_FILE))
        {
            s.addPhrase(parsePhrase(currentToken));
            if (currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_PHRASE))
            {
                eat(currentToken);
            }
        }
        return s;
    }

    /**
     * Parses a document.
     */
    public void parseDocument()
    {
        while (!currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_FILE))
        {
            sentences.add(parseSentence());
            eat(currentToken);
        }
    }

    /**
     * Gets this document's sentences
     * @return an array list containing all of the sentences from this document
     */
    public ArrayList<Sentence> getSentences()
    {
        return sentences;
    }
}