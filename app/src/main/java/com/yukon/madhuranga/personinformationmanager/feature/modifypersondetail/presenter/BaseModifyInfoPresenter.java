package com.yukon.madhuranga.personinformationmanager.feature.modifypersondetail.presenter;

import com.yukon.madhuranga.personinformationmanager.base.mvp.presenter.BasePresenter;
import com.yukon.madhuranga.personinformationmanager.feature.modifypersondetail.view.BaseModifyInfoView;

import java.util.Dictionary;

/**
 * The basic implementation for common logic to be applied to presenters managing UI responsible
 * to modify (update and delete) information.
 */
public abstract class BaseModifyInfoPresenter extends BasePresenter {
    /**
     * Initializes {@link BaseModifyInfoPresenter}
     *
     * @param view View associated with Presenter.
     */
    public BaseModifyInfoPresenter(BaseModifyInfoView view) {
        mView = view;
    }

    /**
     * Fetches information on entity of interest and return as a collection of {@link String}.
     * UI component data input elements deals values as String, regardless of value's true type.
     *
     * @param recordId Identifier of the record for the entity.
     * @return The fields of the entity which should be displayed in UI as a dictionary.
     *         Dictionary was returned as UI already know the fields it should display.
     */
    public abstract Dictionary<String, String> onGetEntityInformationByRecordIdRequest(int recordId);

    /**
     * Deletes the information for an entity.
     * @param recordId Identifier of the entity which will be deleted.
     */
    public abstract void onDeleteEntityInformationRequest(int recordId);

    /**
     * Governs the logic to handle if an error occur while persisting data.
     * @param errorMessage Error message describing the error.
     */
    public abstract void onErrorOccured(String errorMessage);
}
