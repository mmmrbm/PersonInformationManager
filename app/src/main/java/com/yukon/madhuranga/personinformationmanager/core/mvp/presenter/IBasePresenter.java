package com.yukon.madhuranga.personinformationmanager.core.mvp.presenter;

import com.yukon.madhuranga.personinformationmanager.core.mvp.view.IBaseView;

/**
 * Defines the contract which should be implemented by all presenters in the application.
 */
public interface IBasePresenter {
    /**
     * Responsible to create required references to the view associated to presenter.
     *
     * @param view The view governed by Presenter.
     */
    void onViewForeground(IBaseView view);

    /**
     * Responsible to delete references of the view associated to presenter to avoid memory leaks.
     */
    void onViewBackground();

    /**
     * Check if the view is attached. Used when returning from an asynchronous call.
     */
    boolean isViewAttached();
}
