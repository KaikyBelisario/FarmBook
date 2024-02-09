package com.example.farmbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class farmpost extends AppCompatActivity {

    TextInputEditText farmNome, farmCriador, farmLink, farmProducao;
    Button btnCriar, btnVoltar;
    FirebaseDatabase database;
    DatabaseReference reference;
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
        setContentView(R.layout.activity_farmpost2);
        farmNome = findViewById(R.id.farmName);
        farmCriador = findViewById(R.id.criador);
        farmLink = findViewById(R.id.link);
        farmProducao = findViewById(R.id.fproducao);
        btnCriar = findViewById(R.id.criar);
        barProgresso = findViewById(R.id.enviando);
        btnVoltar = findViewById(R.id.voltar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barProgresso.setVisibility(View.VISIBLE);
                String fnome, fcriador, flink, fproducao;
                fnome = String.valueOf(farmNome.getText());
                fcriador = String.valueOf(farmCriador.getText());
                flink = String.valueOf(farmLink.getText());
                fproducao = String.valueOf(farmProducao.getText());

                if (TextUtils.isEmpty(flink)) {
                    barProgresso.setVisibility(View.GONE);
                    Toast.makeText(farmpost.this, "Insira o Nome da Farm", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(fnome)) {
                    barProgresso.setVisibility(View.GONE);
                    Toast.makeText(farmpost.this, "Insira o Criador", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(fcriador)) {
                    barProgresso.setVisibility(View.GONE);
                    Toast.makeText(farmpost.this, "Insira a rede social", Toast.LENGTH_SHORT).show();
                    return;
                }
                Farm farm = new Farm(fnome, fcriador, flink, fproducao);
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("Farm");
                reference.child(fnome).setValue(farm)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(farmpost.this, "Farm registrada!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else{
                                barProgresso.setVisibility(View.GONE);
                                Toast.makeText(farmpost.this, "Falha no registro", Toast.LENGTH_SHORT).show();
                            }
                        }
                });
            }

        });
    }
}

