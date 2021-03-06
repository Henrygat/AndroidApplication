package ua.nure.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ua.nure.myapplication.R;
import ua.nure.myapplication.commands.ChangePasswordClientCommand;
import ua.nure.myapplication.commands.ClientCommand;
import ua.nure.myapplication.fragments.WarningDialogFilingTheGaps;
import ua.nure.myapplication.fragments.WarningDialogPasswordFailed;
import ua.nure.myapplication.fragments.WarningDialogPasswordSucceed;
import utility.CommandsList;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void confirmButtonOnClick(View view) {
        EditText oldPasswordText = findViewById(R.id.oldPasswordText);
        EditText newPasswordText = findViewById(R.id.newPasswordText);

        String oldPassword = oldPasswordText.getText().toString().replace(CommandsList.GAP_STRING,CommandsList.EMPTY_STRING);
        String newPassword = newPasswordText.getText().toString().replace(CommandsList.GAP_STRING,CommandsList.EMPTY_STRING);

        if (oldPassword.equals(CommandsList.EMPTY_STRING) || newPassword.equals(CommandsList.EMPTY_STRING)) {
            MainActivity.getWarningsHelper().showFragment(this, WarningDialogFilingTheGaps.class.getName());
        } else if (!MainActivity.getClient().getPassword().equals(oldPassword))  {
            MainActivity.getWarningsHelper().showFragment(this, WarningDialogPasswordFailed.class.getName());
        } else {
            new Thread(() -> {
                ClientCommand command = MainActivity.getClientCommandsHolder().getCommand(ChangePasswordClientCommand.class.getName());
                if (((ChangePasswordClientCommand)command).setNewPassword(newPassword).execute().equals(ClientCommand.POSITIVE_ANSWER)) {
                    MainActivity.getClient().setPassword(newPassword);
                    MainActivity.getWarningsHelper().showFragment(this, WarningDialogPasswordSucceed.class.getName());
                    this.finish();
                }
            }).start();
        }

    }
}