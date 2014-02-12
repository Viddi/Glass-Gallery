package is.vidarottosson.glass.gallery.models;

//  Created by jonstaff on 1/17/14.

import java.io.Serializable;

public class VideoItem extends FileItem implements Serializable {

	public static final String EXTENSION_MP4 = ".mp4";
	public static final String EXTENSION_AVI = ".avi";
	public static final String EXTENSION_MKV = ".mkv";

	public static final String EXTENSIONS_VIDEO[] = {EXTENSION_MP4, EXTENSION_AVI, EXTENSION_MKV};

	public static final String KEY_FOR_INTENT_EXTRA = "videoItem";

	private String mThumbnailPath;

	//      ____                _                   _
	//     / ___|___  _ __  ___| |_ _ __ _   _  ___| |_ ___  _ __ ___
	//    | |   / _ \| '_ \/ __| __| '__| | | |/ __| __/ _ \| '__/ __|
	//    | |__| (_) | | | \__ \ |_| |  | |_| | (__| || (_) | |  \__ \
	//     \____\___/|_| |_|___/\__|_|   \__,_|\___|\__\___/|_|  |___/

	public VideoItem(String path, String name) {
		super(Type.VIDEO, path, name);
	}

	//	public String getThumbnailPath() {
	//		return mThumbnailPath;
	//	}
	//
	//	public void setThumbnailPath(String path) {
	//		mThumbnailPath = path;
	//	}
}
