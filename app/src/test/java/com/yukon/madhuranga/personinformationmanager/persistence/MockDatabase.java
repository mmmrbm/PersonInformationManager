package com.yukon.madhuranga.personinformationmanager.persistence;

import com.yukon.madhuranga.personinformationmanager.core.mvp.model.IBaseModel;
import com.yukon.madhuranga.personinformationmanager.core.persistence.IApplicationDatabaseManagement;

import java.util.HashMap;

/**
 * A mock implementation of the application data store.
 */
public class MockDatabase implements IApplicationDatabaseManagement {
    /**
     * {@inheritDoc}
     */
    @Override
    public Object getDatabase() {
        return new HashMap<Integer, IBaseModel>() {};
    }
}
