package com.pawnshop.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawnshop.common.CommonUtils;
import com.pawnshop.dao.TicketDao;
import com.pawnshop.po.FileProperty;
import com.pawnshop.service.TicketService;

@Service("TicketService")
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketDao ticketDao;

	@Override
	public int updateStateLoss(int id) {
		return ticketDao.updateStateLoss(id);
	}

	@Override
	public String viewImage(String filePartPath,
			HttpServletRequest request, HttpServletResponse response) {
		String resultString = "";
		response.setContentType("image/gif");
		
		Path path = Paths.get(CommonUtils.upLoadFilePath + File.separator + filePartPath);
		
		if (Files.exists(path)){
			OutputStream outputStream = null;
			
			try {
				RandomAccessFile randomAccessFile = new RandomAccessFile(path.toString(), "r");
				outputStream = response.getOutputStream();
				WritableByteChannel writableByteChannel = Channels.newChannel(outputStream);
				FileChannel fileChannel = randomAccessFile.getChannel();
				
				fileChannel.transferTo(0, fileChannel.size(), writableByteChannel);
				
				fileChannel.close();
				outputStream.flush();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				try {
	                if (outputStream != null) {
	                	outputStream.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
			}
		}else {
			resultString = "文件不存在!";
		}
		
		return resultString;
	}

	@Override
	public String deleteImage(String filePath) {
		String result = "";
		Path path = Paths.get(CommonUtils.upLoadFilePath + File.separator + filePath);
		
		try {
			if (Files.deleteIfExists(path)){
				result = "0000";
			}else {
				result = "文件不存在!";
			}
		} catch (IOException e) {
			e.printStackTrace();
			result = e.getMessage();
		} finally{
			return result;
		}
		
	}

	@Override
	public List<Object> reRenderFileList(String contractId) {
		List<Object> fileList = new ArrayList<>();
		
		try {
			DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(CommonUtils.upLoadFilePath+File.separator+contractId));
		    Iterator<Path> ite = stream.iterator();
		    while (ite.hasNext()) {
		    	HashMap<String, String> map = new HashMap<>();
		    	map.put("fileName", ite.next().getFileName().toString());
		    	map.put("fileStatus", "已上传");
		    	
		    	fileList.add(map);
		    }
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		} finally{
			return fileList;
		}
		
	}
}
