package computation;

import io.IOManager;

public class IntegralCalculator {
    IOManager ioManager;
    OneDimensionalMath oneDimensionalMath;

    public IntegralCalculator(IOManager ioManager){
        this.ioManager = ioManager;
    }

    public void calculateRectangleMethod() {
        int functionNumber = this.getFunctionNumber();
        double a = ioManager.getDoubleDigit("Введите границу a");
        double b = ioManager.getDoubleDigit("Введите границу b");
        if (a>b){
            a+=b;
            b = a - b;
            a = a - b;
        }
        double epsilon = ioManager.getDoubleDigit("Введите погрешность e");
        this.oneDimensionalMath = new OneDimensionalMath(functionNumber);

        if (oneDimensionalMath.getBreakPointKind(a,b) == BreakPointKind.INFINITE){
            ioManager.writeErrorMessage(String.format("Функция №%d содержит неустранимый разрыв на интервале [%f,%f]",functionNumber,a,b));
            return;
        } else if (oneDimensionalMath.getBreakPointKind(a,b) == BreakPointKind.HOLE) {
            if (!ioManager.getBooleanDigit("Устранить точку разрыва?")){
                return;
            }
        }

        int midPartitionsNumber = getPartitionsCountMidSquares(a,b,epsilon,oneDimensionalMath);
        int partitionsNumber = getPartitionsCountSquares(a,b,epsilon,oneDimensionalMath);
        final ResultWrapper[] Results = new ResultWrapper[3];
        double finalA = a;
        double finalB = b;

        Thread tLeft = new Thread(new Runnable() {
            @Override
            public void run() {
                Results[0] = leftSquares(finalA, finalB,partitionsNumber,oneDimensionalMath);
            }
        });
        Thread tMid = new Thread(new Runnable() {
            @Override
            public void run() {
                Results[1] = midSquares(finalA, finalB,midPartitionsNumber,oneDimensionalMath);
            }
        });
        Thread tRight = new Thread(new Runnable() {
            @Override
            public void run() {
                Results[2] = rightSquares(finalA, finalB,partitionsNumber,oneDimensionalMath);
            }
        });
        tMid.start();
        tLeft.start();
        tRight.start();
        try {
            tMid.join();
            tLeft.join();
            tRight.join();
        } catch (InterruptedException e){}

        ioManager.printTableRow(new String[] {"\nМетод:", "Левых Прямоугольников", "Средних Прямоугольников", "Правых Прямоугольников"});
        ioManager.printTableRow(new String[] {"Значение интеграла:", Results[0].getResult()+"", Results[1].getResult()+"", Results[2].getResult()+""});
        ioManager.printTableRow(new String[] {"Число разбиений:", Results[0].getPartitions()+"", Results[1].getPartitions()+"",Results[2].getPartitions()+""});
    }

    private ResultWrapper midSquares(double a, double b, int n, OneDimensionalMath oneDimensionalMath) {
        double integralValue = 0;
        double width = (b-a)/n;
        for ( int i = 0; i < n; i++) {
            double x = a + width*(i+0.5);
            double height = oneDimensionalMath.f(x);
            integralValue += height * width;
        }
        return new ResultWrapper(n, integralValue);
    }

    private ResultWrapper leftSquares(double a, double b, int n, OneDimensionalMath oneDimensionalMath) {
        double integralValue = 0;
        double width = (b-a)/n;
        double x = a;
        for ( int i = 0; i < n; i++) {
            x = a + width*i;
            double height = oneDimensionalMath.f(x);
            integralValue += height * width;
        }
        return new ResultWrapper(n, integralValue);
    }

    private ResultWrapper rightSquares(double a, double b, int n, OneDimensionalMath oneDimensionalMath) {
        double integralValue = 0;
        double width = (b-a)/n;
        double x = a;
        for ( int i = 0; i < n; i++) {
            x = a + width*(i+1);
            double height = oneDimensionalMath.f(x);
            integralValue += height * width;
        }
        return new ResultWrapper(n, integralValue);
    }

    private int getPartitionsCountMidSquares(double a, double b,
                                             double epsilon, OneDimensionalMath oneDimensionalMath) {
        int n = 0;
        double maxF = oneDimensionalMath.getMaxddF(a, b);
        double error = Double.MAX_VALUE;
        do {
            n++;
            double h = (b-a)/n;
            error = maxF*n*h*h*h/24;
        } while (error>epsilon);
        return n;
    }

    private int getPartitionsCountSquares(double a, double b, double epsilon, OneDimensionalMath oneDimensionalMath) {
        int n = 0;
        double maxf = oneDimensionalMath.getMaxdF(a,b); //TODO
        double error = Double.MAX_VALUE;
        do {
            n++;
            double h = (b-a)/n;
            error = maxf*n*h*h/2;
        } while (error>epsilon);
        return n;
    }

    private int getFunctionNumber() {
        int number = 0;
        int options = OneDimensionalMath.getStringRepresentation().length;

        StringBuilder requestTextBuilder = new StringBuilder("Выберите интегрируемую функцию:\n");
        for (int i = 0; i < options-1; i++) {
            requestTextBuilder.append("\t").append(i+1).append(") ").append(OneDimensionalMath.getStringRepresentation()[i]).append("\n");
        }
        requestTextBuilder.append("\t").append(options).append(") ").append(OneDimensionalMath.getStringRepresentation()[options-1]);
        ioManager.writeMessage(requestTextBuilder.toString());

        boolean valid = false;
        while (!valid) {
            number = ioManager.getIntDigit("Номер уравнения");
            if ((number >=1) && (number <= options)) {
                valid = true;
            } else {
                ioManager.writeErrorMessage("Уравнение с таким номером не найдено!");
            }
        }
        return number;
    }

}
