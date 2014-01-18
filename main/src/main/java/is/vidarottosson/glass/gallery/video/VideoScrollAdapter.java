package is.vidarottosson.glass.gallery.video;

//  Created by jonstaff on 1/17/14.

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.glass.widget.CardScrollAdapter;

import java.util.ArrayList;
import java.util.List;

import is.vidarottosson.glass.gallery.R;
import is.vidarottosson.glass.gallery.models.VideoItem;

public class VideoScrollAdapter extends CardScrollAdapter {
	public static final String TAG = VideoScrollAdapter.class.getSimpleName();

	private final Context mContext;
	private List<VideoItem> mVideos;
	private List<Integer> mCachedPositions;

	public static final int MAX_VIDEOS_CACHE = 5;

	//      ____                _                   _
	//     / ___|___  _ __  ___| |_ _ __ _   _  ___| |_ ___  _ __ ___
	//    | |   / _ \| '_ \/ __| __| '__| | | |/ __| __/ _ \| '__/ __|
	//    | |__| (_) | | | \__ \ |_| |  | |_| | (__| || (_) | |  \__ \
	//     \____\___/|_| |_|___/\__|_|   \__,_|\___|\__\___/|_|  |___/

	public VideoScrollAdapter(Context context, List<VideoItem> videos) {
		mContext = context;
		mVideos = videos;
		mCachedPositions = new ArrayList<Integer>(MAX_VIDEOS_CACHE);
	}

	//     ___       _             __                  ___                 _                           _        _   _
	//    |_ _|_ __ | |_ ___ _ __ / _| __ _  ___ ___  |_ _|_ __ ___  _ __ | | ___ _ __ ___   ___ _ __ | |_ __ _| |_(_) ___  _ __  ___
	//     | || '_ \| __/ _ \ '__| |_ / _` |/ __/ _ \  | || '_ ` _ \| '_ \| |/ _ \ '_ ` _ \ / _ \ '_ \| __/ _` | __| |/ _ \| '_ \/ __|
	//     | || | | | ||  __/ |  |  _| (_| | (_|  __/  | || | | | | | |_) | |  __/ | | | | |  __/ | | | || (_| | |_| | (_) | | | \__ \
	//    |___|_| |_|\__\___|_|  |_|  \__,_|\___\___| |___|_| |_| |_| .__/|_|\___|_| |_| |_|\___|_| |_|\__\__,_|\__|_|\___/|_| |_|___/
	//                                                              |_|

	@Override
	public int getCount() {
		return mVideos.size();
	}

	@Override
	public Object getItem(int index) {
		return mVideos.get(index);
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;
		VideoItem video = mVideos.get(position);

		if (view == null) {
			view = LayoutInflater.from(mContext).inflate(R.layout.activity_video, parent);

			holder = new ViewHolder();
			holder.videoView = (VideoView) view.findViewById(R.id.video_videoView);
			holder.txtName = (TextView) view.findViewById(R.id.video_textView);
			holder.progressBar = (ProgressBar) view.findViewById(R.id.video_progressBar);

			view.setTag(holder);
		} else {
            holder = (ViewHolder) view.getTag();
        }



		return null;
	}

	@Override
	public int findIdPosition(Object o) {
		return 0;
	}

	@Override
	public int findItemPosition(Object o) {
		return 0;
	}

	//    __     ___               _   _       _     _
	//    \ \   / (_) _____      _| | | | ___ | | __| | ___ _ __
	//     \ \ / /| |/ _ \ \ /\ / / |_| |/ _ \| |/ _` |/ _ \ '__|
	//      \ V / | |  __/\ V  V /|  _  | (_) | | (_| |  __/ |
	//       \_/  |_|\___| \_/\_/ |_| |_|\___/|_|\__,_|\___|_|

	static class ViewHolder {
		VideoView videoView;
		TextView txtName;
		ProgressBar progressBar;
	}
}
