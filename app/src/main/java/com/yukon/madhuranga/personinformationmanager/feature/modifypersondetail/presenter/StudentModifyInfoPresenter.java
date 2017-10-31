package com.yukon.madhuranga.personinformationmanager.feature.modifypersondetail.presenter;

import com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student;
import com.yukon.madhuranga.personinformationmanager.base.persistence.SqliteApplicationDatabaseManagement;
import com.yukon.madhuranga.personinformationmanager.base.repository.StudentInfoDataSourceManager;
import com.yukon.madhuranga.personinformationmanager.feature.modifypersondetail.view.BaseModifyInfoView;
import com.yukon.madhuranga.personinformationmanager.util.ConstantHolder;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * The basic implementation for common logic to be applied to presenters managing UI responsible
 * to modify information of {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student}.
 */
public class StudentModifyInfoPresenter extends BaseModifyInfoPresenter {
    /**
     * Initializes {@link StudentModifyInfoPresenter}
     * @param view associated with the presenter.
     */
    public StudentModifyInfoPresenter(BaseModifyInfoView view) {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dictionary<String, String> onGetEntityInformationByRecordIdRequest(int recordId)
    {
        Dictionary<String, String> resultSet = null;
        StudentInfoDataSourceManager studentDataSource = null;
        Student persistedStudent = null;

        try
        {
            mView.displayProgressBar();
            resultSet = new Hashtable<String, String>();
            studentDataSource = new StudentInfoDataSourceManager(SqliteApplicationDatabaseManagement.getInstance());
            persistedStudent = (Student) studentDataSource.fetchRecordById(recordId);
            resultSet.put(ConstantHolder.STUDENT_NAME_KEY, persistedStudent.getName());
            resultSet.put(ConstantHolder.STUDENT_AGE_KEY, String.valueOf(persistedStudent.getAge()));
            resultSet.put(ConstantHolder.STUDENT_GPA_KEY, String.valueOf(persistedStudent.getAverageGradeMark()));
            resultSet.put(ConstantHolder.STUDENT_ASSOC_NO_KEY, persistedStudent.getStudentAssociationRegNo());
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
     * Handles the information update request for a {@link Student}.
     * @param recordId Identifier for the {@link Student}.
     * @param newStudentName Updated name for the {@link Student}.
     * @param newStudentAge Updated age for the {@link Student}.
     * @param newStudentGpa Updated GPA for the {@link Student}.
     * @param newStudentAssociationNo Updated Association No for the {@link Student}.
     */
    public void onStudentInfoUpdateRequest(
            int recordId,
            String newStudentName,
            int newStudentAge,
            float newStudentGpa,
            String newStudentAssociationNo
    )
    {
        Student persistedStudent = null;
        StudentInfoDataSourceManager studentDataSource = null;

        try
        {
            mView.displayProgressBar();
            studentDataSource = new StudentInfoDataSourceManager(SqliteApplicationDatabaseManagement.getInstance());
            persistedStudent = (Student) studentDataSource.fetchRecordById(recordId);

            if (persistedStudent != null)
            {
                persistedStudent.setName(newStudentName);
                persistedStudent.setAge(newStudentAge);
                persistedStudent.setAverageGradeMark(newStudentGpa);
                persistedStudent.setStudentAssociationRegNo(newStudentAssociationNo);

                studentDataSource.updateRecord(persistedStudent);
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
     * Handles the information delete request for a {@link Student}.
     * @param recordId Identifier of the {@link Student} record.
     */
    public void onDeleteEntityInformationRequest(int recordId)
    {
        StudentInfoDataSourceManager studentDataSource = null;

        try
        {
            mView.hideProgressBar();
            studentDataSource = new StudentInfoDataSourceManager(SqliteApplicationDatabaseManagement.getInstance());
            studentDataSource.deleteRecord(recordId);
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
