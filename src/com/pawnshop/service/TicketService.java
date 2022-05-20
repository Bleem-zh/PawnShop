package com.pawnshop.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pawnshop.po.FileProperty;

public interface TicketService {

	public int updateStateLoss(int id);

	/**
	 * 查看图片
	 * @param fileName 
	 * @param contractId 
	 * @param request
	 * @param response
	 */
	public String viewImage(String filePartPath, HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * 删除照片
	 * @param filePath
	 * @return
	 */
	public String deleteImage(String filePath);

	/**
	 * 重新渲染上传文件框
	 * @param contractId
	 * @return 返回文件名的集合
	 */
	public List<Object> reRenderFileList(String contractId);
}
