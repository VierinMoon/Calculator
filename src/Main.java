import java.util.*;


public class Main {
    static int number1 = 0;
    static int number2 = 0;

    public static void main(String[] args) throws Exception {
        // Принимаем выражение
        ArrayList<String> userInputArray = getUserInput();

        //Определяем математическое выражение
        String mathAction = getMathAction(userInputArray);

        //Обрабатываем цифровые значения
        boolean isBothRomeFormat = populateUserNumbers(userInputArray);

        //Производим расчет
        int calculationResult = doCalculation(mathAction);

        //Выводим результат
        outputResult(calculationResult, isBothRomeFormat);
    }


    private static void outputResult(int calculationResult, boolean isBothRomeFormat) throws Exception {
        if (isBothRomeFormat) {
            System.out.println(RomanNumeralTools.toRoman(calculationResult));
        } else {
            System.out.println(calculationResult);
        }
    }


    private static int doCalculation(String action) throws Exception {
        return switch (action) {
            case "+" -> number1 + number2;
            case "-" -> number1 - number2;
            case "*" -> number1 * number2;
            case "/" -> Math.round(number1 / number2);
            default -> throw new Exception("Задано неправильное математическое действие");
        };
    }


    private static boolean populateUserNumbers(ArrayList<String> userInputArray) throws Exception {
        String number1String = userInputArray.get(0).trim();
        String number2String = userInputArray.get(1).trim();

        boolean isFirstNumberRomanFormat = RomanNumeralTools.isRoman(number1String);
        boolean isSecondNumberRomanFormat = RomanNumeralTools.isRoman(number2String);

        boolean isBothArabFormat = !isFirstNumberRomanFormat && !isSecondNumberRomanFormat;
        boolean isBothRomeFormat = isFirstNumberRomanFormat && isSecondNumberRomanFormat;

        if (isBothRomeFormat) {
            validationRoman(number1String, number2String);
        }

        if (isBothArabFormat) {
            converterArab(number1String, number2String);

        } else if (isBothRomeFormat) {
            converterRoman(number1String, number2String);

        } else {
            throw new Exception("Числа должны быть одного формата (Рим/Араб)");
        }

        if (isBothArabFormat) {
            validationArab();
        }
        return isBothRomeFormat;
    }


    private static String getMathAction(ArrayList<String> userInputArray) throws Exception {
        String[] operators = {"+", "-", "*", "/"};
        String action = null;

        //Получаем исходную строку
        String userInput = userInputArray.get(userInputArray.size() - 1);

        for (String operator : operators) {
            if (userInput.contains(operator)) {
                action = operator;
                break;
            }
        }
        if (action == null) {
            throw new Exception("Задано неправильное математическое действие");
        }

        return action;
    }


    private static ArrayList<String> getUserInput() throws Exception {
        System.out.println("Введите выражение");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        String regex = "or|-|\\+|\\*|/";
        ArrayList<String> userInputArray = new ArrayList<>(Arrays.asList(userInput.split(regex)));
        //Для сохранения исходной строки
        userInputArray.add(userInput);

        if (userInputArray.size() < 3) {
            throw new Exception("Строка не является математической функцией");
        } else if (userInputArray.size() > 3) {
            throw new Exception("Формат мат. операции не удовлетворяет заданию - 2 операнда, 1 оператор");
        }

        return userInputArray;
    }


    public static void validationArab() throws Exception {
        if (number1 < 1 || number1 > 10 || number2 < 1 || number2 > 10) {
            throw new Exception("Задаваемые числа должны лежать в диапазоне [1; 10]");
        }
    }


    public static void validationRoman(String number1String, String number2String) throws Exception {
        Map<String, Integer> romanNumbers = RomanNumeralTools.getRomanNumbersDict();

        if (romanNumbers.get(number1String) == null || romanNumbers.get(number2String) == null) {
            throw new Exception("Задаваемые числа должны лежать в диапазоне [1; 10]");
        }
    }


    public static void converterRoman(String number1String, String number2String) {
        Map<String, Integer> romanNumbers = RomanNumeralTools.getRomanNumbersDict();

        number1 = romanNumbers.get(number1String);
        number2 = romanNumbers.get(number2String);
    }


    public  static void converterArab(String number1String, String number2String) {
        number1 = Integer.parseInt(number1String);
        number2 = Integer.parseInt(number2String);
    }
}




