package com.yukon.madhuranga.personinformationmanager;

import android.app.Application;

import com.yukon.madhuranga.personinformationmanager.base.persistence.SqliteApplicationDatabaseManagement;

/**
 * Custom {@link android.app.Application} class created to handle application specific logic.
 */
public class PersonInformationManagerApplication extends Application {
    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate() {
        super.onCreate();

        // Database initialization logic
        SqliteApplicationDatabaseManagement applicationDatabaseManager =
                SqliteApplicationDatabaseManagement.getInstance();
        applicationDatabaseManager.initializeApplicationDatabase(this);
    }
}
