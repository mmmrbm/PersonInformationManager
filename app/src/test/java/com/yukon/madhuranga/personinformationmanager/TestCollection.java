package com.yukon.madhuranga.personinformationmanager;

import com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student;
import com.yukon.madhuranga.personinformationmanager.core.mvp.model.IBaseModel;
import com.yukon.madhuranga.personinformationmanager.persistence.MockDatabase;
import com.yukon.madhuranga.personinformationmanager.repository.MockDataSource;

import java.util.List;

/**
 * Collection of tests.
 */
public class TestCollection {
    /**
     * Variable to hold the refere the mock data source.
     */
    private MockDataSource dataSource;

    /**
     * Initializes the {@link TestCollection}
     * @param database The data source injected from client.
     */
    public TestCollection(MockDatabase database)
    {
        dataSource = new MockDataSource(database);
    }

    public boolean testAddEntity()
    {
        int recordCountBeforeAddingEntity = dataSource.countDatabaseRecords();

        Student student = new Student();
        student.setRecordId(recordCountBeforeAddingEntity + 1);
        student.setName("Test Student");
        student.setAge(0);
        student.setStudentAssociationRegNo("Test Assoc Id");

        dataSource.createRecord(student);

        int expected = (recordCountBeforeAddingEntity + 1);
        int actual = dataSource.countDatabaseRecords();

        return expected == actual;
    }


    public boolean testFetchAllEntities()
    {
        List<IBaseModel> allInfoFromDb = dataSource.fetchAllRecords();

        int expected = dataSource.countDatabaseRecords();
        int actual = allInfoFromDb.size();

        return expected == actual;
    }


    public boolean testFetchEntityById(int recordId)
    {
        IBaseModel entity = dataSource.fetchRecordById(recordId);

        int expected = entity.getRecordId();
        int actual = recordId;

        return expected == actual;
    }


    public boolean testRemoveEntity()
    {
        int recordCountBeforeRemovingEntity = dataSource.countDatabaseRecords();

        dataSource.deleteRecord(recordCountBeforeRemovingEntity);

        int expected = (recordCountBeforeRemovingEntity - 1);
        int actual = dataSource.countDatabaseRecords();

        return expected == actual;
    }
}
