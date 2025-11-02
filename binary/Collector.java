package binary;
import java.util.ArrayList;
import java.util.Random;

public class Collector {

    public static String collectedString = "";

    public static Collector lastUsed = null;

    private final int numIndepVars;
    private final int maxDepth;
    private final ArrayList<Node> trees;

    public Collector(int numIndepVars, int maxDepth) {
        this.numIndepVars = Math.max(0, numIndepVars);
        this.maxDepth = Math.max(1, maxDepth);
        this.trees = new ArrayList<Node>();
    }

    public void add(Node n) {
        if (n != null) {
            trees.add(n);

            if (collectedString == null) {
                collectedString = "";
            }
            collectedString = collectedString + n.toString() + " ";
        }
    }

    public void collect(int howMany, Random r) {
        if (r == null) {
            r = new Random();
        }
        int made = 0;
        while (made < howMany) {
            GPTree gpt = new GPTree(numIndepVars, maxDepth, r);
            Node root  = gpt.getRoot();
            trees.add(root);
            made = made + 1;
        }
    }

    public ArrayList<Node> getTrees() {
        ArrayList<Node> copy = new ArrayList<Node>();
        for (int i = 0; i < trees.size(); i++) {
            copy.add(trees.get(i));
        }
        return copy;
    }

    public int size() {
        return trees.size();
    }
}
