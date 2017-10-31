package com.yukon.madhuranga.personinformationmanager.feature.addpersondetail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.yukon.madhuranga.personinformationmanager.R;
import com.yukon.madhuranga.personinformationmanager.feature.addpersondetail.presenter.StudentAddInfoPresenter;

/**
 * Fragment responsible to add information of a new
 * {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student} entity.
 */
public class StudentAddInfoFragment extends BaseAddInfoView {
    /**
     * Variable to hold the reference to the {@link Button} which is responsible to handle persist
     * {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student} information.
     */
    private Button mPersistStudentInfoActionButton;

    /**
     * Variable to hold the reference to the {@link Button} which is responsible to handle the
     * cancellation of persisting new information.
     */
    private Button mCancelStudentInfoActionButton;

    /**
     * Variable to hold the references to the input fields containing information on the new
     * {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student}.
     */
    private EditText mStudentName, mStudentAge, mStudentGPA, mStudentAssociationNo;

    private static final String STUDENT_NAME_SAVE_INSTANT_KEY = "STUDENT_NAME_SAVE_INSTANT_KEY",
                                STUDENT_AGE_SAVE_INSTANT_KEY = "STUDENT_AGE_SAVE_INSTANT_KEY",
                                STUDENT_GPA_SAVE_INSTANT_KEY = "STUDENT_GPA_SAVE_INSTANT_KEY",
                                STUDENT_ASSOC_NO_SAVE_INSTANT_KEY = "STUDENT_ASSOC_NO_SAVE_INSTANT_KEY";

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_add_student_info, container, false);

        mProgressBar = (ProgressBar) fragmentView.findViewById(R.id.student_info_add_progress_bar);

        mStudentName = (EditText) fragmentView.findViewById(R.id.student_name_value);
        mStudentAge = (EditText) fragmentView.findViewById(R.id.student_age_value);
        mStudentGPA = (EditText) fragmentView.findViewById(R.id.student_gpa_value);
        mStudentAssociationNo = (EditText) fragmentView.findViewById(R.id.student_assoc_no_value);

        if(savedInstanceState != null)
        {
            mStudentName.setText(String.valueOf(savedInstanceState.get(STUDENT_NAME_SAVE_INSTANT_KEY)));
            mStudentAge.setText(String.valueOf(savedInstanceState.get(STUDENT_AGE_SAVE_INSTANT_KEY)));
            mStudentGPA.setText(String.valueOf(savedInstanceState.get(STUDENT_GPA_SAVE_INSTANT_KEY)));
            mStudentAssociationNo.setText(String.valueOf(savedInstanceState.get(STUDENT_ASSOC_NO_SAVE_INSTANT_KEY)));
        }

        mPersistStudentInfoActionButton = (Button) fragmentView.findViewById(R.id.button_add_student_info_action);
        mPersistStudentInfoActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persistEntityInfo();
            }
        });

        mCancelStudentInfoActionButton = (Button) fragmentView.findViewById(R.id.button_add_student_info_cancel);
        mCancelStudentInfoActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelOperation();
            }
        });

        mPresenter = new StudentAddInfoPresenter(this);

        return fragmentView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void persistEntityInfo() {
        String studentName = mStudentName.getText().toString();
        int studentAge = Integer.parseInt(mStudentAge.getText().toString());
        float studentGPA = Float.parseFloat(mStudentGPA.getText().toString());
        String studentAssociationNo = mStudentAssociationNo.getText().toString();

        ((StudentAddInfoPresenter) mPresenter).onStudentInfoPersistRequest(studentName, studentAge, studentGPA, studentAssociationNo);

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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(STUDENT_NAME_SAVE_INSTANT_KEY, mStudentName.getText().toString());
        outState.putInt(STUDENT_AGE_SAVE_INSTANT_KEY, Integer.parseInt(mStudentAge.getText().toString()));
        outState.putFloat(STUDENT_GPA_SAVE_INSTANT_KEY, Float.parseFloat(mStudentGPA.getText().toString()));
        outState.putString(STUDENT_ASSOC_NO_SAVE_INSTANT_KEY, mStudentAssociationNo.getText().toString());
    }
}
