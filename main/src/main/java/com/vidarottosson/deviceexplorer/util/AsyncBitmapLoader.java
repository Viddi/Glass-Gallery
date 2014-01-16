package com.vidarottosson.deviceexplorer.util;
//  Created by Viddi on 1/16/14.

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vidarottosson.deviceexplorer.R;
import com.vidarottosson.deviceexplorer.models.PictureItem;

public class AsyncBitmapLoader extends AsyncTask<String, String, Void> {

    private Context mContext;
    private ProgressBar mProgressBar;
    private ImageView mImageView;
    private TextView mTextView;
    private PictureItem mPicture;

    public AsyncBitmapLoader(Context context, ProgressBar progressBar, ImageView imageView, TextView textView, PictureItem picture) {
        mContext = context;
        mProgressBar = progressBar;
        mImageView = imageView;
        mTextView = textView;
        mPicture = picture;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressBar.setVisibility(View.VISIBLE);
        mImageView.setVisibility(View.GONE);
        mTextView.setVisibility(View.GONE);
    }

    @Override
    protected Void doInBackground(String... strings) {
        mPicture.createBitmap(mContext);

        return null;
    }

    @Override
    protected void onPostExecute(Void bitmap) {
        super.onPostExecute(bitmap);

        mProgressBar.setVisibility(View.GONE);
        mImageView.setVisibility(View.VISIBLE);
        mTextView.setVisibility(View.VISIBLE);

        if(mPicture.isLoaded()) {
            mImageView.setImageBitmap(mPicture.getBitmap());
            mTextView.setText(mPicture.getName());
        }
        else {
            mImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_question));
            mTextView.setText(R.string.error_picture);
        }

    }

}
