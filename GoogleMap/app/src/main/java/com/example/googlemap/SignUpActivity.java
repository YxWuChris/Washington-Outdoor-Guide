package com.example.googlemap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;


public class SignUpActivity  extends AppCompatActivity implements OnClickListener {

    private static View view;
    private static EditText fullName, emailId, mobileNumber, password, confirmPassword;
    private static TextView login;
    private static Button signUpButton;
    private static CheckBox terms_conditions;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullName = (EditText) findViewById(R.id.fullName);
        emailId = (EditText) findViewById(R.id.userEmailId);
        mobileNumber = (EditText) findViewById(R.id.mobileNumber);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        signUpButton = (Button) findViewById(R.id.signUpBtn);
        login = (TextView) findViewById(R.id.already_user);
        terms_conditions = (CheckBox) findViewById(R.id.terms_conditions);
        setListeners();

    }

    private void setListeners() {
        signUpButton.setOnClickListener(this);
        login.setOnClickListener(this);
    }


    private boolean checkValidation() {

        String getFullName = fullName.getText().toString();
        String getEmailId = emailId.getText().toString();
        String getMobileNumber = mobileNumber.getText().toString();
        String getPassword = password.getText().toString();
        String getConfirmPassword = confirmPassword.getText().toString();

        if (!getEmailId.matches(emailPattern)) {
            Toast.makeText(getApplicationContext(), "Invalid email address.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (getFullName.equals("") || getFullName.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0
                || getMobileNumber.equals("") || getMobileNumber.length() == 0
                || getPassword.equals("") || getPassword.length() == 0
                || getConfirmPassword.equals("")
                || getConfirmPassword.length() == 0) {
            Toast.makeText(getApplicationContext(), "All fields are required.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!getConfirmPassword.equals(getPassword)) {
            Toast.makeText(getApplicationContext(), "Password doesnt match", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.signUpBtn:



                if(checkValidation()) {
                    String getEmailId = emailId.getText().toString();
                    String getPassword = password.getText().toString();

                    MD5Encryption encryption = new MD5Encryption(password.getText().toString());
                    String encryptionPassword = encryption.getEncryption();

                    SharedPreferences.Editor editor = getSharedPreferences("UserData", MODE_PRIVATE).edit();
                    editor.putString(getEmailId, encryptionPassword);

                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    break;
                }else{
                    Intent intent = new Intent(this, SignUpActivity.class);
                    startActivity(intent);
                    break;
                }
            case R.id.already_user:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }


    }


}
