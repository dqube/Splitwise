package com.example.fairshare.features.auth.di;

import com.example.fairshare.domain.repository.UserRepository;
import com.example.fairshare.domain.usecase.auth.LoginUseCase;
import com.example.fairshare.domain.usecase.auth.SignUpUseCase;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.scopes.ViewModelScoped;

@Module
@InstallIn(ViewModelComponent.class)
public class AuthModule {

    @Provides
    @ViewModelScoped
    public LoginUseCase provideLoginUseCase(UserRepository userRepository) {
        return new LoginUseCase(userRepository);
    }

    @Provides
    @ViewModelScoped
    public SignUpUseCase provideSignUpUseCase(UserRepository userRepository) {
        return new SignUpUseCase(userRepository);
    }
}
