package com.yukon.madhuranga.personinformationmanager.base.mvp.view;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yukon.madhuranga.personinformationmanager.core.mvp.presenter.IBasePresenter;
import com.yukon.madhuranga.personinformationmanager.core.mvp.view.IBaseView;


/**
 * The basic implementation for common logic to be applied to @see Fragment used in the application.
 */
public abstract class BaseView extends Fragment implements IBaseView {
    /**
     * Variable to hold the reference to the presenter associated with view.
     */
    protected IBasePresenter mPresenter;

    /**
     * Variable to hold the reference to the {@link ProgressBar} from UI which will indicate the
     * progress of loading data to the list.
     */
    protected ProgressBar mProgressBar;

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayToastMessage(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter.isViewAttached())
        {
            mPresenter.onViewBackground();
        }
    }
}
