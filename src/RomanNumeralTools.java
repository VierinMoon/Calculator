import java.util.HashMap;
import java.util.Map;

public class RomanNumeralTools {


    public static HashMap<String, Integer> getRomanNumbersDict(){
        HashMap<String, Integer> romanNumbersDict = new HashMap<>();

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

        return romanNumbersDict;
    }

    private static final int[] ARABIC_NUMERALS = {
            1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
    };

    private static final String[] ROMAN_NUMERALS = {
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
    };

    /**
     * <h3>--- Short Description --- </h3>
     * Стачиваем заданное число наибольшей римской цифрой
     * <h3>--- Full Description ---</h3>
     * Используем 2 массива: синтаксис и перевод
     * С помощью цикла проходимся по 2м массивам одновременно
     * Вычитаем из целевого числа наибольшую римскую цифру(ее араб аналог) до момента, пока цч не станет меньше
     * вычитаемого числа;
     * Далее повторяем со след римской цифрой
     * На каждое действие вычитания добавляем в конечную строку римскую цифру
     * @param number Arabic format
     * @return String: Rome format
     * @throws Exception range [1; 3999]
     */

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

