package com.abbes.schoolspace.teacherScreens

import android.R
import android.app.AlertDialog
import android.app.SearchManager
import android.content.Context
import android.content.DialogInterface
import android.content.DialogInterface.OnMultiChoiceClickListener
import android.util.AttributeSet
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.abbes.schoolspace.models.Classe
import com.abbes.schoolspace.models.ClasseItem
import retrofit2.Callback
import java.util.ArrayList


class MultiSpinner : androidx.appcompat.widget.AppCompatSpinner, OnMultiChoiceClickListener,
     DialogInterface.OnCancelListener {
    private var items: ArrayList<ClasseItem>? = null
    private lateinit var selected: BooleanArray
    private var defaultText: String? = null
    private var listener: Callback<Classe>? = null

    constructor(context: Context?) : super(context!!) {}
    constructor(arg0: Context?, arg1: AttributeSet?) : super(arg0!!, arg1) {}
    constructor(arg0: Context?, arg1: AttributeSet?, arg2: Int) : super(arg0!!, arg1, arg2) {}

    override fun onClick(dialog: DialogInterface, which: Int, isChecked: Boolean) {
        if (isChecked) selected[which] = true else selected[which] = false
    }

     override fun onCancel(dialog: DialogInterface?) {
        // refresh text on spinner
        val spinnerBuffer = StringBuffer()
        var someSelected = false
        for (i in items!!.indices) {
            if (selected[i] == true) {
                spinnerBuffer.append(items!![i])
                spinnerBuffer.append(", ")
                someSelected = true
            }
        }
        var spinnerText: String?
        if (someSelected) {
            spinnerText = spinnerBuffer.toString()
            if (spinnerText.length > 2) spinnerText =
                spinnerText.substring(0, spinnerText.length - 2)
        } else {
            spinnerText = defaultText
        }
        val adapter = ArrayAdapter(
            context,
            R.layout.simple_spinner_item, arrayOf(spinnerText)
        )
        setAdapter(adapter)

    }

    override fun performClick(): Boolean {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)

        builder.setPositiveButton(
            R.string.ok,
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        builder.setOnCancelListener(this)
        builder.show()
        return true
    }

    fun setItems(
        items: ArrayList<ClasseItem>, allText: String?,
        listener: Callback<Classe>
    ) {
        this.items = items
        defaultText = allText
        this.listener = listener

        // all selected by default
        selected = BooleanArray(items.size)
        for (i in selected.indices) selected[i] = true

        // all text on the spinner
        val adapter = ArrayAdapter(
            context,
            R.layout.simple_spinner_item, arrayOf(allText)
        )
        setAdapter(adapter)
    }

    interface MultiSpinnerListener {
        fun onItemsSelected(selected: BooleanArray?)
    }


}