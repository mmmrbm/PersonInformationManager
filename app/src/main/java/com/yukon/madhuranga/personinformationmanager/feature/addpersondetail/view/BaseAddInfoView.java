package com.yukon.madhuranga.personinformationmanager.feature.addpersondetail.view;

import com.yukon.madhuranga.personinformationmanager.base.mvp.view.BaseView;
import com.yukon.madhuranga.personinformationmanager.core.mvp.model.IBaseModel;

/**
 * The basic contract for common logic to be applied to UI components responsible to add information.
 */
public abstract class BaseAddInfoView extends BaseView {
    /**
     * Initiates the operation for persisting entity information.
     */
    public abstract void persistEntityInfo();

    /**
     * Cancels the operation of adding persisting new information.
     */
    public abstract void cancelOperation();

    /**
     * Indicates the user has completed working with current view, hence finishing off the view.
     */
    protected void markCompletionOfWork()
    {
        ICancelActionNotificationListner activityAsListner =
                (ICancelActionNotificationListner) getActivity();
        activityAsListner.onActionCancellationInitiated();
    }
}
