import computation.IntegralCalculator;
import io.IOManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //TODO - точки разрыва (поиск + несколько способов их устранения *при возможности)
        //TODO - вычисление оптимального числа разбиений
        //TODO - условия существования интеграла
        IOManager console = new IOManager(new Scanner(System.in));
        IntegralCalculator iCalculator = new IntegralCalculator(console);
        iCalculator.calculateRectangleMethod();
    }
}
