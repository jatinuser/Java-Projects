package com.sms.util;

public class InputSanitizer {

    // Escape HTML special characters before echoing user text into a page
    public static String escapeHtml(String input) {
        if (input == null) return "";
        StringBuilder sb = new StringBuilder(input.length());
        for (char c : input.toCharArray()) {
            switch (c) {
                case '<':  sb.append("&lt;");   break;
                case '>':  sb.append("&gt;");   break;
                case '&':  sb.append("&amp;");  break;
                case '"':  sb.append("&quot;"); break;
                case '\'': sb.append("&#x27;"); break;
                default:   sb.append(c);
            }
        }
        return sb.toString();
    }

    // Allow only letters, spaces, dots, hyphens for names, max 100 chars
    public static boolean isValidName(String name) {
        return name != null && name.matches("[A-Za-z .\\-]{1,100}");
    }
}
