package com.vidarottosson.deviceexplorer.pics;

//  Created by Viddi on 12/6/13.

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.glass.widget.CardScrollAdapter;
import com.vidarottosson.deviceexplorer.R;
import com.vidarottosson.deviceexplorer.models.FileItem;

import java.util.List;

public class PicturesScrollAdapter extends CardScrollAdapter {

    public static final String TAG = PicturesScrollAdapter.class.getSimpleName();

	private Context mContext;
    private List<FileItem> mImageFiles;

	public PicturesScrollAdapter(Context context, List<FileItem> imageFiles) {
		mContext = context;
        mImageFiles = imageFiles;
	}

	@Override
	public int getCount() {
		return mImageFiles.size();
	}

	@Override
	public FileItem getItem(int i) {
		return mImageFiles.get(i);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_pictures, viewGroup);

            holder = new ViewHolder();
            holder.imgPicture = (ImageView) view.findViewById(R.id.pictures_imageView);
            holder.txtName = (TextView) view.findViewById(R.id.pictures_textView);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

//        holder.imgPicture.setImageBitmap(BitmapFactory.decodeFile(mImagePaths.get(position)));
        holder.txtName.setText(mImageFiles.get(position).getName());

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
    }
}
