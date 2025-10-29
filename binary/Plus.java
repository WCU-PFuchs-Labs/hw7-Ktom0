package binary;
import java.util.Random; 

 public class Plus extends Binop {
     
    @Override
    public double eval(double left, double right) {
        return left + right;
    }

   @Override
   public String toString() {
        return "+"; 
    }
 }
