/**
 * a collection of static methods for operating on binary search trees
 * @author Kai Franz
 * @version January 6, 2018
 */
public abstract class BSTUtilities
{
    /**
     * Checks if the given tree contains the object x
     * Runs in O(h) time
     * @param t binary search tree to check
     * @param x object to search for
     * @param display TreeDisplay object
     * @return true if t contains x;
     *         otherwise, false
     */
	public static boolean contains(TreeNode t, Comparable x, TreeDisplay display)
    {
		if (t == null) return false;
		if (x.compareTo(t.getValue()) == 0) {
			display.visit(t);
			return true;
		}
		if (x.compareTo(t.getValue()) < 0) return contains(t.getLeft(), x, display);
		else return contains(t.getRight(), x, display);
	}

    /**
     * Inserts the given value into the binary search tree rooted at t.
     * precondition:  t is a binary search tree in ascending order
     * postcondition: if t is empty, returns a new tree containing x;
     *                otherwise, returns t, with x having been inserted
     *                at the appropriate position to maintain the binary
     *                search tree property; x is ignored if it is a
     *                duplicate of an element already in t; only one new
     *                TreeNode is created in the course of the traversal
     * Runs in O(h) time
     * @param t root of tree to insert from
     * @param x value to be inserted
     * @param display tree display to use
     * @return a pointer to the TreeNode that has been inserted
     */
	public static TreeNode insert(TreeNode t, Comparable x, TreeDisplay display)
	{
		if (t == null)
		{
            return new TreeNode(x);
        }

        display.visit(t);

        if (x.compareTo(t.getValue()) > 0)
        {
            t.setRight(insert(t.getRight(), x, display));
        }

        else if (x.compareTo(t.getValue()) < 0)
        {
            t.setLeft(insert(t.getLeft(), x, display));
        }


        return t;
	}

    /**
     * Deletes the node t.
     * precondition:  t is a binary search tree in ascending order
     * postcondition: returns a pointer to a binary search tree,
     *               in which the value at node t has been deleted
     *               (and no new TreeNodes have been created)
     *
     * @param t node to be deleted
     * @param display tree display to use
     * @return a pointer to the resulting tree
     */
	private static TreeNode deleteNode(TreeNode t, TreeDisplay display)
	{
	    if (t == null) return null;

	    display.visit(t);

	    if (t.getRight() == null) return t.getLeft();

        if (t.getRight().getLeft() == null)
        {
            t.getRight().setLeft(t.getLeft());
            t = t.getRight();
        }

        else
        {
            TreeNode successorParent = t.getRight();
            while (successorParent.getLeft().getLeft() != null) successorParent = successorParent.getLeft();
            t.setValue(successorParent.getLeft().getValue());
            successorParent.setLeft(deleteNode(successorParent.getLeft(), display));
        }

        return t;

	}

    /**
     * Deletes the node containing x from the tree.
     * precondition:  t is a binary search tree in ascending order
     * postcondition: returns a pointer to a binary search tree,
     *               in which the value x has been deleted (if present)
     *               (and no new TreeNodes have been created)
     *
     * @param t root of the tree to delete from
     * @param x the value to delete from the tree
     * @param display tree display to use
     * @return the node that has been deleted
     */
	public static TreeNode delete(TreeNode t, Comparable x, TreeDisplay display)
	{
        if (t == null) return null;

        display.visit(t);

	    if (x.compareTo(t.getValue()) == 0)
        {
            return deleteNode(t, display);
        }

	    else if (x.compareTo(t.getValue()) > 0)
        {
            t.setRight(delete(t.getRight(), x, display));
        }

        else if (x.compareTo(t.getValue()) < 0)
        {
            t.setLeft(delete(t.getLeft(), x, display));
        }

        return t;

	}
}