package binary;
import java.util.Random; 

public class Variable extends Unop {

    private int index;

    public Variable(int i) {
        this.index = i;
    }

    @Override
    public double eval(double[] values) {

        if (values == null) {
            System.out.println("Error: values array is null!");
            return 0.0;
        }

        if (index < 0) {
            System.out.println("Error: variable index is negative (" + index + ")");
            return 0.0;
        }

        if (index >= values.length) {
            System.out.println("Error: variable index " + index + " is too large!");
            return 0.0;
        }

        double result = 0.0;
        for (int i = 0; i < values.length; i++) {
            if (i == index) {
                result = values[i];
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return "X" + index;
    }
}
