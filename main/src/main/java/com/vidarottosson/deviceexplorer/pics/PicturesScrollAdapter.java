package com.vidarottosson.deviceexplorer.pics;

//  Created by Viddi on 12/6/13.

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.glass.widget.CardScrollAdapter;
import com.vidarottosson.deviceexplorer.R;
import com.vidarottosson.deviceexplorer.models.PictureItem;

import java.util.List;

public class PicturesScrollAdapter extends CardScrollAdapter {

    public static final String TAG = PicturesScrollAdapter.class.getSimpleName();

	private Context mContext;
    private List<PictureItem> mPictureItems;

	public PicturesScrollAdapter(Context context, List<PictureItem> pictureItems) {
		mContext = context;
        mPictureItems = pictureItems;
	}

	@Override
	public int getCount() {
		return mPictureItems.size();
	}

	@Override
	public PictureItem getItem(int i) {
		return mPictureItems.get(i);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        PictureItem picture = mPictureItems.get(position);

        if(view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_pictures, viewGroup);

            holder = new ViewHolder();
            holder.imgPicture = (ImageView) view.findViewById(R.id.pictures_imageView);
            holder.txtName = (TextView) view.findViewById(R.id.pictures_textView);
            holder.progressBar = (ProgressBar) view.findViewById(R.id.pictures_progressBar);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        if(picture.isLoaded()) {
            holder.imgPicture.setImageBitmap(picture.getBitmap());
            holder.txtName.setText(picture.getName());
        }
        else {
            new AsyncBitmapLoader(holder.progressBar, holder.imgPicture, holder.txtName, picture).execute();
        }


        return setItemOnCard(this, view);
	}

	@Override
	public int findIdPosition(Object o) {
		return 0;
	}

	@Override
	public int findItemPosition(Object o) {
		return 0;
	}

    static class ViewHolder {
        ImageView imgPicture;
        TextView txtName;
        ProgressBar progressBar;
    }

    private class AsyncBitmapLoader extends AsyncTask<String, String, Bitmap> {

        private ProgressBar mProgressBar;
        private ImageView mImageView;
        private TextView mTextView;
        private PictureItem mPicture;

        public AsyncBitmapLoader(ProgressBar progressBar, ImageView imageView, TextView textView, PictureItem picture) {
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
        protected Bitmap doInBackground(String... strings) {
            return PictureItem.createBitmap(mContext, mPicture.getPath());
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mPicture.setBitmap(bitmap);

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
}
