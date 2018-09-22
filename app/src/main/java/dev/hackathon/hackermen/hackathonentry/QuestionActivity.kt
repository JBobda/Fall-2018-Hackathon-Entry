package dev.hackathon.hackermen.hackathonentry

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {

    //Currently this Activity class serves up the text for the questions on the question xml file
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        textView2.text = "Look, so easy!"

        textView3.text = "I stayed up all night"

        textView.text = "Hello From Kotlin"
    }
}
