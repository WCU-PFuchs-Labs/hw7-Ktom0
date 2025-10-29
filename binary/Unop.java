package binary;
import java.util.Random; 

public abstract class Unop extends Op {
    public abstract double eval(double[] values);
     
     @Override
    public String toString() {
        return "(unary operation)";
    }
}
