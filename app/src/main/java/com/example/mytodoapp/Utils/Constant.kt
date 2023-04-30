package com.example.mytodoapp.Utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import android.widget.EditText
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

object
Constant {
    interface OnTimeSelectedListener {
        fun onTimeSelected(timeInMillis: Long,Time:String)
    }
    fun showTimePicker(v: EditText,context: Context,listener: OnTimeSelectedListener) {
        val timePicker = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val formattedTime: String = when {
                hourOfDay == 0 -> {
                    if (minute < 10) {
                        "${hourOfDay + 12}:0${minute} AM"
                    } else {
                        "${hourOfDay + 12}:${minute} AM"
                    }
                }
                hourOfDay > 12 -> {
                    if (minute < 10) {
                        "${hourOfDay - 12}:0${minute} PM"
                    } else {
                        "${hourOfDay - 12}:${minute} PM"
                    }
                }
                hourOfDay == 12 -> {
                    if (minute < 10) {
                        "${hourOfDay}:0${minute} PM"
                    } else {
                        "${hourOfDay}:${minute} PM"
                    }
                }
                else -> {
                    if (minute < 10) {
                        "${hourOfDay}:0${minute} AM"
                    } else {
                        "${hourOfDay}:${minute} AM"
                    }
                }
            }
            v.setText(formattedTime)
            listener.onTimeSelected( (hourOfDay*3600000 + minute*60000).toLong(),formattedTime)
        }
        val calendar = Calendar.getInstance()
        // get the current hour and minute
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        // create a time picker dialog with the current time as the default value
            val timePickerDialog = TimePickerDialog(context, timePicker, hour, minute ,false)
            timePickerDialog.show()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun clickDataPicker(v: EditText, context: Context, listener: OnTimeSelectedListener){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            context,
            { view, year, monthOfYear, dayOfMonth ->
                v.setText("$dayOfMonth/${monthOfYear + 1}/$year")
                val date = LocalDate.of(year, monthOfYear + 1, dayOfMonth)
                val dateTime = date.atStartOfDay()
                val milliseconds = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                listener.onTimeSelected(milliseconds.toLong(),"$dayOfMonth/${monthOfYear + 1}/$year")
            },
            year,
            month,
            day
        )
        dpd.show()
    }
}