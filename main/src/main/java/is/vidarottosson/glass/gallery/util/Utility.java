package is.vidarottosson.glass.gallery.util;
//  Created by Viddi on 12/8/13.

import is.vidarottosson.glass.gallery.models.FileItem;

public class Utility {
    public static final String MEDIA_FOLDER_SOURCE = "/mnt/sdcard/DCIM/Camera/";

    public static boolean isImage(String filename) {
        String extension = getExtension(filename);

        for(String str : FileItem.EXTENTIONS_PICTURE) {
            if(extension.equals(str)) {
                return true;
            }
        }

        return false;
    }

    public static String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf('.'));
    }
}
