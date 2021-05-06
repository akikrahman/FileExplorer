package com.akikrahman.model;

public class File {
	
	private String path;
	private String name;
	private char drive;
	private String extension;
	private long size;
	private String date;
	
	public File(String path, String name, char drive, String extension, long size, String date) {		
		this.path = path;
		this.name = name;
		this.drive = drive;
		this.extension = extension;
		this.size = size;
		this.date = date;
	}

	public String getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

	public char getDrive() {
		return drive;
	}

	public String getExtension() {
		return extension;
	}

	public long getSize() {
		return size;
	}

	public String getDate() {
		return date;
	}
	
}
