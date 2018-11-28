package fall2018.csc2017.game_center.a3072;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.game_center.LoginActivity;
import fall2018.csc2017.game_center.R;
import fall2018.csc2017.game_center.Score;
import fall2018.csc2017.game_center.Scoreable;
import fall2018.csc2017.game_center.slidingtiles.TileScoreboard;


public class MainActivity extends AppCompatActivity implements Scoreable{

    @SuppressLint("StaticFieldLeak")
    private static MainActivity mainActivity = null;
    private BoardView boardview;
    private int score = 0;
    private TextView tvScore;
    private String username;

    public MainActivity() {
        mainActivity = this;
    }

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        username = getIntent().getStringExtra(LoginActivity.CURRENT_USER);
        setContentView(R.layout.activity_3072);

        boardview = findViewById(R.id.gameView);

        tvScore = findViewById(R.id.tvScore);

        clearScore();
        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScore();
                boardview.startGame();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public void clearScore() {
        score = 0;
        showScore();
    }

    @SuppressLint("SetTextI18n")
    public void showScore() {
        tvScore.setText(score + "");
    }

    public void addScore(int s) {
        score += s;
        showScore();
    }

    @Override
    public int getScore() {
        return score;
    }

}
