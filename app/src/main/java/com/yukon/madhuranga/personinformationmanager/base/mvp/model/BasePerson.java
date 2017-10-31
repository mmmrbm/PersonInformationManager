package com.yukon.madhuranga.personinformationmanager.base.mvp.model;

import com.yukon.madhuranga.personinformationmanager.core.mvp.model.IBaseModel;

/**
 * Represents the base class for the entities in the application.
 */
public class BasePerson implements IBaseModel {
    /**
     * Variable to hold the record id allocated for the model.
     */
    private int recordId;

    /**
     * Variable to hold the name for the model.
     */
    private String name;

    /**
     * Variable to hold the age for the model.
     */
    private int age;

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRecordId() {
        return recordId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAge() {
        return age;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAge(int age) {
        this.age = age;
    }

}
