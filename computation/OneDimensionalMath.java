package computation;


import io.IOManager;

public class OneDimensionalMath {
    private static final String[] stringRepresentation = {
            "1/x",  // функция и ее производные не определены в точке x = 0
            "sin(x) / x", // функция и ее производные содержат устранимый разрыв в x = 0
            "0.5x^3 + 2x^2 + x + 2",
            "5x",
            "-5x",
    };
    private int functionNumber;

    public OneDimensionalMath(int equationNumber){
        this.functionNumber = equationNumber;
    }

    public double f(double x) {
        switch (functionNumber) {
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
        switch (functionNumber) {
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
        switch (functionNumber) {
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

    public double getMaxdF(double a, double b){
        switch (functionNumber) {
            case 1:
                if (a>0 && b >0)
                    return Math.abs(df(a));
                else
                    return Math.abs(df(b));
            case 2:
                return 0.437;   //0.437 - оценка сверху, максимум не просто вычислить
            case 3:
                double left = -2.38742588672279, right = -0.279240779943874, x0 = -4/3; //roots
                if (a>=right)
                    return df(b);
                else if (b<=left)
                    return df(a);

                else if (a>=left && b<=x0)
                    return Math.abs(df(b));
                else if (a>=x0 && b<=right)
                    return Math.abs(df(a));
                else if (a>=left && b<=right)
                    return 5/3D;

                else if (a<=left && b < x0)
                    return Math.max(Math.abs(df(a)), Math.abs(df(b)));
                else if (a>=x0 && b >= right) {
                    return Math.max(Math.abs(df(a)), Math.abs(df(b)));
                }
                else return Math.max(Math.abs(df(a)), Math.max(Math.abs(df(b)), 5/3D));
            case 4:
                return Math.abs(df(b)); // const
            case 5:
                return Math.abs(df(a)); // const
            default:
                return 0;
        }
    }
    public double getMaxddF(double a, double b){
        switch (functionNumber) {
            case 1:
                return Math.max(Math.abs(ddf(a)),Math.abs(ddf(b)));
            case 2:
                return 0.25; //0.25 - оценка сверху, максимум не просто вычислить
            case 3:
                return Math.max(Math.abs(ddf(a)),Math.abs(ddf(b)));
            case 4:
                return Math.abs(df(b)); // const
            case 5:
                return Math.abs(df(a)); // const
            default:
                return 0;
        }
    }
    public BreakPointKind getBreakPointKind(double a, double b) {
        switch (functionNumber) {
            case 1:
                if ((a>0) == (b>0) && (a!=0) && (b!=0)){
                    return BreakPointKind.CONTINUOUS;
                }
                return BreakPointKind.INFINITE;
            case 2:
                if ((a>0) == (b>0) && (a!=0) && (b!=0)){
                    return BreakPointKind.CONTINUOUS;
                }
                return BreakPointKind.HOLE;
            case 3:
                return BreakPointKind.CONTINUOUS;
            case 4:
                return BreakPointKind.CONTINUOUS;
            case 5:
                return BreakPointKind.CONTINUOUS;
            default:
                return BreakPointKind.CONTINUOUS;
        }
    }

    private double f1( double x ){ return  1/x;}
    private double f2( double x ){ return  x==0?1D:Math.sin(x)/x;}
    private double f3( double x ){ return  0.5*Math.pow(x,3)+2*Math.pow(x,2)+x+2;}
    private double f4( double x ){ return  5*x;}
    private double f5( double x ){ return  -5*x;}

    private double df1( double x ){ return  -1/Math.pow(x,2);}
    private double df2( double x ){ return  x==0?0D:Math.cos(x)/x - Math.sin(x)/Math.pow(x,2);}
    private double df3( double x ){ return  1.5*Math.pow(x,2)+4*x+1;}
    private double df4( double x ){ return  5D;}
    private double df5( double x ){ return  -5D;}

    private double ddf1( double x ){ return  2/Math.pow(x,3);}
    private double ddf2( double x ){ return  x==0?-1/3D:( -1*Math.sin(x) - 2*Math.cos(x)/x + 2*Math.sin(x)/Math.pow(x,2) )/x;}
    private double ddf3( double x ){ return  3*x+4;}
    private double ddf4( double x ){ return  0D;}
    private double ddf5( double x ){ return  0D;}

    public static String[] getStringRepresentation() {
        return stringRepresentation;
    }

}
