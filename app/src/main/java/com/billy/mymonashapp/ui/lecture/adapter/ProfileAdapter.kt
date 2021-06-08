package com.billy.mymonashapp.ui.lecture.adapter

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
import com.billy.mymonashapp.ui.lecture.model.CarParkItem
import com.billy.mymonashapp.ui.lecture.model.LectureItem
import com.billy.mymonashapp.ui.lecture.model.ShuttleBusItem

private const val NUMBER_OF_SECTIONS = 3
private const val LECTURES = 0
private const val CARPARKS = 1
private const val SHUTTLEBUS = 2

@SuppressLint("InflateParams")
class ProfileAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var lectureItems: List<LectureItem> = emptyList()
    private var carParkItems: List<CarParkItem> = emptyList()
    private var shuttleBusItems: List<ShuttleBusItem> = emptyList()

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
            LECTURES -> (holder as LecturesViewHolder).bind(lectureItems)
            CARPARKS -> (holder as AvailableCarParksViewHolder).bind(carParkItems)
            SHUTTLEBUS -> (holder as ShuttleBusScheduleViewHolder).bind(shuttleBusItems)
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        if(holder is CleanableViewGroup) {
            holder.onCleanUp()
        }
    }

    override fun getItemCount(): Int = NUMBER_OF_SECTIONS

    fun setLectures(lectureItems: List<LectureItem>) {
        this.lectureItems = lectureItems
        notifyItemChanged(LECTURES)
    }

    fun setAvailableCarParks(carparks: List<CarParkItem>) {
        this.carParkItems = carparks
        notifyItemChanged(CARPARKS)
    }

    fun setShuttleBuses(shuttleBusItems: List<ShuttleBusItem>) {
        this.shuttleBusItems = shuttleBusItems
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

        fun bind(lectureItems: List<LectureItem>) {
            binding.lectureListContainer.apply {
                lectureItems.forEachIndexed { index, lecture ->
                    addViewByIndex(index) { createLectureView(lecture) }
                }
            }
        }

        private fun createLectureView(lectureItem: LectureItem): View {
            val lectureView =
                LayoutInflater.from(view.context).inflate(R.layout.view_lecture_info, null)
            val binding = ViewLectureInfoBinding.bind(lectureView)

            binding.startTime.text = lectureItem.fromTime
            binding.endTime.text = lectureItem.toTime
            binding.unitName.text = lectureItem.name
            binding.lecturerName.text = lectureItem.lecturer
            binding.campusName.text = lectureItem.campusInfoString

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

        fun bind(carparks: List<CarParkItem>) {
            binding.carparkListContainer.apply {
                carparks.forEachIndexed { index, carPark ->
                    addViewByIndex(index) { createCarparkView(carPark) }
                }
            }
        }

        private fun createCarparkView(carpark: CarParkItem): View {
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

        fun bind(busItems: List<ShuttleBusItem>) {
            binding.schedulesContainer.apply {
                busItems.forEachIndexed { index, shuttleBus ->
                    addViewByIndex(index) {
                        createCarparkView(shuttleBus)
                    }
                }
            }
        }

        private fun createCarparkView(buses: ShuttleBusItem): View {
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