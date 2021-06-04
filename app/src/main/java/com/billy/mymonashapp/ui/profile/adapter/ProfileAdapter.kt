package com.billy.mymonashapp.ui.profile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.billy.mymonashapp.R
import com.billy.mymonashapp.application.shared.inflate
import com.billy.mymonashapp.databinding.LayoutAvailableCarparkBinding
import com.billy.mymonashapp.databinding.LayoutLectureGroupBinding
import com.billy.mymonashapp.databinding.LayoutLectureInfoBinding
import com.billy.mymonashapp.databinding.ViewAvailableCarParkBinding
import com.billy.mymonashapp.domain.StudentProfile

private const val NUMBER_OF_SECTIONS = 3
private const val LECTURES = 0
private const val CARPARKS = 1
private const val SHUTTLEBUS = 2

class ProfileAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var profile: StudentProfile? = null
//    private var contacts: List<ContactDetails> = emptyList()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactItemViewHolder {
//        return ContactItemViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.item_contact_detail, parent, false)
//        )
//    }
//
//    fun updateContacts(messageList: List<ContactDetails>) {
//        contacts = messageList
//        notifyDataSetChanged()
//    }
//
//    override fun getItemCount(): Int = contacts.size
//
//    override fun onBindViewHolder(holder: ContactItemViewHolder, position: Int) =
//        holder.bind(contacts[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            LECTURES -> LecturesViewHolder(parent.inflate(R.layout.layout_lecture_group))
            CARPARKS -> LecturesViewHolder(parent.inflate(R.layout.layout_lecture_group))
            SHUTTLEBUS -> LecturesViewHolder(parent.inflate(R.layout.layout_lecture_group))
            else -> throw IllegalStateException("")
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        profile?.let {
            (holder as LecturesViewHolder).bind(it.lectures)
        }
    }

    override fun getItemCount(): Int = NUMBER_OF_SECTIONS

    fun setProfile(profile: StudentProfile) {
        this.profile = profile
        notifyDataSetChanged()
    }

    inner class LecturesViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        private val binding = LayoutLectureGroupBinding.bind(view)

        fun bind(lectures: List<StudentProfile.Lecture>) {
            binding.lectureListContainer.apply {
                lectures.forEach { addView(createLectureView(it)) }
            }
        }

        private fun createLectureView(lecture: StudentProfile.Lecture): View {
            val lectureView =
                LayoutInflater.from(view.context).inflate(R.layout.layout_lecture_info, null)
            val binding = LayoutLectureInfoBinding.bind(lectureView)

            binding.endTime.text = lecture.toTime
            binding.unitName.text = lecture.name
            binding.lecturerName.text = lecture.lecturer

            return lectureView
        }
    }

    inner class AvailableCarParksViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        private val binding = LayoutAvailableCarparkBinding.bind(view)

        fun bind(lectures: List<StudentProfile.CarPark>) {
            binding.carparkListContainer.apply {
                lectures.forEach { addView(createLectureView(it)) }
            }
        }

        private fun createLectureView(carpark: StudentProfile.CarPark): View {
            val carParkView =
                LayoutInflater.from(view.context).inflate(R.layout.view_available_car_park, null)
            val binding = ViewAvailableCarParkBinding.bind(carParkView)
            binding.carparkName.text = carpark.carparkName
            binding.carparkNumber.text = carpark.availableSpaces
            return carParkView
        }
    }

}