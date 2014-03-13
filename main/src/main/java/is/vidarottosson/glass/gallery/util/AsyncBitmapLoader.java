package is.vidarottosson.glass.gallery.util;
//  Created by Viddi on 1/16/14.

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;

import is.vidarottosson.glass.gallery.R;
import is.vidarottosson.glass.gallery.models.PictureItem;

public class AsyncBitmapLoader extends AsyncTask<String, String, Void> {

    private Context mContext;
    private ImageView mImageView;
    private PictureItem mPicture;

    public AsyncBitmapLoader(Context context, ImageView imageView, PictureItem picture) {
        mContext = context;
        mImageView = imageView;
        mPicture = picture;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mImageView.setImageResource(R.drawable.ic_placeholder_photo);
    }

    @Override
    protected Void doInBackground(String... strings) {
        mPicture.createBitmap(mContext);

        return null;
    }

    @Override
    protected void onPostExecute(Void bitmap) {
        super.onPostExecute(bitmap);

        if(mPicture.isLoaded()) {
            mImageView.setImageBitmap(mPicture.getBitmap());
        }
        else {
            mImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_question));
        }

    }

}
