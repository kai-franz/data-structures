import java.io.IOException;
import java.io.Reader;

/**
 * A Scanner is responsible for reading an input seam, one character at a
 * time, and separating the input into tokens.  A token is defined as:
 *  1. A 'word' which is defined as a non-empty sequence of characters that 
 *     begins with an alpha character and then consists of alpha characters, 
 *     numbers, the Stringle quote character "'", or the hyphen character "-".
 *  2. An 'end-of-sentence' delimiter defined as any one of the characters 
 *     ".", "?", "!".
 *  3. An end-of-file token which is returned when the scanner is asked for a
 *     token and the input is at the end-of-file.
 *  4. A phrase separator which consists of one of the characters ",",":" or
 *     ";".
 *  5. A digit.
 *  6. Any other character not defined above.
 * @author Mr. Page
 *
 */

public class Scanner
{
    private Reader in;
    private String currentChar;
    private boolean endOfFile;
    // define symbolic constants for each type of token
    public static enum TOKEN_TYPE{WORD, END_OF_SENTENCE, END_OF_FILE, 
        END_OF_PHRASE, DIGIT, UNKNOWN};
    /**
     * Consuctor for Scanner objects.  The Reader object should be one of
     *  1. A StringReader
     *  2. A BufferedReader wrapped around an Inputseam
     *  3. A BufferedReader wrapped around a FileReader
     *  The instance field for the Reader is initialized to the input parameter,
     *  and the endOfFile indicator is set to false.  The currentChar field is
     *  initialized by the getNextChar method.
     * @param in is the reader object supplied by the program consucting
     *        this Scanner object.
     */
    public Scanner(Reader in)
    {
        this.in = in;
        endOfFile = false;
        getNextChar();
    }
    /**
     * The getNextChar method attempts to get the next character from the input
     * seam.  It sets the endOfFile flag true if the end of file is reached on
     * the input seam.  Otherwise, it reads the next character from the seam
     * and converts it to a Java String object.
     * postcondition: The input seam is advanced one character if it is not at
     * end of file and the currentChar instance field is set to the String
     * representation of the character read from the input seam.  The flag
     * endOfFile is set true if the input seam is exhausted.
     */
    private void getNextChar()
    {
        try
        {
            int inp = in.read();
            if(inp == -1) 
                endOfFile = true;
            else 
                currentChar = "" + (char) inp;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Gets the next character
     * @param s the current character
     */
    private void eat(String s)
    {
        if (s.equals(currentChar))
        {
            getNextChar();
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks if a string is a letter
     * @param s the string to be checked
     * @return true if the string is a letter; otherwise,
     *          false
     */
    private boolean isLetter(String s)
    {
        return (s.compareTo("a") >= 0 && s.compareTo("a") <= 25)
                || (s.compareTo("A") >= 0 && s.compareTo("Z") <= 25);
    }

    /**
     * Checks if a string is a digit
     * @param s the string to be checked
     * @return true if the string is a digit; otherwise,
     *          false
     */
    private boolean isDigit(String s)
    {
        return (s.compareTo("0") >= 0 && s.compareTo("0") <= 9);
    }

    /**
     * Checks if a string is a special character
     * @param s the string to be checked
     * @return true if the string is a special character; otherwise,
     *          false
     */
    private boolean isSpecial(String s)
    {
        return (s.compareTo("\"") == 0 || s.compareTo("-") == 0);
    }

    /**
     * Checks if a string is the end of a phrase
     * @param s the string to be checked
     * @return true if the string is the end of a phrase; otherwise,
     *          false
     */
    private boolean isEOPhrase(String s)
    {
        return (s.compareTo(",") == 0 || s.compareTo(":") == 0 || s
            .compareTo(";") == 0);
    }

    /**
     * Checks if a string is the end of a sentence
     * @param s the string to be checked
     * @return true if the string is the end of a sentence; otherwise,
     *          false
     */
    private boolean isEOSentence(String s)
    {
        return (s.compareTo(".") == 0 || s.compareTo("?") == 0 || s
                .compareTo("!") == 0);
    }

    /**
     * Checks if a string is whitespace
     * @param s the string to be checked
     * @return true if the string is whitespace; otherwise,
     *          false
     */
    private boolean isWhiteSpace(String s) { return (s.compareTo(" ") == 0); }

    /**
     * Checks if there is a next token
     * @return true if the end of the file has not been reached; otherwise,
     *         false
     */
    public boolean hasNextToken()
    {
        return !endOfFile;
    }

    /**
     * Gets the next token
     * @return the next token in the form of a Token class
     */
    public Token nextToken() {
        if (hasNextToken()) {
            while (isWhiteSpace(currentChar))
            {
                if (!hasNextToken())
                {
                    return new Token(Scanner.TOKEN_TYPE.END_OF_FILE, "END");
                }
                eat(currentChar);
            }
            if (isDigit(currentChar))
            {
                Token answer = new Token(Scanner.TOKEN_TYPE.DIGIT, currentChar);
                eat(currentChar);
                return answer;
            }
            else if (isEOPhrase(currentChar))
            {
                Token answer = new Token(Scanner.TOKEN_TYPE.END_OF_PHRASE, currentChar);
                eat(currentChar);
                return answer;
            }
            else if (isEOSentence(currentChar))
            {
                Token answer = new Token(Scanner.TOKEN_TYPE.END_OF_SENTENCE, currentChar);
                eat(currentChar);
                return answer;
            }
            else if (isLetter(currentChar))
            {
                String word = new String();
                while (hasNextToken() && (isLetter(currentChar) || isSpecial(currentChar) || isDigit(currentChar)))
                {
                    word = (word + currentChar.toLowerCase());
                    eat(currentChar);
                }
                return new Token(Scanner.TOKEN_TYPE.WORD, word);
            }
            else
            {
                Token answer = new Token(Scanner.TOKEN_TYPE.UNKNOWN, currentChar);
                eat(currentChar);
                return answer;
            }
        }
        else
        {
            return new Token(Scanner.TOKEN_TYPE.END_OF_FILE, "END");
        }
    }
}
