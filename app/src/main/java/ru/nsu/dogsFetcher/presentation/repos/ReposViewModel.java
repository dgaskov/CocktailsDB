package ru.nsu.dogsFetcher.presentation.repos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import ru.nsu.dogsFetcher.Application;
import ru.nsu.dogsFetcher.data.model.Repo;
import ru.nsu.dogsFetcher.data.model.User;
import ru.nsu.dogsFetcher.data.network.GithubApi;
import ru.nsu.dogsFetcher.data.network.GithubApiClient;

public class ReposViewModel extends ViewModel {
    private String user;

    private GithubApi api;

    public LiveData<List<String>> observeShibesLiveData() { return shibesLiveData; }
    private MutableLiveData<List<String>> shibesLiveData = new MutableLiveData<>();

    public LiveData<String> observeUserLiveData() { return userLiveData; }
    private MutableLiveData<String> userLiveData = new MutableLiveData<>();

    public LiveData<Boolean> observeIsLoadingLiveData() { return isLoadingLiveData; }
    private MutableLiveData<Boolean> isLoadingLiveData = new MutableLiveData<>(false);

    public ReposViewModel(String user) {
        this.user = user;

        // todo make api singleton
        api = GithubApiClient.getClient(Application.getInstance()).create(GithubApi.class);

        init();
    }

    private void init() {
        userLiveData.setValue(user);

        isLoadingLiveData.setValue(true);
        api.getShibesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<String>>() {
                    @Override
                    public void onSuccess(List<String> urls) {
                        isLoadingLiveData.setValue(false);
                        shibesLiveData.setValue(urls);
                    }

                    @Override
                    public void onError(Throwable e) {
                        isLoadingLiveData.setValue(false);
                    }
                });
    }
}
