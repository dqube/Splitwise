package com.example.fairshare.domain.repository;

import com.example.fairshare.domain.common.Result;
import com.example.fairshare.domain.model.User;
import io.reactivex.rxjava3.core.Single;

public interface UserRepository {
    Single<Result<User>> login(String email, String password);
    Single<Result<User>> signUp(String email, String password, String name);
    Single<Result<Void>> logout();
    Single<Result<User>> getCurrentUser();
}
