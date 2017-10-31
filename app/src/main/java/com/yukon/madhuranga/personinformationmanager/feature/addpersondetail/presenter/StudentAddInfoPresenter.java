package com.yukon.madhuranga.personinformationmanager.feature.addpersondetail.presenter;

import com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student;
import com.yukon.madhuranga.personinformationmanager.base.persistence.SqliteApplicationDatabaseManagement;
import com.yukon.madhuranga.personinformationmanager.base.repository.StudentInfoDataSourceManager;
import com.yukon.madhuranga.personinformationmanager.feature.addpersondetail.view.BaseAddInfoView;

/**
 * The basic implementation for common logic to be applied to presenters managing UI responsible
 * to add information of {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student}.
 */
public class StudentAddInfoPresenter extends BaseAddInfoPresenter {
    /**
     * Initializes {@link StudentAddInfoPresenter}
     *
     * @param view associated with the presenter.
     */
    public StudentAddInfoPresenter(BaseAddInfoView view) {
        super(view);
    }

    /**
     * Handles the information persist request for a new {@link Student}.
     * @param studentName Name of the {@link Student}.
     * @param studentAge Age of the {@link Student}.
     * @param studentGpa GPA of the {@link Student}.
     * @param studentAssociationNo Association No of the {@link Student}.
     */
    public void onStudentInfoPersistRequest(
            String studentName,
            int studentAge,
            float studentGpa,
            String studentAssociationNo
    )
    {
        Student newStudent = null;
        StudentInfoDataSourceManager studentDataSource = null;

        try {
            mView.displayProgressBar();
            newStudent = new Student();
            newStudent.setName(studentName);
            newStudent.setAge(studentAge);
            newStudent.setAverageGradeMark(studentGpa);
            newStudent.setStudentAssociationRegNo(studentAssociationNo);

            studentDataSource = new StudentInfoDataSourceManager(SqliteApplicationDatabaseManagement.getInstance());
            long currentRecordCount = studentDataSource.countDatabaseRecords();
            int recordId = (int) (currentRecordCount + 1);
            newStudent.setRecordId(recordId);
            studentDataSource.createRecord(newStudent);
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
    public void onErrorOccured(String ErrorMessage)
    {
        mView.displayToastMessage(ErrorMessage);
    }
}
