package com.yukon.madhuranga.personinformationmanager.feature.modifypersondetail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.yukon.madhuranga.personinformationmanager.R;
import com.yukon.madhuranga.personinformationmanager.util.ConstantHolder;
import com.yukon.madhuranga.personinformationmanager.util.TabPositionToTextConverter;

/**
 * Activity to host the fragments which handles modifying information on persisted entities.
 */
public class ModifyPersonDetailActivity extends AppCompatActivity implements ICancelActionNotificationListner {
    /**
     * Variable to hold the user selected fragment tab position as passed from the
     * {@link com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.view.MainActivity}.
     */
    private int selectedTabPosition;

    /**
     * Variable to hold the record id of the user selected entity as passed from the
     * {@link com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.view.MainActivity}.
     */
    private int selectedRecordId;

    /**
     * Variable to hold the reference to frame layout in the activity to load fragments dynamically.
     */
    private FrameLayout frameLayout;

    /**
     * Variable to hold the reference to fragment manager in current activity.
     */
    private FragmentManager fragmentManager;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_info);

        selectedTabPosition = getIntent().getExtras().getInt(ConstantHolder.SELECTED_TAB_KEY);
        selectedRecordId = getIntent().getExtras().getInt(ConstantHolder.SELECTED_ENTITY_IDENTIFIER_KEY);

        fragmentManager = this.getSupportFragmentManager();

        int backstack = fragmentManager.getBackStackEntryCount();

        if (TabPositionToTextConverter.getTabHeaderTextCollection().get(selectedTabPosition).equals(
                ConstantHolder.TEACHER_UI_TAB_HEADER
        ))
        {
            replaceFragment(new TeacherModifyInfoFragment(), ConstantHolder.MODIFY_TEACHER_INFO_FRAG_TRANS_KEY);
        }
        else if (TabPositionToTextConverter.getTabHeaderTextCollection().get(selectedTabPosition).equals(
                ConstantHolder.STUDENT_UI_TAB_HEADER
        ))
        {
            replaceFragment(new StudentModifyInfoFragment(), ConstantHolder.MODIFY_STUDENT_INFO_FRAG_TRANS_KEY);
        }
    }

    /**
     * Replaces fragment in the display area.
     * @param fragmentToDisplay The fragment to be displayed in the viewing area.
     * @param fragmentTag Tag to be used in the @see FragmentTransaction.
     */
    private void replaceFragment(
            Fragment fragmentToDisplay,
            String fragmentTag )
    {
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantHolder.SELECTED_ENTITY_IDENTIFIER_KEY, selectedRecordId );
        fragmentToDisplay.setArguments(bundle);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.person_info_modify_frame_container, fragmentToDisplay, fragmentTag);
        fragmentTransaction.commit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActionCancellationInitiated() {
        finish();
    }
}
