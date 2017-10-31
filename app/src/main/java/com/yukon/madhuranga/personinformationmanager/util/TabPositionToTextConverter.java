package com.yukon.madhuranga.personinformationmanager.util;

import java.util.Arrays;
import java.util.List;

/**
 * Converts the tab position to text which identifies entity type.
 */
public class TabPositionToTextConverter {
    /**
     * Variable to refer the collection of tab header text.
     */
    private static List<String> tabHeader = Arrays.asList(
            ConstantHolder.TEACHER_UI_TAB_HEADER,
            ConstantHolder.STUDENT_UI_TAB_HEADER);

    /**
     * Gets the collection of Tab Headers
     * @return the collection of Tab Headers
     */
    public static List<String> getTabHeaderTextCollection()
    {
        return tabHeader;
    }
}
