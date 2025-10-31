package binary;
import java.util.Random;

public class GPTree {
    private final int numIndepVars;
    private final int maxDepth;
    private final Random rand;
    private Node root;

    public GPTree(int numIndepVars, int maxDepth, Random rand) {
        this.numIndepVars = Math.max(0, numIndepVars);
        this.maxDepth = Math.max(1, maxDepth);
        this.rand = (rand == null) ? new Random() : rand;
        this.root = build(this.maxDepth);
    }

    private Node build(int depth) {
        if (depth <= 1) {
            return makeRandomLeaf();
        }
        Node left = build(depth - 1);
        Node right = build(depth - 1);

        Binop op = randomOp();
        return new Node(op, left, right);
    }

    private Node makeRandomLeaf() {
        boolean pickVar = rand.nextBoolean();
        if (pickVar && numIndepVars > 0) {
            int idx = rand.nextInt(numIndepVars); 
            return new Node(new Variable(idx));
        } 
        else {
            int n = rand.nextInt(20) + 1; 
            return new Node(new Const(n));
        }
    }

    private Binop randomOp() {
        int k = rand.nextInt(4);
        if (k == 0) return new Plus();
        if (k == 1) return new Minus();
        if (k == 2) return new Mult();
        return new Divide();
    }

    public Node getRoot() {
        return root;
    }

    @Override
    public String toString() {
        return (root == null) ? "(empty)" : root.toString();
    }

    public void traverse(Collector c) {
        if (root != null && c != null) {
            root.traverse(c);  
        } 
        else {
            System.out.println("Warning: GPTree has no root or collector is null!");
        }
    }

    public String getCrossNodes() {
        return Collector.collectedString;
    }
}
