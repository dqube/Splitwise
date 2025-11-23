package com.example.fairshare.data.repository;

import com.example.fairshare.domain.common.Result;
import com.example.fairshare.domain.model.User;
import com.example.fairshare.domain.repository.UserRepository;
import io.reactivex.rxjava3.core.Single;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepositoryImpl implements UserRepository {

    @Inject
    public UserRepositoryImpl() {}

    @Override
    public Single<Result<User>> login(String email, String password) {
        // Mock login delay
        return Single.timer(1, TimeUnit.SECONDS)
                .map(ignored -> {
                    if (email.contains("error")) {
                        return new Result.Error<>(new Exception("Invalid credentials"));
                    }
                    return new Result.Success<>(new User("1", email, "Test User", null));
                });
    }

    @Override
    public Single<Result<User>> signUp(String email, String password, String name) {
        // Mock signup delay
        return Single.timer(1, TimeUnit.SECONDS)
                .map(ignored -> new Result.Success<>(new User("1", email, name, null)));
    }

    @Override
    public Single<Result<Void>> logout() {
        return Single.just(new Result.Success<>(null));
    }

    @Override
    public Single<Result<User>> getCurrentUser() {
        // Mock current user
        return Single.just(new Result.Success<>(new User("1", "test@example.com", "Test User", null)));
    }
}
