package com.tkdev.weatherapp.common.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.CheckBox
import androidx.fragment.app.DialogFragment
import com.tkdev.weatherapp.R
import kotlinx.android.synthetic.main.fragment_share.view.*

class ShareDialog: DialogFragment() {

    lateinit var listener: ShareDialogListener

    interface ShareDialogListener {
        fun onSharePositiveClick(list: ArrayList<Boolean>)
        fun onShareNegativeClick(message: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as ShareDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                    " must implement ShareDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        return activity?.let {
            val builder: AlertDialog.Builder? = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val dialogLayout = inflater.inflate(R.layout.fragment_share, null)
            val currentCheckBox: CheckBox = dialogLayout.share_current_temp
            val weatherCheckBox: CheckBox = dialogLayout.share_weather
            val lastUpdateCheckBox: CheckBox = dialogLayout.share_last_update
            val share = ArrayList<Boolean>()
            builder?.setView(dialogLayout)

            builder?.setPositiveButton(R.string.apply)
            { _, _ ->
                share.add(currentCheckBox.isChecked)
                share.add(weatherCheckBox.isChecked)
                share.add(lastUpdateCheckBox.isChecked)
                listener.onSharePositiveClick(share)
            }
//            builder?.setNegativeButton(R.string.cancel)
//            { _, _ ->
//                listener.onSearchNegativeClick(getString(R.string.search_dialog_onCancel))
//            }

            builder?.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}