package binary;
import java.util.Random;

public class Node implements Cloneable {
    private Node left;
    private Node right;
    private Op operation;

    protected int depth;

    public Node(Unop operation) {
        this.operation = operation;
        this.left = null;
        this.right = null;
        this.depth = 0; 
    }

    public Node(Binop operation, Node left, Node right) {
        this.operation = operation;
        this.left = left;
        this.right = right;
        this.depth = 0;
    }

    public Node(Binop operation) {
        this.operation = operation;
        this.left = null;
        this.right = null;
        this.depth = 0;
    }

    public double eval(double[] values) {
        if (operation instanceof Unop) {
            return ((Unop) operation).eval(values);
        } 
        else if (operation instanceof Binop) {
            double l = (left == null) ? 0.0 : left.eval(values);
            double r = (right == null) ? 0.0 : right.eval(values);
            return ((Binop) operation).eval(l, r);
        } 
        else {
            System.err.println("Error: operation is not a Unop or a Binop!");
            return 0.0;
        }
    }

    public void addRandomKids(NodeFactory nf, int maxDepth, Random rand) {
        if (this.depth >= maxDepth) {
            this.left  = nf.getTerminal(rand);
            this.right = nf.getTerminal(rand);
            this.left.depth  = this.depth + 1;
            this.right.depth = this.depth + 1;
            return;
        }

        int totalChoices = nf.getNumOps() + nf.getNumIndepVars(); 
        int choiceLeft = rand.nextInt(totalChoices + 1); 
        if (choiceLeft < nf.getNumOps()) {
            this.left = nf.getOperator(rand);
            this.left.depth = this.depth + 1;
            this.left.addRandomKids(nf, maxDepth, rand);
        } 
        else {
            this.left = nf.getTerminal(rand);
            this.left.depth = this.depth + 1;
        }

        int choiceRight = rand.nextInt(totalChoices + 1);
        if (choiceRight < nf.getNumOps()) {
            this.right = nf.getOperator(rand);
            this.right.depth = this.depth + 1;
            this.right.addRandomKids(nf, maxDepth, rand);
        } 
        else {
            this.right = nf.getTerminal(rand);
            this.right.depth = this.depth + 1;
        }
    }

    @Override
    public String toString() {
        if (operation instanceof Unop) {
            return operation.toString();
        }
        else if (operation instanceof Binop) {
            String leftText  = (left  == null) ? "?" : left.toString();
            String rightText = (right == null) ? "?" : right.toString();
            return "(" + leftText + " " + operation.toString() + " " + rightText + ")";
        }
        else {
            return "(error)";
        }
    }

    @Override
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } 
        catch (CloneNotSupportedException e) {
            System.out.println("Node can't clone.");
        }
        Node copy = (Node) o;

        if (this.left != null) {
            copy.left = (Node) this.left.clone();
        }
        if (this.right != null) {
            copy.right = (Node) this.right.clone();
        }

        if (this.operation != null) {
            copy.operation = (Op) this.operation.clone();
        }

        copy.depth = this.depth;

        return copy;
    }
}
