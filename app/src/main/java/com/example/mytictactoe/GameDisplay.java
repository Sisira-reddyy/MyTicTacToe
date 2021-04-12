package com.example.mytictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameDisplay extends AppCompatActivity {
    /* 0=circle,1=cross*/
    Button home;
    public String[] winnners;
    public TextView resultText;
    public TextView tv1;
    int activePlayer=0;
    GridLayout gridLayout;
    boolean gameisActive=true;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] wPositions ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedBlock = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedBlock] == 2 && gameisActive) {
            gameState[tappedBlock] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.circle);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.neon_x);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(200);
            for (int[] i : wPositions) {
                if (gameState[i[0]] == gameState[i[1]]
                        && gameState[i[1]] == gameState[i[2]]
                        && gameState[i[0]] != 2) {
                    String winner = winnners[0];
                    if (gameState[i[0]] == 0) {
                        winner = winnners[1];
                    }
                    TextView winnerMssg = (TextView) findViewById(R.id.winnerMssg);
                    winnerMssg.setText(winner + " has won!");
                    Button Pg = findViewById(R.id.button3);
                    Pg.setVisibility(View.VISIBLE);
                    gridLayout.setVisibility(View.INVISIBLE);
                    resultText.setVisibility(View.VISIBLE);
                    gameisActive = false;
                } else {
                    boolean gameisOver = true;
                    for (int counterState : gameState) {
                        if (counterState == 2) gameisOver = false;
                    }
                    if (gameisOver) {
                        TextView winnerMssg = (TextView) findViewById(R.id.winnerMssg);
                        winnerMssg.setText("TIE!");
                        Button Pg = findViewById(R.id.button3);
                        Pg.setVisibility(View.VISIBLE);
                        gridLayout.setVisibility(View.INVISIBLE);

                    }
                }
            }
        }
    }
    public void playAgainButtonClick(View view){
        gameisActive = true;
        Button Pg = findViewById(R.id.button3);
        Pg.setVisibility(View.INVISIBLE);
        resultText.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        activePlayer = 0;
        for (int j = 0; j < gameState.length; j++) {
            gameState[j] = 2;
        }
        for (int j = 0; j < gridLayout.getChildCount(); j++) {
            ((ImageView) gridLayout.getChildAt(j)).setImageResource(0);
        }
    }

    public void gotohome(View view){
        Intent intent = new Intent(getApplicationContext(), PlayerSetup.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_display);
        winnners = getIntent().getStringArrayExtra("PLAYER_NAMES");
        gridLayout = findViewById(R.id.gridLayout);
        resultText = findViewById(R.id.winnerMssg);
        tv1 = findViewById(R.id.textView7);
        tv1.animate().alpha(0f).setDuration(2000);
    }
}