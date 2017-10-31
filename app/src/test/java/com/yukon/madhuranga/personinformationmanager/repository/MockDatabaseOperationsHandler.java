package com.yukon.madhuranga.personinformationmanager.repository;

import com.yukon.madhuranga.personinformationmanager.core.mvp.model.IBaseModel;
import com.yukon.madhuranga.personinformationmanager.core.persistence.IApplicationDatabaseManagement;
import com.yukon.madhuranga.personinformationmanager.core.repository.IApplicationDatabaseOperationsHandler;

import java.util.HashMap;

/**
 * Mock implementation of {@link IApplicationDatabaseOperationsHandler}
 */
public abstract class MockDatabaseOperationsHandler implements IApplicationDatabaseOperationsHandler {
    private HashMap<Integer, IBaseModel> database;

    public MockDatabaseOperationsHandler(IApplicationDatabaseManagement appDbRefHolder) {
        database = (HashMap<Integer, IBaseModel>) appDbRefHolder.getDatabase();
    }

    public HashMap<Integer, IBaseModel> getDatabase()
    {
        return database;
    }
}
