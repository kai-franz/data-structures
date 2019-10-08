import java.util.Iterator;

/**
 * Class for a set that stores data in a binary search tree.
 *
 * @author Kai Franz
 * @version January 6, 2018
 */
public class MyTreeSet<E>
{
	private TreeNode root;
	private int size;
	private TreeDisplay display;

	/**
	 * Constructor for objects of MyTreeSet.
	 */
	public MyTreeSet()
	{
		root = null;
		size = 0;
		display = new TreeDisplay();

		//wait 1 millisecond when visiting a node
		display.setDelay(1);
	}

	/**
	 * Returns the size of the tree set.
	 * @return the size of the tree set
	 */
	public int size()
	{
		return size;
	}

	public boolean contains(Comparable obj) {
	    return BSTUtilities.contains(root, obj, display);
    }

	/**
	 * 	if obj is not present in this set, adds obj and
	 *  returns true; otherwise returns false
	 * @param obj object to add
	 * @return true if obj is not already present;
	 * 	       otherwise, false
	 */
	public boolean add(Comparable obj)
	{
	    if (contains(obj))
        {
            return false;
        }
        root = BSTUtilities.insert(root, obj, display);
        size++;
	    return true;
	}

	/**
	 * 	if obj is not present in this set, removes obj and
	 *  returns true; otherwise returns false
	 * @param obj object to remove
	 * @return true if obj is already present;
	 * 	       otherwise, false
	 */
	public boolean remove(Comparable obj)
	{
	    if (contains(obj))
        {
            root = BSTUtilities.delete(root, obj, display);
            size--;
            return true;
        }
        return false;
	}

	/**
	 * Converts the set to a string.
	 * @return the set in string form
	 */
	public String toString()
	{
	    if (root == null) return "[]";
		return "[" + toString(root).substring(0, toString(root).length() - 2) + "]";
	}

	/**
	 * Converts a tree to string
	 * @param t tree to convert
	 * @return string form of tree
	 */
	private String toString(TreeNode t)
	{
		if (t == null)
			return "";
		String str = "";
		if (t.getLeft() != null)  str += toString(t.getLeft());
		str += t.getValue() + ", ";
        if (t.getRight() != null)  str += toString(t.getRight());
        return str;
	}
}