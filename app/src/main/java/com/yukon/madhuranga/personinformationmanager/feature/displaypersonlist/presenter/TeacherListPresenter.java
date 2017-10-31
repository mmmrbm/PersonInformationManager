package com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.presenter;

import com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student;
import com.yukon.madhuranga.personinformationmanager.base.persistence.SqliteApplicationDatabaseManagement;
import com.yukon.madhuranga.personinformationmanager.base.repository.TeacherInfoDataSourceManager;
import com.yukon.madhuranga.personinformationmanager.core.mvp.model.IBaseModel;
import com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.view.BaseListView;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation for presenter associated with concrete UI to display
 * {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Teacher} list information.
 */
public class TeacherListPresenter extends BaseListPresenter {
    /**
     * String constant to hold the message to be displayed when no data is found
     */
    private final static String noDataFoundMessage = "No Teacher Data Found in the Database";

    /**
     * Initializes the {@link TeacherListPresenter}
     * @param view The view associated with the Presenter.
     */
    public TeacherListPresenter(BaseListView view) {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPersonInfoListRequested()
    {
        List<IBaseModel> teacherInfoList = new ArrayList<>();
        TeacherInfoDataSourceManager teacherDataSource = null;

        try
        {
            teacherDataSource = new TeacherInfoDataSourceManager(SqliteApplicationDatabaseManagement.getInstance());
            mView.displayProgressBar();
            if (teacherDataSource.isDatabaseEmpty())
            {
                this.onNoDataFound();
                return;
            }
            teacherInfoList = teacherDataSource.fetchAllRecords();
            mView.hideProgressBar();
            ((BaseListView)mView).populateList(teacherInfoList);
        }
        catch(Exception ex)
        {
            mView.hideProgressBar();
            onErrorOccured(ex.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onNoDataFound()
    {
        ((BaseListView)mView).displayNoDataFoundMessage(noDataFoundMessage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onErrorOccured(String errorMessage)
    {
        mView.displayToastMessage(errorMessage);
    }
}
