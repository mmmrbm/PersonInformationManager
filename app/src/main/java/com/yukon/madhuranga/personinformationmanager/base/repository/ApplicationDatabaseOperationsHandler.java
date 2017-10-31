package com.yukon.madhuranga.personinformationmanager.base.repository;

import android.database.sqlite.SQLiteDatabase;

import com.yukon.madhuranga.personinformationmanager.core.persistence.IApplicationDatabaseManagement;
import com.yukon.madhuranga.personinformationmanager.core.repository.IApplicationDatabaseOperationsHandler;

/**
 * Represents the model which holds the common functionality and constraints for all
 * database operations handlers for the application.
 */
public abstract class ApplicationDatabaseOperationsHandler implements IApplicationDatabaseOperationsHandler {
    /**
     * A variable to hold @see SQLiteDatabase fetched from injected see IApplicationDatabaseManagement
     */
    private SQLiteDatabase database;

    /**
     * Initializes @see ApplicationDatabaseOperationsHandler
     *
     * @param appDbRefHolder @see IApplicationDatabaseManagement object injected by client.
     */
    public ApplicationDatabaseOperationsHandler(IApplicationDatabaseManagement appDbRefHolder) {
        database = (SQLiteDatabase) appDbRefHolder.getDatabase();
    }

    /**
     * Gets @see SQLiteDatabase used by the database operations handler.
     * @return
     */
    public SQLiteDatabase getDatabase()
    {
        return database;
    }
}
