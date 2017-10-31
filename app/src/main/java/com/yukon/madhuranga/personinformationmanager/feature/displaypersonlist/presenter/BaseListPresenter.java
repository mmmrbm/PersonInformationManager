package com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.presenter;

import com.yukon.madhuranga.personinformationmanager.base.mvp.presenter.BasePresenter;
import com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.view.BaseListView;

/**
 * The basic implementation for common logic to be applied to presenters managing UI hosting lists.
 */
public abstract class BaseListPresenter extends BasePresenter {

    /**
     * Initializes {@link BaseListPresenter}
     *
     * @param view View associated with Presenter.
     */
    public BaseListPresenter(BaseListView view) {
        mView = view;
    }

    /**
     * Handles logic to process request to display
     * {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.BasePerson} list.
     */
    public abstract void onPersonInfoListRequested();

    /**
     * Governs the logic to handle to display no data found.
     */
    public abstract void onNoDataFound();

    /**
     * Governs the logic to handle if an error occur while persisting data.
     * @param errorMessage describing the error.
     */
    public abstract void onErrorOccured(String errorMessage);
}
