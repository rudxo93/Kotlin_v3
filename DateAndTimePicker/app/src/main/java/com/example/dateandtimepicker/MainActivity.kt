package com.example.dateandtimepicker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.dateandtimepicker.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var tvDatePicker : TextView
    private lateinit var btnDatePicker : Button

    private lateinit var tvTimePicker : TextView
    private lateinit var btnTimePicker : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // datePicker
        tvDatePicker = findViewById(R.id.tvDate)
        btnDatePicker = findViewById(R.id.btnDatePicker)

        // timePicker
        tvTimePicker = findViewById(R.id.tv_textTime)
        btnTimePicker = findViewById(R.id.btn_timePicker)

        val myCalendar = Calendar.getInstance()
        val myTimeCalendar = Calendar.getInstance()

        // datePicker
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable(myCalendar)
        }

        btnDatePicker.setOnClickListener {
            DatePickerDialog(this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        // timePicker
        val timePicker = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            myTimeCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            myTimeCalendar.set(Calendar.MINUTE, minute)
            updateTimeLable(myTimeCalendar)
        }

        btnTimePicker.setOnClickListener {
            TimePickerDialog(this, timePicker, myTimeCalendar.get(Calendar.HOUR_OF_DAY), myTimeCalendar.get(Calendar.MINUTE), true).show()
        }

    }

    private fun updateTimeLable(myTimeCalendar: Calendar) {
        val myTimeFormat = "HH : mm"
        val stf = SimpleDateFormat(myTimeFormat, Locale.UK)
        tvTimePicker.setText(stf.format(myTimeCalendar.time))
    }

    private fun updateLable(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        tvDatePicker.setText(sdf.format(myCalendar.time))
    }
}