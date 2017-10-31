package com.yukon.madhuranga.personinformationmanager.base.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;

import com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student;
import com.yukon.madhuranga.personinformationmanager.core.mvp.model.IBaseModel;
import com.yukon.madhuranga.personinformationmanager.core.persistence.IApplicationDatabaseManagement;
import com.yukon.madhuranga.personinformationmanager.util.ConstantHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the model holding data source management logic for @see Student.
 */
public class StudentInfoDataSourceManager extends ApplicationDatabaseOperationsHandler {
    /**
     * Initializes @see StudentInfoDataSourceManager
     *
     * @param appDbRefHolder @see IApplicationDatabaseManagement object injected by client.
     */
    public StudentInfoDataSourceManager(IApplicationDatabaseManagement appDbRefHolder) {
        super(appDbRefHolder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long createRecord(IBaseModel entityToBePersisted) {
        if (entityToBePersisted instanceof Student)
        {
            Student student = (Student) entityToBePersisted;

            ContentValues values = new ContentValues();
            values.put(ConstantHolder.COL_RECORD_ID, student.getRecordId());
            values.put(ConstantHolder.COL_PERSON_NAME, student.getName());
            values.put(ConstantHolder.COL_AGE, student.getAge());
            values.put(ConstantHolder.COL_STUDENT_AVG_GRADE, student.getAverageGradeMark());
            values.put(ConstantHolder.COL_STUDENT_ASSOC_REG_NO, student.getStudentAssociationRegNo());
            return getDatabase().insert(
                    ConstantHolder.STUDENT_TABLE_NAME,
                    null,
                    values);
        }

        return Long.valueOf(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IBaseModel> fetchAllRecords()
    {
        List<IBaseModel> studentInfoFromDb = new ArrayList<IBaseModel>();

        String[] cols = new String[]{
                ConstantHolder.COL_RECORD_ID,
                ConstantHolder.COL_PERSON_NAME,
                ConstantHolder.COL_AGE,
                ConstantHolder.COL_STUDENT_AVG_GRADE,
                ConstantHolder.COL_STUDENT_ASSOC_REG_NO};

        Cursor cursor =  getDatabase().query(
                true,
                ConstantHolder.STUDENT_TABLE_NAME,
                cols,
                null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int recordId = cursor.getInt(cursor.getColumnIndex(ConstantHolder.COL_RECORD_ID));
                String studentName = cursor.getString(cursor.getColumnIndex(ConstantHolder.COL_PERSON_NAME));
                int studentAge = cursor.getInt(cursor.getColumnIndex(ConstantHolder.COL_AGE));
                float studentGPA = cursor.getFloat(cursor.getColumnIndex(ConstantHolder.COL_STUDENT_AVG_GRADE));
                String studentAssocRegId = cursor.getString(cursor.getColumnIndex(ConstantHolder.COL_STUDENT_ASSOC_REG_NO));

                Student student = new Student();
                student.setRecordId(recordId);
                student.setName(studentName);
                student.setAge(studentAge);
                student.setAverageGradeMark(studentGPA);
                student.setStudentAssociationRegNo(studentAssocRegId);

                studentInfoFromDb.add(student);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return studentInfoFromDb;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IBaseModel fetchRecordById(int entityIdentifier) {
        Student student = null;

        String[] cols = new String[]{
                ConstantHolder.COL_RECORD_ID,
                ConstantHolder.COL_PERSON_NAME,
                ConstantHolder.COL_AGE,
                ConstantHolder.COL_STUDENT_AVG_GRADE,
                ConstantHolder.COL_STUDENT_ASSOC_REG_NO};

        Cursor cursor =   getDatabase().query(
                true,
                ConstantHolder.STUDENT_TABLE_NAME,
                cols,
                ConstantHolder.COL_RECORD_ID + " = ?",
                new String[] { String.valueOf(entityIdentifier) }, null, null, null, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int recordId = cursor.getInt(cursor.getColumnIndex(ConstantHolder.COL_RECORD_ID));
                String studentName = cursor.getString(cursor.getColumnIndex(ConstantHolder.COL_PERSON_NAME));
                int studentAge = cursor.getInt(cursor.getColumnIndex(ConstantHolder.COL_AGE));
                float studentGPA = cursor.getInt(cursor.getColumnIndex(ConstantHolder.COL_STUDENT_AVG_GRADE));
                String studentAssocRegId = cursor.getString(cursor.getColumnIndex(ConstantHolder.COL_STUDENT_ASSOC_REG_NO));

                student = new Student();
                student.setRecordId(recordId);
                student.setName(studentName);
                student.setAge(studentAge);
                student.setAverageGradeMark(studentGPA);
                student.setStudentAssociationRegNo(studentAssocRegId);

                cursor.moveToNext();
            }
        }
        cursor.close();

        return student;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateRecord(IBaseModel entityToBePersisted)
    {
        if (entityToBePersisted instanceof Student)
        {
            Student student = (Student) entityToBePersisted;

            ContentValues values = new ContentValues();
            values.put(ConstantHolder.COL_PERSON_NAME, student.getName());
            values.put(ConstantHolder.COL_AGE, student.getAge());
            values.put(ConstantHolder.COL_STUDENT_AVG_GRADE, student.getAverageGradeMark());
            values.put(ConstantHolder.COL_STUDENT_ASSOC_REG_NO, student.getStudentAssociationRegNo());
            return getDatabase().update(
                    ConstantHolder.STUDENT_TABLE_NAME,
                    values,
                    ConstantHolder.COL_RECORD_ID + " = ?",
                    new String[] { String.valueOf(student.getRecordId()) });
        }

        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteRecord(int entityIdentifier)
    {
        return getDatabase().delete(
                ConstantHolder.STUDENT_TABLE_NAME,
                ConstantHolder.COL_RECORD_ID + " = ?",
                new String[] { String.valueOf(entityIdentifier) });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int countDatabaseRecords()
    {
        return (int) DatabaseUtils.queryNumEntries(getDatabase(), ConstantHolder.STUDENT_TABLE_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDatabaseEmpty()
    {
        return (countDatabaseRecords() == 0 ? true : false);
    }
}
