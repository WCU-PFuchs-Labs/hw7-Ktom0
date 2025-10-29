package binary;

public class Divide extends Binop {

    private static final double EPS = 0.0001;

    @Override
    public double eval(double left, double right) {
        double denominator = right;

        double magnitude = Math.abs(denominator);
        boolean tooSmall = (magnitude < EPS);

        if (tooSmall) {
            double safeFallback = 1.0;
            return safeFallback;
        } 
        else {
            double quotient = left / denominator;
            
            if (Double.isInfinite(quotient)) {
                return 1.0;
            } 
            else {
                return quotient;
            }
        }
    }

    @Override
    public String toString() {
        return "/";
    }
}
