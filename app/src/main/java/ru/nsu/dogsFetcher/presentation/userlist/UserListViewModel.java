package ru.nsu.dogsFetcher.presentation.userlist;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import ru.nsu.dogsFetcher.Application;
import ru.nsu.dogsFetcher.data.model.UserList;
import ru.nsu.dogsFetcher.data.network.GithubApi;
import ru.nsu.dogsFetcher.data.network.GithubApiClient;

public class UserListViewModel extends ViewModel {

    public LiveData<String> observeErrorLiveData() { return errorLiveData; }
    private MutableLiveData<String> errorLiveData = new MutableLiveData<>("");

    public LiveData<List<String>> observeShibeListLiveData() { return shibeListLiveData; }
    private MutableLiveData<List<String>> shibeListLiveData = new MutableLiveData<>();

    public LiveData<Boolean> observeSearchButtonEnabled() { return searchButtonEnabled; }
    private MutableLiveData<Boolean> searchButtonEnabled = new MutableLiveData<>(false);

    public LiveData<Boolean> observeIsLoadingLiveData() { return isLoadingLiveData; }
    private MutableLiveData<Boolean> isLoadingLiveData = new MutableLiveData<>(false);

    private GithubApi api;

    public UserListViewModel() {
        api = GithubApiClient.getClient(Application.getInstance()).create(GithubApi.class);
    }

    @SuppressLint("CheckResult")
    public void search() {
        isLoadingLiveData.setValue(true);

        api.getShibesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<String>>() {
                    @Override
                    public void onSuccess(List<String> urls) {
                        isLoadingLiveData.setValue(false);
                        shibeListLiveData.setValue(urls);
                    }

                    @Override
                    public void onError(Throwable e) {
                        isLoadingLiveData.setValue(false);
                        errorLiveData.setValue(e.getMessage());
                    }
                });
    }
}
