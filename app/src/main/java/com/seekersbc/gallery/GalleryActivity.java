package com.seekersbc.gallery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class GalleryActivity extends AppCompatActivity {

    private ImageView iv;
    private final int GALLERY_CODE = 1112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        iv = findViewById(R.id.iv);
        showGalleryView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case GALLERY_CODE:
                    Glide.with(GalleryActivity.this).load(data.getData()).into(iv);
                    break;
            }
        }
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGalleryView();
            }
        });
    }

    protected void showGalleryView(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, GALLERY_CODE);
    }

}
