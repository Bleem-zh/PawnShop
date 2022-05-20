package com.pawnshop.common;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pawnshop.dao.LoginDao;
import com.pawnshop.dao.UtilsDao;
import com.pawnshop.po.TbContractIndex;

@Component("commonUtils")
public class CommonUtils {
	
	//文件上传的目录
	//public static final String upLoadFilePath = "D:\\temp";
	public static final String upLoadFilePath = "/pawndata/pawnUpFile";//服务器目录
	
	/**
	 * 生成日期格式的主键 yyyyMMddHHmmss
	 * @return yyyyMMddHHmmss
	 */
	public static String getDateId(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		
		return dateFormat.format(new Date());
	}
	
	/**
	 * 生成合同表主键
	 * @return
	 */
	public static String getContractUUID(String productType){
		String uuid = "";
		
		switch (productType) {
		case "A0001"://房产
			uuid = "PC";
			break;
		case "A0002"://汽车
			uuid = "CC";
			break;
		}
		return uuid+UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}
	
	/**
	 * 获取数据库类型的时间
	 * @return
	 */
	public static Timestamp getNowTimestamp(){
		return new Timestamp(System.currentTimeMillis());
	}
	
	public static String getIpAddress(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
        if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(("127.0.0.1").equals(ipAddress) || ("0:0:0:0:0:0:0:1").equals(ipAddress)){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(!StringUtils.isEmpty(ipAddress) && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        
        return ipAddress;
	}
	

    /**  
     * 通过IP地址获取MAC地址  
     * @param ip String,127.0.0.1格式  
     * @return mac String  
     * @throws Exception  
     */    
    public static String getMACAddress(String ip) {    
        String line = "";    
        String macAddress = "";    
        final String MAC_ADDRESS_PREFIX = "MAC Address = ";    
        final String LOOPBACK_ADDRESS = "127.0.0.1";    
        //如果为127.0.0.1,则获取本地MAC地址。    
        if (LOOPBACK_ADDRESS.equals(ip)) {    
            //貌似此方法需要JDK1.6。    
			byte[] mac = null;
			try {
				InetAddress inetAddress = InetAddress.getLocalHost();    
				mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (SocketException e) {
				e.printStackTrace();
			}    
            //下面代码是把mac地址拼装成String    
            StringBuilder sb = new StringBuilder();    
            for (int i = 0; i < mac.length; i++) {    
                if (i != 0) {    
                    sb.append("-");    
                }    
                //mac[i] & 0xFF 是为了把byte转化为正整数    
                String s = Integer.toHexString(mac[i] & 0xFF);    
                sb.append(s.length() == 1 ? 0 + s : s);    
            }    
            //把字符串所有小写字母改为大写成为正规的mac地址并返回    
            macAddress = sb.toString().trim().toUpperCase();    
            return macAddress;    
        }    
        //获取非本地IP的MAC地址    
        try {    
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);    
            InputStreamReader isr = new InputStreamReader(p.getInputStream());    
            BufferedReader br = new BufferedReader(isr);    
            while ((line = br.readLine()) != null) {    
                if (line != null) {    
                    int index = line.indexOf(MAC_ADDRESS_PREFIX);    
                    if (index != -1) {    
                        macAddress = line.substring(index + MAC_ADDRESS_PREFIX.length()).trim().toUpperCase();    
                    }    
                }    
            }    
            br.close();    
        } catch (IOException e) {    
            e.printStackTrace(System.out);    
        }    
        return macAddress;    
    }  
    
    /**
     * 转换状态名字
     * @param updateReason
     * @return
     */
    public static String changeStatusName(String updateReason) {
		HashMap<String, String> map = new HashMap<>();
		map.put("已放款", "待还款");
		map.put("按计划还款", "待还款");
		map.put("已逾期", "已逾期");
		map.put("M1", "M1");
		map.put("M2", "M2");
		map.put("M3", "M3");
		map.put("M3+", "M3+");
		map.put("已赎回", "已赎回");
		map.put("逾期处理完成", "待还款");
		map.put("无偿还能力", "不良资产");
	
		return map.get(updateReason);
	}
    
    /**
     * 根据合同ID获取备份产品所需要的参数
     * @return
     * backupTb,sourceTb,primaryKeyName,contractId
     */
    public static HashMap<String, String> getBackupParam(String contractId) {
    	HashMap<String, String> mapParam = new HashMap<>();
    	String prouctTypeFlag = contractId.substring(0, 2);
    	
		switch (prouctTypeFlag) {
		case "PC"://房产
			mapParam.put("backupTb", "tb_property_approve");
			mapParam.put("sourceTb", "tb_property");
			mapParam.put("primaryKeyName", "propertyId");
			mapParam.put("contractId", contractId);
			break;
		
		case "CC"://汽车	
			mapParam.put("backupTb", "tb_car_approve");
			mapParam.put("sourceTb", "tb_car");
			mapParam.put("primaryKeyName", "carId");
			mapParam.put("contractId", contractId);
			break;
		}
		
    	return mapParam;
	}
    
    /**
     * 根据合同ID获取产品所需要的参数
     * @return
     * backupTb,sourceTb,primaryKeyName,contractId
     */
    public static HashMap<String, String> getApplyParam(String contractId) {
    	HashMap<String, String> mapParam = new HashMap<>();
    	String prouctTypeFlag = contractId.substring(0, 2);
    	
    	switch (prouctTypeFlag) {
    	case "PC"://房产
    		mapParam.put("backupTb", "tb_property");
    		mapParam.put("primaryKeyName", "propertyId");
    		mapParam.put("contractId", contractId);
    		break;
    		
    	case "CC"://汽车	
    		mapParam.put("backupTb", "tb_car");
    		mapParam.put("primaryKeyName", "carId");
    		mapParam.put("contractId", contractId);
    		break;
    	}
    	
    	return mapParam;
    }
    
    /**
     * 把典当物代码转化为典当物中文
     * @param productIndex
     * @return
     */
    public static String changeProuctType(String productIndex) {
    	String productType = "";
    	
		switch (productIndex) {
		case "A0001":
			productType = "房产";
			break;

		case "A0002":
			productType = "汽车";
			break;
		
		}
		
		return productType;
	}
    
    /**
     * 把典当物代码转化为典当物中文
     * @param productIndex
     * @return
     */
    public static String changeProuctTypeNum(String productTypeName) {
    	String productType = "";
    	
    	switch (productTypeName) {
    	case "房产":
    		productType = "A0001";
    		break;
    		
    	case "汽车":
    		productType = "A0002";
    		break;
    		
    	}
    	
    	return productType;
    }

}
