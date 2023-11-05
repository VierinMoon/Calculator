import java.util.Scanner;
import java.util.HashMap;



public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите выражение");
        String regex = "or|-|\\+|\\*|/";
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();


        String[] userInputArray = userInput.split(regex);

        if(userInputArray.length < 2) {
            throw new Exception("Строка не является математической функцией");
        } else if (userInputArray.length > 2) {
            throw new Exception("Формат мат. операции не удовлетворяет заданию - 2 операнда, 1 оператор");
        }

        HashMap<String, Integer> romanNumbersDict = new HashMap<String, Integer>();

        romanNumbersDict.put("I", 1);
        romanNumbersDict.put("II", 2);
        romanNumbersDict.put("III", 3);
        romanNumbersDict.put("IV", 4);
        romanNumbersDict.put("V", 5);
        romanNumbersDict.put("VI", 6);
        romanNumbersDict.put("VII", 7);
        romanNumbersDict.put("VIII", 8);
        romanNumbersDict.put("IX", 9);
        romanNumbersDict.put("X", 10);


        int number1 = 0;
        int number2 = 0;

        String action;
        if (userInput.contains("+")) {
            action = "+";
        } else if (userInput.contains("-")) {
            action = "-";
        } else if (userInput.contains("*")) {
            action = "*";
        } else if (userInput.contains("/")) {
            action = "/";
        } else {
            throw new Exception("Задано неправильное математическое действие");
        }


        String number1String = userInputArray[0].trim();
        String number2String = userInputArray[1].trim();


        boolean isFirstNumberRomanFormat = RomanNumeralConverter.isRoman(number1String);
        boolean isSecondNumberRomanFormat = RomanNumeralConverter.isRoman(number2String);

        boolean isBothArabFormat = !isFirstNumberRomanFormat && !isSecondNumberRomanFormat;
        boolean isBothRomeFormat = isFirstNumberRomanFormat && isSecondNumberRomanFormat;




        if (isBothArabFormat) {
            number1 = Integer.parseInt(number1String);
            number2 = Integer.parseInt(number2String);
            if (number1 < 1 || number1 > 10 || number2 < 1 || number2 > 10){
                throw new Exception("Задаваемые числа должны лежать в диапазоне [1; 10]");
            }

        } else if (isBothRomeFormat) {
            if (romanNumbersDict.get(number1String) == null || romanNumbersDict.get(number2String) == null){
                throw new Exception("Задаваемые числа должны лежать в диапазоне [1; 10]");
            }
            number1 = romanNumbersDict.get(number1String);
            number2 = romanNumbersDict.get(number2String);
        } else {
            throw new Exception("Числа должны быть одного формата (Рим/Араб)");
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

        if(isBothRomeFormat){
            System.out.println(RomanNumeralConverter.toRoman(result));
        } else {
            System.out.println(result);
        }

    }
}
