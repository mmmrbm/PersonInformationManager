package com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.view.personinforecyclerview;

import android.view.View;

/**
 * Defines the contract for the Recycler View item listner.
 */
public interface IRecyclerViewItemClickListener {
    /**
     * Responsible to handle the user click event on Recycler View item.
     * @param view The Recycler View item.
     * @param position Position in the recycler view.
     */
    void onClick(View view, int position);

    /**
     * Responsible to handle the user long click event on Recycler View item.
     * @param view The Recycler View item.
     * @param position Position in the recycler view.
     */
    void onLongClick(View view, int position);
}
