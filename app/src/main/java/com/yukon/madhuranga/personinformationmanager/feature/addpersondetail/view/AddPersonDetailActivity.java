package com.yukon.madhuranga.personinformationmanager.feature.addpersondetail.view;

import android.os.Bundle;
import android.os.PersistableBundle;
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
 * Activity to host the fragments which handles adding information on new entities.
 */
public class AddPersonDetailActivity extends AppCompatActivity implements ICancelActionNotificationListner {
    /**
     * Variable to hold the user selected fragment from the
     * {@link com.yukon.madhuranga.personinformationmanager.feature.displaypersonlist.view.MainActivity}.
     */
    private int selectedTabPosition;

    /**
     * Variable to hold the reference to fragment manager in current activity.
     */
    private FragmentManager mFragmentManager;

    /**
     * Variable to hold a reference to currently activated {@link Fragment} in the activity.
     */
    private Fragment currentFragment;

    /**
     * Variable to hold the key which needs to be used when Fragment Transactions are initiated.
     */
    private String keyForFragmentTransaction;

    /**
     * A string constant which act as a key when the state of active {@link Fragment} is saved.
     */
    private static final String SAVED_STATE_KEY_FOR_FRAGMENT = "SAVED_STATE_KEY_FOR_FRAGMENT";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);
        selectedTabPosition = getIntent().getExtras().getInt(ConstantHolder.SELECTED_TAB_KEY);

        mFragmentManager = this.getSupportFragmentManager();




        if (savedInstanceState != null) {
            currentFragment = mFragmentManager.getFragment(savedInstanceState, SAVED_STATE_KEY_FOR_FRAGMENT);
        }
        else
        {
            displayFragmentContent();
            replaceFragment(currentFragment, keyForFragmentTransaction);
        }
    }

    private void displayFragmentContent()
    {
        if (TabPositionToTextConverter.getTabHeaderTextCollection().get(selectedTabPosition).equals(
                ConstantHolder.TEACHER_UI_TAB_HEADER
        ))
        {
            currentFragment = new TeacherAddInfoFragment();
            keyForFragmentTransaction = ConstantHolder.ADD_TEACHER_INFO_FRAG_TRANS_KEY;
        }
        else if (TabPositionToTextConverter.getTabHeaderTextCollection().get(selectedTabPosition).equals(
                ConstantHolder.STUDENT_UI_TAB_HEADER
        ))
        {
            currentFragment = new StudentAddInfoFragment();
            keyForFragmentTransaction = ConstantHolder.ADD_STUDENT_INFO_FRAG_TRANS_KEY;
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
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.person_info_add_frame_container, fragmentToDisplay, fragmentTag);
        fragmentTransaction.commit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActionCancellationInitiated() {
        finish();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //Save the fragment's instance
        mFragmentManager.putFragment(outState, SAVED_STATE_KEY_FOR_FRAGMENT, currentFragment);
    }
}
