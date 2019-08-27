package com.example.travelerpractise;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateDiaryActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Button mButtonChooseImage;
    private Button mButtonUpload;

    private EditText mEditTextFileName;
    private ImageView mImageView;
    private ProgressBar mProgressBar;


    ArrayList<Uri> ImageList = new ArrayList<Uri>();
    private Uri ImageUri;
    private int update_count = 0;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

//    DatabaseReference databaseReference;
    HashMap<String,String> hashMap;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_diary);

        mButtonChooseImage = findViewById(R.id.imageButton);
        mButtonUpload = findViewById(R.id.updateDiary);
//        mTextViewShowUpload = findViewById(R.id.upload_status);
        mImageView = findViewById(R.id.image_choose);
        mEditTextFileName = findViewById(R.id.editName);
        mProgressBar = findViewById(R.id.progressBar);



        mStorageRef = FirebaseStorage.getInstance().getReference().child("Diary");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("ImageFolder");

        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                    //intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent,PICK_IMAGE_REQUEST);
            }


        });

        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //uploadFile();

                for(update_count = 0 ; update_count<ImageList.size(); update_count++){
                    Uri IndividualImage = ImageList.get(update_count);
                    final StorageReference ImageName = mStorageRef.child("Image"+IndividualImage.getLastPathSegment());

                    ImageName.putFile(IndividualImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            ImageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String url = String.valueOf(uri);
                                    hashMap = new HashMap<>();
                                    hashMap.put("Imagelink",url);

                                }
                            });
                        }
                    });

                }
                Diary diary = new Diary(mEditTextFileName.getText().toString().trim(),hashMap);
                mDatabaseRef.push().setValue(diary);

            }
        });

    }

    private void StoreLink(String url) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("UserOne");
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("Imagelink",url);
        Diary diary = new Diary(mEditTextFileName.getText().toString().trim(),hashMap);
        databaseReference.push().setValue(diary);
    }



    private String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }


    //show the image that been choose
    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        //intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST){
            if (resultCode == RESULT_OK){
                if(data.getClipData()!= null){

                    int countClipData = data.getClipData().getItemCount();

                    int currentImageSelet = 0;
                    while(currentImageSelet<countClipData){
                     ImageUri = data.getClipData().getItemAt(currentImageSelet).getUri();
                     ImageList.add(ImageUri);
                     currentImageSelet = currentImageSelet+1;

                        Picasso.with(this).load(ImageUri).into(mImageView);
                    }
                }
            }
        }

    }
}

//if ((requestCode == PICK_IMAGE_REQUEST) && resultCode == RESULT_OK && (data != null) && (data.getData() != null)){
//            ImageUri = data.getData();
//
//            Picasso.with(this).load(ImageUri).into(mImageView);
//            //mImageView.setImageURI(ImageUri);


//    private void uploadFile() {
//        if (ImageUri != null){
//            StorageReference fileReference = mStorageRef
//                    .child(System.currentTimeMillis()+"."+getFileExtension(ImageUri));
//
//            fileReference.putFile(ImageUri)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            Handler handler = new Handler();
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    mProgressBar.setProgress(0);
//                                }
//                            },500);
//
//                            Toast.makeText(CreateDiaryActivity.this,"Update successful",Toast.LENGTH_LONG).show();
//                            Diary diary = new Diary(mEditTextFileName.getText().toString().trim(),
//                                    taskSnapshot.getStorage().getDownloadUrl().toString());
//                            String uploadId = mDatabaseRef.push().getKey();
//                            mDatabaseRef.child(uploadId).setValue(diary);
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//
//                            Toast.makeText(CreateDiaryActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
//                }
//                    })
//                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
//                            mProgressBar.setProgress((int)progress);
//
//                        }
//                    });
//        }else{
//            Toast.makeText(this,"No file selected",Toast.LENGTH_SHORT).show();
//        }
//    }