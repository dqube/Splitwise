package com.example.fairshare.domain.usecase.auth;

import com.example.fairshare.domain.common.Result;
import com.example.fairshare.domain.model.User;
import com.example.fairshare.domain.repository.UserRepository;
import io.reactivex.rxjava3.core.Single;

public class SignUpUseCase {
    private final UserRepository userRepository;

    public SignUpUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Single<Result<User>> execute(String email, String password, String name) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty() || name == null || name.isEmpty()) {
            return Single.just(new Result.Error<>(new IllegalArgumentException("All fields are required")));
        }
        return userRepository.signUp(email, password, name);
    }
}
