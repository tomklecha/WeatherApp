package com.tkdev.weatherapp.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.tkdev.weatherapp.R;

import java.util.ArrayList;
import java.util.Objects;

public class ShareDialog extends DialogFragment {

    public interface ShareDialogListener {
        void onShareDialogPositiveClick(ArrayList<Boolean> booleanList);

        void onShareDialogNegativeClick(String text);
    }

    ShareDialogListener listener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ShareDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement ShareDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_share, null);
        final CheckBox currentCb = dialogView.findViewById(R.id.share_current_temp);
        final CheckBox weatherCb = dialogView.findViewById(R.id.share_weather);
        final CheckBox lastUpdateCb = dialogView.findViewById(R.id.share_last_update);
        ArrayList<Boolean> share = new ArrayList<>();
        builder.setView(dialogView)
                .setPositiveButton(R.string.apply, (dialog, id) -> {
                    share.add(currentCb.isChecked());
                    share.add(weatherCb.isChecked());
                    share.add(lastUpdateCb.isChecked());
                    listener.onShareDialogPositiveClick(share);
                })
//                .setNegativeButton(R.string.cancel, (dialog, which) -> {
//                    setCancelable(true);
//                    listener.onDialogNegativeClick(getString(R.string.search_dialog_onCancel));
//                })
                ;


        return builder.create();
    }
}
