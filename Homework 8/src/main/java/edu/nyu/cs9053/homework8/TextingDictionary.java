package edu.nyu.cs9053.homework8;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TextingDictionary {

    private static final Map<ValidTextKeyPress, String> TEXT_ON_9_KEYS = new HashMap<ValidTextKeyPress, String>();

    static {
        TEXT_ON_9_KEYS.put(ValidTextKeyPress.Two, "abc");
        TEXT_ON_9_KEYS.put(ValidTextKeyPress.Three, "def");
        TEXT_ON_9_KEYS.put(ValidTextKeyPress.Four, "ghi");
        TEXT_ON_9_KEYS.put(ValidTextKeyPress.Five, "jkl");
        TEXT_ON_9_KEYS.put(ValidTextKeyPress.Six, "mno");
        TEXT_ON_9_KEYS.put(ValidTextKeyPress.Seven, "pqrs");
        TEXT_ON_9_KEYS.put(ValidTextKeyPress.Eight, "tuv");
        TEXT_ON_9_KEYS.put(ValidTextKeyPress.Nine, "wxyz");
    }

    private final Collection<String> wordList;

    public TextingDictionary() {
        wordList = new HashSet<String>();
    }

    public void insert(String word) {
        if (!word.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException();
        }
        wordList.add(word);
    }

    public List<String> search(List<ValidTextKeyPress> prefixes) {
        List<String> predictedWordList = new LinkedList<String>();
        for (String word : wordList) {
            if (validWord(word, prefixes)) {
                predictedWordList.add(word);
            }
        }
        return predictedWordList;
    }

    private boolean validWord(String word, List<ValidTextKeyPress> prefixes) {
        int wordIndex = 0;
        boolean validCharacterFlag = true;
        for (ValidTextKeyPress validTextKeyPress : prefixes) {
            validCharacterFlag = validCharacter(word.charAt(wordIndex), validTextKeyPress);
            wordIndex++;
            if (validCharacterFlag == false || wordIndex == word.length()) {
                return validCharacterFlag;
            }
        }
        return validCharacterFlag;
    }

    private boolean validCharacter(char wordCharacter, ValidTextKeyPress validTextKeyPress) {
        char lowerWordCharacter = Character.toLowerCase(wordCharacter);
        if (TEXT_ON_9_KEYS.get(validTextKeyPress).indexOf(lowerWordCharacter) != -1) {
            return true;
        }
        return false;
    }
}
