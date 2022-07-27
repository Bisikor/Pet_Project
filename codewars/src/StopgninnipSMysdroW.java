/**
 * Write a function that takes in a string of one or more words,
 * and returns the same string, but with all five or more letter words reversed
 * (Just like the name of this Kata). Strings passed in will consist of only letters and spaces.
 * Spaces will be included only when more than one word is present.
 */

public class StopgninnipSMysdroW {
    public static void main(String[] args) {
        System.out.println(spinWords("Hey fellow warriors"));

    }

    public static String spinWords(String sentence) {
        StringBuilder returnedSentence = new StringBuilder();
        StringBuilder reversWord = new StringBuilder();
        String[] oneWord = sentence.split(" ");
        for (int i = 0; i < oneWord.length; i++) {
            if (i != 0)
                returnedSentence.append(" ");
            reversWord = new StringBuilder();
            if (oneWord[i].length() > 4) {
                for (int j = oneWord[i].length() - 1; j != -1; j--) {
                    reversWord.append(oneWord[i].charAt(j));
                }

                returnedSentence.append(reversWord);
            } else
                returnedSentence.append(oneWord[i]);
        }
        return returnedSentence.toString();
    }
}
