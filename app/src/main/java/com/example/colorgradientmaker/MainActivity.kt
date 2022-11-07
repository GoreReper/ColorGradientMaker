package com.example.colorgradientmaker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.colorgradientmaker.currentcolor.CurrentColorFragment


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, CurrentColorFragment())
                .commit()
        }

    }






}