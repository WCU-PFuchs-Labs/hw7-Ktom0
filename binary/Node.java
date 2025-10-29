package binary;
import java.util.Random;

public class Node implements Cloneable {

    private Node left;
    private Node right;

    private Op operation;
    protected int depth = 0;

    public Node(Unop operation) {
        this.operation = operation;
        this.left = null;
        this.right = null;
    }

    public Node(Binop operation, Node left, Node right) {
        this.operation = operation;
        this.left = left;
        this.right = right;
    }

    public Node(Binop operation) {
        this.operation = operation;
        this.left = null;
        this.right = null;
    }

    public Op getOperation() {
        return this.operation;
    }

    public double eval(double[] values) {
        if (operation instanceof Unop) {
            Unop asUnop = (Unop) operation;
            double answer = asUnop.eval(values);
            return answer; 
        }
        else if (operation instanceof Binop) {
            Binop asBinop = (Binop) operation;

            double leftValue = 0.0;
            if (left != null) {
                leftValue = left.eval(values);
            } 
            else {
                leftValue = 0.0;
            }

            double rightValue = 0.0;
            if (right != null) {
                rightValue = right.eval(values);
            }
            else {
                rightValue = 0.0;
            }

            double result = asBinop.eval(leftValue, rightValue);
            return result;
        }
        else {
            System.err.println("Error: operation is not a Unop or a Binop!");
            double fallback = 0.0;
            return fallback;
        }
    }

    @Override
    public String toString() {
        if (operation instanceof Unop) {
            return operation.toString();
        }
        else if (operation instanceof Binop) {
            String leftText;
            if (left == null) {
                leftText = "?";
            } 
            else {
                leftText = left.toString();
            }

            String rightText;
            if (right == null) {
                rightText = "?";
            } 
            else {
                rightText = right.toString();
            }

            String middle = operation.toString();
            String finalText = "(" + leftText + " " + middle + " " + rightText + ")";
            return finalText;
        }
        else {
            return "(error)";
        }
    }

    public void traverse(Collector c) {
        c.collect(this);

        if (left != null) {
            left.traverse(c);
        }

        if (right != null) {
            right.traverse(c);
        }
    }

    public void swapLeft(Node trunk) {
        Node temp = this.left;
        this.left = trunk.left;
        trunk.left = temp;
    }

    public void swapRight(Node trunk) {
        Node temp = this.right;
        this.right = trunk.right;
        trunk.right = temp;
    }

    public boolean isLeaf() {
        if (operation instanceof Unop) {
            return true;
        } 
        else {
            return false;
        }
    }

    public void addRandomKids(NodeFactory nf, int maxDepth, Random rand) {
        if (this.isLeaf()) {
            return;
        }
        if (this.depth >= maxDepth) {
            Node leftTerminal = nf.getTerminal(rand);
            leftTerminal.depth = this.depth + 1;
            this.left = leftTerminal;

            Node rightTerminal = nf.getTerminal(rand);
            rightTerminal.depth = this.depth + 1;
            this.right = rightTerminal;

            return;
        }

        int totalChoicesLeft = nf.getNumOps() + nf.getNumIndepVars();
        int pickLeft = rand.nextInt(totalChoicesLeft); 

        if (pickLeft < nf.getNumOps()) {
            Node leftOperator = nf.getOperator(rand);
            leftOperator.depth = this.depth + 1;
            this.left = leftOperator;

            this.left.addRandomKids(nf, maxDepth, rand);
        } 
        else {
            Node leftTerminal = nf.getTerminal(rand);
            leftTerminal.depth = this.depth + 1;
            this.left = leftTerminal;
        }

        int totalChoicesRight = nf.getNumOps() + nf.getNumIndepVars();
        int pickRight = rand.nextInt(totalChoicesRight);

        if (pickRight < nf.getNumOps()) {
            Node rightOperator = nf.getOperator(rand);
            rightOperator.depth = this.depth + 1;
            this.right = rightOperator;

            this.right.addRandomKids(nf, maxDepth, rand);
        } 
        else {
            Node rightTerminal = nf.getTerminal(rand);
            rightTerminal.depth = this.depth + 1;
            this.right = rightTerminal;
        }
    }

    @Override
    public Object clone() {
        Object raw = null;

        try {
            raw = super.clone();    
        } 
        catch (CloneNotSupportedException e) {
            System.out.println("Node can't clone.");
            return null;
        }

        Node copy = (Node) raw;

        if (this.operation != null) {
            Op opCopy = (Op) this.operation.clone();
            copy.operation = opCopy;
        } 
        else {
            copy.operation = null;
        }

        if (this.left != null) {
            Node leftCopy = (Node) this.left.clone();
            copy.left = leftCopy;
        } 
        else {
            copy.left = null;
        }

        if (this.right != null) {
            Node rightCopy = (Node) this.right.clone();
            copy.right = rightCopy;
        } 
        else {
            copy.right = null;
        }

        copy.depth = this.depth;

        return copy;
    }
}
