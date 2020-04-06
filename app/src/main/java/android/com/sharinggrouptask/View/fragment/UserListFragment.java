package android.com.sharinggrouptask.View.fragment;

import android.com.sharinggrouptask.MyApplication;
import android.com.sharinggrouptask.R;
import android.com.sharinggrouptask.View.activity.MainActivity;
import android.com.sharinggrouptask.View.adapter.UserDataAdapter;
import android.com.sharinggrouptask.View.callback.ClickCallBack;
import android.com.sharinggrouptask.ViewModel.UserListViewModel;
import android.com.sharinggrouptask.ViewModel.ViewModelFactory;
import android.com.sharinggrouptask.databinding.FragmentUserListBinding;
import android.com.sharinggrouptask.db.dao.UserDao;
import android.com.sharinggrouptask.db.entity.User;
import android.com.sharinggrouptask.di.Component.ApplicationComponent;
import android.com.sharinggrouptask.di.Component.DaggerFragmentUserListComponent;
import android.com.sharinggrouptask.di.Component.FragmentUserListComponent;
import android.com.sharinggrouptask.model.repository.UserRepository;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import static android.com.sharinggrouptask.helper.NetworkCheck.isNetworkAvailable;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserListFragment extends Fragment implements ClickCallBack {

    @Inject
    UserRepository userRepository;
    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    UserDao userDao;
    private View view;
    private UserListViewModel viewModel;
    private UserDataAdapter userDataAdapter;
    private FragmentUserListBinding binding;

    public UserListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_list, container, false);
        view = binding.getRoot();
        binding.setLifecycleOwner(this);
        initDaggerComponent();
        initRecycleView();
        initViewModel();
        ((MainActivity) Objects.requireNonNull(getActivity())).setListFragmentToolbar();
        backPressed();
        return view;
    }

    /************************************************************************************
     * Function Name: initRecycleView
     * Parameters (in): none
     * Return value: None
     * Description: responsible to initial RecycleView and set Adapter
     **********************************************************************************/
    private void initRecycleView() {
        RecyclerView userListRecyclerView = binding.userListRecyclerView;
        userListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        userListRecyclerView.setHasFixedSize(true);
        userDataAdapter = new UserDataAdapter(this);
        userListRecyclerView.setAdapter(userDataAdapter);
    }

    /************************************************************************************
     * Function Name: initDaggerComponent
     * Parameters (in): none
     * Return value: None
     * Description: responsible to initial Dagger Component
     **********************************************************************************/
    private void initDaggerComponent() {
        ApplicationComponent applicationComponent = MyApplication.get(Objects.requireNonNull(getActivity())).getApplicationComponent();
        FragmentUserListComponent fragmentUserListComponent = DaggerFragmentUserListComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
        fragmentUserListComponent.injectUserListFragment(this);
    }

    /************************************************************************************
     * Function Name: initViewModel
     * Parameters (in): none
     * Return value: None
     * Description: responsible to initial ViewModel
     **********************************************************************************/
    private void initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserListViewModel.class);
        getListOfUser();
    }

    /************************************************************************************
     * Function Name: getListOfUser
     * Parameters (in): none
     * Return value: None
     * Description: responsible to get List of User
     **********************************************************************************/
    private void getListOfUser() {
        viewModel.getUserList().observe(this, user -> {
            binding.loadingLayout.setVisibility(View.GONE);
            if (user != null) {
                if (user.size() > 0) {
                    userDataAdapter.setUserList((ArrayList<User>) user);
                    binding.userListRecyclerView.setVisibility(View.VISIBLE);
                    binding.emptyTextView.setVisibility(View.GONE);
                } else if (isNetworkAvailable(Objects.requireNonNull(getActivity()))) {
                    binding.emptyTextView.setVisibility(View.VISIBLE);
                    binding.userListRecyclerView.setVisibility(View.GONE);
                } else if (!isNetworkAvailable(Objects.requireNonNull(getActivity()))) {
                    binding.noInternetConnectionLayout.noInternetConnectionConstrainLayout.setVisibility(View.VISIBLE);
                    binding.userListRecyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    @Override
    public void onClick(User user) {
        ((MainActivity) Objects.requireNonNull(getActivity())).replaceUserDetailsFragment(user);
    }

    /************************************************************************************
     * Function Name: backPressed
     * Parameters (in): none
     * Return value: None
     * Description: responsible to handle back Pressed
     **********************************************************************************/

    private void backPressed() {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                Objects.requireNonNull(getActivity()).finish();
                return true;
            }
            return false;
        });

    }
}
