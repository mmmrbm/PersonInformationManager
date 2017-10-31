package com.yukon.madhuranga.personinformationmanager.base.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.yukon.madhuranga.personinformationmanager.util.ConstantHolder;

/**
 * Entity responsible to create the SQLite database for the application.
 */
public class DatabaseInitializationManager {
    /**
     * Variable to hold the @see Context which will be passed to @see DatabaseDefinitionManager.
     */
    private Context applicationContext;

    /**
     * Variable to hold the @see DatabaseDefinitionManager which is used to execute DDL queries
     * against the database.
     */
    private DatabaseStructureDefinitionManager dbManager;

    /**
     * Variable to hold the reference to create @see SQLiteDatabase.
     */
    private SQLiteDatabase database;

    /**
     * Initializes @see DatabaseInitiateManager
     *
     * @param appContext The @see Context required to instantiate @see DatabaseDefinitionManager.
     */
    DatabaseInitializationManager(Context appContext)
    {
        applicationContext = appContext;
    }

    /**
     * Creates the SQLite database used by application.
     *
     * @return Created SQLite database.
     */
    public SQLiteDatabase createDatabase()
    {
        dbManager = new DatabaseStructureDefinitionManager(this.applicationContext);
        database = dbManager.getWritableDatabase();
        return database;
    }

    /**
     * Deletes the database used by the application.
     *
     * @return Status of the deletion process as a boolean.
     */
    public boolean deleteDatabase() {
        return this.applicationContext.deleteDatabase(ConstantHolder.DATABASE_NAME);
    }
}
