package com.billy.mymonashapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBar

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.apply {
            elevation = 0.0f
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            val customTitle = layoutInflater.inflate(R.layout.layout_user_info, null)
            customTitle.findViewById<TextView>(R.id.student_name).text = getString(R.string.profile_name, getString(R.string.place_holder_name))
            customTitle.findViewById<TextView>(R.id.date_week).text = getString(R.string.place_holder_date_week)

            customView = customTitle
        }
    }
}