package ru.nsu.dogsFetcher.presentation.repos;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.nsu.dogsFetcher.data.model.User;

public class ReposViewModelFactory implements ViewModelProvider.Factory {
    private String user;

    public ReposViewModelFactory(String user) {
        this.user = user;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ReposViewModel(user);
    }
}
