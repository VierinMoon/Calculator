import java.util.AbstractMap;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите выражение");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        String regex = "or|-|\\+|\\*|/";
        String[] userInputArray = userInput.split(regex);


        if (userInputArray.length < 2) {
            throw new Exception("Строка не является математической функцией");
        } else if (userInputArray.length > 2) {
            throw new Exception("Формат мат. операции не удовлетворяет заданию - 2 операнда, 1 оператор");
        }


        String[] operators = {"+", "-", "*", "/"};
        String action = null;

        for (String operator : operators) {
            if (userInput.contains(operator)) {
                action = operator;
                break;
            }
        }
        if (action == null) {
            throw new Exception("Задано неправильное математическое действие");
        }


        String number1String = userInputArray[0].trim();
        String number2String = userInputArray[1].trim();


        boolean isFirstNumberRomanFormat = RomanNumeralTools.isRoman(number1String);
        boolean isSecondNumberRomanFormat = RomanNumeralTools.isRoman(number2String);

        boolean isBothArabFormat = !isFirstNumberRomanFormat && !isSecondNumberRomanFormat;
        boolean isBothRomeFormat = isFirstNumberRomanFormat && isSecondNumberRomanFormat;

        int number2 = 0;
        int number1 = 0;

        if (isBothRomeFormat) {
            validationRoman(number1String, number2String);
        }

        if (isBothArabFormat) {
            Map.Entry<Integer, Integer> pair = converterArab(number1String, number2String);
            number1 = pair.getKey();
            number2 = pair.getValue();

        } else if (isBothRomeFormat) {
            Map.Entry<Integer, Integer> pair = converterRoman(number1String, number2String);
            number1 = pair.getKey();
            number2 = pair.getValue();


        } else {
            throw new Exception("Числа должны быть одного формата (Рим/Араб)");
        }

        if (isBothArabFormat) {
            validationArab(number1, number2);
        }


        int result = 0;

        switch (action) {
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                result = Math.round(number1 / number2);
                break;
            default:
                throw new Exception("Задано неправильное математическое действие");
        }

        if (isBothRomeFormat) {
            System.out.println(RomanNumeralTools.toRoman(result));
        } else {
            System.out.println(result);
        }

    }


    public static void validationArab(Integer number1, Integer number2) throws Exception {
        if (number1 < 1 || number1 > 10 || number2 < 1 || number2 > 10) {
            throw new Exception("Задаваемые числа должны лежать в диапазоне [1; 10]");
        }
    }


    public static void validationRoman(String number1, String number2) throws Exception {
        Map<String, Integer> romanNumbers = RomanNumeralTools.getRomanNumbersDict();

        if (romanNumbers.get(number1) == null || romanNumbers.get(number2) == null) {
            throw new Exception("Задаваемые числа должны лежать в диапазоне [1; 10]");
        }
    }


    public static Map.Entry<Integer, Integer> converterRoman(String number1String, String number2String) {
        Map<String, Integer> romanNumbers = RomanNumeralTools.getRomanNumbersDict();

        int number1 = romanNumbers.get(number1String);
        int number2 = romanNumbers.get(number2String);
        return new AbstractMap.SimpleEntry<>(number1, number2);
    }


    public  static Map.Entry<Integer, Integer> converterArab(String number1String, String number2String) {
        int number1 = Integer.parseInt(number1String);
        int number2 = Integer.parseInt(number2String);
        return new AbstractMap.SimpleEntry<>(number1, number2);
    }
}




