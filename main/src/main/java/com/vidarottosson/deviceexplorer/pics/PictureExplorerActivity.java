package com.vidarottosson.deviceexplorer.pics;
//  Created by Viddi on 12/6/13.

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.vidarottosson.deviceexplorer.R;

public class PictureExplorerActivity extends Activity {

    public static final String TAG = PictureExplorerActivity.class.getSimpleName();

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictures);

        mImageView = (ImageView) findViewById(R.id.pictures_imageView);

        Bitmap dummy = BitmapFactory.decodeResource(getResources(), R.drawable.dummy);
        mImageView.setImageBitmap(dummy);

        // TODO: Find Pictures folder and display list
    }
}
