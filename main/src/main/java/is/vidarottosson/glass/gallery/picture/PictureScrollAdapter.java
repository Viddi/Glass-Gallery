package is.vidarottosson.glass.gallery.picture;

//  Created by Viddi on 12/6/13.

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.google.android.glass.widget.CardScrollAdapter;

import java.util.List;

import is.vidarottosson.glass.gallery.R;
import is.vidarottosson.glass.gallery.models.PictureItem;

public class PictureScrollAdapter extends CardScrollAdapter {

	public static final String TAG = PictureScrollAdapter.class.getSimpleName();

	private Context mContext;
	private List<PictureItem> mPictureItems;

	private LruCache<String, Bitmap> mMemoryCache;

	public PictureScrollAdapter(Context context, List<PictureItem> pictureItems) {
		mContext = context;
		mPictureItems = pictureItems;

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final int memClassBytes = am.getMemoryClass() * 1024 * 1024;
        final int cacheSize = memClassBytes / 8;

		mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				return bitmap.getByteCount() / 1024;
			}
		};
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

		if (view == null) {
			view = LayoutInflater.from(mContext).inflate(R.layout.activity_pictures, viewGroup, false);

			holder = new ViewHolder();
			holder.imgPicture = (ImageView) view.findViewById(R.id.pictures_imageView);

			view.setTag(holder);
		}
        else {
			holder = (ViewHolder) view.getTag();
		}

		if (getBitmapFromMemCache(picture.getPath()) != null) {
			holder.imgPicture.setImageBitmap(getBitmapFromMemCache(picture.getPath()));
            Log.d(TAG, "Bitmap exists");
		}
        else {
			loadBitmap(picture, holder.imgPicture);
		}

		return view;
	}

	public int getPosition(Object item) {
		if (item instanceof Integer) {
			int idInt = (Integer) item;
			if (idInt >= 0 && idInt < mPictureItems.size()) {
				return idInt;
			}
		}
		return AdapterView.INVALID_POSITION;
	}

	static class ViewHolder {
		ImageView imgPicture;
	}

	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
		if (getBitmapFromMemCache(key) == null) {
			mMemoryCache.put(key, bitmap);
		}
	}

	public Bitmap getBitmapFromMemCache(String key) {
		return mMemoryCache.get(key);
	}

	public void loadBitmap(PictureItem picture, ImageView imageView) {
		final Bitmap bitmap = getBitmapFromMemCache(picture.getPath());

		if (bitmap != null) {
			imageView.setImageBitmap(bitmap);
            Log.d(TAG, "Bitmap exists");
        }
        else {
			imageView.setImageResource(R.drawable.ic_placeholder_photo);

            AsyncBitmapLoader asyncBitmapLoader = new AsyncBitmapLoader(imageView, picture);
            asyncBitmapLoader.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
            Log.d(TAG, "Bitmap doesn't exist");
		}
	}

	public class AsyncBitmapLoader extends AsyncTask<String, String, Bitmap> {

		private ImageView mImageView;
		private PictureItem mPicture;

		public AsyncBitmapLoader(ImageView imageView, PictureItem picture) {
			mImageView = imageView;
			mPicture = picture;
		}

		@Override
		protected Bitmap doInBackground(String... strings) {
			return mPicture.createBitmap(mContext);
		}

		@Override
		protected void onPostExecute(Bitmap bitmap) {
			super.onPostExecute(bitmap);

			if (bitmap != null) {
				mImageView.setImageBitmap(bitmap);
                addBitmapToMemoryCache(mPicture.getPath(), bitmap);
                Log.d(TAG, "Successfully loaded bitmap with path: " + mPicture.getPath());
			}
		}

	}

}
