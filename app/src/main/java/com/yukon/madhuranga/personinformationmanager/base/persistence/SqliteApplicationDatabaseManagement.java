package com.yukon.madhuranga.personinformationmanager.base.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.yukon.madhuranga.personinformationmanager.core.persistence.IApplicationDatabaseManagement;

/**
 * A concrete implementation of @see IApplicationDatabaseManagement for the SQLiteDatabase
 * used for the application.
 */
public class SqliteApplicationDatabaseManagement implements IApplicationDatabaseManagement {
    /**
     * A variable to hold a reference to self to implement the Singleton design pattern.
     */
    private static SqliteApplicationDatabaseManagement sqliteDatabaseReferenceHolderSingleton = null;

    /**
     * A variable to hold the reference to the created database.
     */
    private SQLiteDatabase database;

    /**
     * Initializes @see SqliteApplicationDatabaseManagement.
     * A private constructor to limit the instantiation of the model due to Singleton pattern.
     */
    private SqliteApplicationDatabaseManagement() {
    }

    /**
     * A thread-safe, static method to get the single instance of @see SqliteApplicationDatabaseManagement.
     *
     * @return The single instance of @see SqliteApplicationDatabaseManagement.
     */
    public synchronized static SqliteApplicationDatabaseManagement getInstance() {
        if(sqliteDatabaseReferenceHolderSingleton == null) {
            sqliteDatabaseReferenceHolderSingleton = new SqliteApplicationDatabaseManagement();
        }
        return sqliteDatabaseReferenceHolderSingleton;
    }

    /**
     * Responsible to initialize the database used in the application.
     *
     * @param applicationContext @see Context to be used to create the database.
     */
    public void initializeApplicationDatabase(Context applicationContext)
    {
        if (database == null)
        {
            DatabaseInitializationManager databaseInitiateManager =
                    new DatabaseInitializationManager(applicationContext);
            database = databaseInitiateManager.createDatabase();
        }
    }

    /**
     * Responsible to delete the application database.
     *
     * @param applicationContext @see Context to be used to create the database.
     */
    public void deleteApplicationDatabase(Context applicationContext)
    {
        DatabaseInitializationManager databaseInitiateManager =
                new DatabaseInitializationManager(applicationContext);
        databaseInitiateManager.deleteDatabase();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SQLiteDatabase getDatabase() {
        return database;
    }
}
