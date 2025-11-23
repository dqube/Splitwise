package com.example.fairshare.domain.usecase.auth;

import com.example.fairshare.domain.common.Result;
import com.example.fairshare.domain.model.User;
import com.example.fairshare.domain.repository.UserRepository;
import io.reactivex.rxjava3.core.Single;

public class LoginUseCase {
    private final UserRepository userRepository;

    public LoginUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Single<Result<User>> execute(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return Single.just(new Result.Error<>(new IllegalArgumentException("Email and password cannot be empty")));
        }
        return userRepository.login(email, password);
    }
}
