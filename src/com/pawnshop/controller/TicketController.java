package com.pawnshop.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.pawnshop.common.CommonUtils;
import com.pawnshop.common.UploadSchedule;
import com.pawnshop.po.FileProperty;
import com.pawnshop.po.Jewellery;
import com.pawnshop.po.LoanerBetween;
import com.pawnshop.service.TicketService;

@Controller
@RequestMapping(value = "/ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;
	
	@Autowired
    private ThreadPoolTaskExecutor taskExecutor;

	/*-----------------------------挂失当票-----------------------------*/
	// 跳转到挂失当票页面
	@RequestMapping(value = "/toReport")
	public String toReport() {
		return "report";
	}

	// 挂失
	@RequestMapping(value = "/reportLoss", method = RequestMethod.POST)
	@ResponseBody
	public void jewelleryEvaluation(@RequestParam int tid) {
		System.out.println("要挂失的当票id：" + tid);
		ticketService.updateStateLoss(tid);
	}
	
	/**
	 * 图片上传
	 * @param file
	 * @param contractId
	 * @return
	 */
	@RequestMapping(value = "/upLoadFile")
	@ResponseBody 
	public Map<String, Object> upLoadFile(MultipartFile file,String contractId){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String codeStatus = "";
		
		System.out.println("【上传文件的合同编号是】---->"+contractId);
		
		//上传图片业务逻辑
		//处理上传图片任务
		System.out.println("DiseaseCourseInterface|uploadPic 当前线程数:"+taskExecutor.getActiveCount());
		
		Future<Object> submit = taskExecutor.submit(new UploadSchedule(file, contractId));
		try {
			codeStatus = (String) submit.get();
		} catch (Exception e) {
			e.printStackTrace();
			codeStatus = "500";
		} finally{
			resultMap.put("code", codeStatus);
			resultMap.put("n", "100");
			return resultMap;
		}
		
	}

	/**
	 * 查看图片
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/viewImage")
	@ResponseBody 
	public void viewImage(String contractId,String fileName,HttpServletRequest request,HttpServletResponse response){
		ticketService.viewImage(File.separator+contractId+File.separator+fileName,
				request,response);
	}
	
	/**
	 * 打开文件上传界面
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/toUploadFile")
	public String toUploadFile(String contractId,Model model){
		model.addAttribute("contractId", contractId);
		
		return "user/uploadFile";
	}
	/**
	 * 删除图片
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/deleteImage")
	@ResponseBody 
	public String deleteImage(String contractId,String fileName){
		return ticketService.deleteImage(File.separator+contractId+File.separator+fileName);
	}
	
	/**
	 * 重新渲染上传文件框
	 * @param contractId
	 * @return 返回文件名的集合
	 */
	@RequestMapping(value = "/reRenderFileList")
	@ResponseBody 
	public Map<String, Object> reRenderFileList(String contractId){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "");
		
		List<Object> fileList = ticketService.reRenderFileList(contractId);
		
	    result.put("count",fileList.size());
	    result.put("data", JSON.toJSON(fileList));
	    
	    System.out.println("【获取已上传的文件列表】--->"+fileList.toString());
	    
		return result;
		
	}
}
