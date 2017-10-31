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
import com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.presenter.TeacherListPresenter;
import com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.view.personinforecyclerview.IRecyclerViewItemClickListener;
import com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.view.personinforecyclerview.PersonInfoRecyclerViewTouchListener;
import com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.view.personinforecyclerview.PersonInformationRecyclerViewAdapter;
import com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.view.personinforecyclerview.RecyclerViewItemDivider;
import com.yukon.madhuranga.personinformationmanager.util.ConstantHolder;

import java.util.List;

/**
 * Fragment responsible to display the information of all
 * {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Teacher} entities.
 */
public class TeacherListFragment extends BaseListView {
    /**
     * Variable to hold the reference to the {@link RecyclerView} from UI which will be used to
     * display the list of entity infomration.
     */
    private RecyclerView mTeacherInfoRecyclerView;

    /**
     * Variable to hold the reference to the {@link TextView} from UI which will be used to display
     * no data found message.
     */
    private TextView mNoTeacherDataFoundMessageHolder;

    /**
     * Reference to {@link PersonInformationRecyclerViewAdapter} used to display @see Student data.
     */
    private PersonInformationRecyclerViewAdapter mTeacherInformationRecyclerViewAdapter;

    /**
     * The collection of information on
     * {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student} which will be
     * fed to recylcer view.
     */
    private List<IBaseModel> mTeacherList;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_teacher_list, container, false);
        mProgressBar = (ProgressBar) fragmentView.findViewById(R.id.teacher_info_fetch_progress_bar);
        mTeacherInfoRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.teacher_info_recycler_view);
        mNoTeacherDataFoundMessageHolder = (TextView) fragmentView.findViewById(R.id.teacher_info_no_data_notification);
        mPresenter = new TeacherListPresenter(this);

        return fragmentView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onResume() {
        super.onResume();
        this.performCustomStartupActions();
    }

    /**
     * Invokes the presenter functionality.
     */
    private void performCustomStartupActions()
    {
        ((TeacherListPresenter) mPresenter).onPersonInfoListRequested();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayProgressBar() {
        super.displayProgressBar();
        mNoTeacherDataFoundMessageHolder.setVisibility(View.GONE);
        mTeacherInfoRecyclerView.setVisibility(View.GONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideProgressBar() {
        super.hideProgressBar();
        mNoTeacherDataFoundMessageHolder.setVisibility(View.GONE);
        mTeacherInfoRecyclerView.setVisibility(View.VISIBLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void populateList(List<IBaseModel> entityList) {
        if (entityList.size() > 0)
        {
            this.mTeacherList = entityList;
            setUpRecyclerViewWithAdapter();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayNoDataFoundMessage(String message) {
        mProgressBar.setVisibility(View.GONE);
        mTeacherInfoRecyclerView.setVisibility(View.GONE);
        mNoTeacherDataFoundMessageHolder.setVisibility(View.VISIBLE);
        mNoTeacherDataFoundMessageHolder.setText(message);
    }

    /**
     * Sets recycler view with correct adapter and other information,
     */
    private void setUpRecyclerViewWithAdapter()
    {
        mTeacherInformationRecyclerViewAdapter =
                new PersonInformationRecyclerViewAdapter(this.mTeacherList, ConstantHolder.TEACHER_UI_TAB_HEADER);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mTeacherInfoRecyclerView.setLayoutManager(mLayoutManager);
        mTeacherInfoRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTeacherInfoRecyclerView.addItemDecoration(
                new RecyclerViewItemDivider(getContext(), LinearLayoutManager.VERTICAL));
        mTeacherInfoRecyclerView.setAdapter(mTeacherInformationRecyclerViewAdapter);

        mTeacherInfoRecyclerView.addOnItemTouchListener(
                new PersonInfoRecyclerViewTouchListener(
                        getContext(),
                        mTeacherInfoRecyclerView,
                        new IRecyclerViewItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                IBaseModel selectedEntity = mTeacherList.get(position);
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
