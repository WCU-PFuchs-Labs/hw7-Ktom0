import binary.*;
import java.util.Random;

public class TestRefactored {

    static int numIndepVars = 3;  
    static int maxDepth = 5;  
    static Random rand = new Random();

    public static void main(String[] args) {
        double[] data = new double[numIndepVars];
        data[0] = 3.14;
        data[1] = 2.78;
        data[2] = 1.00;

        Binop[] ops = { new Plus(), new Minus(), new Mult(), new Divide() };

        NodeFactory factory = new NodeFactory(ops, numIndepVars);

        Node root = factory.getOperator(rand);
        root.addRandomKids(factory, maxDepth, rand);

        String exprText = root.toString();
        double value = root.eval(data);
        System.out.println(exprText + " = " + String.format("%.2f", value));
    }
}
