import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Finds the author of a mystery document.
 *
 * @author Kai
 * @version June 1, 2018
 */
public class FindAuthor
{

    Document d;
    static double[] weights = {0, 11, 33, 50, 0.4, 4};
    ArrayList<String> stats;


    /**
     * Constructor for FindAuthor objects
     * @param fileName Name of the mystery document
     * @param statFiles ArrayList containing the stat files for known authors
     * @throws IOException
     */
    public FindAuthor(String fileName, ArrayList<String> statFiles) throws IOException
    {
        BufferedReader b;
        b = new BufferedReader(new FileReader(fileName));
        String currentString;
        String docString = "";
        currentString = b.readLine();
        while (currentString != null)
        {
            docString +=  currentString;
            currentString = b.readLine();
        }
        d = new Document(new Scanner(new StringReader(docString)));
        d.parseDocument();
        stats = statFiles;

    }

    /**
     * Gets the average word length of the document
     * @return a double containing the average word length in characters
     */
    public double getAverageWordLength()
    {
        int totalWords = 0;
        int totalWordLength = 0;
        for (Sentence s : d.getSentences())
        {
            ArrayList<Phrase> phrases = s.copy();
            for (Phrase p : phrases)
            {
                ArrayList<Token> tokens = p.copy();
                totalWords += tokens.size();
                for (Token t : tokens)
                {
                    totalWordLength += t.getToken().length();
                }
            }
        }

        return (double) totalWordLength / totalWords;
    }

    /**
     * Gets the ratio of the number of unique words to total words in the document
     * @return the type-token ratio as a double
     */
    public double getTypeTokenRatio()
    {
        Set<String> uniqueWords = new HashSet<String>();
        int totalWords = 0;
        for (Sentence s : d.getSentences())
        {
            ArrayList<Phrase> phrases = s.copy();
            for (Phrase p : phrases)
            {
                ArrayList<Token> tokens = p.copy();
                totalWords += tokens.size();
                for (Token t : tokens)
                {
                    String word = t.getToken();
                    word.toLowerCase();
                    uniqueWords.add(word);
                }
            }
        }
        int numberOfUniqueWords = uniqueWords.size();
        return (double) numberOfUniqueWords / (double) totalWords;
    }

    /**
     * Gets the Hapax-Legomana ratio of the mystery document
     * @return the Hapax-Legomana ratio as a double
     */
    public double getHapaxLegomana()
    {
        Set<String> appearedOnce = new HashSet<String>();
        Set<String> appearedMoreThanOnce = new HashSet<String>();
        int totalWords = 0;
        for (Sentence s : d.getSentences())
        {
            ArrayList<Phrase> phrases = s.copy();
            for (Phrase p : phrases)
            {
                ArrayList<Token> tokens = p.copy();
                totalWords += tokens.size();
                for (Token t : tokens)
                {
                    String word = t.getToken().toLowerCase();
                    if (!appearedOnce.contains(word)
                            && !appearedMoreThanOnce.contains(word))
                    {
                        appearedOnce.add(word);
                    } else if (appearedOnce.contains(word))
                    {
                        appearedMoreThanOnce.add(word);
                        appearedOnce.remove(word);
                    }
                }
            }
        }
        int oneAppearance = appearedOnce.size();
        double answer = (double) oneAppearance / (double) totalWords;
        return answer;
    }

    /**
     * Gets the average number of words per sentence
     * @return the average number of words per sentence as a double
     */
    public double getAverageWordsPerSentence()
    {
        int totalWords = 0;
        int numberOfSentences = d.getSentences().size();
        for (Sentence s : d.getSentences())
        {
            ArrayList<Phrase> phrases = s.copy();
            for (Phrase p : phrases)
            {
                ArrayList<Token> tokens = p.copy();
                totalWords += tokens.size();
            }
        }
        return (double) totalWords / (double) numberOfSentences;
    }

    /**
     * returns the average number of phrases per sentence, as a double
     * @return the average sentence complexity
     */
    public double getSentenceComplexity()
    {
        int totalPhrases = 0;
        int totalSentences = d.getSentences().size();
        for (Sentence s : d.getSentences())
        {
            ArrayList<Phrase> phrases = s.copy();
            totalPhrases += phrases.size();
        }
        return (double) totalPhrases / (double) totalSentences;
    }

    /**
     * Finds the author of the mystery document
     * @return the name of the author who most likely wrote the document as a string
     * @throws IOException
     */
    public String findAuthor() throws IOException
    {
        ArrayList<Double> docStats = new ArrayList<Double>(Arrays.asList(getAverageWordLength(),
                getTypeTokenRatio(), getHapaxLegomana(),
                getAverageWordsPerSentence(), getSentenceComplexity()));
        ArrayList<Double> answers = new ArrayList<Double>();
        for (int i = 0; i < stats.size(); i++)
        {
            BufferedReader br = new BufferedReader(new FileReader(stats.get(i)));
            br.readLine();
            ArrayList<Double> answer = new ArrayList<Double>();
            for (int j = 0; j < 5; j++)
            {
                String stat = br.readLine();
                double val = Double.parseDouble(stat);
                answer.add(val);
            }

            double total = 0;
            for (int k = 0; k < answer.size(); k++)
            {
                total += (Math.abs(docStats.get(k) - answer.get(k))) * weights[k];
            }
            answers.add(total);
        }
        int indexOfSmallest = 0;
        for (int index = 0; index < answers.size(); index++)
        {
            if (answers.get(index) < answers.get(indexOfSmallest))
            {
                indexOfSmallest = index;
            }
        }
        BufferedReader br = new BufferedReader(new FileReader(
                stats.get(indexOfSmallest)));
        return br.readLine();
    }
}
