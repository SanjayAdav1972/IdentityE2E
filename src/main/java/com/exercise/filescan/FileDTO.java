package com.exercise.filescan;

public class FileDTO {

	private String name;
	private String mimeType;
	private long size;
	private String extension;

	public FileDTO(String name, String mimeType, long size, String extension) {
		super();
		this.name = name;
		this.mimeType = mimeType;
		this.size = size;
		this.extension = extension;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public String toString() {
		return "File [name=" + name + ", mimeType=" + mimeType + ", size=" + size + ", extension=" + extension + "]";
	}
	
	

}
