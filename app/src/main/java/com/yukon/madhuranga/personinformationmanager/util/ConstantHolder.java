package com.yukon.madhuranga.personinformationmanager.util;

/**
 * Represent an model whose sole purpose is to hold constants used
 * in the application for various purposes.
 */
public class ConstantHolder {
    public static final String DATABASE_NAME = "people_information_db";
    public static final int DATABASE_VERSION = 1;

    public static final String TEACHER_TABLE_NAME = "teacher_info";
    public static final String STUDENT_TABLE_NAME = "student_info";

    public static final String COL_RECORD_ID = "record_id";
    public static final String COL_PERSON_NAME = "name";
    public static final String COL_AGE = "age";

    public static final String COL_STUDENT_AVG_GRADE = "avg_grade_mark";
    public static final String COL_STUDENT_ASSOC_REG_NO = "association_reg_no";

    public static final String COL_TEACHER_HIGHEST_EDU_QUAL = "highest_edu_qual";

    public static final String TEACHER_UI_TAB_HEADER = "Teacher";
    public static final String STUDENT_UI_TAB_HEADER = "Student";

    public static final String TEACHER_NAME_KEY = "TeacherName";
    public static final String TEACHER_AGE_KEY = "TeacherAge";
    public static final String TEACHER_HIGHEST_EDU_QUAL_KEY = "TeacherHighestEduQual";

    public static final String STUDENT_NAME_KEY = "StudentName";
    public static final String STUDENT_AGE_KEY = "StudentAge";
    public static final String STUDENT_GPA_KEY = "StudentGPA";
    public static final String STUDENT_ASSOC_NO_KEY = "StudentAssocNo";

    public static final String SELECTED_TAB_KEY = "SELECTED_TAB";
    public static final String SELECTED_ENTITY_IDENTIFIER_KEY = "SELECTED_ENTITY_IDENTIFIER";

    public static final String ADD_TEACHER_INFO_FRAG_TRANS_KEY = "ADD_TEACHER";
    public static final String ADD_STUDENT_INFO_FRAG_TRANS_KEY = "ADD_STUDENT";

    public static final String MODIFY_TEACHER_INFO_FRAG_TRANS_KEY = "MODIFY_TEACHER";
    public static final String MODIFY_STUDENT_INFO_FRAG_TRANS_KEY = "MODIFY_STUDENT";
}

