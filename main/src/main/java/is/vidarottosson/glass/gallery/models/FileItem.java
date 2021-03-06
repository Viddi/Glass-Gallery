package is.vidarottosson.glass.gallery.models;

//  Created by Viddi on 12/6/13.

import java.io.File;

import is.vidarottosson.glass.gallery.util.Utility;

public class FileItem {

	public enum Type {
		PICTURE, VIDEO, MUSIC
	}

	private String mPath;
	private Type mFileType;
	private String mName;
	private String mExtension;
	private long mLastModified;

	public FileItem(String filePath) {
		this(new File(filePath));
	}

	public FileItem(File file) {
		if (Utility.isImage(file.getAbsolutePath())) {
			mFileType = Type.PICTURE;
		} else if (Utility.isVideo(file.getAbsolutePath())) {
			mFileType = Type.VIDEO;
		}

		mPath = file.getAbsolutePath();
		mName = file.getName();
		setExtension(file.getName());

		mLastModified = file.lastModified();
	}

	public String getPath() {
		return mPath;
	}

	public void setPath(String path) {
		mPath = path;
	}

	public Type getFileType() {
		return mFileType;
	}

	public void setFileType(Type fileType) {
		mFileType = fileType;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
		setExtension(name);
	}

	public String getExtension() {
		return mExtension;
	}

	public void setExtension(String fileName) {
		mExtension = (fileName.substring(fileName.lastIndexOf('.')));
	}

	public long getLastModified() {
		return mLastModified;
	}

	public boolean deleteItem() {
		if (mPath != null) {
			File file = new File(mPath);

			return file.delete();
		}

		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		FileItem fileItem = (FileItem) o;

		if (mExtension != null ? !mExtension.equals(fileItem.mExtension) : fileItem.mExtension != null)
			return false;
		if (mFileType != fileItem.mFileType)
			return false;
		if (mName != null ? !mName.equals(fileItem.mName) : fileItem.mName != null)
			return false;
		if (mPath != null ? !mPath.equals(fileItem.mPath) : fileItem.mPath != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = mPath != null ? mPath.hashCode() : 0;
		result = 31 * result + (mFileType != null ? mFileType.hashCode() : 0);
		result = 31 * result + (mName != null ? mName.hashCode() : 0);
		result = 31 * result + (mExtension != null ? mExtension.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "FileItem{" + "mPath='" + mPath + '\'' + ", mFileType=" + mFileType + ", mName='" + mName + '\'' + ", mExtension='" + mExtension + '\'' + '}';
	}
}
