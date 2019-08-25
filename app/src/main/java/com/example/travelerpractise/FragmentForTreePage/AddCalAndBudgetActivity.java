package com.example.travelerpractise.FragmentForTreePage;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travelerpractise.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AddCalAndBudgetActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText evenName;
    private EditText address;
    private EditText phoneNumber;
    private EditText cost;
    private EditText web;
    private EditText openHour;

    private ImageView EvenImage;

    private Button saveEven;
    private Uri mImageUri;

    ArrayList<Uri> ImageList = new ArrayList<Uri>();

    private DatabaseReference databaseReference;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cal_and_budget);



        EvenImage = findViewById(R.id.imageD);
        saveEven = findViewById(R.id.save_even);

        evenName = findViewById(R.id.budget_name);
        address = findViewById(R.id.address);
        phoneNumber = findViewById(R.id.phone_number);
        cost = findViewById(R.id.expense);
        web = findViewById(R.id.website);
        openHour = findViewById(R.id.open_hour);


        databaseReference = FirebaseDatabase.getInstance().getReference("EvenPlanner");
        storageReference = FirebaseStorage.getInstance().getReference("EvenPlannerImage");

        EvenImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImage();

            }
        });

        saveEven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadEven();
                Intent intent = new Intent(AddCalAndBudgetActivity.this,TreeFragmentContenerActivity.class);
                startActivity(intent);

            }


        });


    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void UploadEven() {
        if(mImageUri != null){
            StorageReference fileReference = storageReference.child(System.currentTimeMillis()+"."
                    +getFileExtension(mImageUri));
            fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    String name = evenName.getText().toString();
                    String Address = address.getText().toString();
                    String PhoneNumber = phoneNumber.getText().toString();
                    String Cost = cost.getText().toString();
                    String Website = web.getText().toString();
                    String OpenHour = openHour.getText().toString();

                    Even even = new Even(name,Address,PhoneNumber,OpenHour,Cost,Website,taskSnapshot.getStorage().getDownloadUrl().toString());
                    String uploadId = databaseReference.push().getKey();
                    databaseReference.child(uploadId).setValue(even);

                }
            });
        }else {
            Toast.makeText(this,"to file selected",Toast.LENGTH_LONG).show();
        }


    }

//    private void saveTofirebase() {
//
//        if(evenName != null){
//            final String name = evenName.getText().toString();
//            StorageReference storageRef = storageReference.child(name+userID+System.currentTimeMillis());
//            storageRef.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                    String Address = address.getText().toString();
//                    String PhoneNumber = phoneNumber.getText().toString();
//                    int Cost = Integer.parseInt(cost.getText().toString());
//                    String Website = web.getText().toString();
//                    String OpenHour = openHour.getText().toString();
//                    String Image = null;
//
//
//                   Even even = new Even(name,Address,PhoneNumber,OpenHour,Cost,Website,null, false);
//                   String uploadId = databaseReference.push().getKey();
//                   databaseReference.child(uploadId).setValue(even);
//                }
//            });
//        }
//    }

    private void PickImage() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
     //   intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);


    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST){
            if (resultCode == RESULT_OK){
                if(data.getClipData()!= null){

//                        mImageUri = data.getClipData().getItemAt(0).getUri();
                        mImageUri = data.getData();
//                        ImageList.add(mImageUri);

                        Picasso.with(this).load(mImageUri).into(EvenImage);

                }
            }
        }

    }
}

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == PICK_IMAGE_REQUEST) {
//            if (resultCode == RESULT_OK) {
//                if (data.getClipData() != null) {
//                    mImageUri = data.getData();
//
//                    //Picasso.with(this).load(mImageUri).into(EvenImage);
//                    //image.setImageURI(mImageUri)
//                } else {
//                    Toast.makeText(AddCalAndBudgetActivity.this, "you have to have a Even Name", Toast.LENGTH_LONG).show();
//                }
//            }
//
//
//        }
//    }

