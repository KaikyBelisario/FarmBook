package com.example.farmbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseRef;
    ListView listView;
    FirebaseDatabase db;
    FirebaseAuth mAuth;
    FirebaseUser user;
    Button criarf, logout;
    Adapter adapter;
    ArrayList<Farm> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseDatabase.getInstance();

        logout = findViewById(R.id.logout);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance() .signOut();
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });
        ArrayList<Farm> list = new ArrayList<>();
        listView = findViewById(R.id.farmlist);
        adapter = new Adapter(this,list);
        criarf = findViewById(R.id.criarf);

        listView.setAdapter(adapter);

        databaseRef = FirebaseDatabase.getInstance().getReference("Farm");

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Farm farm = dataSnapshot.getValue(Farm.class);
                    list.add(farm);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Erro ao carregar", Toast.LENGTH_SHORT).show();
            }
        });
        criarf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), farmpost.class);
            startActivity(intent);
            finish();
        }
        });
    }
}