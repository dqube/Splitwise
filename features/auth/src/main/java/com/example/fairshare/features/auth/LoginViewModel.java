package com.example.fairshare.features.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.fairshare.domain.common.Result;
import com.example.fairshare.domain.model.User;
import com.example.fairshare.domain.usecase.auth.LoginUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javax.inject.Inject;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private final LoginUseCase loginUseCase;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<AuthUiState> uiState = new MutableLiveData<>();

    @Inject
    public LoginViewModel(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    public LiveData<AuthUiState> getUiState() {
        return uiState;
    }

    public void login(String email, String password) {
        uiState.setValue(new AuthUiState(true, null, null)); // Loading

        disposables.add(loginUseCase.execute(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result instanceof Result.Success) {
                        uiState.setValue(new AuthUiState(false, ((Result.Success<User>) result).getData(), null));
                    } else if (result instanceof Result.Error) {
                        uiState.setValue(new AuthUiState(false, null, ((Result.Error<User>) result).getException().getMessage()));
                    }
                }, error -> {
                    uiState.setValue(new AuthUiState(false, null, error.getMessage()));
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }

    public static class AuthUiState {
        public final boolean isLoading;
        public final User user;
        public final String error;

        public AuthUiState(boolean isLoading, User user, String error) {
            this.isLoading = isLoading;
            this.user = user;
            this.error = error;
        }
    }
}
