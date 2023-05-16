package com.learning.lab_2_var_9

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.learning.myapplication.R
import java.io.BufferedReader
import java.io.InputStreamReader

class DataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        val dataTextView: TextView = findViewById(R.id.dataTextView)

        try {
            val fileInputStream = openFileInput("data.txt")
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()
            val lines = bufferedReader.readLines()

            for (line in lines) {
                stringBuilder.append(line)
            }

            dataTextView.text = stringBuilder.toString()
            bufferedReader.close()

        } catch (e: Exception) {
            dataTextView.text = "Сховище пусте."
        }
    }
}
