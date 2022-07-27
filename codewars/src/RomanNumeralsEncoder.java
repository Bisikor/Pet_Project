/**
 * Create a function taking a positive integer as its parameter
 * and returning a string containing the Roman Numeral representation of that integer.
 * <p>
 * Modern Roman numerals are written by expressing each digit separately starting
 * with the left most digit and skipping any digit with a value of zero.
 * In Roman numerals 1990 is rendered: 1000=M, 900=CM, 90=XC; resulting in MCMXC.
 * 2008 is written as 2000=MM, 8=VIII; or MMVIII. 1666 uses each Roman symbol in descending order: MDCLXVI.
 */

public class RomanNumeralsEncoder {
    public static void main(String[] args) {
        System.out.println(solution(3564));
    }

    public static String solution(int digital) {
        String stringDigital = String.valueOf(digital);
        StringBuilder rimConvert = new StringBuilder();

        for (int i = 0; i < stringDigital.length(); i++) {
            int nowInt = Integer.parseInt(String.valueOf(stringDigital.charAt(i)));
            double pow = Math.pow(10, stringDigital.length() - i - 1);
            int nowConverted = (int) (nowInt * pow);
            if (nowInt < 9 && nowInt > 5) {
                int calculate = nowInt - 5;
                rimConvert.append(oneNumberConvert((int) (5 * pow)));
                rimConvert.append(String.valueOf(oneNumberConvert((int) (1 * pow))).repeat(calculate));
            }
            if (nowInt > 1 && nowInt < 5) {
                if (nowInt == 4) {
                    int counter = 5 - nowInt;
                    rimConvert.append(String.valueOf(oneNumberConvert((int) (1 * pow))).repeat(counter));
                    rimConvert.append(oneNumberConvert((int) (5 * pow)));
                } else
                    rimConvert.append(String.valueOf(oneNumberConvert((int) (1 * pow))).repeat(nowInt));
            }

            rimConvert.append(oneNumberConvert(nowConverted));

        }
        return rimConvert.toString();
    }

    public static String oneNumberConvert(int digital) {
        return switch (digital) {
            case 1 -> "I";
            case 5 -> "V";
            case 9 -> "IX";
            case 10 -> "X";
            case 50 -> "L";
            case 90 -> "XC";
            case 100 -> "C";
            case 500 -> "D";
            case 900 -> "CM";
            case 1000 -> "M";
            default -> "";
        };
    }
}
