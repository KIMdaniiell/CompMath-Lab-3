import computation.IntegralCalculator;
import io.IOManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IOManager console = new IOManager(new Scanner(System.in));
        IntegralCalculator iCalculator = new IntegralCalculator(console);
        iCalculator.calculateRectangleMethod();
    }
}
