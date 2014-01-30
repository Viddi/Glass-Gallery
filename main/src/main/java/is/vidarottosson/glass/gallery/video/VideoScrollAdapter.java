package is.vidarottosson.glass.gallery.video;

//  Created by jonstaff on 1/17/14.

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.glass.widget.CardScrollAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import is.vidarottosson.glass.gallery.R;
import is.vidarottosson.glass.gallery.models.VideoItem;
import is.vidarottosson.glass.gallery.widget.VideoView;

public class VideoScrollAdapter extends CardScrollAdapter {
	public static final String TAG = VideoScrollAdapter.class.getSimpleName();

	private final Context mContext;
	private List<VideoItem> mVideos;

	//      ____                _                   _
	//     / ___|___  _ __  ___| |_ _ __ _   _  ___| |_ ___  _ __ ___
	//    | |   / _ \| '_ \/ __| __| '__| | | |/ __| __/ _ \| '__/ __|
	//    | |__| (_) | | | \__ \ |_| |  | |_| | (__| || (_) | |  \__ \
	//     \____\___/|_| |_|___/\__|_|   \__,_|\___|\__\___/|_|  |___/

	public VideoScrollAdapter(Context context, List<VideoItem> videos) {
		mContext = context;
		mVideos = videos;
	}

	//     ____                            _                  ___                      _     _
	//    / ___| _   _ _ __   ___ _ __ ___| | __ _ ___ ___   / _ \__   _____ _ __ _ __(_) __| | ___  ___
	//    \___ \| | | | '_ \ / _ \ '__/ __| |/ _` / __/ __| | | | \ \ / / _ \ '__| '__| |/ _` |/ _ \/ __|
	//      __) | |_| | |_) |  __/ | | (__| | (_| \__ \__ \ | |_| |\ V /  __/ |  | |  | | (_| |  __/\__ \
	//    |____/ \__,_| .__/ \___|_|  \___|_|\__,_|___/___/  \___/  \_/ \___|_|  |_|  |_|\__,_|\___||___/
	//                |_|

	@Override
	public int getCount() {
		return mVideos.size();
	}

	@Override
	public VideoItem getItem(int index) {
		return mVideos.get(index);
	}

	@Override
	public View getView(final int position, View view, ViewGroup parent) {
		ViewHolder holder;
		VideoItem video = mVideos.get(position);

		if (view == null) {
			view = LayoutInflater.from(mContext).inflate(R.layout.activity_video_list, parent);

			holder = new ViewHolder();
			holder.thumbnail = (ImageView) view.findViewById(R.id.videoList_imageView);
			holder.txtName = (TextView) view.findViewById(R.id.videoList_textView);
			holder.progressBar = (ProgressBar) view.findViewById(R.id.videoList_progressBar);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		holder.thumbnail.setBackgroundDrawable(new BitmapDrawable(mContext.getResources(), video.getThumbnailImage()));
		holder.txtName.setText(video.getName());

		return setItemOnCard(this, view);
	}

	@Override
	public int findIdPosition(Object o) {
		if (o instanceof Integer) {
			int x = (Integer) o;
			if (x >= 0 && x < mVideos.size()) {
				return x;
			}
		}
		return AdapterView.INVALID_POSITION;
	}

	@Override
	public int findItemPosition(Object o) {
		return findIdPosition(o);
	}

	//    __     ___               _   _       _     _
	//    \ \   / (_) _____      _| | | | ___ | | __| | ___ _ __
	//     \ \ / /| |/ _ \ \ /\ / / |_| |/ _ \| |/ _` |/ _ \ '__|
	//      \ V / | |  __/\ V  V /|  _  | (_) | | (_| |  __/ |
	//       \_/  |_|\___| \_/\_/ |_| |_|\___/|_|\__,_|\___|_|

	static class ViewHolder {
		ImageView thumbnail;
		TextView txtName;
		ProgressBar progressBar;
	}
}
