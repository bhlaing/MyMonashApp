package com.billy.mymonashapp.ui.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.billy.mymonashapp.BaseActivity
import com.billy.mymonashapp.R
import com.billy.mymonashapp.application.shared.observeNonNull
import com.billy.mymonashapp.databinding.ActivityProfileBinding
import com.billy.mymonashapp.domain.StudentProfile
import com.billy.mymonashapp.ui.profile.adapter.ProfileAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : BaseActivity() {
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var bindings: ActivityProfileBinding
    private lateinit var profileAdapter: ProfileAdapter

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindings = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(bindings.root)

        supportActionBar?.apply {
            elevation = 0.0f
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            val customTitle = layoutInflater.inflate(R.layout.view_user_info, null)
            customTitle.findViewById<TextView>(R.id.student_name).text = getString(
                R.string.profile_name, getString(
                    R.string.place_holder_name
                )
            )
            customTitle.findViewById<TextView>(R.id.date_week).text =
                getString(R.string.place_holder_date_week)

            customView = customTitle
        }
        setUpStudentProfileView()
        setUpObservers()
    }

    private fun setUpStudentProfileView() {
        profileAdapter = ProfileAdapter()

        bindings.studentProfileListView.apply {
            adapter = profileAdapter

            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setUpObservers() {
        observeNonNull(viewModel.loading, ::onLoadingStateChanged)
        observeNonNull(viewModel.userProfile, ::onUserProfileStateChanged)
    }

    private fun onLoadingStateChanged(loading: Boolean) {
        if (loading) {
            bindings.loadingSpinner.visibility = VISIBLE
        } else {
            bindings.loadingSpinner.visibility = GONE
        }
    }

    private fun onUserProfileStateChanged(studentProfile: StudentProfile) {
        profileAdapter.setProfile(studentProfile)
    }
}