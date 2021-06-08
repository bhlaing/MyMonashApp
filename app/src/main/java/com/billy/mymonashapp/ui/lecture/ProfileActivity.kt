package com.billy.mymonashapp.ui.lecture

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
import com.billy.mymonashapp.ui.lecture.adapter.ProfileAdapter
import com.billy.mymonashapp.ui.lecture.model.CarParkItem
import com.billy.mymonashapp.ui.lecture.model.LectureItem
import com.billy.mymonashapp.ui.lecture.model.ShuttleBusItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : BaseActivity() {
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var bindings: ActivityProfileBinding
    private lateinit var profileAdapter: ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindings = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(bindings.root)

        setCustomActionBar()
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
        randomiseRows()

        observeNonNull(viewModel.loading, ::onLoadingStateChanged)
        observeNonNull(viewModel.availableLectures, ::onUserProfileStateChange)
        observeNonNull(viewModel.availableCarParksItem, ::onCarParkAvailabilityChange)
        observeNonNull(viewModel.shuttleBusItemSchedule, ::onShuttleBusScheduleChange)
    }

    private fun onLoadingStateChanged(loading: Boolean) {
        if (loading) {
            bindings.loadingSpinner.visibility = VISIBLE
        } else {
            bindings.loadingSpinner.visibility = GONE
        }
    }

    private fun onUserProfileStateChange(lectureItems: List<LectureItem>) {
        profileAdapter.setLectures(lectureItems)
    }

    private fun onCarParkAvailabilityChange(availableCarParkItems: List<CarParkItem>) {
        profileAdapter.setAvailableCarParks(availableCarParkItems)
    }

    private fun onShuttleBusScheduleChange(busItems: List<ShuttleBusItem>) {
        profileAdapter.setShuttleBuses(busItems)
    }

    private fun randomiseRows() {
        viewModel.lectureCount = (1..3).random()
        viewModel.busCount = (1..3).random()
        viewModel.carParkCount = (1..3).random()
    }

    @SuppressLint("InflateParams")
    private fun setCustomActionBar() {
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
    }
}