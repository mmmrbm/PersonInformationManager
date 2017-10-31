package com.yukon.madhuranga.personinformationmanager.feature.modifypersondetail.view;

/**
 * Defines the contract on communication channel between activity and the fragments adding
 * information of entities.
 */
public interface ICancelActionNotificationListner {
    /**
     * Notifies the parent activity when action was cancelled in fragment level.
     */
    void onActionCancellationInitiated();
}
