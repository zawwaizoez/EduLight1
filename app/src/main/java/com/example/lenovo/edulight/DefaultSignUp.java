package com.example.lenovo.edulight;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class DefaultSignUp extends AppCompatActivity implements View.OnFocusChangeListener {

   //for firebase
   private FirebaseAuth mAuth;
    private Typeface boldFont, thinFont, normalFont;

    private TextView default_signup_header_title,default_signup_header_content;
    private TextView default_signup_term_of_service_title,default_signup_term_of_service_content;

    private EditText default_signup_gmail_address,default_signup_password,default_signup_confirm_password;

    private Button default_signup_confirm_btn;
    private Toolbar default_signup_toolbar;
    private AppBarLayout default_signup_action_bar;
    private CollapsingToolbarLayout default_signup_collapse_toolbar;
    private ProgressDialog mProgress;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_signup);
        mProgress =new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        boldFont = Typeface.createFromAsset(getAssets(), "oxygen.bold.ttf");
        thinFont = Typeface.createFromAsset(getAssets(), "oxygen.light.ttf");
        normalFont = Typeface.createFromAsset(getAssets(), "oxygen.regular.ttf");
        setTextFont();
        setEditTextFont();
        setButtonTextFont();
        setUpToolbar();
        setUpCollapseEditText();
        setUpCollapsingToolbar();
    }

    private void setUpCollapsingToolbar() {
        default_signup_collapse_toolbar = (CollapsingToolbarLayout)findViewById(R.id.default_sign_up_collapse_toolbar);
        default_signup_collapse_toolbar.setBackgroundColor(Color.parseColor("#00000000"));
        default_signup_collapse_toolbar.setCollapsedTitleTypeface(normalFont);

        default_signup_action_bar = (AppBarLayout)findViewById(R.id.default_sign_up_action_bar);
        default_signup_action_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(scrollRange == -1)
                {
                    scrollRange = default_signup_action_bar.getTotalScrollRange();
                }
                if(scrollRange + verticalOffset == 0)
                {
                    default_signup_collapse_toolbar.setTitle("Sign up");
                    isShow = true;
                }
                else if(isShow)
                {
                    default_signup_collapse_toolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });


    }

    private void setUpCollapseEditText() {
        default_signup_gmail_address.setOnFocusChangeListener(this);
        default_signup_password.setOnFocusChangeListener(this);
        default_signup_confirm_password.setOnFocusChangeListener(this);

    }

    private void setUpToolbar() {
        default_signup_toolbar = (Toolbar)findViewById(R.id.default_sign_up_toolbar);
        setSupportActionBar(default_signup_toolbar);
        default_signup_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_toolbar_back_24dp,null));
        default_signup_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void setButtonTextFont() {
        default_signup_confirm_btn = (Button)findViewById(R.id.default_signup_confirm_btn);
        default_signup_confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String email =  default_signup_gmail_address.getText().toString();
             String password=   default_signup_password.getText().toString();
              String confirmPassword=  default_signup_confirm_password.getText().toString();
              if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmPassword)){
                  if(password.equals(confirmPassword)){
                      mProgress.setTitle("Registering User");
                      mProgress.setMessage("Please wait a moment");
                      mProgress.setCanceledOnTouchOutside(false);
                      mProgress.show();
                      register_user(email,password);
                  }
                  else{
                      Toast.makeText(getApplicationContext(),"Password and confirm passoword are not equal",Toast.LENGTH_LONG).show();
                  }
              }
            }
        });

        default_signup_confirm_btn.setTypeface(normalFont);
    }

    private void register_user(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mProgress.dismiss();
                    Intent i = new Intent(DefaultSignUp.this,MainActivity.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_LONG).show();
                }
                else{
                    mProgress.hide();
                    Toast.makeText(getApplicationContext(),"not ok",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setEditTextFont() {
        default_signup_gmail_address = (EditText)findViewById(R.id.default_signup_gmail_address);
        default_signup_password = (EditText)findViewById(R.id.default_signup_password);
        default_signup_confirm_password = (EditText)findViewById(R.id.default_signup_confirm_password);
        default_signup_gmail_address.setTypeface(normalFont);
        default_signup_password.setTypeface(normalFont);
        default_signup_confirm_password.setTypeface(normalFont);
    }
    private void setTextFont() {
        default_signup_header_title = (TextView)findViewById(R.id.default_signup_header_title);
        default_signup_header_content = (TextView)findViewById(R.id.default_signup_header_content);
        default_signup_term_of_service_title = (TextView)findViewById(R.id.default_signup_term_of_service_title);
        default_signup_term_of_service_content = (TextView)findViewById(R.id.default_signup_term_of_service_content);
        default_signup_header_title.setTypeface(boldFont);
        default_signup_header_content.setTypeface(normalFont);
        default_signup_term_of_service_title.setTypeface(normalFont);
        default_signup_term_of_service_title.setText(Html.fromHtml("<u>Terms of Service</u>"));
        default_signup_term_of_service_content.setTypeface(normalFont);
    }

    @Override
    public void onFocusChange(View view, boolean focus) {
        switch(view.getId())
        {
            case R.id.default_signup_gmail_address:
                if(focus)
                {
                    default_signup_action_bar.setExpanded(false);
                }
                break;
            case R.id.default_signup_password:
                if(focus)
                {
                    default_signup_action_bar.setExpanded(false);
                }
                break;
            case R.id.default_signup_confirm_password:
                if(focus)
                {
                    default_signup_action_bar.setExpanded(false);
                }
                break;
        }
    }
}