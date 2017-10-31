package com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.view.personinforecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yukon.madhuranga.personinformationmanager.R;
import com.yukon.madhuranga.personinformationmanager.base.mvp.model.BasePerson;
import com.yukon.madhuranga.personinformationmanager.core.mvp.model.IBaseModel;
import com.yukon.madhuranga.personinformationmanager.util.ConstantHolder;

import java.util.List;

/**
 * Represents a custom Recycler View Adapter to display information on
 * {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student}
 */
public class PersonInformationRecyclerViewAdapter
        extends RecyclerView.Adapter<PersonInformationViewHolder> {
    /**
     * A variable to hold the information of @see Student as a @see List
     */
    private List<IBaseModel> personInformationList;

    /**
     * A variable to hold the person type to determine type related information as image.
     */
    private String personType;

    /**
     * Initializes @see StudentInformationRecyclerViewAdapter.
     *
     * @param personInfoList Information of @see Student injected from client.
     */
    public PersonInformationRecyclerViewAdapter(
            List<IBaseModel> personInfoList,
            String sourcePersonType) {
        this.personInformationList = personInfoList;
        this.personType = sourcePersonType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonInformationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_person_row, parent, false);

        return new PersonInformationViewHolder(itemView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(PersonInformationViewHolder holder, int position) {
        BasePerson person = (BasePerson) personInformationList.get(position);

        if (this.personType.equals(ConstantHolder.TEACHER_UI_TAB_HEADER))
        {
            holder.personPhoto.setImageResource(R.drawable.teacher);
        }
        else if (this.personType.equals(ConstantHolder.STUDENT_UI_TAB_HEADER))
        {
            holder.personPhoto.setImageResource(R.drawable.student);
        }

        holder.recordId.setText(String.valueOf(person.getRecordId()));
        holder.personName.setText(person.getName());
        holder.personAge.setText(String.valueOf(person.getAge()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return personInformationList.size();
    }
}
