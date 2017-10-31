package com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.view;

/**
 * Defines the contract on communication channel between activity and the fragments holding
 * information collection.
 */
public interface IListFragmentItemSelectedListner {
    /**
     * Notifies the parent activity when an item is selected by user.
     * @param recordId the identifier of the item selected by user.
     */
    void onEntitySelectedFromList(int recordId);
}

