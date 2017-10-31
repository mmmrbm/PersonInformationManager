package com.yukon.madhuranga.personinformationmanager.core.repository;

import com.yukon.madhuranga.personinformationmanager.core.mvp.model.IBaseModel;

import java.util.List;

/**
 * Defines the contract which should be implemented by database operation handlers
 * for entities for the application.
 */
public interface IApplicationDatabaseOperationsHandler {
    /**
     * Creates a record for a @see IPeopleManagerEntity in the database.
     *
     * @param entityToBePersisted @see IPeopleManagerEntity containing information to be persisted
     *                            in the database.
     *
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    long createRecord(IBaseModel entityToBePersisted);

    /**
     * Fetches information for all @see IPeopleManagerEntity entities presented in the database.
     *
     * @return Information for all @see entities as a @see List of @see IPeopleManagerEntity.
     */
    List<IBaseModel> fetchAllRecords();

    /**
     * Fetches information for particular @see IPeopleManagerEntity model presented in the database.
     * identified by presented identifier.
     *
     * @param entityIdentifier Identifier associated to @see IPeopleManagerEntity model to be fetched.
     *
     * @return Information for all @see entities as a @see List of @see IPeopleManagerEntity.
     */
    IBaseModel fetchRecordById(int entityIdentifier);

    /**
     * Updates information for particular @see IPeopleManagerEntity model.
     *
     * @param entityToBePersisted @see IPeopleManagerEntity containing information to perform the update operation.
     *
     * @return The number of rows affected.
     */
    int updateRecord(IBaseModel entityToBePersisted);

    /**
     * Deletes information for particular @see IPeopleManagerEntity model identified by presented identifier.
     *
     * @param entityIdentifier Identifier associated to @see IPeopleManagerEntity model to be fetched.
     *
     * @return the number of rows affected if a whereClause is passed in, 0 otherwise.
     */
    int deleteRecord(int entityIdentifier);

    /**
     * Returns number of data records in the database for a particular model.
     *
     * @return The number of data records in the database
     */
    int countDatabaseRecords();

    /**
     * Returns a flag to indicate whether table for a particular model has no records at all.
     *
     * @return true if no records were found in the able for a particular model, false otherwise.
     */
    boolean isDatabaseEmpty();
}
