
/*!
 * 用于加载所有内置模块
 * MIT Licensed  
 */
 
layui.define(function(){
  var mods = [] 
  ,builtin = layui.cache.builtin;
  layui.each(builtin, function(modName){
    (modName === 'all' || modName === 'layui.all') || mods.push(modName);
  });
  layui.cache.startTime = new Date().getTime();

  return mods;
}(), function(exports){
  "use strict";
  
  var MOD_NAME = 'all'
  
  //外部接口
  ,all = {
    config: {}
    ,time: function(){
      var time = new Date().getTime() - layui.cache.startTime;
      delete layui.cache.startTime;
      return time;
    }()
  };

  exports(MOD_NAME, all);
});

//计算还款计划
function calculateRepaymentPlan(formData){
	balance = formData.balance;
	rate = formData.interest/100;
	term = formData.term;
	beginDay = formData.beginDay;
	
	switch(formData.repayMethod){
		case "等额本息":
			return equalLoanPanments(balance,rate,term);
			break;
		case "等额本金":
			return equalPrincipalPayment(balance,rate,term,beginDay);
			break;
		case "先息后本":
			return interestBeforeCapital(balance,rate,term,beginDay);
			break;
	}
}

//等额本息
function equalLoanPanments(balance,rate,term){
	//每月还款
	var monthReyStr = ((balance*rate/12*(Math.pow((1+rate/12),term))/(Math.pow((1+rate/12),term)-1))).toFixed(2);
	//还款总额
	var grossRepaymentStr = monthReyStr*term;
	//利息总额
	var grossInterestStr = grossRepaymentStr - balance;

	var result = {monthRey:monthReyStr,
				  grossRepayment:grossRepaymentStr.toFixed(2),
				  grossInterest:grossInterestStr.toFixed(2)};
	
	return result;
}

//等额本金
function equalPrincipalPayment(balance,rate,term,beginDay){
	var repayPlanObj = [];
	
	balance = Number(balance);
	
	//每月还本金
	var monthReyStr = Number(balance/term);
	//利息总额
	var grossInterestStr = Number(0);
	
	for(var i=1; i<=term; i++){
		//该月需还利息
		var repayRate = Number((balance-(monthReyStr*(i-1)))*rate);
		grossInterestStr += repayRate;
		
		var repayDate = getRepayDate(beginDay,i);
		
		repayPlanObj[i-1] = {
				monthReyStr:(monthReyStr+repayRate).toFixed(2),//每月还款总额
				monthBalance: monthReyStr.toFixed(2),//每月还款本金
				repayRate:repayRate.toFixed(2),//利息
				repayDate: repayDate//还款日期
		};
	}
	
	var grossRepaymentStr = balance  + grossInterestStr;
	
	var result = {monthRey:monthReyStr.toFixed(2),
				  grossRepayment:grossRepaymentStr.toFixed(2),
				  grossInterest:grossInterestStr.toFixed(2),
				  repayPlan:repayPlanObj};
	
	return result;
}

//先息后本
function interestBeforeCapital(balance,rate,term,beginDay){
var repayPlanObj = [];
	
	balance = Number(balance);
	
	//每月还利息
	var monthReyStr = Number(balance*rate);
	//利息总额
	var grossInterestStr = monthReyStr*term;
	
	for(var i=1; i<=term; i++){
		var repayDate = getRepayDate(beginDay,i);
		var monthRey = Number(0);
		var monthBalanceStr = Number(0);
		
		if(i == term){
			monthRey = balance + monthReyStr;
			monthBalanceStr = balance;
		}else{
			monthRey = monthReyStr;
		}
		
		repayPlanObj[i-1] = {
				monthReyStr: monthRey.toFixed(2),//每月还款总额
				monthBalance: monthBalanceStr,//每月还款本金
				repayRate: monthReyStr.toFixed(2),//利息
				repayDate: repayDate//还款日期
		};
	}
	
	var grossRepaymentStr = balance  + grossInterestStr;
	
	var result = {monthRey:monthReyStr.toFixed(2),
				  grossRepayment:grossRepaymentStr,
				  grossInterest:grossInterestStr.toFixed(2),
				  repayPlan:repayPlanObj};
	
	return result;
}

//检查还款计划的参数是否正确
function checkRepayForm(formData){
    var parnt = /^[1-9]\d*(\.\d+)?$/;//大于0的数
	if(parnt.test(formData.balance) == false){
		layer.msg("借款金额格式不正确！", {icon: 5,time:3000}); 
		return false;
	}
	if(parnt.test(formData.interest) == false){
		layer.msg("借款月息格式不正确！", {icon: 5,time:3000}); 
		return false;
	}
	
	if(formData.endDateInput == "" || formData.endDateInput == null){
		layer.msg("起始日期为空！", {icon: 5,time:3000}); 
		return false;
	}
	
	//校验
	var re = /^[0-9]+$/ ;
    if(formData.term==0 || re.test(formData.term) == false){
    	layer.msg("借款期限必须是正整数！", {icon: 5,time:3000}); 
    	return false;
    }
	
}

 function getTerminateDay(termObj){
		    var $ = layui.jquery 
		    	,laydate = layui.laydate; 
			
			var beginDay = document.getElementById("beginDay").value;
			var jsDate = new Date(beginDay);
	    	var year = Number(jsDate.getFullYear());
	    	var month = Number(jsDate.getMonth() + 1);
	    	var day = Number(jsDate.getDate());
	    	var term = Number(termObj);
	    	
	    	//商
	    	var quotient = parseInt((month+term)/12);
	    	//余数
	    	var remainder = (month+term) % 12;
	    	
	    	//开始日期月份的最后一天
	    	var beginEndDate = laydate.getEndDate(month, year);
	    	//判断是否是最后一天
	    	if(day == beginEndDate){
		    	if(day == beginEndDate){
			    	//结束那天
			    	day = laydate.getEndDate(remainder, year+quotient);
		    	}
	    	}
	    	
	    	if(remainder < 10){
	    		remainder = "0"+remainder;
	    	}
	    	if(day<10){
	    		day = "0"+day;
	    	}
	    	
	    	year += quotient;
	    	
	    	var resultStr = year+"-"+remainder+"-"+day;
			
	    	return resultStr;
}
 
 /**
  * 获取还款日
  */
 function getRepayDate(beginDay,term){
		 var $ = layui.jquery 
		 ,laydate = layui.laydate; 
		 
		 var jsDate = new Date(beginDay);
		 var year = Number(jsDate.getFullYear());
		 var month = Number(jsDate.getMonth() + 1);
		 var day = Number(jsDate.getDate());
		 term = Number(term);
		 
		 //商
		 var quotient = parseInt((month+term)/12);
		 //余数
		 var remainder = (month+term) % 12;
		 
		 //开始日期月份的最后一天
		 var beginEndDate = laydate.getEndDate(month, year);
		 //判断是否是最后一天
		 if(day == beginEndDate){
			 if(day == beginEndDate){
				 //结束那天
				 day = laydate.getEndDate(remainder, year+quotient);
			 }
		 }
		 
		 if(remainder < 10){
			 remainder = "0"+remainder;
		 }
		 if(day<10){
			 day = "0"+day;
		 }
		 
		 year += quotient;
		 
		 var resultStr = year+"-"+remainder+"-"+day;
		 
		 return resultStr;
 }


