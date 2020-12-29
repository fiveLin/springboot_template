package com.example.lin.resp;

/**
 * 通用结果编码。<br />
 * 
 * 如果业务中有特殊的编码，项目内扩展自定义（必须大于2000）。
 * 
 * @author LuLihong
 */
public interface ResultCode {
	/**	操作成功	*/
	static final int SUCC 					= 0;
	/**	操作失败	*/
	static final int FAIL 					= 1000;
	
	
	/*------------------ 调用者鉴权: (1000, 1100) ------------------*/	
	/**	Token不存在	*/
	static final int TOKEN_NOEXIST			= 1001;
	/**	Token已到期	*/
	static final int TOKEN_EXPIRED			= 1002;
	
	/**	访问频率超限	*/
	static final int ACCESS_FRE_OVER		= 1003;
	/**	无API访问权限	*/
	static final int API_NO_PERM			= 1004;
	/**	API不存在	*/
	static final int API_NOEXIST			= 1005;
	/**	IP无访问权限	*/
	static final int IP_NO_PERM			    = 1006;

	/**	登录账号不存在 */
	static final int AUTH_USER_NOEXIST		= 1020;
	/**	登录密码错误	*/
	static final int AUTH_PASSWORD_ERR		= 1021;
	
	/**	账号没有关联调度账号	*/
	static final int AUTH_USER_DIS_NOEXIST		= 1022;
	/*------------------ 调用者鉴权 -----------------END-*/
	
	
	/*------------------ 调用API时发生错误: [1100, 2000) ------------------*/
	/**	请求参数错误	*/
	static final int PARAM_ERR              = 1100;
	/**	缺少参数	*/
	static final int PARAM_NOEXIST          = 1101;
	/** 参数格式错误  */
	static final int PARAM_FMT_ERR          = 1102;
	/** 分页查询时页号错误 */
	static final int PAGE_NUM_ERR           = 1103;
	/** 分页查询时每页条数错误（超限） */
	static final int PAGE_SIZE_ERR          = 1104;
	
	
	/**	数据不存在	*/
	static final int DATA_NOEXIST           = 1120;
	/**	数据已存在	*/
	static final int DATA_EXIST             = 1121;
	/** 本数据与其它数据存在引用关系（如：部门下还有人员、还有下级部门） */
	static final int DATA_REF_EXIST         = 1122;
	/** 数据格式错误 */
	static final int DATA_FMT_ERR           = 1123;
	/** 数据库执行失败 */
	static final int DATA_EXECUTE_ERR       = 1124;
	/** 数据库插入失败 */
	static final int DATA_INSERT_ERR        = 1125;
	/** 数据库更新失败 */
	static final int DATA_UPDATE_ERR        = 1126;
	
	
	/**	文件格式错误	*/
	static final int FILE_FMT_ERR           = 1200;
	/** 文件打开失败 */
	static final int FILE_OPEN_FAIL         = 1201;
	/** 文件读写失败 */
	static final int FILE_RW_FAIL           = 1202;
	/** 文件不存在 */
	static final int FILE_NOEXIST           = 1203;
	
	
	
	/** 连接异常（数据库、Socket、Dubbo、Redis） */
	static final int CONNECTION_ERR         = 1220;
	/** 建立连接失败 */
	static final int CONNECT_FAIL           = 1221;
	/** 发送数据/指令失败 */
	static final int SENDING_FAIL           = 1222;
	/** 接收数据失败 */
	static final int RECEIVE_FAIL           = 1223;
	/** 传输超时 */
	static final int TRANSFER_TIMEOUT       = 1224;
	/** 对端无响应（返回包） */
	static final int NO_RESPONDING          = 1225;
	/** 与服务器断开连接 */
	static final int DISCONNECT_SERVER      = 1226;

    /** 执行超时 */
    static final int EXEC_TIMEOUT           = 1240;
    /** 任务队列已满 */
    static final int TASK_QUEUE_FULL        = 1241;
	
	/*------------------ 调用API时发生错误 -----------------END-*/	
	
	
	/*------------------ 业务自定义: [2000, ∞) ------------------*/
	/*------------------ 业务自定义: [2000, ∞) -----------------END-*/
	
	
	
}
