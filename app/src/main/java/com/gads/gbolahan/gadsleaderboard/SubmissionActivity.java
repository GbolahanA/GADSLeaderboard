package com.gads.gbolahan.gadsleaderboard;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity {

    private APIInterface mAPIInterface;

    @Override
    protected void onCreate (final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        /*assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);*/

        Toolbar toolbar = findViewById(R.id.gads_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowTitleEnabled(false);

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
                alertDialog.dismiss();
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

        Call<Void> call = mAPIInterface.submitProject(firstName, lastName, emailAddress, link);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse (Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(getBaseContext(), "Successful", Toast.LENGTH_LONG).show();
                    Log.e("PostResponse", String.valueOf(response.code()));
                    successDialog();
                } else {
                    Toast.makeText(getBaseContext(), "Something went wrong...Please try later!", Toast.LENGTH_LONG).show();
                    Log.e("PostFailedResponse", String.valueOf(response.code()));
                    errorDialog();
                }
            }

            @Override
            public void onFailure (Call<Void> call, Throwable t) {
                Log.e("PostFailureError", t.getMessage());
                Toast.makeText(getBaseContext(), "Something went wrong...Failed!", Toast.LENGTH_LONG).show();
                // t.printStackTrace();
                errorDialog();
            }
        });
    }
}