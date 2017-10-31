package com.yukon.madhuranga.personinformationmanager.feature.modifypersondetail.view;

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
import com.yukon.madhuranga.personinformationmanager.feature.modifypersondetail.presenter.StudentModifyInfoPresenter;
import com.yukon.madhuranga.personinformationmanager.util.ConstantHolder;

import java.util.Hashtable;

/**
 * Fragment responsible to modify information of a persisted
 * {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student} entity.
 */
public class StudentModifyInfoFragment extends BaseModifyInfoView {
    /**
     * Record id of the selected {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student}
     * passed by parent activity.
     */
    private int mRecordIdOfSelectedEntity;

    /**
     * Variable to hold the reference to the {@link Button} which is responsible to handle persist
     * {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student} information.
     */
    private Button mDeleteStudentInfoActionButton;

    /**
     * Variable to hold the reference to the {@link Button} which is responsible to handle the
     * cancellation of persisting new information.
     */
    private Button mModifyStudentInfoActionButton;

    /**
     * Variable to hold the references to the input fields containing information on the new
     * {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student}.
     */
    private EditText mStudentName, mStudentAge, mStudentGPA, mStudentAssociationNo;

    /**
     * Variables to hold the student name and association no returned from database.
     */
    private String persistedStudentName, persistedStudentAssociationNo;

    /**
     * Variables to hold the student age returned from database.
     */
    private int persistedStudentAge;

    /**
     * Variables to hold the student GPA returned from database.
     */
    private float persistedStudentGPA;

    /**
     * Variables to hold the student name and association no when data update confirmation is fired.
     */
    private String currentStudentName, currentStudentAssociationNo;

    /**
     * Variables to hold the student age when data update confirmation is fired.
     */
    private int currentStudentAge;

    /**
     * Variables to hold the student GPA when data update confirmation is fired.
     */
    private float currentStudentGPA;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_modify_student_info, container, false);

        Bundle bundle = this.getArguments();
        mRecordIdOfSelectedEntity = bundle.getInt(ConstantHolder.SELECTED_ENTITY_IDENTIFIER_KEY);

        dialogBuilder = new AlertDialog.Builder(getContext());

        mProgressBar = (ProgressBar) fragmentView.findViewById(R.id.student_info_modify_progress_bar);

        mStudentName = (EditText) fragmentView.findViewById(R.id.student_modify_name_value);
        mStudentAge = (EditText) fragmentView.findViewById(R.id.student_modify_age_value);
        mStudentGPA = (EditText) fragmentView.findViewById(R.id.student_modify_gpa_value);
        mStudentAssociationNo = (EditText) fragmentView.findViewById(R.id.student_modify_assoc_no_value);

        mDeleteStudentInfoActionButton = (Button) fragmentView.findViewById(R.id.button_delete_student_info_action);
        mDeleteStudentInfoActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRecordDeletionConfirmation();
            }
        });

        mModifyStudentInfoActionButton = (Button) fragmentView.findViewById(R.id.button_update_student_info_action);
        mModifyStudentInfoActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEntityInformation();
            }
        });

        mPresenter = new StudentModifyInfoPresenter(this);

        populateWithPersistedData();

        return fragmentView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void populateWithPersistedData() {
        Hashtable<String, String> persistedInformation =
                (Hashtable<String, String>) ((StudentModifyInfoPresenter) mPresenter).onGetEntityInformationByRecordIdRequest(mRecordIdOfSelectedEntity);

        persistedStudentName = persistedInformation.get(ConstantHolder.STUDENT_NAME_KEY);
        persistedStudentAge = Integer.parseInt(persistedInformation.get(ConstantHolder.STUDENT_AGE_KEY));
        persistedStudentGPA = Float.parseFloat(persistedInformation.get(ConstantHolder.STUDENT_GPA_KEY));
        persistedStudentAssociationNo = persistedInformation.get(ConstantHolder.STUDENT_ASSOC_NO_KEY);

        mStudentName.setText(persistedStudentName);
        mStudentAge.setText(String.valueOf(persistedStudentAge));
        mStudentGPA.setText(String.valueOf(persistedStudentGPA));
        mStudentAssociationNo.setText(persistedStudentAssociationNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEntityInformation() {
        if (hasInfoUpdated())
        {
            ((StudentModifyInfoPresenter) mPresenter).onStudentInfoUpdateRequest(
                    mRecordIdOfSelectedEntity,
                    currentStudentName,
                    currentStudentAge,
                    currentStudentGPA,
                    currentStudentAssociationNo);
        }

        markCompletionOfWork();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteEntityInformation() {
        ((StudentModifyInfoPresenter) mPresenter).onDeleteEntityInformationRequest(mRecordIdOfSelectedEntity);

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
        currentStudentName = mStudentName.getText().toString();
        currentStudentAge = Integer.parseInt(mStudentAge.getText().toString());
        currentStudentGPA = Float.parseFloat(mStudentGPA.getText().toString());
        currentStudentAssociationNo = mStudentAssociationNo.getText().toString();

        return (    (!(persistedStudentName.equals(currentStudentName)))
                    ||  (persistedStudentAge != currentStudentAge)
                    ||  (persistedStudentGPA != currentStudentGPA)
                    ||  (!(persistedStudentAssociationNo.equals(currentStudentAssociationNo))));
    }
}
