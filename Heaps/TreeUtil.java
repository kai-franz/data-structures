import java.util.*;
/**
 * TreeUtil contains the following methods for manipulating binary trees:
 * leftmost, rightmost, maxDepth, createRandom, countNodes, countLeaves, preOrder, postOrder, inOrder, fillList,
 * saveTree, buildTree, loadTree, copy, sameShape, createDecodingTree, insertMorse, decodeMorse, eval
 * @author Kai Franz
 * @version January 11, 2018
 *
 */
public class TreeUtil
{
    //used to prompt for command line input
    private static Scanner in = new Scanner(System.in);

    private static final boolean debug = false;


    /**
     * Returns the value of the left-most node in the tree.
     * @param t the tree to be used
     * @return the value of the left-most tree node
     */
    public static Object leftmost(TreeNode t)
    {
        if (t == null) return null;
        if (t.getLeft() == null) return t.getValue();
        return leftmost(t.getLeft());
    }

    /**
     * Returns the value of the right-most node in the tree.
     * @param t the tree to be used
     * @return the value of the right-most tree node
     */
    public static Object rightmost(TreeNode t)
    {
        if (t == null) return null;
        if (t.getRight() == null) return t.getValue();
        return rightmost(t.getRight());
    }
    /**
     * Returns the max depth of the tree.
     * @param t the tree to be used
     * @return the maximum depth of the tree
     */
    public static int maxDepth(TreeNode t)
    {
        if (t == null) return 0;
        int left = 0;
        int right = 0;
        if (t.getLeft() != null) left = maxDepth(t.getLeft());
        if (t.getRight() != null) right = maxDepth(t.getRight());
        return Math.max(left, right) + 1;
    }

    /**
     * create a random tree of the specified depth.  No attempt to balance the tree
     * is provided.
     * @param depth of the tree
     * @return TreeNode object that points to the generated tree
     */
    public static TreeNode createRandom(int depth)
    {
        if (Math.random() * Math.pow(2, depth) < 1)
            return null;
        return new TreeNode(((int)(Math.random() * 10)),
                createRandom(depth - 1),
                createRandom(depth - 1));
    }
    /**
     * counts the nodes in a given tree.
     * @param t tree to count
     * @return the total number of nodes in the tree
     */
    public static int countNodes(TreeNode t)
    {
        int sum = 0;
        if (t == null) return sum;
        if (t.getLeft() != null) sum += countNodes(t.getLeft());
        if (t.getRight() != null) sum += countNodes(t.getRight());
        return sum + 1;
    }
    /**
     * counts the number of leaves in a given tree.
     * @param t the tree to count
     * @return the number of leaves in the tree
     */
    public static int countLeaves(TreeNode t)
    {
        int sum = 0;
        if (t == null) return sum;
        if (t.getLeft() == null && t.getRight() == null) return 1;
        if (t.getLeft() != null) sum += countLeaves(t.getLeft());
        if (t.getRight() != null) sum += countLeaves(t.getRight());
        return sum;
    }
    /**
     * traverses the tree using preorder traversal.
     * @param t the tree to traverse
     * @param display the tree display to be used
     */
    public static void preOrder(TreeNode t, TreeDisplay display)
    {
        if (t != null)
        {
            display.visit(t);
            preOrder(t.getLeft(), display);
            preOrder(t.getRight(), display);
        }
    }

    /**
     * traverses the tree using post order traversal.
     * @param t the tree to traverse
     * @param display the tree display to be used
     */
    public static void postOrder(TreeNode t, TreeDisplay display)
    {
        if (t != null)
        {
            postOrder(t.getLeft(), display);
            postOrder(t.getRight(), display);
            display.visit(t);
        }
    }

    /**
     * traverses the tree using in order traversal.
     * @param t the tree to traverse
     * @param display the tree display to be used
     */
    public static void inOrder(TreeNode t, TreeDisplay display)
    {
        if (t != null)
        {
            inOrder(t.getLeft(), display);
            display.visit(t);
            inOrder(t.getRight(), display);
        }
    }

    /**
     * Fills a list with the given tree, using "$" for null pointers.
     * @param t the tree to fill the list with
     * @param list the list to fill
     */
    public static void fillList(TreeNode t, List<String> list)
    {
        list.add(t.getValue().toString());
        if (t.getLeft() == null)
        {
            list.add("$");
        }
        else
        {
            fillList(t.getLeft(), list);
        }
        if (t.getRight() == null)
        {
            list.add("$");
        }
        else
        {
            fillList(t.getRight(), list);
        }

    }
    /**
     * saveTree uses the FileUtil utility class to save the tree rooted at t
     * as a file with the given file name
     * @param fileName is the name of the file to create which will hold the data
     *        values in the tree
     * @param t is the root of the tree to save
     */
    public static void saveTree(String fileName, TreeNode t)
    {
        List<String> list = new ArrayList<>();
        fillList(t, list);
        FileUtil.saveFile(fileName, list.iterator());
    }
    /**
     * buildTree takes in an iterator which will iterate through a valid description of
     * a binary tree with String values.  Null nodes are indicated by "$" markers
     * @param it the iterator which will iterate over the tree description
     * @return a pointer to the root of the tree built by the iteration
     */
    public static TreeNode buildTree(Iterator<String> it)
    {
        TreeNode t = null;
        if (it.hasNext())
        {
            String s = it.next();
            System.out.println(s);
            if (!s.equals("$"))
            {
                t = new TreeNode(s);
                t.setLeft(buildTree(it));
                t.setRight(buildTree(it));
            }
        }
        return t;
    }
    /**
     * read a file description of a tree and then build the tree
     * @param fileName is a valid file name for a file that describes a binary tree
     * @return a pointer to the root of the tree
     */
    public static TreeNode loadTree(String fileName)
    {
        return buildTree(FileUtil.loadFile(fileName));
    }
    /**
     * utility method that waits for a user to type text into Std Input and then press enter
     * @return the string entered by the user
     */
    private static String getUserInput()
    {
        return in.nextLine();
    }
    /**
     * plays a single round of 20 questions
     * postcondition:  plays a round of twenty questions, asking the user questions as it
     *                 walks down the given knowledge tree, lighting up the display as it goes;
     *                 modifies the tree to include information learned.
     * @param t a pointer to the root of the game tree
     * @param display which will show the progress of the game
     */
    private static void twentyQuestionsRound(TreeNode t, TreeDisplay display)
    {
        throw new RuntimeException("Write ME!");
    }
    /**
     * plays a game of 20 questions
     * Begins by reading in a starting file and then plays multiple rounds
     * until the user enters "quit".  Then the final tree is saved
     */
    public static void twentyQuestions()
    {
        throw new RuntimeException("Write ME!");
    }
    /**
     * copy a binary tree
     * @param t the root of the tree to copy
     * @return a new tree, which is a complete copy
     *         of t with all new TreeNode objects
     *         pointing to the same values as t (in the same order, shape, etc)
     */
    public static TreeNode copy(TreeNode t)
    {
        if (t == null) return null;
        return new TreeNode(t.getValue(), copy(t.getLeft()), copy(t.getRight()));
    }

    /**
     * tests to see if two trees have the same shape, but not necessarily the
     * same values.  Two trees have the same shape if they have TreeNode objects
     * in the same locations relative to the root
     * @param t1 pointer to the root of the first tree
     * @param t2 pointer to the root of the second tree
     * @return true if t1 and t2 describe trees having the same shape, false otherwise
     */
    public static boolean sameShape(TreeNode t1, TreeNode t2)
    {
        if (t1 == null || t2 == null)
        {
            return t1 == t2;
        }
        return sameShape(t1.getLeft(), t2.getLeft()) && sameShape(t1.getRight(), t2.getRight());
    }
    /**
     * Generate a tree for decoding Morse code
     * @param display the display that will show the decoding tree
     * @return the decoding tree
     */
    public static TreeNode createDecodingTree(TreeDisplay display)
    {
        TreeNode tree = new TreeNode("Morse Tree");
        display.displayTree(tree);
        insertMorse(tree, "a", ".-", display);
        insertMorse(tree, "b", "-...", display);
        insertMorse(tree, "c", "-.-.", display);
        insertMorse(tree, "d", "-..", display);
        insertMorse(tree, "e", ".", display);
        insertMorse(tree, "f", "..-.", display);
        insertMorse(tree, "g", "--.", display);
        insertMorse(tree, "h", "....", display);
        insertMorse(tree, "i", "..", display);
        insertMorse(tree, "j", ".---", display);
        insertMorse(tree, "k", "-.-", display);
        insertMorse(tree, "l", ".-..", display);
        insertMorse(tree, "m", "--", display);
        insertMorse(tree, "n", "-.", display);
        insertMorse(tree, "o", "---", display);
        insertMorse(tree, "p", ".--.", display);
        insertMorse(tree, "q", "--.-", display);
        insertMorse(tree, "r", ".-.", display);
        insertMorse(tree, "s", "...", display);
        insertMorse(tree, "t", "-", display);
        insertMorse(tree, "u", "..-", display);
        insertMorse(tree, "v", "...-", display);
        insertMorse(tree, "w", ".--", display);
        insertMorse(tree, "x", "-..-", display);
        insertMorse(tree, "y", "-.--", display);
        insertMorse(tree, "z", "--..", display);
        return tree;
    }
    /**
     * helper method for building a Morse code decoding tree.
     * postcondition:  inserts the given letter into the decodingTree,
     *                 in the appropriate position, as determined by
     *                 the given Morse code sequence; lights up the display
     *                 as it walks down the tree
     * @param decodingTree is the partial decoding tree
     * @param letter is the letter to add
     * @param code is the Morse code for letter
     * @param display is the display that will show progress as the method walks
     *        down the tree
     */
    private static void insertMorse(TreeNode decodingTree, String letter,
                                    String code, TreeDisplay display)
    {
        if (code.equals(""))
        {
            decodingTree.setValue(letter);
            display.visit(decodingTree);
        }
        else if (code.substring(0,1).equals("."))
        {
            if (decodingTree.getLeft() == null)
            {
                decodingTree.setLeft(new TreeNode(""));
            }
            insertMorse(decodingTree.getLeft(), letter, code.substring(1), display);
        }
        else if (code.substring(0,1).equals("-"))
        {
            if (decodingTree.getRight() == null)
            {
                decodingTree.setRight(new TreeNode(""));
            }
            insertMorse(decodingTree.getRight(), letter, code.substring(1), display);
        }
    }


    /**
     * decodes Morse code by walking the decoding tree according to the input code
     * @param decodingTree is the Morse code decoding tree
     * @param cipherText is Morse code consisting of dots, dashes, and spaces
     * @param display is the display object that will show the decoding progress
     * @return the string represented by cipherText
     */
    public static String decodeMorse(TreeNode decodingTree, String cipherText, TreeDisplay display)
    {
        String str = cipherText;
        String result = "";
        if (str.contains(" "))
        {
            while (str.contains(" "))
            {
                result += decodeMorse(decodingTree, str.substring(0, str.indexOf(" ")), display);
                str = str.substring(str.indexOf(" ") + 1);
            }
            return result + decodeMorse(decodingTree, str, display);
        }
        if (cipherText.equals(""))
        {
            display.visit(decodingTree);
            return decodingTree.getValue().toString();
        }
        if (cipherText.substring(0, 1).equals("."))
        {
            return decodeMorse(decodingTree.getLeft(), cipherText.substring(1), display);
        }
        else
        {
            return decodeMorse(decodingTree.getRight(), cipherText.substring(1), display);
        }
    }

    /**
     * Evaluates a given expression tree.
     * @param expTree the expression tree to evaluate
     * @return the value of expression
     */
    public static int eval(TreeNode expTree)
    {
        if (expTree.getValue().equals("*"))
        {
            return eval(expTree.getLeft()) * eval(expTree.getRight());
        }
        else if (expTree.getValue().equals("+"))
        {
            return eval(expTree.getLeft()) + eval(expTree.getRight());
        }
        else
            return (int) expTree.getValue();
    }
    /**
     * optional work
     */
    public static TreeNode createExpressionTree(String exp)
    {
        throw new RuntimeException("Write ME!");
    }

    /**
     * debug printout
     * postcondition: out is printed to System.out
     * @param out the string to send to System.out
     */

    private static void debugPrint(String out)
    {
        if(debug) System.out.println("debug: " + out);
    }
}
