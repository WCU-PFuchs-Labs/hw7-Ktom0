package binary;
import java.util.Random;

public class NodeFactory {

    private Node[] currentOps;

    private int numIndepVars;

    public NodeFactory(Binop[] binops, int numVars) {
        this.numIndepVars = numVars;

        if (binops == null) {
            this.currentOps = new Node[0];
        } 
        else {
            this.currentOps = new Node[binops.length];
            for (int i = 0; i < binops.length; i++) {
                this.currentOps[i] = new Node(binops[i]);
            }
        }
    }

    public int getNumOps() {
        return currentOps.length;
    }

    public int getNumIndepVars() {
        return numIndepVars;
    }

    public Node getOperator(Random rand) {
        if (currentOps.length == 0) {
            return new Node(new Plus());
        }
        int pick = rand.nextInt(currentOps.length); 
        Node template = currentOps[pick];
        return (Node) template.clone();
    }

    public Node getTerminal(Random rand) {
        int roll = rand.nextInt(numIndepVars + 1); 
        if (roll == numIndepVars) {
            double d = rand.nextDouble(); 
            return new Node(new Const(d));
        } 
        else {
            return new Node(new Variable(roll));
        }
    }
}
