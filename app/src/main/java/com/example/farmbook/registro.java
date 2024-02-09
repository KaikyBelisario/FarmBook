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
import com.google.firebase.database.FirebaseDatabase;

public class registro extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextSenha, editTextUsuario;
    Button btnRegistro;
    FirebaseAuth mAuth;

    TextView vtlogin;
    ProgressBar barProgresso;
   /* @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextSenha = findViewById(R.id.senha);
        editTextUsuario = findViewById(R.id.usuario);
        btnRegistro = findViewById(R.id.btn_registro);
        barProgresso = findViewById(R.id.progresso);
        vtlogin = findViewById(R.id.vtlogin);
        vtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barProgresso.setVisibility(View.VISIBLE);
                String email, senha, nome;
                email = String.valueOf(editTextEmail.getText());
                senha = String.valueOf(editTextSenha.getText());
                nome = String.valueOf(editTextUsuario.getText());

                if (TextUtils.isEmpty(nome)) {
                    barProgresso.setVisibility(View.GONE);
                    Toast.makeText(registro.this, "Insira o Nome de usuario", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    barProgresso.setVisibility(View.GONE);
                    Toast.makeText(registro.this, "Insira um Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(senha)) {
                    barProgresso.setVisibility(View.GONE);
                    Toast.makeText(registro.this, "Insira uma Senha", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Usuario usuario = new Usuario(nome, email);
                                    FirebaseDatabase.getInstance().getReference("Usuario").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(usuario)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(registro.this, "Registrado com sucesso", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            });
                                } else {
                                    barProgresso.setVisibility(View.GONE);
                                    Toast.makeText(registro.this, "Falha no registro", Toast.LENGTH_SHORT).show();
                                }
                            }
                 });

            }
        });
    }
}