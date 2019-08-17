package com.example.travelerpractise.FragmentForTreePage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travelerpractise.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
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
    private ImageView image;

    private Button saveEven;

    ArrayList<Uri> EvenImage = new ArrayList<Uri>();
    private Uri mImageUri;
    private String userID = "helen";

    private DatabaseReference databaseReference;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cal_and_budget);

//        evenName = findViewById(R.id.even_name);
//        address = findViewById(R.id.address);
//        phoneNumber = findViewById(R.id.phone_number);
//        cost = findViewById(R.id.expense);
//        web = findViewById(R.id.website);
//        openHour = findViewById(R.id.open_hour);
//        image = findViewById(R.id.imageD);
//
//        saveEven = findViewById(R.id.save_even);
//
//        databaseReference = FirebaseDatabase.getInstance().getReference("EvenPlanner");
//        storageReference = FirebaseStorage.getInstance().getReference("EvenPlanner");
//
//
//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PickImage();
//
//            }
//        });
//
//        saveEven.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                saveTofirebase();
//            }
//
//
//        });


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
//                    boolean todo = false;
//
//                   Even even = new Even(name,Address,PhoneNumber,OpenHour,Cost,Website,null, false);
//                   String uploadId = databaseReference.push().getKey();
//                   databaseReference.child(uploadId).setValue(even);
//                }
//            });
//        }
//    }

    private void PickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){

            mImageUri = data.getData();

            Picasso.with(this).load(mImageUri).into(image);
            //image.setImageURI(mImageUri)
        }else{
            Toast.makeText(AddCalAndBudgetActivity.this,"you have to have a Even Name",Toast.LENGTH_LONG).show();
        }
    }
}
