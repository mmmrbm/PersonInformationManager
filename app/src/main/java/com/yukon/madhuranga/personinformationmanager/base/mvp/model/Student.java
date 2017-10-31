package com.yukon.madhuranga.personinformationmanager.base.mvp.model;

/**
 * Represents the Student model for the application.
 */
public class Student extends BasePerson {
    /**
     * Variable to hold the Average Grade Mark for the Student.
     */
    private float averageGradeMark;

    /**
     * Variable to hold the Association Registration No for the Student.
     */
    private String studentAssociationRegNo;

    /**
     * Gets the Average Grade Mark for the Student.
     *
     * @return the Association Registration No for the Student.
     */
    public float getAverageGradeMark() {
        return averageGradeMark;
    }

    /**
     * Sets the Average Grade Mark for the Student.
     *
     * @param averageGradeMark for the Student.
     */
    public void setAverageGradeMark(float averageGradeMark) {
        this.averageGradeMark = averageGradeMark;
    }

    /**
     * Gets the Association Registration No for the Student.
     *
     * @return the Association Registration No for the Student.
     */
    public String getStudentAssociationRegNo() {
        return studentAssociationRegNo;
    }

    /**
     * Sets the Association Registration No for the Student.
     *
     * @param studentAssociationRegNo for the Student.
     */
    public void setStudentAssociationRegNo(String studentAssociationRegNo) {
        this.studentAssociationRegNo = studentAssociationRegNo;
    }
}
