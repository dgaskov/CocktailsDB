package ru.nsu.loremPicsum.presentation.userlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.nsu.loremPicsum.R;
import ru.nsu.loremPicsum.Application;
import ru.nsu.loremPicsum.data.model.User;
import ru.nsu.loremPicsum.data.model.UserList;

public class UserListViewModel extends ViewModel {
    private UserList userList;
    private String query;

    public LiveData<List<User>> observeUserListLiveData() { return userListLiveData; }
    private MutableLiveData<List<User>> userListLiveData = new MutableLiveData<>();

    public LiveData<String> observeHeaderLiveData() { return headerLiveData; }
    private MutableLiveData<String> headerLiveData = new MutableLiveData<>();

    UserListViewModel(String query, UserList userList) {
        this.query = query;
        this.userList = userList;

        userListLiveData.setValue(userList.getItems());
        headerLiveData.setValue(Application.getInstance().getString(R.string.user_list_header, query, userList.getItems().size()));
    }
}
