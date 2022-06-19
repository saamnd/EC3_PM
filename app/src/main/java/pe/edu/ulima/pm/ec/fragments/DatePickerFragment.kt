package pe.edu.ulima.pm.ec.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment(val listener: (day:Int,month:Int,year:Int) -> Unit): DialogFragment(),
    DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val c:Calendar = Calendar.getInstance()
        val day:Int = c.get(Calendar.DAY_OF_MONTH)
        val month: Int = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        val picker = DatePickerDialog(activity as Context, this,year,month,day)

        return picker

    }

    override fun onDateSet(view: DatePicker?, day:Int,month:Int,year:Int) {
        listener(day,month,year)
    }


}