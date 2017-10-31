package com.yukon.madhuranga.personinformationmanager.feature.modifypersondetail.view;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.yukon.madhuranga.personinformationmanager.R;
import com.yukon.madhuranga.personinformationmanager.base.mvp.view.BaseView;

/**
 * The basic contract for common logic to be applied to UI components responsible to add information.
 */
public abstract class BaseModifyInfoView extends BaseView {
    /**
     * Variable to hold a reference to {@link AlertDialog.Builder} to generate dialog box to get
     * record delete confirmation.
     */
    protected AlertDialog.Builder dialogBuilder;

    /**
     * Initiates the operation for fetching persisted information and display on UI.
     */
    public abstract void populateWithPersistedData();

    /**
     * Initiates the operation for updating persisted entity information.
     */
    public abstract void updateEntityInformation();

    /**
     * Initiates the operation for deleting persisted entity information.
     */
    public abstract void deleteEntityInformation();

    /**
     * Cancels the operation of modifying persisted information of an entity.
     */
    public abstract void cancelOperation();

    /**
     * Investigates whether data has been modified by user. If data has not being modified
     * no need to call database.
     * @return true if data has changed by user, false otherwise.
     */
    public abstract boolean hasInfoUpdated();

    /**
     * Displays a dialog box to get the user confirmation before deleting the record.
     */
    protected void getRecordDeletionConfirmation()
    {
        dialogBuilder.setTitle(R.string.view_person_delete_confirm_dialog_title);
        dialogBuilder.setMessage(R.string.view_person_delete_confirm_dialog_message);

        dialogBuilder.setPositiveButton(
                R.string.view_person_delete_confirm_dialog_positive_button_text,
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        deleteEntityInformation();
                        dialog.dismiss();
                    }
                });

        dialogBuilder.setNegativeButton(
                R.string.view_person_delete_confirm_dialog_negative_button_text,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert = dialogBuilder.create();
        alert.show();
    }

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
