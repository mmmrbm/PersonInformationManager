package com.yukon.madhuranga.personinformationmanager.feature.addpersondetail.presenter;

import com.yukon.madhuranga.personinformationmanager.base.mvp.model.Teacher;
import com.yukon.madhuranga.personinformationmanager.base.persistence.SqliteApplicationDatabaseManagement;
import com.yukon.madhuranga.personinformationmanager.base.repository.TeacherInfoDataSourceManager;
import com.yukon.madhuranga.personinformationmanager.feature.addpersondetail.view.BaseAddInfoView;

/**
 * The basic implementation for common logic to be applied to presenters managing UI responsible
 * to add information of {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Teacher}.
 */
public class TeacherAddInfoPresenter extends BaseAddInfoPresenter {
    /**
     * Initializes {@link TeacherAddInfoPresenter}
     *
     * @param view associated with the presenter.
     */
    public TeacherAddInfoPresenter(BaseAddInfoView view) {
        super(view);
    }

    /**
     * Handles the information persist request for a new {@link Teacher}.
     * @param teacherName Name of the {@link Teacher}.
     * @param teacherAge Age of the {@link Teacher}.
     * @param teacherHighestEduQual Highest Education Qualification of the {@link Teacher}.
     */
    public void onTeacherInfoPersistRequest(
            String teacherName,
            int teacherAge,
            String teacherHighestEduQual
    )
    {
        Teacher newTeacher = null;
        TeacherInfoDataSourceManager teacherDataSource = null;

        try {
            mView.displayProgressBar();
            newTeacher = new Teacher();
            newTeacher.setName(teacherName);
            newTeacher.setAge(teacherAge);
            newTeacher.setHighestEducationalQualification(teacherHighestEduQual);

            teacherDataSource = new TeacherInfoDataSourceManager(SqliteApplicationDatabaseManagement.getInstance());
            long currentRecordCount = teacherDataSource.countDatabaseRecords();
            int recordId = (int) (currentRecordCount + 1);
            newTeacher.setRecordId(recordId);
            teacherDataSource.createRecord(newTeacher);
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
