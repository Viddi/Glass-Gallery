package is.vidarottosson.glass.gallery.video;

//  Created by jonstaff on 1/17/14.

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.glass.widget.CardScrollAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import is.vidarottosson.glass.gallery.R;
import is.vidarottosson.glass.gallery.models.VideoItem;

public class VideoScrollAdapter extends CardScrollAdapter implements TextureView.SurfaceTextureListener, MediaPlayer.OnCompletionListener,
		MediaPlayer.OnPreparedListener {
	public static final String TAG = VideoScrollAdapter.class.getSimpleName();

	private final Context mContext;
	private List<VideoItem> mVideos;
	private List<Integer> mCachedPositions;

	private MediaPlayer mPlayer;

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
	public Object getItem(int index) {
		return mVideos.get(index);
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		final ViewHolder holder;

		if (view == null) {
			view = LayoutInflater.from(mContext).inflate(R.layout.activity_video, parent);

			holder = new ViewHolder();
			holder.videoView = (TextureView) view.findViewById(R.id.video_videoView);
			holder.txtName = (TextView) view.findViewById(R.id.video_textView);
			holder.progressBar = (ProgressBar) view.findViewById(R.id.video_progressBar);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		holder.videoView.setSurfaceTextureListener(this);

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
		TextureView videoView;
		TextView txtName;
		ProgressBar progressBar;
	}

	//     ___       _             __                  ___                 _                           _        _   _
	//    |_ _|_ __ | |_ ___ _ __ / _| __ _  ___ ___  |_ _|_ __ ___  _ __ | | ___ _ __ ___   ___ _ __ | |_ __ _| |_(_) ___  _ __  ___
	//     | || '_ \| __/ _ \ '__| |_ / _` |/ __/ _ \  | || '_ ` _ \| '_ \| |/ _ \ '_ ` _ \ / _ \ '_ \| __/ _` | __| |/ _ \| '_ \/ __|
	//     | || | | | ||  __/ |  |  _| (_| | (_|  __/  | || | | | | | |_) | |  __/ | | | | |  __/ | | | || (_| | |_| | (_) | | | \__ \
	//    |___|_| |_|\__\___|_|  |_|  \__,_|\___\___| |___|_| |_| |_| .__/|_|\___|_| |_| |_|\___|_| |_|\__\__,_|\__|_|\___/|_| |_|___/
	//                                                              |_|

	@Override
	public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
		Surface s = new Surface(surface);

		mPlayer = new MediaPlayer();
		try {
			mPlayer.setDataSource("http://daily3gp.com/vids/747.3gp");
		}
		catch (IOException e) {
			Log.e(TAG, "No data source available.");
		}
		mPlayer.setSurface(s);
		mPlayer.prepareAsync();
		mPlayer.setOnPreparedListener(this);
		mPlayer.setOnCompletionListener(this);
	}

	@Override
	public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

	}

	@Override
	public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
		return false;
	}

	@Override
	public void onSurfaceTextureUpdated(SurfaceTexture surface) {

	}

	@Override
	public void onPrepared(MediaPlayer mediaPlayer) {

	}

	@Override
	public void onCompletion(MediaPlayer mediaPlayer) {

	}
}
