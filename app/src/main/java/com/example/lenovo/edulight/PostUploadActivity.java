package com.example.lenovo.edulight;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class PostUploadActivity extends AppCompatActivity {

    private Toolbar activity_upload_toolbar;
    private TextView activity_upload_toolbar_title;
    private Button activity_upload_toolbar_post_btn,activity_upload_user_post_image_btn;
    private EditText activity_upload_user_post_title,activity_upload_user_post_decription;
    private ImageView activity_upload_user_post_image;
    private Typeface boldFont,normalFont,thinFont;
    private final int RESULT_LOAD_IMAGE = 1;
    int postId =1;
    //for firebase
    private DatabaseReference mDatabase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_upload);
        boldFont = Typeface.createFromAsset(getAssets(), "oxygen.bold.ttf");
        thinFont = Typeface.createFromAsset(getAssets(),"oxygen.light.ttf");
        normalFont = Typeface.createFromAsset(getAssets(),"oxygen.regular.ttf");
        setUpToolbar();
        referenceEditText();
        imagePickUp();
    }
    private void imagePickUp() {

        activity_upload_user_post_image_btn = (Button)findViewById(R.id.activity_upload_user_post_image_btn);
        activity_upload_user_post_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
               intent.addCategory(Intent.CATEGORY_OPENABLE);
               intent.setType("images/*");
               startActivityForResult(Intent.createChooser(intent,"Select Picture"),RESULT_LOAD_IMAGE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data!=null)
        {
            Uri uri = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                activity_upload_user_post_image.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void referenceEditText() {
        activity_upload_user_post_title = (EditText)findViewById(R.id.activity_upload_user_post_title);
        activity_upload_user_post_decription = (EditText)findViewById(R.id.activity_upload_user_post_description);
        activity_upload_user_post_image = (ImageView)findViewById(R.id.activity_upload_user_post_image);

        String post_title = activity_upload_user_post_title.getText().toString();
        String post_content = activity_upload_user_post_decription.getText().toString();

    }

    private void setUpToolbar() {
        activity_upload_toolbar = (Toolbar)findViewById(R.id.activity_upload_toolbar);
        activity_upload_toolbar_title = (TextView)activity_upload_toolbar.findViewById(R.id.activity_upload_toolbar_title);
        activity_upload_toolbar_title.setText("Create Post");
        activity_upload_toolbar_title.setTypeface(normalFont);
        activity_upload_toolbar_post_btn = (Button)activity_upload_toolbar.findViewById(R.id.activity_upload_toolbar_post_btn);
        activity_upload_toolbar_post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title =  activity_upload_user_post_title.getText().toString();
                String description=   activity_upload_user_post_decription.getText().toString();

                FirebaseUser user2 = FirebaseAuth.getInstance().getCurrentUser();
                String userId = user2.getUid();

                mDatabase =FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("POST"+postId );
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("Title",title );
                hashMap.put("Description",description);
                hashMap.put("image","link_one");
                mDatabase.setValue(hashMap);
                postId++;
            }
        });
        activity_upload_toolbar_post_btn.setTypeface(normalFont);
        setSupportActionBar(activity_upload_toolbar);



        activity_upload_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_toolbar_back_24dp,null));
        activity_upload_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }
}
