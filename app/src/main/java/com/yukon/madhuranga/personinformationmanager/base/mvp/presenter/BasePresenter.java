package com.yukon.madhuranga.personinformationmanager.base.mvp.presenter;

import com.yukon.madhuranga.personinformationmanager.core.mvp.presenter.IBasePresenter;
import com.yukon.madhuranga.personinformationmanager.core.mvp.view.IBaseView;

/**
 * The basic implementation for common logic to be applied to all presenters in the application.
 */
public abstract class BasePresenter implements IBasePresenter {

    /**
     * Denotes a @see IBaseView which will be associated with the presenter.
     */
    protected IBaseView mView;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewForeground(IBaseView view) {
        this.mView = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewBackground() {
        mView = null;
    }

    /**
     * Check if the view is attached.
     * This checking is only necessary when returning from an asynchronous call
     */
    public final boolean isViewAttached() {
        return mView != null;
    }
}
