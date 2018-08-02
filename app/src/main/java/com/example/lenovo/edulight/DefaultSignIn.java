package com.example.lenovo.edulight;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DefaultSignIn extends AppCompatActivity implements View.OnFocusChangeListener, View.OnClickListener {
    private CollapsingToolbarLayout default_sign_in_collapse_toolbar;
    private AppBarLayout default_sign_in_action_bar;
    private Typeface boldFont,thinFont,normalFont;
    private EditText default_login_username,default_login_user_password;
    private TextInputLayout default_login_username_layout,default_login_password_layout;
    private TextView default_login_header_title,default_login_header_content;
    private TextView default_login_term_of_service_title,default_login_term_of_service_content,default_login_forget_password;
    private Toolbar default_sign_in_toolbar;
    private Button default_login_confirm_btn;
    private CheckBox default_login_remember_check;
    private ProgressDialog mProgress;
    //for firebase
    private FirebaseAuth mAuth;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_signin);
        mProgress =new ProgressDialog(this);
        //for firebase
        mAuth = FirebaseAuth.getInstance();



        setUpCollapsingToolbar();
        boldFont = Typeface.createFromAsset(getAssets(),"oxygen.bold.ttf");
        thinFont = Typeface.createFromAsset(getAssets(),"oxygen.light.ttf");
        normalFont = Typeface.createFromAsset(getAssets(),"oxygen.regular.ttf");
        setUpToolbar();
        setUpEditTextFont();
        setUpTextFont();
        setUpCollaspeEditText();



 //loginUser(userEmail,userPassword);

    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");
                            //FirebaseUser user = mAuth.getCurrentUser();
                           // updateUI(user);

                            mProgress.setTitle("Logging User");
                            mProgress.setMessage("Please wait a moment");
                            mProgress.setCanceledOnTouchOutside(false);
                            mProgress.show();
                           /* Toast.makeText(DefaultSignIn.this, "Authentication success.",
                                    Toast.LENGTH_SHORT).show();*/

                           Intent intent = new Intent(DefaultSignIn.this,MainActivity.class);
                           startActivity(intent);
                           finish();

                        } else {
                            // If sign in fails, display a message to the user.
                          //  Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(DefaultSignIn.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void setUpToolbar() {
        default_sign_in_toolbar = (Toolbar)findViewById(R.id.default_sign_in_toolbar);
        setSupportActionBar(default_sign_in_toolbar);
        default_sign_in_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_toolbar_back_24dp,null));
        default_sign_in_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
    private void setUpCollaspeEditText() {
        default_login_username.setOnFocusChangeListener(this);
        default_login_user_password.setOnFocusChangeListener(this);
    }
    public void onFocusChange(View view, boolean focus) {
        switch(view.getId())
        {
            case R.id.default_login_gmail_address:
                if(focus)
                {
                    default_sign_in_action_bar.setExpanded(false,true);
                }
                break;
            case R.id.default_login_user_password:
                if(focus)
                {
                    default_sign_in_action_bar.setExpanded(false,true);
                }
                break;
        }
    }
    private void setUpTextFont() {
        default_login_header_title = (TextView)findViewById(R.id.default_login_header_title);
        default_login_header_content = (TextView)findViewById(R.id.default_login_header_content);
        default_login_term_of_service_title = (TextView)findViewById(R.id.default_login_term_of_service_title);
        default_login_term_of_service_content = (TextView)findViewById(R.id.default_login_term_of_service_content);
        default_login_forget_password = (TextView)findViewById(R.id.default_login_forget_password);
        default_login_remember_check = (CheckBox)findViewById(R.id.default_login_remember_check);
        default_login_confirm_btn = (Button)findViewById(R.id.default_login_btn);
        default_login_confirm_btn.setOnClickListener(this);
        default_login_remember_check.setTypeface(normalFont);

        default_login_forget_password.setTypeface(normalFont);
        default_login_header_title.setTypeface(boldFont);
        default_login_header_content.setTypeface(normalFont);
        default_login_term_of_service_title.setTypeface(normalFont);
        default_login_term_of_service_title.setText(Html.fromHtml("<u>Terms of Service</u>"));
        default_login_term_of_service_content.setTypeface(normalFont);
    }

    private void setUpEditTextFont() {
        default_login_username = (EditText)findViewById(R.id.default_login_gmail_address);
        default_login_user_password = (EditText)findViewById(R.id.default_login_user_password);
        default_login_username.setSelected(false);
        default_login_user_password.setSelected(false);
        default_login_username.setTypeface(normalFont);
        default_login_user_password.setTypeface(normalFont);


    }

    private void setUpCollapsingToolbar() {
        default_sign_in_collapse_toolbar = (CollapsingToolbarLayout)findViewById(R.id.default_sign_in_collapse_toolbar);
        default_sign_in_collapse_toolbar.setBackgroundColor(Color.parseColor("#00000000"));
        default_sign_in_collapse_toolbar.setCollapsedTitleTypeface(normalFont);

        default_sign_in_action_bar = (AppBarLayout)findViewById(R.id.default_sign_in_action_bar);
        default_sign_in_action_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(scrollRange == -1)
                {
                    scrollRange = default_sign_in_action_bar.getTotalScrollRange();
                }
                if(scrollRange + verticalOffset == 0)
                {
                    default_sign_in_collapse_toolbar.setTitle("Sign in");
                    isShow = true;
                }
                else if(isShow)
                {
                    default_sign_in_collapse_toolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.default_login_btn:
                /*Intent login_main_intent = new Intent(this,MainActivity.class);
                startActivity(login_main_intent);
                finish();*/



                String userEmail =   default_login_username.getText().toString();
                String userPassword =   default_login_user_password.getText().toString();
                loginUser(userEmail,userPassword);
                break;
        }
    }
}
