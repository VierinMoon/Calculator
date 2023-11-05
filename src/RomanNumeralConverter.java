//Создаем новый класс для преобразования арабских чисел в римские
//Создаем массив с осн римскими числами и числами, полученными в результате отрицания (X, IX)
//Создаем массив со значениями в араб формате
//Создаем функцию, принимающую на вход арабские числа
//Функция проверяет условие (1-3999)
//Внутри функции строим строку из римских чисел
//Создаем  функцию выводящую на экран результат преобразования
//
public class RomanNumeralConverter {
    private static final int[] ARABIC_NUMERALS = {
            1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
    };

    private static final String[] ROMAN_NUMERALS = {
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
    };

    public static String toRoman(int number) throws Exception {
        if (number > 3999 || number <= 0) {
            throw new Exception("Результат вычисления римских чисел не может превышать 3999 или быть меньше 1");
        }

        StringBuilder romanResult = new StringBuilder();

        for (int i = 0; i < ARABIC_NUMERALS.length; i++) {
            while (number >= ARABIC_NUMERALS[i]) {
                romanResult.append(ROMAN_NUMERALS[i]);
                number -= ARABIC_NUMERALS[i];
            }
        }
        return romanResult.toString();
    }
    public static boolean isRoman(String userInput) {
        for (String romanNumeral : ROMAN_NUMERALS) {
           if (userInput.contains(romanNumeral)) {
               return true;
           }
       }
        return false;
    }
}
