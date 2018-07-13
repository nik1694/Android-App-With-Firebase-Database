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
import android.widget.Toast;

import com.example.nikhil.sqlitelogin.R;
import com.example.nikhil.sqlitelogin.helper.InputValidation;
import com.example.nikhil.sqlitelogin.model.User;
import com.example.nikhil.sqlitelogin.sql.DataBaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

private final AppCompatActivity activity= RegisterActivity.this;

    private NestedScrollView nestedScrollView;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;


    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;


    private AppCompatButton appCompatButtonRegister;

    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;
    private DataBaseHelper dataBaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        firebaseAuth=FirebaseAuth.getInstance();


        initView();
        initListener();
        initObject();
    }


    private void initView()
    {
        nestedScrollView= (NestedScrollView) findViewById(R.id.nestedScrollView);

        progressDialog=new ProgressDialog(this);

        textInputLayoutName= (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail= (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword= (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword= (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextName= (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail= (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword=(TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword=(TextInputEditText) findViewById(R.id.textInputEditTextConfirmPpassword);

        appCompatButtonRegister= (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewLoginLink=(AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);


    }

    private void initListener()
    {

        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);

    }


    private void initObject()
    {

       // dataBaseHelper =new DataBaseHelper(activity);
        inputValidation=new InputValidation(activity);
       // user =new User();
    }






    private void RegisterUser() {

        String name = textInputEditTextName.getText().toString().trim();
        String email = textInputEditTextEmail.getText().toString().trim();
        String password = textInputEditTextPassword.getText().toString().trim();
        String confirmPassword = textInputEditTextConfirmPassword.getText().toString().trim();



        if(TextUtils.isEmpty(email))
        {

            Toast.makeText(this, "PleaseEnter Email", Toast.LENGTH_SHORT).show();

            return;

        }

        if(TextUtils.isEmpty(password))
        {

            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();

            return;

        }

        if(TextUtils.isEmpty(name))
        {

            Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show();

            return;

        }

        if(TextUtils.isEmpty(confirmPassword))
        {

            Toast.makeText(this, "Please Enter confirmPassword", Toast.LENGTH_SHORT).show();

            return;

        }


       /*

        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword,textInputEditTextConfirmPassword,textInputLayoutPassword,getString(R.string.error_password_match)))
        {
            return;
        }




        if(!inputValidation.isInputEditTextFilled(textInputEditTextName,textInputLayoutName,getString(R.string.error_message_name)))
        {
            return;

        }

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

        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword,textInputEditTextConfirmPassword,textInputLayoutPassword,getString(R.string.error_password_match)))
        {
            return;
        }
*/

        progressDialog.setMessage("Registring User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if(task.isSuccessful())
                {

                   // finish();
                    Toast.makeText(RegisterActivity.this,"User Register Successfully",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                   //startActivity(new  Intent(getApplicationContext(),LoginActivity.class));

                    //Snackbar.make(nestedScrollView,getString(R.string.success_message),Snackbar.LENGTH_LONG).show();
                }
                else {

                    Toast.makeText(RegisterActivity.this,"User Register Fail",Toast.LENGTH_SHORT).show();

                    progressDialog.dismiss();
                   // Snackbar.make(nestedScrollView,getString(R.string.error_email_exists),Snackbar.LENGTH_LONG).show();
                }
            }
        });



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




    }

        @Override
    public void onClick(View v) {

            switch (v.getId())
            {
                case R.id.appCompatButtonRegister:
                    RegisterUser();

                    emptyInputEditText();
                    //startActivity(new  Intent(getApplicationContext(),LoginActivity.class));


                    //postDataToSQLite();
                    break;
                case R.id.appCompatTextViewLoginLink:
                    finish();
                    startActivity(new  Intent(getApplicationContext(),LoginActivity.class));

                    break;

            }
}




/*private  void postDataToSQLite()
{

    if(!inputValidation.isInputEditTextFilled(textInputEditTextName,textInputLayoutName,getString(R.string.error_message_name)))
    {
        return;

    }

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

    if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword,textInputEditTextConfirmPassword,textInputLayoutPassword,getString(R.string.error_password_match)))
    {
        return;
    }

    if(!dataBaseHelper.CheckUser(textInputEditTextEmail.getText().toString().trim()))

    {

user.setName(textInputEditTextName.getText().toString().trim());
 user.setEmail(textInputEditTextEmail.getText().toString().trim());
  user.setPassword(textInputEditTextPassword.getText().toString().trim());
        dataBaseHelper.addUser(user);


        Snackbar.make(nestedScrollView,getString(R.string.success_message),Snackbar.LENGTH_LONG).show();
        emptyInputEditText();

    }
    else {
        Snackbar.make(nestedScrollView,getString(R.string.error_email_exists),Snackbar.LENGTH_LONG).show();
    }

}*/

private void emptyInputEditText()
{

    textInputEditTextName.setText(null);

    textInputEditTextEmail.setText(null);

    textInputEditTextPassword.setText(null);
    textInputEditTextConfirmPassword.setText(null);

}


}