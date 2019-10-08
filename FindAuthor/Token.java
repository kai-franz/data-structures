/**
 * Class for Token objects
 *
 * @author Kai
 * @version June 1, 2018
 */
public class Token
{
    private Scanner.TOKEN_TYPE type;
    private String token;

    /**
     * Constructor for Token objects
     * @param t the type of the token
     * @param s the String text
     */
    public Token(Scanner.TOKEN_TYPE t, String s)
    {
        type = t;
        token = s;
    }

    /**
     * Gets the type of the token
     * @return the type of the token
     */
    public Scanner.TOKEN_TYPE getType()
    {
        return type;
    }

    /**
     * Gets the token as a string
     * @return the token as a string
     */
    public String getToken()
    {
        return token;
    }

    /**
     * Gets the String form of the token
     * @return the token as a string
     */
    @Override
    public String toString()
    {
        return token;
    }

    /**
     * Checks if this token is equal to another
     * @param obj the object to be comapred to
     * @return true if the two tokens have the same type and text; otherwise,
     *         false
     */
    @Override
    public boolean equals(Object obj)
    {
        return token.equals(obj);
    }

    /**
     * Gets the hash code of the token
     * @return the hash code
     */
    @Override
    public int hashCode()
    {
        return token.hashCode();
    }


}
