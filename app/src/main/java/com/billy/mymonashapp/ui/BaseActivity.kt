package com.billy.mymonashapp.ui

import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.billy.mymonashapp.R

open class BaseActivity : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
}