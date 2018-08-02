package com.example.lenovo.edulight;

import android.content.Intent;
import android.database.Cursor;
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

               /* Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("images*//*");
                startActivityForResult(intent,RESULT_LOAD_IMAGE);*/
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data)
        {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn,null,null,null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String pitcurePath = cursor.getString(columnIndex);
            activity_upload_user_post_image = (ImageView)findViewById(R.id.activity_upload_user_post_image);
            activity_upload_user_post_image.setImageBitmap(BitmapFactory.decodeFile(pitcurePath));
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
