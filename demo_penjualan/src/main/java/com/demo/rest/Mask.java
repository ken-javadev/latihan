package com.demo.rest;

public class Mask {

    public Mask() {
        System.out.println(mask("To Stark"));
        System.out.println(mask("Peter Parker"));
        System.out.println(mask("Bumblebee"));
        System.out.println(mask("El Taurino"));
        System.out.println(mask("John Doe"));
        System.out.println(mask("Crazy Rich Asian"));
        System.out.println(mask("Stephen Strange"));
        System.out.println(mask("A Kid With Supernatural Abilities"));
    }

    public String mask(String original) {
        StringBuilder ret = new StringBuilder();
        String[] word = original.split(" ");
        for (String obj : word) {
            if (obj.length() == 2) {
                String newWord = obj;
                ret.append(newWord);
            } else {
                String newWord = replaceWord(obj, '*');
                ret.append(newWord);
            }
            ret.append(" ");
        }

        String result = ret.toString();
        return result.substring(0, result.length() - 1); //substring for repleace space in last word
    }

    public static String replaceWord(String word, char replacer) {
        StringBuilder ret = new StringBuilder();
        if (word.length() > 2) {
            ret.append(word.charAt(0));
            for (int i = 1; i < word.length() - 1; i++) {
                ret.append(replacer);
            }
            ret.append(word.charAt(word.length() - 1));
            return ret.toString();
        }

        return word;
    }

    public static void main(String[] args) {
        new Mask();
    }
}
