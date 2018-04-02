package com.example.mdenker.interestup;

import java.util.List;

public class Util {

    /**
     * Joins items in the given list with a delimiter.
     * @param delimiter The delimiter to place in between each item.
     * @param items The list of items.
     * @return A String listing each given item, separated by the delimiter.
     */
    public static String join(String delimiter, List<String> items) {
        if (items.size() == 0) {
            return "";
        }

        StringBuilder buffer = new StringBuilder(items.get(0));
        for (int i = 1; i < items.size(); i++) {
            buffer.append(delimiter);
            buffer.append(" ");
            buffer.append(items.get(i));
        }
        return buffer.toString();
    }
}
