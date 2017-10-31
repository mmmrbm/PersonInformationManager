package com.yukon.madhuranga.personinformationmanager.core.mvp.view;

import android.content.Context;

/**
 * Defines the contract which should be implemented by all UI components in the application.
 */
public interface IBaseView {

    /**
     * Displays a progress bar to indicate the action is processing.
     *
     */
    void displayProgressBar();

    /**
     * Hides the progress bar when action processing is completed.
     */
    void hideProgressBar();

    /**
     * Displays a toast message with a given text message. Useful to output error messages and
     * sucecss messages for user interactions.
     *
     * @param message The content to be displayed in the taost.
     */
    void displayToastMessage(String message);


    /**
     * Responsible to get the current @see Context.
     *
     * @return Current @see Context.
     */
    Context getContext();
}
