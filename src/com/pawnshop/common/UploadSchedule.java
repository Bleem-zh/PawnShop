package com.pawnshop.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


public class UploadSchedule implements Callable<Object>{
	
	private MultipartFile file;
	private String contractId;
	private String sepa = File.separator;
	
	public UploadSchedule(MultipartFile file,String contractId) {
		this.file = file;
		this.contractId = contractId;
	}
	
	/*
	@Override
	public void run() {
		System.out.println("文件上传开始..............");
		
		Path path = Paths.get(CommonUtils.upLoadFilePath + sepa + contractId);
		File targetFile = new File(path.toString(), file.getOriginalFilename());
		
		try {
			rwLock.writeLock().lock();
			if(!Files.exists(path)){
				//目录不存在
				path = Files.createDirectory(path);
			}
			rwLock.writeLock().unlock();
			
			file.transferTo(targetFile);
			
			System.out.println("文件上传完成........");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/

	@Override
	public Object call() throws Exception {
		String resultString = "0000";
		System.out.println("文件上传开始..............");
		
		Path path = Paths.get(CommonUtils.upLoadFilePath + sepa + contractId);
		File targetFile = new File(path.toString(), file.getOriginalFilename());
		
		try {
			if(!Files.exists(path)){
				//目录不存在
				path = Files.createDirectory(path);
			}
			file.transferTo(targetFile);
			
			System.out.println("文件上传完成........");
		} catch (FileAlreadyExistsException e) {
			file.transferTo(targetFile);
			System.out.println("文件上传完成........");
		} finally{
			return resultString;
		}
	}

}
