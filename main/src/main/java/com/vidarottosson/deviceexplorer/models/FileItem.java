package com.vidarottosson.deviceexplorer.models;
//  Created by Viddi on 12/6/13.

public class FileItem {

    public enum Type {
        PICTURE, VIDEO
    }

    private String path;
    private int fileType;

    public FileItem(String path, int fileType) {
        this.path = path;
        this.fileType = fileType;
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

    @Override
    public String toString() {
        return "FileItem{" +
                "path='" + path + '\'' +
                ", fileType=" + fileType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileItem fileItem = (FileItem) o;

        if (fileType != fileItem.fileType) return false;
        if (path != null ? !path.equals(fileItem.path) : fileItem.path != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = 31 * result + fileType;
        return result;
    }
}
