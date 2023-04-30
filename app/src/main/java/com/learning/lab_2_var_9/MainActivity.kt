package com.learning.lab_2_var_9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learning.lab_2_var_9.ui.main.MainFragment
import com.learning.myapplication.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}