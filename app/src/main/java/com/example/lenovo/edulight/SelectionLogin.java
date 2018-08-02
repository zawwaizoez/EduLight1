package com.example.lenovo.edulight;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Type;

public class SelectionLogin extends AppCompatActivity implements View.OnClickListener {
    private Typeface boldFont,normalFont,thinFont;
    private TextView selection_text_one,selection_text_two,selection_text_three;
    private TextView selection_text_four,selection_text_five,selection_text_six;
    private Button facebookSignupBtn,googleSignupBtn,gmailSignupBtn;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_login);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        boldFont = Typeface.createFromAsset(getAssets(),"oxygen.bold.ttf");
        thinFont = Typeface.createFromAsset(getAssets(),"oxygen.light.ttf");
        normalFont = Typeface.createFromAsset(getAssets(),"oxygen.regular.ttf");
        changeStatusBarColor();
        setFontFace();
        setButton();
    }

    private void setButton() {
        facebookSignupBtn = (Button)findViewById(R.id.user_facebook_signupBtn);
        googleSignupBtn = (Button)findViewById(R.id.user_google_signupBtn);
        gmailSignupBtn = (Button)findViewById(R.id.user_gmail_signupBtn);
        facebookSignupBtn.setTypeface(normalFont);
        googleSignupBtn.setTypeface(normalFont);
        gmailSignupBtn.setTypeface(normalFont);
        facebookSignupBtn.setOnClickListener(this);
        gmailSignupBtn.setOnClickListener(this);
        googleSignupBtn.setOnClickListener(this);

    }
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.user_facebook_loginBtn:
                break;
            case R.id.user_google_signupBtn:
                break;
            case R.id.user_gmail_signupBtn:
                Intent default_sign_up_intent = new Intent(this,DefaultSignUp.class);
                startActivity(default_sign_up_intent);
                break;
            case R.id.selection_text_four:
                Intent sign_in_intent = new Intent(this,DefaultSignIn.class);
                startActivity(sign_in_intent);
                break;
        }
    }
    private void setFontFace() {

        selection_text_one = (TextView)findViewById(R.id.selection_text_one);
        selection_text_one.setTypeface(boldFont);

        selection_text_two = (TextView)findViewById(R.id.selection_text_two);
        selection_text_one.setTypeface(boldFont);


        selection_text_three = (TextView)findViewById(R.id.selection_text_three);
        selection_text_three.setTypeface(normalFont);

        selection_text_four = (TextView)findViewById(R.id.selection_text_four);
        selection_text_four.setOnClickListener(this);
        selection_text_four.setTypeface(normalFont);

        selection_text_five = (TextView)findViewById(R.id.selection_text_five);

        selection_text_six = (TextView)findViewById(R.id.selection_text_six);

        selection_text_five.setTypeface(normalFont);
        selection_text_six.setTypeface(normalFont);
        selection_text_six.setText(Html.fromHtml("<u>Terms of Service</u>"));
    }


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

}
