package com.yukon.madhuranga.personinformationmanager.base.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;

import com.yukon.madhuranga.personinformationmanager.base.mvp.model.Teacher;
import com.yukon.madhuranga.personinformationmanager.core.mvp.model.IBaseModel;
import com.yukon.madhuranga.personinformationmanager.core.persistence.IApplicationDatabaseManagement;
import com.yukon.madhuranga.personinformationmanager.util.ConstantHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents the model holding data source management logic for @see Teacher.
 */
public class TeacherInfoDataSourceManager extends ApplicationDatabaseOperationsHandler {
    /**
     * Initializes @see TeacherInfoDataSourceManager
     *
     * @param appDbRefHolder @see IApplicationDatabaseManagement object injected by client.
     */

    public TeacherInfoDataSourceManager(IApplicationDatabaseManagement appDbRefHolder)
    {
        super(appDbRefHolder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long createRecord(IBaseModel entityToBePersisted) {
        if (entityToBePersisted instanceof Teacher)
        {
            Teacher teacher = (Teacher) entityToBePersisted;
            ContentValues values = new ContentValues();
            values.put(ConstantHolder.COL_RECORD_ID, teacher.getRecordId());
            values.put(ConstantHolder.COL_PERSON_NAME, teacher.getName());
            values.put(ConstantHolder.COL_AGE, teacher.getAge());
            values.put(ConstantHolder.COL_TEACHER_HIGHEST_EDU_QUAL, teacher.getHighestEducationalQualification());
            return getDatabase().insert(
                    ConstantHolder.TEACHER_TABLE_NAME,
                    null,
                    values);
        }
        return 0;
    }

    @Override
    public List<IBaseModel> fetchAllRecords()
    {
        List<IBaseModel> teacherInfoFromDb = new ArrayList<IBaseModel>();

        String[] cols = new String[]{
                ConstantHolder.COL_RECORD_ID,
                ConstantHolder.COL_PERSON_NAME,
                ConstantHolder.COL_AGE,
                ConstantHolder.COL_TEACHER_HIGHEST_EDU_QUAL};

        Cursor cursor = getDatabase().query(
                true,
                ConstantHolder.TEACHER_TABLE_NAME,
                cols,
                null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int recordId = cursor.getInt(cursor.getColumnIndex(ConstantHolder.COL_RECORD_ID));
                String studentName = cursor.getString(cursor.getColumnIndex(ConstantHolder.COL_PERSON_NAME));
                int studentAge = cursor.getInt(cursor.getColumnIndex(ConstantHolder.COL_AGE));
                String studentAssocRegId = cursor.getString(cursor.getColumnIndex(ConstantHolder.COL_TEACHER_HIGHEST_EDU_QUAL));

                Teacher teacher = new Teacher();
                teacher.setRecordId(recordId);
                teacher.setName(studentName);
                teacher.setAge(studentAge);
                teacher.setHighestEducationalQualification(studentAssocRegId);

                teacherInfoFromDb.add(teacher);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return teacherInfoFromDb;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IBaseModel fetchRecordById(int entityIdentifier) {

        Teacher teacher = null;

        String[] cols = new String[]{
                ConstantHolder.COL_RECORD_ID,
                ConstantHolder.COL_PERSON_NAME,
                ConstantHolder.COL_AGE,
                ConstantHolder.COL_TEACHER_HIGHEST_EDU_QUAL};

        Cursor cursor =  getDatabase().query(
                true,
                ConstantHolder.TEACHER_TABLE_NAME,
                cols,
                ConstantHolder.COL_RECORD_ID + "= ?",
                new String[] { String.valueOf(entityIdentifier) }, null, null, null, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int recordId = cursor.getInt(cursor.getColumnIndex(ConstantHolder.COL_RECORD_ID));
                String teacherName = cursor.getString(cursor.getColumnIndex(ConstantHolder.COL_PERSON_NAME));
                int teacherAge = cursor.getInt(cursor.getColumnIndex(ConstantHolder.COL_AGE));
                String teacherAssocRegId = cursor.getString(cursor.getColumnIndex(ConstantHolder.COL_TEACHER_HIGHEST_EDU_QUAL));

                teacher = new Teacher();
                teacher.setRecordId(recordId);
                teacher.setName(teacherName);
                teacher.setAge(teacherAge);
                teacher.setHighestEducationalQualification(teacherAssocRegId);

                cursor.moveToNext();
            }
        }
        cursor.close();

        return teacher;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateRecord(IBaseModel entityToBePersisted)
    {
        if (entityToBePersisted instanceof Teacher)
        {
            Teacher teacher = (Teacher) entityToBePersisted;
            ContentValues values = new ContentValues();
            values.put(ConstantHolder.COL_PERSON_NAME, teacher.getName());
            values.put(ConstantHolder.COL_AGE, teacher.getAge());
            values.put(ConstantHolder.COL_TEACHER_HIGHEST_EDU_QUAL, teacher.getHighestEducationalQualification());
            return getDatabase().update(
                    ConstantHolder.TEACHER_TABLE_NAME,
                    values,
                    ConstantHolder.COL_RECORD_ID + " = ?",
                    new String[] { String.valueOf(teacher.getRecordId()) });
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
                ConstantHolder.TEACHER_TABLE_NAME,
                ConstantHolder.COL_RECORD_ID + " = ?",
                new String[] { String.valueOf(entityIdentifier) });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int countDatabaseRecords()
    {
        return (int) DatabaseUtils.queryNumEntries(getDatabase(), ConstantHolder.TEACHER_TABLE_NAME);
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
