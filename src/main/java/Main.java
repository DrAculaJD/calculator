import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    /**
     * Точка входа в систему, запрашивает данные у пользователя, обрабатывает их и выводит результат.
     * @param args аргуиенты запуска программы, в данном случае они не нужны.
     */
    public static void main(String[] args) throws Exception {
        // пользователю выводится сообщение в предложением ввести операцию,
        // после ввода данные разбиваются по пробелам и записываются в список для обработки
        System.out.print("Введите операцию в формате x + y: ");
        Scanner scanner = new Scanner(System.in);

        String operation = scanner.nextLine();
        List<String> numbersAndOperation = Arrays.stream(operation.split(" ")).toList();

        // данные проверяются на соответствие условиям задачи и, если пользователь ввел римские цифры,
        // форматируются для расчета в арабском представлении, понятном Java
        List<String> trueData = DataChecking.checkNumbers(numbersAndOperation);
        int result = arabianCalc(trueData);

        // в зависимости от представдения данных они обрабатываются и выводятся в консоль
        // если ранее до этого этапа не обнаружилась ошибка в операции, которую ввел пользователь
        if (DataChecking.typeOfResult.equals(TypeOfResult.ROMAN)) {
            System.out.println("Результат вычисления: " + convertToRoman(result));
        } else {
            System.out.println("Результат вычисления: " + result);
        }

    }

    /**
     * Калькулятор арабских чисел. Определяет и расчитывает операцию, которую ввел пользователь.
     * @param data список переменных и операций для расчета
     * @return результат расчета в арабском представлении
     */
    public static int arabianCalc(List<String> data) {
        final int firstNumber = Integer.parseInt(data.get(0));
        final int secondNumber = Integer.parseInt(data.get(2));
        final String operator = data.get(1);

        return switch (operator) {
            case "-" -> firstNumber - secondNumber;
            case "*" -> firstNumber * secondNumber;
            case "/" -> firstNumber / secondNumber;
            default -> firstNumber + secondNumber;
        };
    }

    /**
     * Метод конвертирует число из арабского в римское представление. Если число не соответсвует условию задачи
     * пользователь получит сообщение об ошибке и программа заершит работу.
     * @param result арабское представление числа
     * @return римское представление числа
     */
    public static String convertToRoman(int result) throws Exception {

        if (result <= 0) {
            DataChecking.exitProgram();
        }

        final int[] arabiansSymbols = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        final String[] romansSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder romanBuilder = new StringBuilder();

        for (int i = 0; i < arabiansSymbols.length; i++) {
            while (result >= arabiansSymbols[i]) {
                romanBuilder.append(romansSymbols[i]);
                result -= arabiansSymbols[i];
            }
        }

        return romanBuilder.toString();
    }

}
