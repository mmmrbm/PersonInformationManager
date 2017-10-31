package com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.view.personinforecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yukon.madhuranga.personinformationmanager.R;

/**
 * Represents the entity containing the logic governing the information view when @see Student
 * entry from recycler view.
 */
public class PersonInformationViewHolder extends RecyclerView.ViewHolder {
    /**
     * Variables to hold {@link ImageView} from UI.
     */
    public ImageView personPhoto;

    /**
     * Variables to hold {@link TextView} from UI.
     */
    public TextView recordId, personName, personAge;

    /**
     * Initializes @see StudentInformationViewHolder.
     *
     * @param view Reference to the UI layout.
     */
    public PersonInformationViewHolder(View view) {
        super(view);
        personPhoto = (ImageView) view.findViewById(R.id.person_photo);
        recordId = (TextView) view.findViewById(R.id.person_record_id);
        personName = (TextView) view.findViewById(R.id.person_name);
        personAge = (TextView) view.findViewById(R.id.person_age);
    }
}
