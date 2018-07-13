package com.example.nikhil.sqlitelogin.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nikhil.sqlitelogin.R;
import com.example.nikhil.sqlitelogin.helper.InputValidation;
import com.example.nikhil.sqlitelogin.sql.DataBaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{

   private final AppCompatActivity activity= LoginActivity.this;

    private FirebaseAuth firebaseAuth;



    private NestedScrollView nestedScrollView;

    private ProgressDialog progressDialog;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private  TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView appCompatTextViewLinkRegister;

    private InputValidation inputValidation;
    //private DataBaseHelper dataBaseHelper;

    String emailMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        firebaseAuth=FirebaseAuth.getInstance();



        //emailMatch=textInputEditTextEmail.getText().toString();



        getSupportActionBar().hide();

        initView();
        initListener();
        initObject();

    }



    private void initView()
    {
        nestedScrollView= (NestedScrollView) findViewById(R.id.nestedScrollView);


        progressDialog=new ProgressDialog(this);
        textInputLayoutEmail= (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword= (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail= (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword=(TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin= (AppCompatButton) findViewById(R.id.appCompatButtonLogin);

        appCompatTextViewLinkRegister=(AppCompatTextView) findViewById(R.id.textViewLinkRegister);


    }

    private void initListener()
    {

    appCompatButtonLogin.setOnClickListener(this);
        appCompatTextViewLinkRegister.setOnClickListener(this);

    }

    private void initObject()
    {

        //dataBaseHelper =new DataBaseHelper(activity);
        inputValidation=new InputValidation(activity);
    }


    private void LoginUser()
    {
        String email=textInputEditTextEmail.getText().toString().trim();
        String password=textInputEditTextPassword.getText().toString().trim();








        if(TextUtils.isEmpty(email))
        {

            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();

            return;

        }

        if(TextUtils.isEmpty(password))
        {

            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();

            return;

        }



        /*if(!inputValidation.isInputEditTextFilled(textInputEditTextEmail,textInputLayoutEmail,getString(R.string.error_message_email)))
        {
            return;

        }
        if(!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail,getString(R.string.error_message_email)))

        {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword,textInputLayoutPassword,getString(R.string.error_message_password)))
        {
            return;
        }
*/


         progressDialog.setMessage("Logging User");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                progressDialog.dismiss();
                if(firebaseAuth.getCurrentUser() != null )
                {


                    //finish();
                   // Toast.makeText(LoginActivity.this, "User Login Suceess ", Toast.LENGTH_SHORT).show();
                   // startActivity(new  Intent(getApplicationContext(),UsersActivity.class));



                }


               /* if(dataBaseHelper.CheckUser(textInputEditTextEmail.getText().toString().trim()))

                {
                    Intent accountsIntent= new Intent(activity, UserActivity.class);
                    accountsIntent.putExtra("Email",textInputEditTextEmail.getText().toString().trim());
                    emptyInputEditText();
                    startActivity(accountsIntent);
                }
                else {
                    Snackbar.make(nestedScrollView,getString(R.string.error_valid_email_password),Snackbar.LENGTH_LONG).show();
                }*/

              // progressDialog.setMessage("Registring User");



                if(task.isSuccessful())
                {

                    Toast.makeText(LoginActivity.this, " User Login Suceess ", Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(new  Intent(getApplicationContext(),UsersActivity.class));

                    //Snackbar.make(nestedScrollView,getString(R.string.success_message),Snackbar.LENGTH_LONG).show();
                }
                else
                {

                    Toast.makeText(LoginActivity.this, "User Login fail..Try again ", Toast.LENGTH_SHORT).show();

                    startActivity(new  Intent(getApplicationContext(),LoginActivity.class));

                    //  Snackbar.make(nestedScrollView,getString(R.string.error_email_exists),Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.appCompatButtonLogin:

                LoginUser();
                //Intent accountsIntent= new Intent(activity, UsersActivity.class);
                //accountsIntent.putExtra("Email",textInputEditTextEmail.getText().toString().trim());
                emptyInputEditText();
                //startActivity(accountsIntent);
               // verifyFromSQLite();
                break;

            case R.id.textViewLinkRegister:
                finish();
                Intent intentRegister=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }



}

   /* private void verifyFromSQLite()
    {

        if(!inputValidation.isInputEditTextFilled(textInputEditTextEmail,textInputLayoutEmail,getString(R.string.error_message_email)))
        {
            return;

        }
        if(!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail,getString(R.string.error_message_email)))

        {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword,textInputLayoutPassword,getString(R.string.error_message_password)))
        {
            return;
        }

        if(dataBaseHelper.CheckUser(textInputEditTextEmail.getText().toString().trim()))

        {
            Intent accountsIntent= new Intent(activity, UserActivity.class);
            accountsIntent.putExtra("Email",textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
        }
        else {
            Snackbar.make(nestedScrollView,getString(R.string.error_valid_email_password),Snackbar.LENGTH_LONG).show();
        }


    }*/


    private void emptyInputEditText()
    {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
