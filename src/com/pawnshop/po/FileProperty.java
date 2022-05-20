package com.pawnshop.po;

public class FileProperty {
	private String fileName;
	private String fileStatus;
	
	
	public FileProperty(String fileName, String fileStatus) {
		super();
		this.fileName = fileName;
		this.fileStatus = fileStatus;
	}


	@Override
	public String toString() {
		return "FileProperty [fileName=" + fileName + ", fileStatus="
				+ fileStatus + "]";
	}
	
}
