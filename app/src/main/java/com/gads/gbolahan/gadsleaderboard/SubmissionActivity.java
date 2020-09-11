package com.gads.gbolahan.gadsleaderboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gads.gbolahan.gadsleaderboard.retro.SubmissionDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity {

    private APIInterface mAPIInterface;

    @Override
    protected void onCreate (final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        Button submitButton = findViewById(R.id.button_submit_project);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                submitProjectDialog();
            }
        });
    }

    private void submitProjectDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_confirm, null);

        Button buttonOk = dialogView.findViewById(R.id.buttonOk);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                submitProject();
            }
        });

        alertDialog.show();
    }

    private void errorDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_error, null);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }

    private void successDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_success, null);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }

    private void submitProject(){
        mAPIInterface = APIClient.getClient("https://docs.google.com/forms/d/e/").create(APIInterface.class);

        EditText textFirstName = findViewById(R.id.text_firstname);
        EditText textLastName = findViewById(R.id.text_lastname);
        EditText textEmailAddress = findViewById(R.id.text_email_address);
        EditText textProjectLink = findViewById(R.id.text_project_link);

        String firstName = textFirstName.getText().toString();
        String lastName = textLastName.getText().toString();
        String emailAddress = textEmailAddress.getText().toString();
        String link = textProjectLink.getText().toString();

        Call<SubmissionDetails> call = mAPIInterface.submit(firstName, lastName, emailAddress, link);
        call.enqueue(new Callback<SubmissionDetails>() {
            @Override
            public void onResponse (Call<SubmissionDetails> call, Response<SubmissionDetails> response) {
                if(response.isSuccessful()) {
                    Log.e("Response", String.valueOf(response.code()));
                    successDialog();
                } else {
                    Log.e("Response", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure (Call<SubmissionDetails> call, Throwable t) {
                Log.e("error", t.getMessage());
                Toast.makeText(getBaseContext(), "Something went wrong...Please try later!", Toast.LENGTH_LONG).show();
                t.printStackTrace();
                errorDialog();
            }
        });
    }
}