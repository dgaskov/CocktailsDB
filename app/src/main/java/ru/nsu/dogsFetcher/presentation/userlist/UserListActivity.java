package ru.nsu.dogsFetcher.presentation.userlist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import ru.nsu.dogsFetcher.R;
import ru.nsu.dogsFetcher.presentation.repos.ReposActivity;
import ru.nsu.dogsFetcher.presentation.userlist.list.OnShibeClickListener;
import ru.nsu.dogsFetcher.presentation.userlist.list.UserListAdapter;

import static ru.nsu.dogsFetcher.presentation.repos.ReposActivity.USER_KEY;

public class UserListActivity extends AppCompatActivity implements OnShibeClickListener {
    public static String USER_LIST_KEY = "user_list_key";
    public static String QUERY_KEY = "query_key";

    private RecyclerView rvUserList;
    private UserListAdapter userListAdapter;
    private TextView tvHeader;
    private UserListViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_list);

        rvUserList = findViewById(R.id.rvUsers);
        tvHeader = findViewById(R.id.tvHeader);
        userListAdapter = new UserListAdapter(this);

        viewModel = ViewModelProviders.of(this).get(UserListViewModel.class);
        initList();
        viewModel.search();

        viewModel.observeShibeListLiveData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> users) {
                userListAdapter.setItems(users);
            }
        });

    }

    @Override
    public void onClick(String url) {
        Intent intent = new Intent(UserListActivity.this, ReposActivity.class);
        intent.putExtra(USER_KEY, url);

        startActivity(intent);
    }

    private void initList() {
        rvUserList.setLayoutManager(new LinearLayoutManager(this));
        rvUserList.setAdapter(userListAdapter);
    }
}
