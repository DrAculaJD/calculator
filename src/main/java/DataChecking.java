import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Класс содержит методы и данные, необходимые для проверки операции, которую ввел пользователь.
 */
public class DataChecking {

    /** Переменная определяет, какой тип цифр используется в результате вычислений. */
    public static TypeOfResult typeOfResult = TypeOfResult.ARABIAN;

    /**
     * Метод осуществляет проверку данных, которые ввел пользователь на соответствие условию задания.
     * @param data список переменных и операций, которые ввел пользователь
     * @return форматированный список переменных и операций, которые ввел пользователь,
     * если расчет ведется в римских цифрах. В ином случае данные возвращаются без изменений.
     */
    public static List<String> checkNumbers(List<String> data) throws Exception {
        // в первую очередь осуществляется проверка оператора
        checkOperator(data.get(1));

        // формируются данные и переменные для проверки того в каких числах введены данные пользователем
        Map<String, String> romanNumerals = getRomanNumeralsMap();
        String firstNumber = data.get(0);
        String secondNumber = data.get(2);
        boolean isRoman = romanNumerals.containsKey(firstNumber) && romanNumerals.containsKey(secondNumber);
        boolean isArabian = !romanNumerals.containsKey(firstNumber) && !romanNumerals.containsKey(secondNumber);

        // инициализируется переменная, в которой будут храниться данные, подготовленные для расчета
        List<String> checkResult = new LinkedList<>();

        // проверка того, что пользователь ввел ординаковые по типу цифры (две арабские или две римские)
        if (!isArabian && !isRoman) {
            exitProgram();
        } else if (isRoman) {
            // если пользователь ввел римские цифры для расчета, тогда осуществляется их приведение к арабским,
            // для дальнейших вычислений
            typeOfResult = TypeOfResult.ROMAN;
            checkResult.add(0, romanNumerals.get(firstNumber));
            checkResult.add(1, data.get(1));
            checkResult.add(2, romanNumerals.get(secondNumber));
        } else {
            // если пользователь ввел арабские цифры, тогда осуществляется их проверка на соответствие условию задачи
            checkArabian(data);
            // при успешной проверке нет необходимости форматировать дланные перед расчетами
            checkResult = data;
        }

        return checkResult;
    }

    /**
     * Осуществляет провеку введенных арабских цифр на соответсвие условию задания.
     * Если проверка не пройдена, пользователю отправляется сообщение об ошибке и программа завершает рабоу.
     * @param data список переменных и операций, которые ввел пользователь
     * @see DataChecking#exitProgram()
     */
    private static void checkArabian(List<String> data) throws Exception {
        try {
            // преобразование переменных операции к типу Integer,
            // если пользователь ввел не числа, то код выбросит ошибку
            int firstNumber = Integer.parseInt(data.get(0));
            int secondNumber = Integer.parseInt(data.get(2));

            // проверка переменных на соответсвие условиям задания
            boolean firstIsAllowable = (10 >= firstNumber) && (firstNumber >= 1);
            boolean secondAllowable = (10 >= secondNumber) && (secondNumber >= 1);

            if (!firstIsAllowable || !secondAllowable) {
                exitProgram();
            }
        } catch (Exception e) {
            exitProgram();
        }
    }

    /**
     * Формирует и возвращает список римских цифр от 1 до 10.
     * @return список римских цифр от 1 до 10
     */
    public static Map<String, String> getRomanNumeralsMap() {
        Map<String, String> romanNumerals = new HashMap<>();
        romanNumerals.put("I", "1");
        romanNumerals.put("II", "2");
        romanNumerals.put("III", "3");
        romanNumerals.put("IV", "4");
        romanNumerals.put("V", "5");
        romanNumerals.put("VI", "6");
        romanNumerals.put("VII", "7");
        romanNumerals.put("VIII", "8");
        romanNumerals.put("IX", "9");
        romanNumerals.put("X", "10");

        return romanNumerals;
    }

    /**
     * Метод проверят, является ли оператор в введенной операции допустимым.
     * В случае отрицательных резултатов проверки программа прекращает свое выполнение.
     * @param operator оператор, который необходимо проверить
     * @see DataChecking#exitProgram()
     */
    private static void checkOperator(String operator) throws Exception {
        List<String> operators = new ArrayList<>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");

        if (!operators.contains(operator)) {
            exitProgram();
        }
    }

    /**
     * Метод выводит в консоль сообщение об ошибке в введенных данных и завершает программу.
     */
    public static void exitProgram() throws Exception {
        throw new Exception("Введены неверные данные, программа будет завершена.");
        //System.exit(0);
    }
}
