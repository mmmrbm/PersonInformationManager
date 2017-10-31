package com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yukon.madhuranga.personinformationmanager.R;
import com.yukon.madhuranga.personinformationmanager.core.mvp.model.IBaseModel;
import com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.presenter.StudentListPresenter;
import com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.view.personinforecyclerview.IRecyclerViewItemClickListener;
import com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.view.personinforecyclerview.PersonInfoRecyclerViewTouchListener;
import com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.view.personinforecyclerview.PersonInformationRecyclerViewAdapter;
import com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.view.personinforecyclerview.RecyclerViewItemDivider;
import com.yukon.madhuranga.personinformationmanager.util.ConstantHolder;

import java.util.List;

/**
 * Fragment responsible to display the information of all
 * {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student} entities.
 */
public class StudentListFragment extends BaseListView {
    /**
     * Variable to hold the reference to the {@link RecyclerView} from UI which will be used to
     * display the list of entity infomration.
     */
    private RecyclerView mStudentInfoRecyclerView;

    /**
     * Variable to hold the reference to the {@link TextView} from UI which will be used to display
     * no data found message.
     */
    private TextView mNoStudentDataFoundMessageHolder;

    /**
     * Reference to {@link PersonInformationRecyclerViewAdapter} used to display @see Student data.
     */
    private PersonInformationRecyclerViewAdapter mStudentInformationRecyclerViewAdapter;

    /**
     * The collection of information on
     * {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student} which will be
     * fed to recylcer view returned as {@link IBaseModel}.
     */
    private List<IBaseModel> mStudentList;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_student_list, container, false);
        mProgressBar = (ProgressBar) fragmentView.findViewById(R.id.student_info_fetch_progress_bar);
        mStudentInfoRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.student_info_recycler_view);
        mNoStudentDataFoundMessageHolder = (TextView) fragmentView.findViewById(R.id.student_info_no_data_notification);
        mPresenter = new StudentListPresenter(this);

        return fragmentView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onResume() {
        super.onResume();
        ((StudentListPresenter) mPresenter).onPersonInfoListRequested();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayProgressBar() {
        super.displayProgressBar();
        mNoStudentDataFoundMessageHolder.setVisibility(View.GONE);
        mStudentInfoRecyclerView.setVisibility(View.VISIBLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideProgressBar() {
        super.hideProgressBar();
        mNoStudentDataFoundMessageHolder.setVisibility(View.GONE);
        mStudentInfoRecyclerView.setVisibility(View.VISIBLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void populateList(List<IBaseModel> entityList) {
        if (entityList.size() > 0)
        {
            this.mStudentList = entityList;
            setUpRecyclerViewWithAdapter();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayNoDataFoundMessage(String message) {
        mProgressBar.setVisibility(View.GONE);
        mStudentInfoRecyclerView.setVisibility(View.GONE);
        mNoStudentDataFoundMessageHolder.setVisibility(View.VISIBLE);
        mNoStudentDataFoundMessageHolder.setText(message);
    }

    /**
     * Sets recycler view with correct adapter and other information,
     */
    private void setUpRecyclerViewWithAdapter()
    {
        mStudentInformationRecyclerViewAdapter =
                new PersonInformationRecyclerViewAdapter(this.mStudentList, ConstantHolder.STUDENT_UI_TAB_HEADER);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mStudentInfoRecyclerView.setLayoutManager(mLayoutManager);
        mStudentInfoRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mStudentInfoRecyclerView.addItemDecoration(
                new RecyclerViewItemDivider(getContext(), LinearLayoutManager.VERTICAL));
        mStudentInfoRecyclerView.setAdapter(mStudentInformationRecyclerViewAdapter);

        mStudentInfoRecyclerView.addOnItemTouchListener(
                new PersonInfoRecyclerViewTouchListener(
                        getContext(),
                        mStudentInfoRecyclerView,
                        new IRecyclerViewItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                IBaseModel selectedEntity = mStudentList.get(position);
                                IListFragmentItemSelectedListner activityAsListner =
                                        (IListFragmentItemSelectedListner) getActivity();
                                activityAsListner.onEntitySelectedFromList(selectedEntity.getRecordId());
                            }

                            @Override
                            public void onLongClick(View view, int position) {

                            }
                        }));
    }


}
