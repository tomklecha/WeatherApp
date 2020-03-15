package com.tkdev.weatherapp.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.tkdev.weatherapp.MainActivity;
import com.tkdev.weatherapp.R;

import java.util.Objects;

public class SearchDialog extends DialogFragment {

    public interface SearchDialogListener {
        void onDialogPositiveClick(String text);

        void onDialogNegativeClick(String text);
    }

    SearchDialogListener listener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (SearchDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement SearchDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_search, null);
        final EditText city = dialogView.findViewById(R.id.city_search_text);

        builder.setView(dialogView)
                .setPositiveButton(R.string.apply, (dialog, id) -> {
                    listener.onDialogPositiveClick(city.getText().toString());
                })
                .setNegativeButton(R.string.cancel, (dialog, which) -> {
                    setCancelable(true);
                    listener.onDialogNegativeClick(getString(R.string.search_dialog_onCancel));
                });


        return builder.create();
    }
}
