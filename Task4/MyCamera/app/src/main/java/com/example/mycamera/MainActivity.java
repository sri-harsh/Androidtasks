package com.example.mycamera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button b1,b2;
    ImageView iv;
    public static final int CAMERA_REQUEST_CODE = 20;
    public static final int Gallery_REQUEST_CODE = 10;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.camera_button);
        b2=findViewById(R.id.gallery_button);
        iv=findViewById(R.id.imageview);

    }

    public void OpenCamera(View view) {
        Intent I = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(I,CAMERA_REQUEST_CODE);
    }

    public void OpenGallery(View view) {
        Intent I= new Intent();
        I.setType("image/*");
        I.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(I,Gallery_REQUEST_CODE);
        //by using  startActivityForResult we can get back the data of the present action

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAMERA_REQUEST_CODE){
            if(resultCode==RESULT_OK){
                Bitmap bm=(Bitmap) data.getExtras().get("data");
                iv.setImageBitmap(bm);
                //can use both uri and bitmap for image view
            }
        }
        if(requestCode==Gallery_REQUEST_CODE){
            if(resultCode==RESULT_OK){
                Uri u = data.getData();
                iv.setImageURI(u);
                //can use both uri and bitmap for image view
            }
        }
    }




}