package is.vidarottosson.glass.gallery.util;
//  Created by Viddi on 12/8/13.

import java.text.DecimalFormat;

import is.vidarottosson.glass.gallery.models.PictureItem;
import is.vidarottosson.glass.gallery.models.VideoItem;

public class Utility {
    public static final String MEDIA_FOLDER_SOURCE = "/mnt/sdcard/DCIM/Camera/";

    public static boolean isImage(String filename) {
        String extension = getExtension(filename);

        for(String str : PictureItem.EXTENSIONS_PICTURE) {
            if(extension.equals(str)) {
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
        if(size <= 0) return "0";
        final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size)/Math.log10(1024));

        return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
}
