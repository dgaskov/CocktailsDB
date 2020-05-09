package ru.nsu.dogsFetcher.presentation.start;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.nsu.dogsFetcher.R;
import ru.nsu.dogsFetcher.presentation.userlist.UserListActivity;

public class StartActivity extends AppCompatActivity {
//    StartViewModel viewModel;

    private Button bSearch;
    private ProgressBar pbLoading;
    private EditText etUsername;
    private TextView tvError;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openUserList();

//        bSearch = findViewById(R.id.bSearch);
//        pbLoading = findViewById(R.id.pbLoading);
//        etUsername = findViewById(R.id.etUsername);
//        tvError = findViewById(R.id.tvError);
//
//        viewModel = ViewModelProviders.of(this).get(StartViewModel.class);
//        context = this;
//
//        viewModel.observeSearchButtonEnabled().observe(this, new Observer<Boolean>() {
//            @Override
//            public void onChanged(Boolean aBoolean) {
//                bSearch.setEnabled(aBoolean);
//            }
//        });
//
//        etUsername.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                // empty
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                viewModel.validateUsername(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                // empty
//            }
//        });
//
////        viewModel.observeShibeListLiveData().observe(this, new Observer<List<String>>() {
////            @Override
////            public void onChanged(List<String> userList) {
////                if (userList.size() == 0) {
////                    Toast.makeText(context, R.string.start_error_no_results, Toast.LENGTH_LONG).show();
////                } else {
////                    openUserList();
////                }
////            }
////        });
//
//        viewModel.observeIsLoadingLiveData().observe(this, new Observer<Boolean>() {
//            @Override
//            public void onChanged(Boolean aBoolean) {
//                pbLoading.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
//            }
//        });
//
//        viewModel.observeErrorLiveData().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                tvError.setText(s);
//                tvError.setVisibility(s.equals("") ? View.GONE : View.VISIBLE);
//            }
//        });
//
//        bSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewModel.search(etUsername.getText().toString());
//            }
//        });
    }

    private void openUserList() {
        Intent intent = new Intent(StartActivity.this, UserListActivity.class);
        startActivity(intent);
    }
}
