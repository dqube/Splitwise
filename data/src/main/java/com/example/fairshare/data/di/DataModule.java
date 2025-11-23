package com.example.fairshare.data.di;

import com.example.fairshare.data.repository.UserRepositoryImpl;
import com.example.fairshare.domain.repository.UserRepository;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public abstract class DataModule {

    @Binds
    @Singleton
    public abstract UserRepository bindUserRepository(UserRepositoryImpl userRepositoryImpl);
}
