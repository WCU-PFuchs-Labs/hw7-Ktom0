package binary;

import java.util.ArrayList;
import java.util.Random;

public class Collector {
    private final int numIndepVars;
    private final int maxDepth;
    private final ArrayList<Node> trees;

    public Collector(int numIndepVars, int maxDepth) {
        this.numIndepVars = Math.max(0, numIndepVars);
        this.maxDepth = Math.max(1, maxDepth);
        this.trees = new ArrayList<Node>();
    }

    public void collect(int howMany, Random r) {
        if (r == null) r = new Random();
        int made = 0;
        while (made < howMany) {
            GPTree gpt = new GPTree(numIndepVars, maxDepth, r);
            Node rootCopy = gpt.getRoot(); 
            trees.add(rootCopy);
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

    public void evaluateAll(double[] values) {
        if (trees == null || trees.size() == 0) {
            System.out.println("(no trees stored)");
            return;
        }
        for (int i = 0; i < trees.size(); i++) {
            Node n = trees.get(i);
            double v = n.eval(values);
            System.out.println(n.toString() + " = " + v);
        }
    }

    public int size() {
        return trees.size();
    }
}
