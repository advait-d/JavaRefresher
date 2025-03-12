package org.javarefresher;

public class Done {
    public static void done(String[] args) {
                /*
        Write a function to reverse words in the sentence.
        Reverse the words only.
        Dots, spaces and commas should remain as it is.
        Words will contain aA to zZ characters only and will not contain anything else.
        Delimiters are only dots, spaces and commas.
        For example: ~~~~~~~~~~~~~~~~~~~~~~~~~~~ Input (String):
        yM, eman, si. arqI. zizA ~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Output (String): My, name, is. Iqra. Aziz ~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Input (String): My, name. is Basavaraj ~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Output (String): yM, eman. si jaravasaB
         */
        String input = "yM, eman, si. arqI. zizA";
        // My, name, is. Iqra. Aziz
        String reversedInput = reverseWords(input);
        System.out.println(reversedInput);
    }

    public static String reverseWords(String sentence) {
        String[] words = sentence.split(" ");
        // My, name, is. Iqra. Aziz
        StringBuilder reversedSentence = new StringBuilder();
        for (String word:words) {
            StringBuilder intermediate = new StringBuilder(word);
            int start = 0, wordLen = word.length()-1;
            while (start < wordLen) {
                char temp = intermediate.charAt(start);
                intermediate.setCharAt(start, intermediate.charAt(wordLen));
                intermediate.setCharAt(wordLen, temp);
                start++;
                wordLen--;
            }
            reversedSentence.append(intermediate.toString());
            //StringBuilder reversedWord = new StringBuilder(new StringBuilder(word).reverse().toString());
            //reversedSentence.append(reversedWord);
        }
        return reversedSentence.toString();
    }
}
