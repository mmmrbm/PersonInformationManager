package com.yukon.madhuranga.personinformationmanager.core.mvp.model;

/**
 * Defines the contract which should be implemented by all business entities in the application.
 */
public interface IBaseModel {
    /**
     * Returns the record id for the model when persisted in
     * the database.
     *
     * @return the record id allocated to the model in database.
     */
    int getRecordId();

    /**
     * Sets the record id for the person.
     *
     * @param recordId
     */
    void setRecordId(int recordId);

    /**
     * Gets the name of the model.
     *
     * @return the name of the model.
     */
    String getName();

    /**
     * Sets name for the model.
     *
     * @param name for the model.
     */
    void setName(String name);

    /**
     * Gets the age of the model.
     *
     * @return the age of the model.
     */
    int getAge();

    /**
     * Sets age for the model.
     *
     * @param age for the model.
     */
    void setAge(int age);
}
