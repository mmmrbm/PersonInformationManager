package com.yukon.madhuranga.personinformationmanager.feature.addpersondetail.presenter;

import com.yukon.madhuranga.personinformationmanager.base.mvp.presenter.BasePresenter;
import com.yukon.madhuranga.personinformationmanager.feature.addpersondetail.view.BaseAddInfoView;
import com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.presenter.BaseListPresenter;

/**
 * The basic implementation for common logic to be applied to presenters managing UI responsible
 * to add information.
 */
public abstract class BaseAddInfoPresenter extends BasePresenter {
    /**
     * Initializes {@link BaseListPresenter}
     *
     * @param view View associated with Presenter.
     */
    public BaseAddInfoPresenter(BaseAddInfoView view) {
        mView = view;
    }

    /**
     * Governs the logic to handle if an error occur while persisting data.
     * @param ErrorMessage Error message describing the error.
     */
    public abstract void onErrorOccured(String ErrorMessage);
}
