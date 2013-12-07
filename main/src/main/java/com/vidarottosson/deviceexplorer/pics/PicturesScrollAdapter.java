package com.vidarottosson.deviceexplorer.pics;

//  Created by Viddi on 12/6/13.

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;

import com.google.android.glass.widget.CardScrollAdapter;
import com.vidarottosson.deviceexplorer.R;

import java.util.List;

public class PicturesScrollAdapter extends CardScrollAdapter {

	private Context mContext;
    private LayoutInflater mInflater;
    private List<Uri> mUris;

	public PicturesScrollAdapter(Context context, List<Uri> uris) {
		mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mUris = uris;
	}

	@Override
	public int getCount() {
		return mUris.size();
	}

	@Override
	public Object getItem(int i) {
		return null;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null) {
            view = mInflater.inflate(R.layout.activity_pictures, viewGroup, false);

            holder = new ViewHolder();
            holder.imgPicture = (ImageView) view.findViewById(R.id.pictures_imageView);
            holder.txtName = (TextView) view.findViewById(R.id.pictures_textView);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        holder.imgPicture.setImageURI(mUris.get(position));
        holder.txtName.setText("TODO: Set Image Name");

        return view;
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
