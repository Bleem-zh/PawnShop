package com.pawnshop.common;

import java.io.File;
import java.io.IOException;
import java.nio.channels.Selector;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

/**
 * 文件上传工具
 * @author Administrator
 *
 */
@Controller
public class UpLoadUtil {
	public static void main(String[] args) throws IOException {
		/*//创建选择器
		Selector selector = Selector.open();
		
		ModelMap map) throws IOException {
			// 保存图片的路径，图片上传成功后，将路径保存到数据库
			String filePath = "D:\\zupload";
			// 获取原始图片的扩展名
			String originalFilename = file.getOriginalFilename();
			// 生成文件新的名字
			String newFileName = UUID.randomUUID() + originalFilename;
			// 封装上传文件位置的全路径
			File targetFile = new File(filePath, newFileName);
			file.transferTo(targetFile);
			
			// 保存到数据库
			jewellery.setJphoto(newFileName);
			pawnDao.saveJphoto(jewellery);
			
			return null;*/
		
	}
}
