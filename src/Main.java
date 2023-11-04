import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
// Приветствуем пользователя
// Пользователь прописывает выражение x + y, где случайное действие
// Считыватель разделяет строку по пробелам на массив
// Определить математическое действие
// Создать матрицу из действий
// Обработать каждое действие
// Выдать строку или исключение с завершением программы
//Если мы пытаемся получить из массива значение заданного пользователем числа и выдает ар число, то цифра римская
//Если нет то выдает null






//
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите выражение");
        String regex = "or|-|\\+|\\*|/";
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String[] userInputArray = userInput.split(regex);

        HashMap<String, Integer> romeNumbersDict = new HashMap<String, Integer>();

        romeNumbersDict.put("I", 1);
        romeNumbersDict.put("II", 2);
        romeNumbersDict.put("III", 3);
        romeNumbersDict.put("IV", 4);
        romeNumbersDict.put("V", 5);
        romeNumbersDict.put("VI", 6);
        romeNumbersDict.put("VII", 7);
        romeNumbersDict.put("VIII", 8);
        romeNumbersDict.put("IX", 9);
        romeNumbersDict.put("X", 10);


        int number1 = 0;
        int number2 = 0;

        String action;
        if(userInput.contains("+")){
            action = "+";
        }  else if (userInput.contains("-")){
            action = "-";
        }  else if (userInput.contains("*")){
            action = "*";
        }  else if (userInput.contains("/")){
            action = "/";
        }  else {
            throw new Exception("Задано неправильное математическое действие");
        }
        //Проверить какие цифры использует пользователь
        //Если обе римские, произвести вычисления
        //Если разного типа выдать исключение

        String number1String = userInputArray[0].trim();
        String number2String = userInputArray[1].trim();

        boolean isFirstNumberArabicFormat = romeNumbersDict.get(number1String) == null;
        boolean isSecondNumberArabicFormat = romeNumbersDict.get(number2String) == null;

        boolean isBothArabFormat = isFirstNumberArabicFormat && isSecondNumberArabicFormat;
        boolean isBothRomeFormat = !isFirstNumberArabicFormat && !isSecondNumberArabicFormat;

        if(isBothArabFormat) {
            number1 = Integer.parseInt(number1String);
            number2 = Integer.parseInt(number2String);

        } else if (isBothRomeFormat) {
            number1 = romeNumbersDict.get(number1String);
            number2 = romeNumbersDict.get(number2String);
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

        Map<Integer, String> reversedMap = new HashMap<>();
        for(Map.Entry<String, Integer> entry : romeNumbersDict.entrySet()){
            reversedMap.put(entry.getValue(), entry.getKey());
        }

        if (isBothRomeFormat){
            System.out.println(reversedMap.get(result));
        } else {
            System.out.println(result);
        }


    }
}



// (((((Римские цифры))))
// Создать массив из Римских цифр до 10 (Аналог regex)
// Проверить содержит ли массив number 1 и 2
// Если одно из значений - выдать искл
// Если оба преобразовать в арабские
// Посчитать выражение
// Если меньше 0, выдать исключение
// Если больше преобразовать обратно и выдать
//
//
//
// ДОБАВИТЬ ПРОВЕРКУ ВВОДИМОЙ СТРОКИ!


