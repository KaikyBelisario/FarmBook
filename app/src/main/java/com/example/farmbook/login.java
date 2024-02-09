package com.example.farmbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextSenha;
    Button btnLogin;
    FirebaseAuth mAuth;
    ProgressBar barProgresso;
    TextView cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextSenha = findViewById(R.id.senha);
        btnLogin = findViewById(R.id.btn_login);
        barProgresso = findViewById(R.id.progresso);
        cadastro = findViewById(R.id.cadastro);
        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), registro.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, senha;
                email = String.valueOf(editTextEmail.getText());
                senha = String.valueOf(editTextSenha.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(login.this, "Insira um Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(senha)) {
                    Toast.makeText(login.this, "Insira uma Senha", Toast.LENGTH_SHORT).show();
                    return;
                }
                barProgresso.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email, senha)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                barProgresso.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    barProgresso.setVisibility(View.VISIBLE);
                                    Toast.makeText(login.this, "Logado com sucesso!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    barProgresso.setVisibility(View.GONE);
                                    Toast.makeText(login.this, "Falha no login", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}