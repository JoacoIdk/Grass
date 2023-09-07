package me.zephi.grass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BracketTracker {
    private static final Map<Character, Character> endingMapping = new HashMap<>();

    static {
        endingMapping.put('(', ')');
        endingMapping.put('{', '}');
        endingMapping.put('[', ']');
    }

    private final List<Character> indentations = new ArrayList<>();

    public void addIndentation(char indentationType) {
        indentations.add(endingMapping.get(indentationType));
    }

    public boolean removeIndentation(char indentationType) {
        return indentations.remove(indentations.size() - 1) == indentationType;
    }
}