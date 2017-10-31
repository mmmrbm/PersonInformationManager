package com.yukon.madhuranga.personinformationmanager.feature.modifypersondetail.presenter;

import com.yukon.madhuranga.personinformationmanager.base.mvp.model.Teacher;
import com.yukon.madhuranga.personinformationmanager.base.persistence.SqliteApplicationDatabaseManagement;
import com.yukon.madhuranga.personinformationmanager.base.repository.TeacherInfoDataSourceManager;
import com.yukon.madhuranga.personinformationmanager.feature.modifypersondetail.view.BaseModifyInfoView;
import com.yukon.madhuranga.personinformationmanager.util.ConstantHolder;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * The basic implementation for common logic to be applied to presenters managing UI responsible
 * to modify information of {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Teacher}.
 */
public class TeacherModifyInfoPresenter extends BaseModifyInfoPresenter {
    /**
     * Initializes {@link StudentModifyInfoPresenter}
     * @param view associated with the presenter.
     */
    public TeacherModifyInfoPresenter(BaseModifyInfoView view) {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dictionary<String, String> onGetEntityInformationByRecordIdRequest(int recordId)
    {
        Dictionary<String, String> resultSet = null;
        TeacherInfoDataSourceManager teacherDataSource = null;
        Teacher persistedTeacher = null;

        try
        {
            mView.displayProgressBar();
            resultSet = new Hashtable<String, String>();
            teacherDataSource = new TeacherInfoDataSourceManager(SqliteApplicationDatabaseManagement.getInstance());
            persistedTeacher = (Teacher) teacherDataSource.fetchRecordById(recordId);
            resultSet.put(ConstantHolder.TEACHER_NAME_KEY, persistedTeacher.getName());
            resultSet.put(ConstantHolder.TEACHER_AGE_KEY, String.valueOf(persistedTeacher.getAge()));
            resultSet.put(ConstantHolder.TEACHER_HIGHEST_EDU_QUAL_KEY, persistedTeacher.getHighestEducationalQualification());
            mView.hideProgressBar();
        }
        catch(Exception ex)
        {
            mView.hideProgressBar();
            onErrorOccured(ex.getMessage());
        }

        return resultSet;
    }

    /**
     * Handles the information update request for a {@link Teacher}.
     * @param recordId Identifier for the {@link Teacher}.
     * @param newTeacherName Updated name for the {@link Teacher}.
     * @param newTeacherAge Updated age for the {@link Teacher}.
     * @param newTeacherHighestEduQual Updated Association No for the {@link Teacher}.
     */
    public void onTeacherInfoUpdateRequest(
            int recordId,
            String newTeacherName,
            int newTeacherAge,
            String newTeacherHighestEduQual
    )
    {
        Teacher persistedTeacher = null;
        TeacherInfoDataSourceManager teacherDataSource = null;

        try
        {
            mView.displayProgressBar();
            teacherDataSource = new TeacherInfoDataSourceManager(SqliteApplicationDatabaseManagement.getInstance());
            persistedTeacher = (Teacher) teacherDataSource.fetchRecordById(recordId);

            if (persistedTeacher != null)
            {
                persistedTeacher.setName(newTeacherName);
                persistedTeacher.setAge(newTeacherAge);
                persistedTeacher.setHighestEducationalQualification(newTeacherHighestEduQual);

                teacherDataSource.updateRecord(persistedTeacher);
            }
            mView.hideProgressBar();
        }
        catch (Exception ex)
        {
            mView.hideProgressBar();
            onErrorOccured(ex.getMessage());
        }
    }

    /**
     * Handles the information delete request for a {@link Teacher}.
     * @param recordId Identifier of the {@link Teacher} record.
     */
    public void onDeleteEntityInformationRequest(int recordId)
    {
        TeacherInfoDataSourceManager teacherDataSource = null;

        try
        {
            mView.displayProgressBar();
            teacherDataSource = new TeacherInfoDataSourceManager(SqliteApplicationDatabaseManagement.getInstance());
            teacherDataSource.deleteRecord(recordId);
            mView.hideProgressBar();
        }
        catch (Exception ex)
        {
            mView.hideProgressBar();
            onErrorOccured(ex.getMessage());
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onErrorOccured(String errorMessage) {
        mView.displayToastMessage(errorMessage);
    }
}
