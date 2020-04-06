package android.com.sharinggrouptask.View.fragment;


import android.com.sharinggrouptask.MyApplication;
import android.com.sharinggrouptask.R;
import android.com.sharinggrouptask.ViewModel.UserListViewModel;
import android.com.sharinggrouptask.ViewModel.ViewModelFactory;
import android.com.sharinggrouptask.databinding.FragmentUserDetailsBinding;
import android.com.sharinggrouptask.db.entity.User;
import android.com.sharinggrouptask.di.Component.ApplicationComponent;
import android.com.sharinggrouptask.di.Component.DaggerFragmentUserDetailsComponent;
import android.com.sharinggrouptask.di.Component.FragmentUserDetailsComponent;
import android.com.sharinggrouptask.model.repository.UserRepository;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import javax.inject.Inject;

/******************************************************************************
 * Module: UserDetailsFragment
 *
 * File Name: UserDetailsFragment.java
 *
 * Description: Source file for UserDetailsFragment
 *
 * Author: Rana Tarek
 ******************************************************************************/
public class UserDetailsFragment extends Fragment {
    @Inject
    UserRepository userRepository;
    @Inject
    ViewModelFactory viewModelFactory;
    private UserListViewModel viewModel;
    private User userLocal;
    private FragmentUserDetailsBinding binding;

    public UserDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_details, container, false);
        View view = binding.getRoot();
        binding.setLifecycleOwner(this);
        initDaggerComponent();
        initViewModel();
        return view;
    }
    /************************************************************************************
     * Function Name: initDaggerComponent
     * Parameters (in): none
     * Return value: None
     * Description: responsible to initial Dagger Component
     **********************************************************************************/
    private void initDaggerComponent() {
        ApplicationComponent applicationComponent = MyApplication.get(Objects.requireNonNull(getActivity())).getApplicationComponent();
        FragmentUserDetailsComponent fragmentUserDetailsComponent = DaggerFragmentUserDetailsComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
        fragmentUserDetailsComponent.injectUserDetailsFragment(this);
    }
    /************************************************************************************
     * Function Name: initViewModel
     * Parameters (in): none
     * Return value: None
     * Description: responsible to initial ViewModel
     **********************************************************************************/
    private void initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserListViewModel.class);
        Bundle bundle = getArguments();
        if (bundle != null) {
            userLocal = bundle.getParcelable("User");
            getUserData();
        }

    }
    /************************************************************************************
     * Function Name: getUserData
     * Parameters (in): none
     * Return value: None
     * Description: responsible to get User Details
     **********************************************************************************/
    private void getUserData() {
        viewModel.getUserDetails(userLocal.getId()).observe(this, user -> {
            binding.loadingLayout.setVisibility(View.GONE);
            if (user != null) {
                binding.setUser(user);
            } else {
                binding.setUser(userLocal);
            }
        });
    }

}
