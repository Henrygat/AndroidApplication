package ua.nure.myapplication.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class WarningDialogPasswordFailed extends DialogFragment {
    private final String WARNING = "Wrong password.\nTry again!";
    private final String POSITIVE_BUTTON_TEXT = "OKAY";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder
                .setMessage(WARNING)
                .setPositiveButton(POSITIVE_BUTTON_TEXT, null)
                .create();
    }
}
