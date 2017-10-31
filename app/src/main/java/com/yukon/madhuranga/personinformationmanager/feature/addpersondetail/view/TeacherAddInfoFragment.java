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
import com.yukon.madhuranga.personinformationmanager.feature.addpersondetail.presenter.TeacherAddInfoPresenter;

/**
 * Fragment responsible to add information of a new
 * {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Teacher} entity.
 */
public class TeacherAddInfoFragment extends BaseAddInfoView {
    /**
     * Variable to hold the reference to the {@link Button} which is responsible to handle persist
     * {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student} information.
     */
    private Button mPersistTeacherInfoActionButton;

    /**
     * Variable to hold the reference to the {@link Button} which is responsible to handle the
     * cancellation of persisting new information.
     */
    private Button mCancelTeachernfoActionButton;

    /**
     * Variable to hold the references to the input fields containing information on the new
     * {@link com.yukon.madhuranga.personinformationmanager.base.mvp.model.Student}.
     */
    private EditText mTeacherName, mTeacherAge, mTeacherHighestEduQual;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_add_teacher_info, container, false);

        mProgressBar = (ProgressBar) fragmentView.findViewById(R.id.teacher_info_add_progress_bar);

        mTeacherName = (EditText) fragmentView.findViewById(R.id.teacher_name_value);
        mTeacherAge = (EditText) fragmentView.findViewById(R.id.teacher_age_value);
        mTeacherHighestEduQual = (EditText) fragmentView.findViewById(R.id.teacher_highest_edu_qual_value);

        mPersistTeacherInfoActionButton = (Button) fragmentView.findViewById(R.id.button_add_teacher_info_action);
        mPersistTeacherInfoActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persistEntityInfo();
            }
        });

        mCancelTeachernfoActionButton = (Button) fragmentView.findViewById(R.id.button_add_teacher_info_cancel);
        mCancelTeachernfoActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelOperation();
            }
        });

        mPresenter = new TeacherAddInfoPresenter(this);

        return fragmentView;
    }

    @Override
    public void persistEntityInfo() {
        String teacherName = mTeacherName.getText().toString();
        int teacherAge = Integer.parseInt(mTeacherAge.getText().toString());
        String teacherHighestEduQual = mTeacherHighestEduQual.getText().toString();

        ((TeacherAddInfoPresenter) mPresenter).onTeacherInfoPersistRequest(teacherName, teacherAge, teacherHighestEduQual);

        markCompletionOfWork();
    }

    @Override
    public void cancelOperation() {
        markCompletionOfWork();
    }
}
