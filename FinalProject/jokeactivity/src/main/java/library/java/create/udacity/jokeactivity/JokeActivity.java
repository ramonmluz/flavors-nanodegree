package library.java.create.udacity.jokeactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static String JOKE_KEY = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        TextView jokeTxt = findViewById(R.id.jokeTxt);
        jokeTxt.setText(getIntent().getStringExtra(JOKE_KEY));
    }
}
