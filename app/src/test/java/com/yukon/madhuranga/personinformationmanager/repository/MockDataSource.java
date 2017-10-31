package com.yukon.madhuranga.personinformationmanager.repository;

import com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student;
import com.yukon.madhuranga.personinformationmanager.base.mvp.model.Teacher;
import com.yukon.madhuranga.personinformationmanager.core.mvp.model.IBaseModel;
import com.yukon.madhuranga.personinformationmanager.core.persistence.IApplicationDatabaseManagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A mocked concrete implementation of {@link MockDatabaseOperationsHandler}
 */
public class MockDataSource extends MockDatabaseOperationsHandler {

    public MockDataSource(IApplicationDatabaseManagement appDbRefHolder)
    {
        super(appDbRefHolder);
    }

    @Override
    public long createRecord(IBaseModel entityToBePersisted) {
        int recordId = getDatabase().size();
        getDatabase().put((recordId + 1), entityToBePersisted);
        return getDatabase().size();
    }

    @Override
    public List<IBaseModel> fetchAllRecords() {
        Collection<IBaseModel> valuesFromDb = getDatabase().values();
        ArrayList<IBaseModel> valuesAsList = new ArrayList<IBaseModel>(valuesFromDb);
        return valuesAsList;
    }

    @Override
    public IBaseModel fetchRecordById(int entityIdentifier) {
        return getDatabase().get(entityIdentifier);
    }

    @Override
    public int updateRecord(IBaseModel entityToBePersisted) {
        if (entityToBePersisted instanceof Student)
        {
            Student studentInfoToUpdate = (Student) entityToBePersisted;
            Student studentInfoFromDb = (Student) getDatabase().get((Integer) entityToBePersisted.getRecordId());
            if (studentInfoToUpdate != null || studentInfoFromDb != null)
            {
                studentInfoFromDb.setName(studentInfoToUpdate.getName());
                studentInfoFromDb.setAge(studentInfoToUpdate.getAge());
                studentInfoFromDb.setStudentAssociationRegNo(studentInfoToUpdate.getStudentAssociationRegNo());
            }

        }
        else if (entityToBePersisted instanceof Teacher)
        {
            Teacher teacherInfoToUpdate = (Teacher) entityToBePersisted;
            Teacher teacherInfoFromDb = (Teacher) getDatabase().get((Integer) entityToBePersisted.getRecordId());
            if (teacherInfoToUpdate != null || teacherInfoFromDb != null)
            {
                teacherInfoFromDb.setName(teacherInfoToUpdate.getName());
                teacherInfoFromDb.setAge(teacherInfoToUpdate.getAge());
                teacherInfoFromDb.setHighestEducationalQualification(teacherInfoToUpdate.getHighestEducationalQualification());
            }
        }

        return getDatabase().size();
    }

    @Override
    public int deleteRecord(int entityIdentifier) {
        int recordId = getDatabase().size();
        getDatabase().remove(entityIdentifier);
        return getDatabase().size();
    }

    @Override
    public int countDatabaseRecords() {
        return getDatabase().size();
    }

    @Override
    public boolean isDatabaseEmpty() {
        return (getDatabase().size() == 0 ? true : false);
    }
}