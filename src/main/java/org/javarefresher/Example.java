package org.javarefresher;

public class Example {
// return reducedString if reducedString length is less than one length, else return original string
// abc -> a1b1c1
//
// aaaabbbbbaaaadddddddd - > a4b5a4
// aaaaa -> a5
// currChar = a
// currCharCounter = 0

    public static void example(String[] args) {
        String one = "aaaabbbbbccccccdddddddd";

        char currChar = one.charAt(0);
        int currCharCounter = 1, stringLength = one.length();
        StringBuilder reducedString = new StringBuilder();
        for (int i = 0; i < stringLength - 1; i++) {
            if (one.charAt(i + 1) == currChar) {
                currCharCounter++;
            } else {
                String currSequence = currChar + Integer.toString(currCharCounter);
                reducedString.append(currSequence);
                currCharCounter = 1;
                currChar = one.charAt(i+1);
            }
        }
        reducedString.append(currChar).append(currCharCounter);
        if (reducedString.length() > one.length()) {
            System.out.println(one);
        } else {
            System.out.println(reducedString);
        }
    }
}
