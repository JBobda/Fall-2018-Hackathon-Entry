package dev.hackathon.hackermen.hackathonentry

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_message.*

class InputManager : AppCompatActivity(){

    var barOne = 0
    var barTwo = 0
    var location = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get reference to button
        val button = findViewById(R.id.button) as Button
        // set on-click listener
        button.setOnClickListener {
            // your code to perform when the user clicks on the button
            barOne = seekBar.progress;
            barTwo = seekBar.progress;
            location = textInputLayout.editText.toString();


            println(barOne);
            println(barTwo);
            println(location);

        }
    }
}