package is.vidarottosson.glass.gallery.util;

//  Created by Viddi on 12/8/13.

import java.text.DecimalFormat;

import is.vidarottosson.glass.gallery.models.PictureItem;
import is.vidarottosson.glass.gallery.models.VideoItem;

public class Utility {
	public static final String MEDIA_FOLDER_SOURCE = "/mnt/sdcard/DCIM/Camera/";

	public static final int MILLIS_PER_DAY = 86400000;
	public static final int MILLIS_PER_HOUR = 3600000;
	public static final int MILLIS_PER_MINUTE = 60000;
	public static final int MILLIS_PER_SECOND = 1000;

	public static boolean isImage(String filename) {
		String extension = getExtension(filename);

		for (String str : PictureItem.EXTENSIONS_PICTURE) {
			if (extension.equals(str)) {
				return true;
			}
		}

		return false;
	}

	public static boolean isVideo(String filename) {
		String extension = getExtension(filename);

		for (String str : VideoItem.EXTENSIONS_VIDEO) {
			if (extension.equals(str)) {
				return true;
			}
		}

		return false;
	}

	public static String getExtension(String filename) {
		return filename.substring(filename.lastIndexOf('.'));
	}

	public static String readableFileSize(long size) {
		if (size <= 0)
			return "0";
		final String[] units = new String[] {"B", "KB", "MB", "GB", "TB"};
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));

		return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}

	public static String readableTimeDifferenceSinceNow(long millis) {
		long difference = System.currentTimeMillis() - millis;

		int hours = (int) (difference / MILLIS_PER_HOUR);

		if (hours < 24) {
			if (hours > 1) {
				return hours + " hours ago";
			} else if (hours == 1) {
				return "an hour ago";
			} else {
				int minutes = (int) (difference % MILLIS_PER_HOUR) / (MILLIS_PER_MINUTE);

				return minutes > 1 ? minutes + " minutes ago" : (((difference % MILLIS_PER_HOUR) % MILLIS_PER_MINUTE) / MILLIS_PER_SECOND) + " seconds ago";
			}
		} else {
			return (difference / MILLIS_PER_DAY) + " days ago";
		}
	}
}
