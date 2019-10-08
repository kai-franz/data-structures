public class Tester {
    public static void main(String[] args)
    {
        TreeNode tree = TreeUtil.createRandom(6);
        TreeDisplay display = new TreeDisplay();
        display.displayTree(tree);
        System.out.println(TreeUtil.maxDepth(tree));
        System.out.println(TreeUtil.leftmost(tree));
        System.out.println(TreeUtil.rightmost(tree));
    }
}
