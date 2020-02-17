package io.integral.katas.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OCR {

    private static final Map<Integer, List<String>> PATTERNS = Map.of(
        0, List.of(
            " _ ",
            "| |",
            "|_|",
            "   "),
        1, List.of(
            "   ",
            "  |",
            "  |",
            "   "),
        2, List.of(
            " _ ",
            " _|",
            "|_ ",
            "   "),
        3, List.of(
            " _ ",
            " _|",
            " _|",
            "   "),
        4, List.of(
            "   ",
            "|_|",
            "  |",
            "   "),
        5, List.of(
            " _ ",
            "|_ ",
            " _|",
            "   "),
        6, List.of(
            " _ ",
            "|_ ",
            "|_|",
            "   "),
        7, List.of(
            " _ ",
            "  |",
            "  |",
            "   "),
        8, List.of(
            " _ ",
            "|_|",
            "|_|",
            "   "),
        9, List.of(
            " _ ",
            "|_|",
            " _|",
            "   "));


    public static String convert(String text) {
        var entries = new ArrayList<String>();
        var lines = text.split("\n");
        for (var entryNumber = 0; entryNumber < lines.length; entryNumber += 4) {
            StringBuilder entry = new StringBuilder();
            for (var entryLine = 0; entryLine < 4; entryLine++) {
                entry.append(lines[entryNumber + entryLine]).append("\n");
            }
            entries.add(entry.toString());
        }

        return entries.stream().map(entry -> {
            var patterns = new ArrayList<String>();
            var entryLines = entry.split("\n");
            for (var patternNumber = 0; patternNumber < entryLines[0].length(); patternNumber += 3) {
                var pattern = "";
                for (String entryLine : entryLines) {
                    pattern += entryLine.substring(patternNumber, patternNumber + 3);
                }
                patterns.add(pattern);
            }

            var accountNumber = patterns.stream().map(pattern -> {
                for (var digit : PATTERNS.keySet()) {
                    if (String.join("", PATTERNS.get(digit)).equals(pattern)) {
                        return digit.toString();
                    }
                }
                return "?";
            }).collect(Collectors.joining(""));

            if (accountNumber.contains("?")) {
                return accountNumber + " ILL";
            } else if (!isValid(accountNumber)) {
                return accountNumber + " ERR";
            }
            return accountNumber;
        }).collect(Collectors.joining("\n"));
    }

    public static boolean isValid(String text) {
        if (text.length() != 9) return false;
        var digits = text.split("");
        var sum = 0;
        for (var i = 0; i < 9; i++) {
            sum += (9 - i) * Integer.parseInt(digits[i]);
        }
        return sum % 11 == 0;
    }
}
