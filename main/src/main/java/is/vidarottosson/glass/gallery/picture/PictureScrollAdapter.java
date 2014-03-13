package is.vidarottosson.glass.gallery.picture;

//  Created by Viddi on 12/6/13.

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.google.android.glass.widget.CardScrollAdapter;

import java.util.ArrayList;
import java.util.List;

import is.vidarottosson.glass.gallery.R;
import is.vidarottosson.glass.gallery.models.PictureItem;
import is.vidarottosson.glass.gallery.util.AsyncBitmapLoader;

public class PictureScrollAdapter extends CardScrollAdapter {

    public static final String TAG = PictureScrollAdapter.class.getSimpleName();

	private Context mContext;
    private List<PictureItem> mPictureItems;
    private List<Integer> mCachedPositions;
    private List<AsyncBitmapLoader> mBitmapLoaders;

    public static final int MAX_PICTURES_CACHE = 10;

	public PictureScrollAdapter(Context context, List<PictureItem> pictureItems) {
		mContext = context;
        mPictureItems = pictureItems;
        mCachedPositions = new ArrayList<Integer>(MAX_PICTURES_CACHE);
        mBitmapLoaders = new ArrayList<AsyncBitmapLoader>(MAX_PICTURES_CACHE);
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
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_pictures, viewGroup, false);

            holder = new ViewHolder();
            holder.imgPicture = (ImageView) view.findViewById(R.id.pictures_imageView);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        recycleBitmaps(position);

        if(picture.isLoaded()) {
            holder.imgPicture.setImageBitmap(picture.getBitmap());
        }
        else {
            AsyncBitmapLoader loader = new AsyncBitmapLoader(mContext, holder.imgPicture, picture);
            loader.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);

            mBitmapLoaders.add(loader);
        }

        return setItemOnCard(this, view);
	}

	@Override
	public int findIdPosition(Object id) {
        if (id instanceof Integer) {
            int idInt = (Integer) id;
            if (idInt >= 0 && idInt < mPictureItems.size()) {
                return idInt;
            }
        }
        return AdapterView.INVALID_POSITION;
	}

	@Override
	public int findItemPosition(Object item) {
        return findIdPosition(item);
	}

    static class ViewHolder {
        ImageView imgPicture;
    }

    private void recycleBitmaps(int position) {
        if(!mCachedPositions.contains(position)) {
            mCachedPositions.add(position);

            if(mCachedPositions.size() > MAX_PICTURES_CACHE) {
                AsyncBitmapLoader loader = mBitmapLoaders.get(0);
                loader.cancel(true);
                mBitmapLoaders.remove(0);

                mPictureItems.get(mCachedPositions.get(0)).destroyBitmap();
                mCachedPositions.remove(0);
            }
        }
    }
}
