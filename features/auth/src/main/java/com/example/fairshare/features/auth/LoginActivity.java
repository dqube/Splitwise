package com.example.fairshare.features.auth;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.fairshare.features.auth.databinding.ActivityLoginBinding;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    private LoginViewModel viewModel;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.btnLogin.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();
            viewModel.login(email, password);
        });

        viewModel.getUiState().observe(this, state -> {
            if (state.isLoading) {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.btnLogin.setEnabled(false);
            } else {
                binding.progressBar.setVisibility(View.GONE);
                binding.btnLogin.setEnabled(true);
                if (state.error != null) {
                    Toast.makeText(this, state.error, Toast.LENGTH_SHORT).show();
                } else if (state.user != null) {
                    Toast.makeText(this, "Welcome " + state.user.getName(), Toast.LENGTH_SHORT).show();
                    // Navigate to Home
                }
            }
        });
    }
}
