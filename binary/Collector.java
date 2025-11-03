package binary;
import java.util.ArrayList;
import java.util.Random;

public class Collector {
    public static String collectedString = "";

    private final int numIndepVars;
    private final int maxDepth;

    private final ArrayList<Binop> binops;

    public Collector(int numIndepVars, int maxDepth) {
        this.numIndepVars = Math.max(0, numIndepVars);
        this.maxDepth = Math.max(1, maxDepth);
        this.binops = new ArrayList<Binop>();
    }

    public static void clearCollected() {
        collectedString = "";
    }

    public void add(Node n) {
        if (n == null) return;

        Op op = n.getOperation();
        if (op instanceof Binop) {
            binops.add((Binop) op);

            if (collectedString.length() > 0) collectedString += " ";
            collectedString += op.toString();   
        }
    }

    public void collect(int howMany, Random r) {
        if (r == null) r = new Random();
        int made = 0;
        while (made < howMany) {
            GPTree gpt = new GPTree(numIndepVars, maxDepth, r);
            made++;
        }
    }

    public int size() {
        return binops.size();
    }
}
