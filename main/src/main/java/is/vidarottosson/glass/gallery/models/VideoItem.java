package is.vidarottosson.glass.gallery.models;

//  Created by jonstaff on 1/17/14.

import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;

public class VideoItem extends FileItem implements Parcelable {

	public static final String EXTENSION_MP4 = ".mp4";
	public static final String EXTENSION_AVI = ".avi";
	public static final String EXTENSION_MKV = ".mkv";

	public static final String EXTENSIONS_VIDEO[] = {EXTENSION_MP4, EXTENSION_AVI, EXTENSION_MKV};

	public static final String KEY_FOR_INTENT_EXTRA = "videoItem";

	//      ____                _                   _
	//     / ___|___  _ __  ___| |_ _ __ _   _  ___| |_ ___  _ __ ___
	//    | |   / _ \| '_ \/ __| __| '__| | | |/ __| __/ _ \| '__/ __|
	//    | |__| (_) | | | \__ \ |_| |  | |_| | (__| || (_) | |  \__ \
	//     \____\___/|_| |_|___/\__|_|   \__,_|\___|\__\___/|_|  |___/

	public VideoItem(File file) {
		super(file);
	}

	//     ____                    _       _     _
	//    |  _ \ __ _ _ __ ___ ___| | __ _| |__ | | ___
	//    | |_) / _` | '__/ __/ _ \ |/ _` | '_ \| |/ _ \
	//    |  __/ (_| | | | (_|  __/ | (_| | |_) | |  __/
	//    |_|   \__,_|_|  \___\___|_|\__,_|_.__/|_|\___|

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(getPath());
		out.writeString(getName());
	}

	public static final Parcelable.Creator<VideoItem> CREATOR = new Parcelable.Creator<VideoItem>() {
		public VideoItem createFromParcel(Parcel in) {
			return new VideoItem(in);
		}

		public VideoItem[] newArray(int size) {
			return new VideoItem[size];
		}
	};

	private VideoItem(Parcel in) {
		super(in.readString());
	}
}
