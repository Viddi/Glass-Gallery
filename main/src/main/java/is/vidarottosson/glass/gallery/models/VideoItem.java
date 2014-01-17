package is.vidarottosson.glass.gallery.models;

//  Created by jonstaff on 1/17/14.

import java.util.Arrays;
import java.util.List;

public class VideoItem extends FileItem {

    public static final String EXTENSION_MP4 = ".mp4";
    public static final String EXTENSION_AVI = ".avi";
    public static final String EXTENSION_MKV = ".mkv";

    public static final List<String> EXTENSIONS_VIDEO = Arrays.asList(EXTENSION_MP4, EXTENSION_AVI, EXTENSION_MKV);


    public VideoItem(String path, String name) {
        super(Type.VIDEO, path, name);

    }
}
