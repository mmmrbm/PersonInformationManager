package com.yukon.madhuranga.personinformationmanager.feature.modifypersondetail.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.yukon.madhuranga.personinformationmanager.R;
import com.yukon.madhuranga.personinformationmanager.feature.modifypersondetail.presenter.TeacherModifyInfoPresenter;
import com.yukon.madhuranga.personinformationmanager.util.ConstantHolder;

import java.util.Hashtable;

/**
 * Fragment responsible to modify information of a persisted
 * {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Teacher} entity.
 */
public class TeacherModifyInfoFragment extends BaseModifyInfoView {
    /**
     * Record id of the selected {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student}
     * passed by parent activity.
     */
    private int mRecordIdOfSelectedEntity;

    /**
     * Variable to hold the reference to the {@link Button} which is responsible to handle persist
     * {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student} information.
     */
    private Button mDeleteTeacherInfoActionButton;

    /**
     * Variable to hold the reference to the {@link Button} which is responsible to handle the
     * cancellation of persisting new information.
     */
    private Button mModifyTeacherInfoActionButton;

    /**
     * Variable to hold the references to the input fields containing information on the new
     * {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student}.
     */
    private EditText mTeacherName, mTeacherAge, mTeacherHighestEduQual;

    /**
     * Variables to hold the Teacher name and Highest Educational Qualification returned from database.
     */
    private String persistedTeacherName, persistedTeacherHighestEduQual;

    /**
     * Variables to hold the Teacher age returned from database.
     */
    private int persistedTeacherAge;

    /**
     * Variables to hold the Teacher name and association no when data update confirmation is fired.
     */
    private String currentTeacherName, currentTeacherHighestEduQual;

    /**
     * Variables to hold the student age when data update confirmation is fired.
     */
    private int currentTeacherAge;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_modify_teacher_info, container, false);

        Bundle bundle = this.getArguments();
        mRecordIdOfSelectedEntity = bundle.getInt(ConstantHolder.SELECTED_ENTITY_IDENTIFIER_KEY);

        dialogBuilder = new AlertDialog.Builder(getContext());

        mProgressBar = (ProgressBar) fragmentView.findViewById(R.id.teacher_info_modify_progress_bar);

        mTeacherName = (EditText) fragmentView.findViewById(R.id.teacher_modify_name_value);
        mTeacherAge = (EditText) fragmentView.findViewById(R.id.teacher_modify_age_value);
        mTeacherHighestEduQual = (EditText) fragmentView.findViewById(R.id.teacher_modify_highest_edu_qual_value);

        mDeleteTeacherInfoActionButton = (Button) fragmentView.findViewById(R.id.button_delete_teacher_info_action);
        mDeleteTeacherInfoActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRecordDeletionConfirmation();
            }
        });

        mModifyTeacherInfoActionButton = (Button) fragmentView.findViewById(R.id.button_update_teacher_info_action);
        mModifyTeacherInfoActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEntityInformation();
            }
        });

        mPresenter = new TeacherModifyInfoPresenter(this);

        populateWithPersistedData();

        return fragmentView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void populateWithPersistedData() {
        Hashtable<String, String> persistedInformation =
                (Hashtable<String, String>) ((TeacherModifyInfoPresenter) mPresenter).onGetEntityInformationByRecordIdRequest(mRecordIdOfSelectedEntity);

        persistedTeacherName = persistedInformation.get(ConstantHolder.TEACHER_NAME_KEY);
        persistedTeacherAge = Integer.parseInt(persistedInformation.get(ConstantHolder.TEACHER_AGE_KEY));
        persistedTeacherHighestEduQual = persistedInformation.get(ConstantHolder.TEACHER_HIGHEST_EDU_QUAL_KEY);

        mTeacherName.setText(persistedTeacherName);
        mTeacherAge.setText(String.valueOf(persistedTeacherAge));
        mTeacherHighestEduQual.setText(persistedTeacherHighestEduQual);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEntityInformation() {
        if(hasInfoUpdated())
        {
            ((TeacherModifyInfoPresenter) mPresenter).onTeacherInfoUpdateRequest(
                    mRecordIdOfSelectedEntity,
                    currentTeacherName,
                    currentTeacherAge,
                    currentTeacherHighestEduQual);
        }

        markCompletionOfWork();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteEntityInformation() {
        ((TeacherModifyInfoPresenter) mPresenter).onDeleteEntityInformationRequest(mRecordIdOfSelectedEntity);

        markCompletionOfWork();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelOperation() {
        markCompletionOfWork();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasInfoUpdated()
    {
        currentTeacherName = mTeacherName.getText().toString();
        currentTeacherAge = Integer.parseInt(mTeacherAge.getText().toString());
        currentTeacherHighestEduQual = mTeacherHighestEduQual.getText().toString();

        return (    (!(persistedTeacherName.equals(currentTeacherName)))
                ||  (persistedTeacherAge != currentTeacherAge)
                ||  (!(persistedTeacherHighestEduQual.equals(currentTeacherHighestEduQual))));
    }
}
