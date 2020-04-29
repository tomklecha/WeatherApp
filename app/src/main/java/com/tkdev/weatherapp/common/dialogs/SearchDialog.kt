package com.tkdev.weatherapp.common.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.tkdev.weatherapp.R
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchDialog : DialogFragment() {

    lateinit var listener: SearchDialogListener

    interface SearchDialogListener {
        fun onSearchPositiveClick(city: String)
        fun onSearchNegativeClick(message: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as SearchDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                    " must implement SearchDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder: AlertDialog.Builder? = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val dialogLayout = inflater.inflate(R.layout.fragment_search, null)
            val editText: EditText = dialogLayout.city_search_text
            builder?.setView(dialogLayout)

            builder?.setPositiveButton(R.string.apply)
            { _, _ ->
                listener.onSearchPositiveClick(editText.text.toString())
            }
            builder?.setNegativeButton(R.string.cancel)
            { _, _ ->
                listener.onSearchNegativeClick(getString(R.string.search_dialog_onCancel))
            }

            builder?.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}