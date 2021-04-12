package com.example.mytictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PlayerSetup extends AppCompatActivity {

    public EditText player1;
    public EditText player2;
    static String editTextTextPersonName;
    static String editTextTextPersonName2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_setup);

        player1 = findViewById(R.id.editTextTextPersonName);
        player2 = findViewById(R.id.editTextTextPersonName2);
    }

    public void submitButtonClick(View view) {
        editTextTextPersonName = player1.getText().toString();
        editTextTextPersonName2 = player2.getText().toString();
        String[] array1 = {editTextTextPersonName, editTextTextPersonName2};

        Intent intent = new Intent (PlayerSetup.this, GameDisplay.class);
        intent.putExtra( "PLAYER_NAMES", array1);
        startActivity(intent);

    }
}