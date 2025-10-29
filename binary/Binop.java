package binary;
import java.util.Random; 

public abstract class Binop extends Op {
     public abstract double eval(double left, double right);

     @Override
    public String toString() {
        return "(binary operation)";
    }
}
