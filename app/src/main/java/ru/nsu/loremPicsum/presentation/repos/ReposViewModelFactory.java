package ru.nsu.loremPicsum.presentation.repos;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.nsu.loremPicsum.data.model.User;

public class ReposViewModelFactory implements ViewModelProvider.Factory {
    private User user;

    public ReposViewModelFactory(User user) {
        this.user = user;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ReposViewModel(user);
    }
}
