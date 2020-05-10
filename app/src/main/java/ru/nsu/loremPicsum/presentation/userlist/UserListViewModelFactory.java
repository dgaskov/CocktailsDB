package ru.nsu.loremPicsum.presentation.userlist;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.nsu.loremPicsum.data.model.UserList;

public class UserListViewModelFactory implements ViewModelProvider.Factory {
    private String query;
    private UserList userList;

    public UserListViewModelFactory(String query, UserList userList) {
        this.query = query;
        this.userList = userList;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UserListViewModel(query, userList);
    }
}
