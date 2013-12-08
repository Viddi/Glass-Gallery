package com.vidarottosson.deviceexplorer.models;
//  Created by Viddi on 12/6/13.

public class FileItem {

    public enum Type {
        PICTURE, VIDEO, MUSIC
    }

    public static final String EXTENSION_PNG = ".png";
    public static final String EXTENSION_JPG = ".jpg";
    public static final String EXTENSION_JPEG = ".jpeg";
    public static final String EXTENSION_BMP = ".bmp";

    private String path;
    private int fileType;
    private String name;
    private String extension;

    public FileItem() {

    }

    public FileItem(String path, int fileType, String name, String extension) {
        this.path = path;
        this.fileType = fileType;
        this.name = name;
        this.extension = extension;
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
        setExtension(name.substring(name.lastIndexOf('.')));
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }


}
