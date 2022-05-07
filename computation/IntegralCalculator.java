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

        if (hasBreakPoints(functionNumber, a, b) == BreakPointKind.INFINITE){
            ioManager.writeErrorMessage(String.format("Функция №%d содержит неустранимый разрыв на интервале [%f,%f]",functionNumber,a,b));
            return;
        } else if (hasBreakPoints(functionNumber, a, b) == BreakPointKind.HOLE) {
            if (!ioManager.getBooleanDigit("Устранить точку разрыва?")){
                return;
            }
        }

        int partitions = getPartitionsCount(a,b,epsilon,oneDimensionalMath);
        ResultWrapper midResult = midSquares(a,b,partitions,oneDimensionalMath);
        ResultWrapper leftResult = leftSquares(a,b,partitions,oneDimensionalMath);
        ResultWrapper rightResult = rightSquares(a,b,partitions,oneDimensionalMath);

        ioManager.printTableRow(new String[] {"\nМетод:", "Левых Прямоугольников", "Средних Прямоугольников", "Правых Прямоугольников"});
        ioManager.printTableRow(new String[] {"Значение интеграла:", leftResult.getResult()+"", midResult.getResult()+"", rightResult.getResult()+""});
        ioManager.printTableRow(new String[] {"Число разбиений:", leftResult.getPartitions()+"", midResult.getPartitions()+"",rightResult.getPartitions()+""});
    }

    private BreakPointKind hasBreakPoints(int functionNumber, double a, double b) {
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

    private ResultWrapper midSquares(double a, double b, int n, OneDimensionalMath oneDimensionalMath) {
        double integralValue = 0;
        double width = (b-a)/n;
        for ( int i = 0; i < n; i++) {
            double x = a + width*(i+0.5);
            double heigth = oneDimensionalMath.f(x);
            integralValue += heigth * width;
        }
        return new ResultWrapper(n, integralValue);
    }

    private ResultWrapper leftSquares(double a, double b, int n, OneDimensionalMath oneDimensionalMath) {
        double integralValue = 0;
        double width = (b-a)/n;
        double x = a;
        for ( int i = 0; i < n; i++) {
            x = a + width*i;
            double heigth = oneDimensionalMath.f(x);
            integralValue += heigth * width;
        }
        return new ResultWrapper(n, integralValue);
    }

    private ResultWrapper rightSquares(double a, double b, int n, OneDimensionalMath oneDimensionalMath) {
        double integralValue = 0;
        double width = (b-a)/n;
        double x = a;
        for ( int i = 0; i < n; i++) {
            x = a + width*(i+1);
            double heigth = oneDimensionalMath.f(x);
            integralValue += heigth * width;
        }
        return new ResultWrapper(n, integralValue);
    }

    private int getPartitionsCount(double a, double b, double epsilon, OneDimensionalMath oneDimensionalMath) {
        return 100;
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
