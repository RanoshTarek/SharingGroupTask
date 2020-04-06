package android.com.sharinggrouptask.View.activity;

import android.com.sharinggrouptask.MyApplication;
import android.com.sharinggrouptask.R;
import android.com.sharinggrouptask.View.fragment.UserDetailsFragment;
import android.com.sharinggrouptask.View.fragment.UserListFragment;
import android.com.sharinggrouptask.databinding.ActivityMainBinding;
import android.com.sharinggrouptask.db.entity.User;
import android.com.sharinggrouptask.di.Component.ApplicationComponent;
import android.com.sharinggrouptask.di.Component.DaggerMainActivityComponent;
import android.com.sharinggrouptask.di.Component.MainActivityComponent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

import javax.inject.Inject;

import static android.com.sharinggrouptask.helper.StaticValue.mTwoPane;

/******************************************************************************
 * Module: MainActivity
 *
 * File Name: MainActivity.java
 *
 * Description: Source file for MainActivity
 *
 * Author: Rana Tarek
 ******************************************************************************/
public class MainActivity extends AppCompatActivity {
    @Inject
    UserListFragment listFragment;
    @Inject
    UserDetailsFragment detailsFragment;
    FragmentTransaction transaction;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDaggerComponent();
        Objects.requireNonNull(getSupportActionBar()).hide();
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        if (binding.itemDetailContainer != null) {
            mTwoPane = true;
        }
        replaceUserListFragment();
    }

    /************************************************************************************
     * Function Name: initDaggerComponent
     * Parameters (in): none
     * Return value: None
     * Description: responsible to initial Dagger Component
     **********************************************************************************/
    private void initDaggerComponent() {
        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
        mainActivityComponent.injectMainActivity(this);
    }

    /************************************************************************************
     * Function Name: replaceUserListFragment
     * Parameters (in): User- user
     * Return value: None
     * Description: responsible to replace replaceUserListFragment*
     **********************************************************************************/
    public void replaceUserListFragment() {
        listFragment = new UserListFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, listFragment);
        transaction.addToBackStack("tag");
        transaction.commit();
    }

    /************************************************************************************
     * Function Name: replaceUserDetailsFragment
     * Parameters (in): User- user
     * Return value: None
     * Description: responsible to replace UserDetailsFragment*
     **********************************************************************************/
    public void replaceUserDetailsFragment(User user) {
        detailsFragment = new UserDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("User", user);
        detailsFragment.setArguments(bundle);
        binding.toolbar.backIcon.setVisibility(View.VISIBLE);
        transaction = getSupportFragmentManager().beginTransaction();
        if (mTwoPane) {
            transaction.replace(R.id.itemDetailContainer, detailsFragment);
            binding.toolbar.backIcon.setVisibility(View.GONE);
        } else {
            transaction.replace(R.id.fragmentContainer, detailsFragment);
            binding.toolbar.titleTextView.setText(R.string.profile);
            binding.toolbar.backIcon.setOnClickListener(v -> this.onBackPressed());
        }
        transaction.addToBackStack("tag");
        transaction.commit();
    }

    /************************************************************************************
     * Function Name: setListFragmentToolbar
     * Parameters (in): None
     * Return value: None
     * Description: responsible to set Toolbar of List Fragment and hide back icon*
     **********************************************************************************/
    public void setListFragmentToolbar() {
        binding.toolbar.titleTextView.setText(R.string.app_name);
        binding.toolbar.backIcon.setVisibility(View.GONE);
    }
}
