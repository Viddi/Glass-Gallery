package is.vidarottosson.glass.gallery.widget;

//  Created by jonathon on 1/29/14.

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;

import is.vidarottosson.glass.gallery.models.VideoItem;

public class VideoView extends TextureView {
	public static final String TAG = VideoView.class.getSimpleName();

	private VideoItem mVideo;

	public VideoView(Context context) {
		this(context, null);
	}

	public VideoView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public VideoView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public VideoItem getVideo() {
		return mVideo;
	}

	public void setVideo(VideoItem video) {
		mVideo = video;
	}
}
