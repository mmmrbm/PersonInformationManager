package com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.view;

import com.yukon.madhuranga.personinformationmanager.base.mvp.view.BaseView;
import com.yukon.madhuranga.personinformationmanager.core.mvp.model.IBaseModel;

import java.util.List;

/**
 * The basic implementation for common logic to be applied to UI components hosting lists.
 */
public abstract class BaseListView extends BaseView {

    /**
     * Populates the list view component attached to the concrete view with entity information list.
     *
     * @param entityList List of application entities to be displayed in the list UI component.
     */
    public abstract void populateList(List<IBaseModel> entityList);

    /**
     * Displays no data found message in the list view component attached to the concrete view.
     *
     * @param message To be displayed on the list view component.
     */
    public abstract void displayNoDataFoundMessage(String message);

}
