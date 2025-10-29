import binary.*;
import java.util.ArrayList;
import java.util.Random;

public class TestGPTree {
    public static void main(String[] args) {
        double[] valuesA = {1.0, 2.0, 3.0};
        double[] valuesB = {2.0, 1.0, 0.0};

        int numIndepVars = 3; 
        int maxDepth = 3;
        int howMany = 2;   

        Random rng = new Random();

        Collector collector = new Collector(numIndepVars, maxDepth);
        collector.collect(howMany, rng);

        ArrayList<Node> trees = collector.getTrees();

        for (int i = 0; i < trees.size(); i++) {
            Node expr = trees.get(i);

            System.out.println("When {X0,X1,X2} = {" + valuesA[0] + "," + valuesA[1] + "," + valuesA[2] + "}:");
            System.out.println(expr.toString() + " = " + expr.eval(valuesA));
            System.out.println();

            System.out.println("When {X0,X1,X2} = {" + valuesB[0] + "," + valuesB[1] + "," + valuesB[2] + "}:");
            System.out.println(expr.toString() + " = " + expr.eval(valuesB));
            System.out.println();
        }
    }
}
