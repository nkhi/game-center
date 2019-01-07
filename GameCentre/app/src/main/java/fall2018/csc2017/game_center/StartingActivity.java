package fall2018.csc2017.game_center;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * The initial activity for the game center.
 */
public class StartingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_);
        addRegisterButtonListener();
        addLoginButtonListener();
    }


    /**
     * Activate the login button
     */
    private void addLoginButtonListener() {
        Button loginButton = findViewById(R.id.LoginActivityButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLogin();
            }
        });
    }

    /**
     * Activate the register button
     */
    private void addRegisterButtonListener() {
        Button registerButton = findViewById(R.id.RegisterActivityButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToRegister();
            }
        });
    }

    /**
     * Switch to login screen
     */
    private void switchToLogin() {
        Intent tmp = new Intent(this, LoginActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to register screen
     */
    private void switchToRegister() {
        Intent tmp = new Intent(this, RegisterActivity.class);
        startActivity(tmp);
    }

}
