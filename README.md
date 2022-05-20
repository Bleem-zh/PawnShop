# pawnshop
一个基于SSM的典当交易系统<br>

# 主要功能
1.用户的注册、登录、退出<br>
2.用户查看、修改个人信息<br>
3.用户查看、修改、删除个人典当物品<br>
4.珠宝的估当、典当、续当、赎当<br>
5.挂失当票<br>
6.管理典当物，包括对典当物的查看、删除、修改、<br>
7.管理用户，包括对用户的增加、删除、修改、查看<br>
8.有待完善...<br>

# 使用技术
1.后台框架：Spring + SpringMVC + Mybatis<br>
2.前端框架：Layui jquery<br>
3.异步交互：ajax<br>
4.数据库：Mysql<br>/PawnShop/WebRoot/WEB-INF/PawnShop/login/add_jewellery.jsp



# 房产字段

借款金额：balance

借款月息：interest

借款期限：term

借款成数：percentage







table_property_contract 房贷表 

	contractId 主键
	balance 金额
	interest 月息
	term 期限
	percentage 成数
	repaymentSource 还款来源
	comment 说明

table_loaner_between 借款人中间表
	contractId
	loanerBetweenId 主键
	loanerId 客户Id

table_loaner 借款人
	loanerId 主键
	name 姓名
	gender 性别
	age 年龄
	register 户籍
	marriage 婚姻
	credit 征信
	loanerType借款人类型

table_property_between 房产中间表
	contractId
	proBetId 主键
	propertyId

table_property 房产表
	propertyId 主键
	communityName 小区名称
	communityAddress 小区地址
	functional 房产类型
	area 房产面积
	type 房型
	decoration 装修
	status 状态
	year 房龄
	obligee 权利人
	obligeeRelation 权利人关系
	olderChildren 老人小孩
	registe 户口
	source 房产来源
	count 房产总价
	univalent 单价
	mortgage 抵押贷款
	percentage 贷款成数
	mortgageType 抵押类型
