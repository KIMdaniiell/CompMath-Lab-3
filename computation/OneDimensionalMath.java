package computation;


import io.IOManager;

public class OneDimensionalMath {
    private static final String[] stringRepresentation = {
            "1/x",
            "sin(x) / x",
            "0.5x^3 + 2x^2 + x + 2",
            "5x",
            "-5x",
    };
    private int equationNumber;

    public OneDimensionalMath(int equationNumber){
        this.equationNumber = equationNumber;
    }

    public double f(double x) {
        switch (equationNumber) {
            case 1:
                return f1(x);
            case 2:
                return f2(x);
            case 3:
                return f3(x);
            case 4:
                return f4(x);
            case 5:
                return f5(x);
            default:
                return 0;
        }
    }
    public double df(double x) {
        switch (equationNumber) {
            case 1:
                return df1(x);
            case 2:
                return df2(x);
            case 3:
                return df3(x);
            case 4:
                return df4(x);
            case 5:
                return df5(x);
            default:
                return 0;
        }
    }
    public double ddf(double x) {
        switch (equationNumber) {
            case 1:
                return ddf1(x);
            case 2:
                return ddf2(x);
            case 3:
                return ddf3(x);
            case 4:
                return ddf4(x);
            case 5:
                return ddf5(x);
            default:
                return 0;
        }
    }

    private double f1( double x ){ return  1/x;}
    private double f2( double x ){ return  Math.sin(x)/x;}
    private double f3( double x ){ return  0.5*Math.pow(x,3)+2*Math.pow(x,2)+x+2;}
    private double f4( double x ){ return  5*x;}
    private double f5( double x ){ return  -5*x;}

    private double df1( double x ){ return  -1/Math.pow(x,2);}
    private double df2( double x ){ return  Math.cos(x)/x - Math.sin(x)/Math.pow(x,2);}
    private double df3( double x ){ return  1.5*Math.pow(x,2)+4*x+1;}
    private double df4( double x ){ return  5;}
    private double df5( double x ){ return  -5;}

    private double ddf1( double x ){ return  2/Math.pow(x,3);}
    private double ddf2( double x ){ return  ( -1*Math.sin(x) - 2*Math.cos(x)/x + 2*Math.sin(x)/Math.pow(x,2) )/x;}
    private double ddf3( double x ){ return  3*x+4;}
    private double ddf4( double x ){ return  0;}
    private double ddf5( double x ){ return  0;}

    public static String[] getStringRepresentation() {
        return stringRepresentation;
    }

}
