package com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.presenter;

import com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student;
import com.yukon.madhuranga.personinformationmanager.base.persistence.SqliteApplicationDatabaseManagement;
import com.yukon.madhuranga.personinformationmanager.base.repository.StudentInfoDataSourceManager;
import com.yukon.madhuranga.personinformationmanager.core.mvp.model.IBaseModel;
import com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.view.BaseListView;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation for presenter associated with concrete UI to display
 * {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student} list information.
 */
public class StudentListPresenter extends BaseListPresenter {
    /**
     * String constant to hold the message to be displayed when no data is found
     */
    private final static String noDataFoundMessage = "No Student Data Found in the Database";

    /**
     * Initializes {@link StudentListPresenter}
     * @param view View associated with presenter.
     */
    public StudentListPresenter(BaseListView view) {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPersonInfoListRequested()
    {
        List<IBaseModel> studentInfoList = new ArrayList<>();
        StudentInfoDataSourceManager studentDataSource = null;

        try
        {
            studentDataSource = new StudentInfoDataSourceManager(SqliteApplicationDatabaseManagement.getInstance());

            mView.displayProgressBar();
            if (studentDataSource.isDatabaseEmpty())
            {
                this.onNoDataFound();
                return;
            }

            studentInfoList = studentDataSource.fetchAllRecords();
            mView.hideProgressBar();
            ((BaseListView)mView).populateList(studentInfoList);
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
