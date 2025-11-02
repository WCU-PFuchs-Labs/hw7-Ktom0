import binary.*;
import java.util.Random;

public class TestGPTree {
    public static void main(String[] args) {

        Binop[] ops = { new Plus(), new Minus(), new Mult(), new Divide() };
        int numIndepVars = 3;
        NodeFactory nf = new NodeFactory(ops, numIndepVars);

        Random r = new Random();

        int maxDepth = 3;

        GPTree t1 = new GPTree(nf, maxDepth, r);
        GPTree t2 = new GPTree(nf, maxDepth, r);

        Collector c1 = new Collector(numIndepVars, maxDepth);
        t1.traverse(c1);

        Collector c2 = new Collector(numIndepVars, maxDepth);
        t2.traverse(c2);

        System.out.println("After crossover");
    }
}
