package is.vidarottosson.glass.gallery.pics;

//  Created by Viddi on 12/6/13.

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

    public static final int MAX_PICTURES_CACHE = 10;

	public PictureScrollAdapter(Context context, List<PictureItem> pictureItems) {
		mContext = context;
        mPictureItems = pictureItems;
        mCachedPositions = new ArrayList<Integer>(MAX_PICTURES_CACHE);
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

        recycleBitmaps(position);

        if(picture.isLoaded()) {
            holder.imgPicture.setImageBitmap(picture.getBitmap());
            holder.txtName.setText(picture.getName());
        }
        else {
            new AsyncBitmapLoader(mContext, holder.progressBar, holder.imgPicture, holder.txtName, picture).execute();
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

    private void recycleBitmaps(int position) {
        if(!mCachedPositions.contains(position)) {
            mCachedPositions.add(position);

            if(mCachedPositions.size() > MAX_PICTURES_CACHE) {
                mPictureItems.get(mCachedPositions.get(0)).destroyBitmap();
                mCachedPositions.remove(0);
            }
        }
    }
}
