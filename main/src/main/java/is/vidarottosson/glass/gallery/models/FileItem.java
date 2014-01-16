package is.vidarottosson.glass.gallery.models;
//  Created by Viddi on 12/6/13.

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileItem {

    public enum Type {
        PICTURE, VIDEO, MUSIC
    }

    public static final String EXTENSION_PNG = ".png";
    public static final String EXTENSION_JPG = ".jpg";
    public static final String EXTENSION_JPEG = ".jpeg";
    public static final String EXTENSION_BMP = ".bmp";

    public static final List<String> EXTENTIONS_PICTURE = Arrays.asList(EXTENSION_PNG, EXTENSION_JPG, EXTENSION_JPEG, EXTENSION_BMP);

    private String path;
    private int fileType;
    private String name;
    private String extension;

    public FileItem() {
    }

    public FileItem(int fileType, String path, String name) {
        this.fileType = fileType;
        this.path = path;
        this.name = name;
        setExtension(name);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setExtension(name);
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String fileName) {
        this.extension = (fileName.substring(fileName.lastIndexOf('.')));
    }

    public boolean deleteItem() {
        File file = new File(path);

        if(file.delete()) {
            // TODO: Create a callback to release items that are in main memory
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileItem fileItem = (FileItem) o;

        if (fileType != fileItem.fileType) return false;
        if (extension != null ? !extension.equals(fileItem.extension) : fileItem.extension != null)
            return false;
        if (name != null ? !name.equals(fileItem.name) : fileItem.name != null) return false;
        if (!path.equals(fileItem.path)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = path.hashCode();
        result = 31 * result + fileType;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FileItem{" +
                "path='" + path + '\'' +
                ", fileType=" + fileType +
                ", name='" + name + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}
