package com.example.nikhil.sqlitelogin.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.RasterizerSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nikhil.sqlitelogin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.nikhil.sqlitelogin.R.id.text1;

public class UserActivity extends AppCompatActivity implements View.OnClickListener{



    private FirebaseAuth firebaseAuth;

    private TextView textViewName;

    private Button buttonLogout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);



        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null)
        {

            finish();

            startActivity(new Intent(this,LoginActivity.class));
        }


        FirebaseUser user=firebaseAuth.getCurrentUser();


        textViewName=(TextView) findViewById(text1);


        textViewName.setText("Welcome"+user.getEmail());

        buttonLogout=(Button) findViewById(R.id.buttonLogout);


        buttonLogout.setOnClickListener(this);


        //String nameFromIntent=getIntent().getStringExtra("EMAIL");
       // textViewName.setText("Welcome" + nameFromIntent);


    }

    @Override
    public void onClick(View view) {



        if(view == buttonLogout){

            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));

        }

    }
}
