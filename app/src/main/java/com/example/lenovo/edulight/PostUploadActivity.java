package com.example.lenovo.edulight;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
    private StorageReference mImageStorage;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth mAuth;
    FirebaseUser user2;
    String userId;

    Uri uri;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_upload);

        //firebase
        mImageStorage = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        mAuth = FirebaseAuth.getInstance();

         user2 = FirebaseAuth.getInstance().getCurrentUser();
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
    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(10);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data!=null)
        {
            uri   = data.getData();
            //uploadImage(uri);
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                activity_upload_user_post_image.setImageBitmap(bitmap);

                    //uploadImage(uri);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(Uri uri) {
        StorageReference filepath = mImageStorage.child("profile_images").child(random()+".jpg");
        filepath.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"success image",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"fail image",Toast.LENGTH_LONG).show();
                }
            }
        });
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


                 userId = user2.getUid();

                /*mDatabase =FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("POST"+postId );
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("Title",title );
                hashMap.put("Description",description);
                hashMap.put("image","link_one");
                mDatabase.setValue(hashMap);
                postId++;*/

                uploadImage(uri);
               String imageUri = uri.toString();
                Map<String,Object> postMap = new HashMap<>();
                postMap.put("image_url",imageUri);
                postMap.put("description",description);
                postMap.put("user_id",userId);
                postMap.put("timestamp", FieldValue.serverTimestamp());

                firebaseFirestore.collection("Posts").add(postMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
if(task.isSuccessful()){
    Toast.makeText(getApplicationContext(),"succ",Toast.LENGTH_LONG).show();
}
else{
    Toast.makeText(getApplicationContext(),"wrong",Toast.LENGTH_LONG).show();
}
                    }

                });






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
