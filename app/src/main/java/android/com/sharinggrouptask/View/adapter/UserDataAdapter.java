package android.com.sharinggrouptask.View.adapter;

import android.com.sharinggrouptask.R;
import android.com.sharinggrouptask.View.callback.ClickCallBack;
import android.com.sharinggrouptask.databinding.RowUserLayoutBinding;
import android.com.sharinggrouptask.db.entity.User;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;

/******************************************************************************
 * Module: UserDataAdapter
 *
 * File Name: UserDataAdapter.java
 *
 * Description: Source file for UserDataAdapter
 *
 * Author: Rana Tarek
 ******************************************************************************/
public class UserDataAdapter
        extends RecyclerView.Adapter<UserDataAdapter.UserViewHolder> {
    private ArrayList<User> users;
    private ClickCallBack callBack;

    public UserDataAdapter(ClickCallBack callBack) {
        this.callBack = callBack;
    }

    @NotNull
    @Override
    public UserViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        RowUserLayoutBinding rowUserLayoutBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.row_user_layout, viewGroup, false);
        return new UserViewHolder(rowUserLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        User currentUser = users.get(i);
        userViewHolder.userListItemBinding.setUser(currentUser);
        userViewHolder.itemView.setOnClickListener(view -> callBack.onClick(currentUser));
    }

    @Override
    public int getItemCount() {
        if (users != null) {
            return users.size();
        } else {
            return 0;
        }
    }

    /************************************************************************************
     * Function Name: setUserList
     * Parameters (in): ArrayList<User> users
     * Return value: None
     * Description: responsible to set list of users
     **********************************************************************************/
    public void setUserList(ArrayList<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private RowUserLayoutBinding userListItemBinding;

        UserViewHolder(@NonNull RowUserLayoutBinding usertListItemBinding) {
            super(usertListItemBinding.getRoot());
            this.userListItemBinding = usertListItemBinding;
        }
    }
}