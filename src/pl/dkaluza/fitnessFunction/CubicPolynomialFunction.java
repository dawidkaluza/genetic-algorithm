package pl.dkaluza.fitnessFunction;

public class CubicPolynomialFunction implements FitnessFunction {
    private final int a;
    private final int b;
    private final int c;
    private final int d;

    public CubicPolynomialFunction(int a, int b, int c, int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double calc(double x) {
        return a * Math.pow(x, 3) + b * Math.pow(x, 2) + c * x + d;
    }
}
