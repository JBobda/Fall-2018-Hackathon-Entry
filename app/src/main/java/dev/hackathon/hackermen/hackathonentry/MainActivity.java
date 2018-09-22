package dev.hackathon.hackermen.hackathonentry;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, QuestionAdapter.class);
        //EditText editText = (EditText) findViewById(R.id.);
        //String message = editText.getText().toString();
        //intent.putExtra("Random Text", message);
        startActivity(intent);
    }
}
