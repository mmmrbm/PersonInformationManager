package com.yukon.madhuranga.personinformationmanager.base.mvp.model;

/**
 * Represents the Teacher model for the application.
 */
public class Teacher extends BasePerson {
    /**
     * Variable to hold the Highest Educational Qualification for the Teacher.
     */
    private String highestEducationalQualification;

    /**
     * Gets the Highest Educational Qualification for the Teacher.
     *
     * @return the Highest Educational Qualification for the Teacher.
     */
    public String getHighestEducationalQualification() {
        return highestEducationalQualification;
    }

    /**
     * Sets the Highest Educational Qualification for the Teacher.
     *
     * @param highestEducationalQualification for the Teacher.
     */
    public void setHighestEducationalQualification(String highestEducationalQualification) {
        this.highestEducationalQualification = highestEducationalQualification;
    }
}
