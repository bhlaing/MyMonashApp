package com.billy.mymonashapp.ui.profile.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.billy.mymonashapp.R
import com.billy.mymonashapp.application.shared.inflate
import com.billy.mymonashapp.databinding.*
import com.billy.mymonashapp.ui.profile.model.CarPark
import com.billy.mymonashapp.ui.profile.model.Lecture
import com.billy.mymonashapp.ui.profile.model.ShuttleBus

private const val NUMBER_OF_SECTIONS = 3
private const val LECTURES = 0
private const val CARPARKS = 1
private const val SHUTTLEBUS = 2

@SuppressLint("InflateParams")
class ProfileAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var lectures: List<Lecture> = emptyList()
    private var carParks: List<CarPark> = emptyList()
    private var shuttleBuses: List<ShuttleBus> = emptyList()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            LECTURES -> LecturesViewHolder(parent.inflate(R.layout.layout_lecture_group))
            CARPARKS -> AvailableCarParksViewHolder(parent.inflate(R.layout.layout_available_carpark))
            SHUTTLEBUS -> ShuttleBusScheduleViewHolder(parent.inflate(R.layout.layout_shuttle_buses))
            else -> throw IllegalStateException("")
        }

    override fun getItemViewType(position: Int): Int =
        when (position) {
            0 -> LECTURES
            1 -> CARPARKS
            2 -> SHUTTLEBUS
            else -> throw IllegalStateException("")
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position) {
            LECTURES -> (holder as LecturesViewHolder).bind(lectures)
            CARPARKS -> (holder as AvailableCarParksViewHolder).bind(carParks)
            SHUTTLEBUS -> (holder as ShuttleBusScheduleViewHolder).bind(shuttleBuses)
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        if(holder is CleanableViewGroup) {
            holder.onCleanUp()
        }
    }

    override fun getItemCount(): Int = NUMBER_OF_SECTIONS

    fun setLectures(lectures: List<Lecture>) {
        this.lectures = lectures
        notifyItemChanged(LECTURES)
    }

    fun setAvailableCarParks(carparks: List<CarPark>) {
        this.carParks = carparks
        notifyItemChanged(CARPARKS)
    }

    fun setShuttleBuses(shuttleBuses: List<ShuttleBus>) {
        this.shuttleBuses = shuttleBuses
        notifyItemChanged(SHUTTLEBUS)
    }

    private fun LinearLayout.addViewByIndex(index: Int, f: () -> View) {
        val view = f()
        if (index == 0) {
            addView(view)
        } else {
            addSeperatedView(view)
        }
    }

    private fun ViewGroup.addSeperatedView(view: View) {
        val seperator =
            LayoutInflater.from(this.context).inflate(R.layout.view_horizontal_divider, null)
                .apply {
                    val params = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    params.gravity = Gravity.CENTER_HORIZONTAL

                    layoutParams = params
                }
        addView(seperator)
        addView(view)
    }

    inner class LecturesViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view), CleanableViewGroup {
        private val binding = LayoutLectureGroupBinding.bind(view)

        fun bind(lectures: List<Lecture>) {
            binding.lectureListContainer.apply {
                lectures.forEachIndexed { index, lecture ->
                    addViewByIndex(index) { createLectureView(lecture) }
                }
            }
        }

        private fun createLectureView(lecture: Lecture): View {
            val lectureView =
                LayoutInflater.from(view.context).inflate(R.layout.layout_lecture_info, null)
            val binding = LayoutLectureInfoBinding.bind(lectureView)

            binding.startTime.text = lecture.fromTime
            binding.endTime.text = lecture.toTime
            binding.unitName.text = lecture.name
            binding.lecturerName.text = lecture.lecturer
            binding.campusName.text = lecture.campusInfoString

            return lectureView
        }

        override fun onCleanUp() {
            binding.lectureListContainer.removeAllViews()
        }
    }

    inner class AvailableCarParksViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view), CleanableViewGroup {
        private val binding = LayoutAvailableCarparkBinding.bind(view)

        fun bind(carparks: List<CarPark>) {
            binding.carparkListContainer.apply {
                carparks.forEachIndexed { index, carPark ->
                    addViewByIndex(index) { createCarparkView(carPark) }
                }
            }
        }

        private fun createCarparkView(carpark: CarPark): View {
            val carParkView =
                LayoutInflater.from(view.context).inflate(R.layout.view_available_car_park, null)
            val binding = ViewAvailableCarParkBinding.bind(carParkView)
            binding.carparkName.text = carpark.carparkName
            binding.carparkNumber.text = carpark.availableSpaces
            return carParkView
        }

        override fun onCleanUp() {
            binding.carparkListContainer.removeAllViews()
        }
    }

    inner class ShuttleBusScheduleViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view), CleanableViewGroup {
        private val binding = LayoutShuttleBusesBinding.bind(view)

        fun bind(buses: List<ShuttleBus>) {
            binding.schedulesContainer.apply {
                buses.forEachIndexed { index, shuttleBus ->
                    addViewByIndex(index) {
                        createCarparkView(shuttleBus)
                    }
                }
            }
        }

        private fun createCarparkView(buses: ShuttleBus): View {
            val view =
                LayoutInflater.from(view.context).inflate(R.layout.view_shuttle_bus, null)
            val binding = ViewShuttleBusBinding.bind(view)
            binding.campusFrom.text = buses.from
            binding.campusTo.text = buses.to
            binding.duration.text = buses.duration
            return view
        }

        override fun onCleanUp() {
            binding.schedulesContainer.removeAllViews()
        }
    }

    interface CleanableViewGroup {
        fun onCleanUp()
    }
}