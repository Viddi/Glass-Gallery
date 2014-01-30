package is.vidarottosson.glass.gallery.video;

//  Created by jonathon on 1/29/14.

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;

import is.vidarottosson.glass.gallery.models.VideoItem;

public class VideoPlayer extends TextureView {
	public static final String TAG = VideoPlayer.class.getSimpleName();

	private VideoItem mVideo;

	public VideoPlayer(Context context) {
		this(context, null);
	}

	public VideoPlayer(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public VideoPlayer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public VideoItem getVideo() {
		return mVideo;
	}

	public void setVideo(VideoItem video) {
		mVideo = video;
	}
}
