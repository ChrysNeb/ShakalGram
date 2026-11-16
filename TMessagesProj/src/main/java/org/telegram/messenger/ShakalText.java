package org.telegram.messenger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Explicit, local transformation of a plain-text draft. */
public final class ShakalText {
    private static final Pattern TOKEN = Pattern.compile(
            "(?:[a-zA-Z][a-zA-Z0-9+.-]*://|www\\.|t\\.me/)[^\\s]+"
            + "|[@#/][\\p{L}\\p{N}_]+|[\\p{L}\\p{N}_]+",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    private ShakalText() {}

    public interface Edit {
        void replace(int start, int end, String replacement);
    }

    public static void editFromEnd(String text, Edit edit) {
        java.util.ArrayList<int[]> ranges = new java.util.ArrayList<>();
        Matcher matcher = TOKEN.matcher(text);
        while (matcher.find()) {
            if (!matcher.group().equals(replace(matcher.group()))) {
                ranges.add(new int[] { matcher.start(), matcher.end() });
            }
        }
        for (int i = ranges.size() - 1; i >= 0; i--) {
            int[] range = ranges.get(i);
            edit.replace(range[0], range[1], replace(text.substring(range[0], range[1])));
        }
    }

    public static String transform(String text) {
        Matcher matcher = TOKEN.matcher(text);
        StringBuilder result = new StringBuilder(text.length());
        int last = 0;
        while (matcher.find()) {
            result.append(text, last, matcher.start());
            result.append(replace(matcher.group()));
            last = matcher.end();
        }
        return result.append(text, last, text.length()).toString();
    }

    private static String replace(String word) {
        String lower = word.toLowerCase(java.util.Locale.ROOT);
        String changed;
        switch (lower) {
            case "привет": changed = "превед"; break;
            case "автор": changed = "аффтар"; break;
            case "медведь": changed = "медвед"; break;
            case "сейчас": changed = "щас"; break;
            case "ещё":
            case "еще": changed = "исчо"; break;
            case "жжёшь":
            case "жжешь": changed = "жжош"; break;
            case "жжёт":
            case "жжет": changed = "жжот"; break;
            case "конечно": changed = "канешна"; break;
            case "сегодня": changed = "севодня"; break;
            case "вообще": changed = "ваще"; break;
            case "что": changed = "што"; break;
            case "чтобы": changed = "штобы"; break;
            case "тоже": changed = "тожы"; break;
            case "ничего": changed = "ничиво"; break;
            case "человек": changed = "чилавег"; break;
            default: return word;
        }
        if (word.equals(word.toUpperCase(java.util.Locale.ROOT))) {
            return changed.toUpperCase(java.util.Locale.ROOT);
        }
        if (Character.isUpperCase(word.charAt(0))) {
            return Character.toUpperCase(changed.charAt(0)) + changed.substring(1);
        }
        return changed;
    }
}
