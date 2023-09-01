package com.example.horfay123.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.horfay123.R
import com.example.horfay123.databinding.DateAndTimeBootomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.puskal.merocalendar.DateClickListener
import com.puskal.merocalendar.enum.CalendarType
import com.puskal.merocalendar.enum.LocalizationType
import com.puskal.merocalendar.model.DateModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale


class DateAndTimeBottomSheet : BottomSheetDialogFragment(R.layout.date_and_time_bootom_sheet) {

    lateinit var binding: DateAndTimeBootomSheetBinding
    private val calender = Calendar.getInstance()
    private val TAG = "DateAndTimeBottomSheet"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DateAndTimeBootomSheetBinding.inflate(inflater, container, false)
        calenderView()

        return binding.root
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dayOfToday = LocalDate.now().dayOfWeek.name
        val dateOfToday = calender.get(Calendar.DATE)
        val dayOfTomorrow = LocalDate.now().plusDays(1).dayOfWeek.name
        val dateOfTomorrow = LocalDate.now().plusDays(1).dayOfMonth
        val nextDayOfTomorrow = LocalDate.now().plusDays(2).dayOfWeek.name
        val nextDateOfTomorrow = LocalDate.now().plusDays(2).dayOfMonth
        val monthAndYear =
            LocalDate.now().month.toString() + " - " + LocalDate.now().year.toString()
        val currentTime = SimpleDateFormat("hh:mm:aa").format(Calendar.getInstance().time)

        calenderView()

        // add one hours extra time : 12:00:01
        val addOneHours = Calendar.getInstance()
        addOneHours.add(Calendar.HOUR_OF_DAY, 1)
        val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        timeFormat.format(addOneHours.time) // 1:00:01
        Log.e(
            TAG,
            "Today: $dayOfToday:$dateOfToday , Tomorrow: $dayOfTomorrow:$dateOfTomorrow , NextDayOfTomorrow: $nextDayOfTomorrow:$nextDateOfTomorrow , monthAndYear:$monthAndYear",
        )
        binding.apply {
            // using subString we got a first 3 letter of String's
            /*dateTextView.text = dayOfToday.substring(0..2) + "\n" + dateOfToday
            tomorrowTextView.text = dayOfTomorrow.substring(0..2) + "\n" + dateOfTomorrow
            dayOfTomorrowText.text = nextDayOfTomorrow.substring(0..2) + "\n" + nextDateOfTomorrow*/
            timeTextview1.text = currentTime
            timeTextview2.text = timeFormat.format(addOneHours.time)
            timeTextview3.text = timeFormat.format(addOneHours.time)

            button.setOnClickListener {
                startActivity(Intent(requireContext(), SummaryScreen::class.java))
            }
        }
    }

    private fun calenderView() {
        val dateClickListener = object : DateClickListener {
            override fun onDateClick(dateModel: DateModel) {
                Log.e("onDateClick","data is ${dateModel.localizedFormattedDate}")
                binding.monthYearTextview.text = dateModel.localizedFormattedDate

                // binding.tvCurrentDate.text =  dateModel.localizedFormattedDate
            }
        }

        with(binding) {
            previousDate.setOnClickListener {
                horizontalCalenderView.setPreviousMonthDate()
            }

            nextDate.setOnClickListener {
                horizontalCalenderView.setNextMonthDate()
            }

        }

        binding.horizontalCalenderView.setCalendarType(CalendarType.AD)
            .setLanguage(LocalizationType.ENGLISH_US)
            .setOnDateClickListener(dateClickListener)
            .build()
    }

    override fun getTheme(): Int {
        return R.style.FullScreenBottomSheetDialog
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog1 = BottomSheetDialog(requireContext(), theme)
        dialog1.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            val parentLayout = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { dialog ->
                val behaviour = BottomSheetBehavior.from(dialog)
                setupFullHeight(dialog)
                behaviour.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
        return dialog1

    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

}