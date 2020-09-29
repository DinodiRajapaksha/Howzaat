package com.example.teams;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserTeamCategoty extends AppCompatActivity {

    Button btnNational;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_team_categoty);

        btnNational = findViewById(R.id.BTNational);

        btnNational.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(UserTeamCategoty.this,NationalTeams.class);
                startActivity(intent);

            }
        });
    }
}