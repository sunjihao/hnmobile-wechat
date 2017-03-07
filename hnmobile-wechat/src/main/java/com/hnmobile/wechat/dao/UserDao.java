package com.hnmobile.wechat.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.hnmobile.wechat.mvc.SpringContext;
import com.hnmobile.wechat.mvc.model.PageObject;
import com.hnmobile.wechat.mvc.model.PageVO;
import com.hnmobile.wechat.mvc.model.UserFuncVO;
import com.hnmobile.wechat.mvc.model.UserListVO;
import com.hnmobile.wechat.mvc.model.UserLoginLogVO;
import com.hnmobile.wechat.mvc.model.UserRoleVO;
import com.hnmobile.wechat.mvc.model.UserVO;
import com.hnmobile.wechat.util.DateUtils;


public class UserDao extends JdbcBaseDao{

	public UserVO getUserByAccount(String userAccount ){
		
		String sql="SELECT USER_ACCOUNT,PASSWD,UPDATE_TIME,CREATE_TIME,LOCKED,REGISTER_TIME,USER_CNAME";
		sql+=" ,TEL,BIRTH_DAY,BIRTHDAY_TYPE,SEX FROM MMC.T_USER T WHERE T.USER_ACCOUNT=? AND T.ACTION_FLAG<>'D'";
		List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, userAccount);
		
		UserVO uvo = new UserVO();
		for( Map<String, Object> rs:results ){
			String passwd = (String) rs.get("PASSWD");
			Date updateTime = (Date) rs.get("UPDATE_TIME");
			Date createTime = (Date) rs.get("CREATE_TIME");
			String lock = (String) rs.get("LOCKED");
			Date registerTime = (Date) rs.get("REGISTER_TIME");
			String userCname = rs.get("USER_CNAME")==null?null:rs.get("USER_CNAME")+"";
			String tel = rs.get("TEL")==null?null:rs.get("TEL")+"";
			String birthDay = rs.get("BIRTH_DAY")==null?null:rs.get("BIRTH_DAY")+"";
			String birthdayType = rs.get("BIRTHDAY_TYPE")==null?"2":rs.get("BIRTHDAY_TYPE")+"";
			String sex = rs.get("SEX")==null?null:rs.get("SEX")+"";
			
			uvo.setBirthdayType(birthdayType);
			uvo.setUserCname(userCname);
			uvo.setTel(tel);
			uvo.setBirthday(birthDay);
			uvo.setSex(sex);
			uvo.setPasswd(passwd);
			uvo.setUpdateTime(updateTime);
			uvo.setCreateTime(createTime);
			uvo.setUserAccount(userAccount);
			uvo.setLock(lock);
			uvo.setRegisterTime(registerTime);
			
			return uvo;
		}
		return null;
	}
	
	public void updateUser( UserVO uvo ){
		
		String sql="";
		sql+="UPDATE MMC.T_USER ";
		sql+="SET PASSWD =  ?,";
		sql+="  UPDATE_TIME =  ?,";
		sql+="  CREATE_TIME =  ?, ";
		sql+="  REGISTER_TIME =  ?, ";
		sql+="  LOCKED =  ? ";
		sql+="  ACTION_FLAG =  'U' ";
		sql+="WHERE USER_ACCOUNT =  ?";
		jdbcTemplate.update(sql, uvo.getPasswd(),uvo.getUpdateTime(),
				uvo.getCreateTime(),uvo.getRegisterTime(),uvo.getLock(),uvo.getUserAccount());
	}
	
    public void delUser(String userAccount,String actionUser){
		
		String sql="";
		sql+="UPDATE MMC.T_USER ";
		sql+="SET UPDATE_TIME =?,";
		sql+="  ACTION_FLAG =?, ";
		sql+="  ACTION_USER =? ";
		sql+="WHERE USER_ACCOUNT=?";
		jdbcTemplate.update(sql, new Date(),"D",actionUser,userAccount);
	}
	
	public PageVO<UserListVO> getUserList(int pageNo,int pageSize){
		
		PageVO<UserListVO> pageVO = new PageVO<UserListVO>();
		
		String sql="";
		sql+="SELECT COUNT(1) FROM "; 
		sql+=" MMC.T_USER A WHERE A.ACTION_FLAG<>'D'";
		int total = total(sql);
		
		sql="SELECT A.REGISTER_TIME,A.USER_ACCOUNT,A.LOCKED FROM "; 
		sql+=" MMC.T_USER A WHERE A.ACTION_FLAG<>'D'";
		sql+=" limit "+(pageNo*pageSize)+","+pageSize;
		List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
		
		List<UserListVO> ulvolist = new ArrayList<UserListVO>();
		for( Map<String, Object> rs:results ){
			String registerTime = DateUtils.formatDateTime((Date) rs.get("REGISTER_TIME")) ;
			String userAccount = (String) rs.get("USER_ACCOUNT");
			String lock = (((String) rs.get("LOCKED")).equals("0"))?"正常":"锁定";
			
			UserListVO ulvo = new UserListVO();
			ulvo.setRegisterTime(registerTime);
			ulvo.setUserAccount(userAccount);
			ulvo.setLock(lock);
			ulvolist.add(ulvo);
		}
		pageVO.setListVO(ulvolist);
		pageVO.setTotal(total);
		return pageVO;
	}
	
	
	public PageVO<UserListVO> queryUser(int pageNo,int pageSize,String userAccount){
		
		PageVO<UserListVO> pageVO = new PageVO<UserListVO>();
		
		String sql="";
		sql+="SELECT COUNT(1) FROM "; 
		sql+=" MMC.T_USER A WHERE A.ACTION_FLAG<>'D'";
		if( !StringUtils.isEmpty(userAccount))sql+=" AND A.USER_ACCOUNT LIKE '%"+userAccount+"%' ";
		int total = total(sql);
		
		sql="SELECT A.REGISTER_TIME,A.USER_ACCOUNT,A.LOCKED FROM "; 
		sql+=" MMC.T_USER A WHERE A.ACTION_FLAG<>'D' ";
		if( !StringUtils.isEmpty(userAccount))sql+=" AND A.USER_ACCOUNT LIKE '%"+userAccount+"%' ";
		sql+=" limit "+(pageNo*pageSize)+","+pageSize;
		List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
		
		List<UserListVO> ulvolist = new ArrayList<UserListVO>();
		for( Map<String, Object> rs:results ){
			String registerTime = DateUtils.formatDateTime((Date) rs.get("REGISTER_TIME")) ;
			userAccount = (String) rs.get("USER_ACCOUNT");
			String lock = (((String) rs.get("LOCKED")).equals("0"))?"正常":"锁定";
			
			UserListVO ulvo = new UserListVO();
			ulvo.setRegisterTime(registerTime);
			ulvo.setUserAccount(userAccount);
			ulvo.setLock(lock);
			ulvolist.add(ulvo);
		}
		pageVO.setListVO(ulvolist);
		pageVO.setTotal(total);
		return pageVO;
	}
	
	public UserVO getUser(String userAccount,String birthday,String birthdayType,String sex,String tel){
		
		if( StringUtils.isEmpty(userAccount))return null;
		String sql="";
		sql="SELECT A.REGISTER_TIME,A.USER_ACCOUNT,C.ROLE_NAME,C.ROLE_ID,A.LOCKED, "; 
		sql+=" A.SEX,A.TEL,A.USER_CNAME,A.BIRTH_DAY,A.BIRTHDAY_TYPE,A.PASSWD ";
		sql+=" FROM MMC.T_USER A ,MMC.T_USER_ROLE B,MMC.T_ROLE C WHERE A.USER_ACCOUNT=B.USER_ACCOUNT AND B.ROLE_ID=C.ROLE_ID ";
		sql+=" AND A.USER_ACCOUNT = ?";
		if (!StringUtils.isEmpty(birthday)) {
		    sql+=" AND A.BIRTH_DAY='"+birthday+"'";
		}
		if (!StringUtils.isEmpty(birthdayType)) {
            sql+=" AND A.BIRTHDAY_TYPE='"+birthdayType+"'";
        }
		if (!StringUtils.isEmpty(sex)) {
            sql+=" AND A.SEX='"+sex+"'";
        }
		if (!StringUtils.isEmpty(tel)) {
            sql+=" AND A.TEL='"+tel+"'";
        }
		List<Map<String, Object>> results = jdbcTemplate.queryForList(sql,userAccount);
		
		for( Map<String, Object> rs:results ){
			Date registerTime = (Date) rs.get("REGISTER_TIME") ;
			String userCName = rs.get("USER_CNAME")==null?"":rs.get("USER_CNAME")+"";
			sex = rs.get("SEX")==null?"":rs.get("SEX")+"";
			tel = rs.get("TEL")==null?"":rs.get("TEL")+"";
			birthday = rs.get("BIRTH_DAY")==null?"":rs.get("BIRTH_DAY")+"";
			birthdayType = rs.get("BIRTHDAY_TYPE")==null?"2":rs.get("BIRTHDAY_TYPE")+"";
			String roleName = (String) rs.get("ROLE_NAME");
			int roleId = (int) rs.get("ROLE_ID");
			String lock = (String) rs.get("LOCKED");
			
			UserVO uvo = new UserVO();
			uvo.setUserCname(userCName);
			uvo.setPasswd(rs.get("PASSWD")+"");
			uvo.setSex(sex);
			uvo.setTel(tel);
			uvo.setBirthday(birthday);
			uvo.setBirthdayType(birthdayType);
			uvo.setRegisterTime(registerTime);
			uvo.setRegisterTimeStr(DateUtils.formatDateTime(registerTime));
			if (!StringUtils.isEmpty(sex)) {
			    uvo.setSexName(sex.equals("F")?"女":"男");
			}
			if (!StringUtils.isEmpty(birthdayType)) {
                uvo.setBirthdayTypeName(birthdayType.equals("1")?"农历":"阳历");
            }
			uvo.setUserAccount(userAccount);
			uvo.setRoleName(roleName);
			uvo.setRoleId(roleId);
			uvo.setLock(lock);
			return uvo;
		}
		return null;
	}
	
	public void inserLoginLog( UserLoginLogVO userLoginLogVO ){
	    String sql="INSERT INTO MMC.T_USER_LOGIN_LOG";
	    sql+="(USER_ACCOUNT,LOGIN_IP,LOGIN_TIME,LOGIN_ERROR_TIME,LAST_LOGIN_RESULT)";
	    sql+="VALUES(?,?,?,?,?,?)";
	    jdbcTemplate.update(sql,userLoginLogVO.getUserAccount(),
	    		userLoginLogVO.getLoginIp(),userLoginLogVO.getLoginTime(),userLoginLogVO.getLoginErrorTime(),
	    		userLoginLogVO.getLastLoginResult());
	}
	
	public void updateLoginLog( UserLoginLogVO userLoginLogVO ){
	    String sql="";
	    sql+="UPDATE MMC.T_USER_LOGIN_LOG ";
	    sql+="SET LOGIN_IP = ?,";
	    sql+="  LOGIN_TIME = ?,";
	    sql+="  LOGIN_ERROR_TIME = ?,";
	    sql+="  ACTION_FLAG = 'U',";
	    sql+="  LAST_LOGIN_RESULT = ? ";
	    sql+="WHERE USER_ACCOUNT = ?";
	    jdbcTemplate.update(sql,
	    		userLoginLogVO.getLoginIp(),userLoginLogVO.getLoginTime(),
	    		userLoginLogVO.getLoginErrorTime(),userLoginLogVO.getLastLoginResult(),userLoginLogVO.getUserAccount());
	}
	
	public UserLoginLogVO getLoginLog( String userAccount ){
	    String sql="";
	    sql+="SELECT LOGIN_LOG_ID,USER_ACCOUNT,LOGIN_IP,LOGIN_TIME,LOGIN_ERROR_TIME,LAST_LOGIN_RESULT ";
	    sql+=" FROM MMC.T_USER_LOGIN_LOG WHERE USER_ACCOUNT=?  ";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, userAccount);
		
		for( Map<String, Object> rs:results ){
			UserLoginLogVO uvo = new UserLoginLogVO();
			int loginLogId = (int) rs.get("LOGIN_LOG_ID");
			String loginIP = (String) rs.get("LOGIN_IP");
			Date loginTime = (Date) rs.get("LOGIN_TIME");
			int loginErrorTime = (int) rs.get("LOGIN_ERROR_TIME");
			int lastLoginResult = (int) rs.get("LAST_LOGIN_RESULT");
			
			uvo.setLastLoginResult(lastLoginResult);
			uvo.setUserAccount(userAccount);
			uvo.setLoginErrorTime(loginErrorTime);
			uvo.setLoginIp(loginIP);
			uvo.setLoginLogId(loginLogId);
			uvo.setLoginTime(loginTime);
			uvo.setLoginErrorTime(loginErrorTime);
			uvo.setLastLoginResult(lastLoginResult);
			return uvo;
		}
		return null;
	}
	

	public void updateUserInfo(String userName, String roleId, String lock,String registerTime) {
		String sql="UPDATE MMC.T_USER SET REGISTER_TIME=?,LOCKED=?,ACTION_FLAG='U',UPDATE_TIME=CURRENT_TIMESTAMP WHERE USER_ACCOUNT=?";
		jdbcTemplate.update(sql, registerTime,lock,userName);
		sql="UPDATE MMC.T_USER_ROLE,MMC.T_USER SET MMC.T_USER_ROLE.ROLE_ID=?,MMC.T_USER_ROLE.ACTION_FLAG='U'," ;
		sql+="MMC.T_USER_ROLE.UPDATE_TIME=CURRENT_TIMESTAMP WHERE ";
        sql+="MMC.T_USER_ROLE.USER_ACCOUNT= MMC.T_USER.USER_ACCOUNT AND MMC.T_USER.USER_ACCOUNT=?";
        jdbcTemplate.update(sql, roleId,userName);
	}
	
	public void updateUserInfo(String userAccount,String registerTime,String tel,String birthday,
            String cname,String sex,String birthDayType){
	    String sql="UPDATE MMC.T_USER SET REGISTER_TIME=?,TEL=?";
	    sql+=",USER_CNAME=?,SEX=?,BIRTHDAY_TYPE=?";
	    sql+=",BIRTH_DAY=?,ACTION_FLAG='U',UPDATE_TIME=CURRENT_TIMESTAMP WHERE USER_ACCOUNT=?";
        jdbcTemplate.update(sql,registerTime,tel,cname,sex,birthDayType,birthday,userAccount);
	}
	
	public void updateUserPasswd(String userAccount,String passwd){
        String sql="UPDATE MMC.T_USER SET PASSWD=?";
        sql+=",ACTION_FLAG='U',UPDATE_TIME=CURRENT_TIMESTAMP WHERE USER_ACCOUNT=?";
        jdbcTemplate.update(sql,passwd,userAccount);
    }
	
	public void updateUserLock(String userAccount,String lock){
        String sql="UPDATE MMC.T_USER SET LOCKED=?";
        sql+=",ACTION_FLAG='U',UPDATE_TIME=CURRENT_TIMESTAMP WHERE USER_ACCOUNT=?";
        jdbcTemplate.update(sql,lock,userAccount);
    }
	
	public void logicDelUserInfo(String userName) {
		String sql="UPDATE MMC.T_USER SET ACTION_FLAG='D',UPDATE_TIME=CURRENT_TIMESTAMP WHERE USER_ACCOUNT=?";
		jdbcTemplate.update(sql,userName);
		sql="UPDATE MMC.T_USER_ROLE,MMC.T_USER SET MMC.T_USER_ROLE.ACTION_FLAG='D'," ;
		sql+="MMC.T_USER_ROLE.UPDATE_TIME=CURRENT_TIMESTAMP WHERE ";
        sql+="MMC.T_USER_ROLE.USER_ACCOUNT= MMC.T_USER.USER_ACCOUNT AND MMC.T_USER.USER_ACCOUNT=?";
        jdbcTemplate.update(sql,userName);
	}
	

	public void addUserInfo(String userName, Integer roleId, String lock,String tel
            ,String birthday,String passwd,String birthdayType,String sex) {
		String sql="INSERT INTO MMC.T_USER(USER_ACCOUNT,PASSWD,REGISTER_TIME" +
				",UPDATE_TIME,CREATE_TIME,LOCKED,ACTION_FLAG,TEL,BIRTH_DAY,BIRTHDAY_TYPE,SEX)";
		sql+="VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		String now = DateUtils.formatDateTime(new Date());
		jdbcTemplate.update(sql,userName,passwd,now,now,now,lock,"I",tel,birthday,birthdayType,sex);
		
		if(roleId!=null){
			sql="INSERT INTO  MMC.T_USER_ROLE(USER_ACCOUNT,ROLE_ID,UPDATE_TIME,CREATE_TIME,ACTION_FLAG)";
			sql+="VALUES(?,?,?,?,?)";
			jdbcTemplate.update(sql,userName,roleId,now,now,"I");
		}
		
	}
	
	/**
	 * 会员账号存在，返回true，否则返回false
	 * @param memberAccount
	 * @return
	 */
	public boolean isUserAccountExists(String memberAccount) {
		String sql=" SELECT 1 ";
		sql+=" FROM MMC.T_USER T_USER WHERE T_USER.USER_ACCOUNT=? ";
		List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, memberAccount);
		return results!=null&&!results.isEmpty();
	}
	
	public static void main( String[]args ){
		UserDao dao = SpringContext.ctx.getBean("userDao", UserDao.class);
		
		dao.addUserInfo("sunguangxiao22",1,"1",null,null,null,null,null);
		
		System.out.println("end");
	}

	public PageObject<UserFuncVO> queryUserFunc(int pageNo, int pageSize,String userAccount,
			String funcShowName,String funcName,String isMenu) {
		PageObject<UserFuncVO> pageVO = new PageObject<UserFuncVO>();
		String sql="";
		sql+="SELECT DISTINCT RS.* FROM (";
		sql+=" SELECT C.FUNC_ID,C.FUNC_NAME,C.FUNC_URL,C.FUNC_SHOW_NAME,C.GROUP,A.UPDATE_TIME,0 FUNC_TYPE,C.IS_MENU FROM MMC.T_USER_FUNC A ,MMC.T_FUNC C WHERE A.FUNC_ID=C.FUNC_ID"; 
		sql+=" AND A.USER_ACCOUNT='"+userAccount+"' AND A.ACTION_FLAG<>'D' AND C.ACTION_FLAG<>'D' ";
		sql+=" UNION ";
		sql+=" SELECT C.FUNC_ID,C.FUNC_NAME,C.FUNC_URL,C.FUNC_SHOW_NAME,C.GROUP,A.UPDATE_TIME,1 FUNC_TYPE,C.IS_MENU FROM MMC.T_USER_ROLE A ,MMC.T_ROLE B,MMC.T_ROLE_FUNC D,MMC.T_FUNC C WHERE";
		sql+=" A.USER_ACCOUNT='"+userAccount+"' AND A.ROLE_ID=B.ROLE_ID AND B.ROLE_ID=D.ROLE_ID AND D.FUNC_ID=C.FUNC_ID ";
		sql+=" AND A.ACTION_FLAG<>'D' AND B.ACTION_FLAG<>'D' AND C.ACTION_FLAG<>'D' AND D.ACTION_FLAG<>'D')RS WHERE 1=1 ";
	    if(!StringUtils.isEmpty(funcName)){
	    	sql+=" AND RS.FUNC_NAME LIKE '%"+funcName+"%'";
	    }
	    if(!StringUtils.isEmpty(funcShowName)){
	    	sql+=" AND RS.FUNC_SHOW_NAME LIKE '%"+funcShowName+"%'";
	    }
	    if(!StringUtils.isEmpty(isMenu)){
	    	sql+=" AND RS.IS_MENU = '"+isMenu+"'";
	    }
		int total = total(jdbcTemplate,sql);
		
		sql+=" ORDER BY `GROUP`";
		sql+=" limit "+(pageNo*pageSize)+","+pageSize;
		List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
		
		List<UserFuncVO> volist = new ArrayList<UserFuncVO>();
		for( Map<String, Object> rs:results ){
			UserFuncVO userFuncVO = new UserFuncVO();
			userFuncVO.setFuncId( (int) rs.get("FUNC_ID"));
			userFuncVO.setFuncName( (String) rs.get("FUNC_NAME"));
			userFuncVO.setFuncShowName((String) rs.get("FUNC_SHOW_NAME"));
			userFuncVO.setFuncUrl( (String) rs.get("FUNC_URL"));
			long funcType = (long) rs.get("FUNC_TYPE");
			if(funcType==0)userFuncVO.setAction("<a href='#' class='del'>[删除]</a>");
			volist.add(userFuncVO);
		}
		pageVO.setDatas(volist);
		pageVO.setTotalCount(total);
		return pageVO;
	}
	
	public List<UserFuncVO> queryUserFunc(String userAccount) {
		String sql="";
		sql+="SELECT DISTINCT RS.* FROM (";
		sql+=" SELECT C.FUNC_ID,C.FUNC_NAME,C.FUNC_SHOW_NAME,C.GROUP,A.UPDATE_TIME,0 FUNC_TYPE,C.FUNC_URL FROM MMC.T_USER_FUNC A ,MMC.T_FUNC C WHERE A.FUNC_ID=C.FUNC_ID"; 
		sql+=" AND A.USER_ACCOUNT='"+userAccount+"' AND A.ACTION_FLAG<>'D' AND C.ACTION_FLAG<>'D' ";
		sql+=" UNION ";
		sql+=" SELECT C.FUNC_ID,C.FUNC_NAME,C.FUNC_SHOW_NAME,C.GROUP,A.UPDATE_TIME,1 FUNC_TYPE,C.FUNC_URL FROM MMC.T_USER_ROLE A ,MMC.T_ROLE B,MMC.T_ROLE_FUNC D,MMC.T_FUNC C WHERE";
		sql+=" A.USER_ACCOUNT='"+userAccount+"' AND A.ROLE_ID=B.ROLE_ID AND B.ROLE_ID=D.ROLE_ID AND D.FUNC_ID=C.FUNC_ID ";
		sql+=" AND A.ACTION_FLAG<>'D' AND B.ACTION_FLAG<>'D' AND C.ACTION_FLAG<>'D' AND D.ACTION_FLAG<>'D')RS WHERE 1=1 ";
		List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
		
		List<UserFuncVO> voList = new ArrayList<UserFuncVO>();
		for( Map<String, Object> rs:results ){
			UserFuncVO userFuncVO = new UserFuncVO();
			userFuncVO.setFuncId( (int) rs.get("FUNC_ID"));
			userFuncVO.setFuncName( (String) rs.get("FUNC_NAME"));
			userFuncVO.setFuncShowName((String) rs.get("FUNC_SHOW_NAME"));
			userFuncVO.setFuncUrl( (String) rs.get("FUNC_URL"));
			voList.add(userFuncVO);
		}
		return voList;
	}
	
	public PageObject<UserFuncVO> queryUserWithoutFunc(int pageNo, int pageSize,String userAccount,String funcShowName,String funcName) {
		PageObject<UserFuncVO> pageVO = new PageObject<UserFuncVO>();
		String sql="";
		sql+="SELECT C.FUNC_ID,C.FUNC_NAME,C.FUNC_SHOW_NAME,C.GROUP,C.UPDATE_TIME FROM MMC.T_FUNC C WHERE C.ACTION_FLAG<>'D' ";
		sql+=" AND NOT EXISTS(";
		sql+=" SELECT 1 FROM  MMC.T_USER_FUNC B WHERE C.FUNC_ID=B.FUNC_ID AND B.USER_ACCOUNT='"+userAccount+"'";
		sql+=" AND B.ACTION_FLAG<>'D'";
		sql+=" ) AND NOT EXISTS(";
		sql+=" SELECT 1 FROM MMC.T_USER_ROLE A ,MMC.T_ROLE B,MMC.T_ROLE_FUNC D ";
		sql+=" WHERE A.USER_ACCOUNT='"+userAccount+"' AND A.ROLE_ID=B.ROLE_ID AND B.ROLE_ID=D.ROLE_ID ";
		sql+=" AND D.FUNC_ID=C.FUNC_ID  AND A.ACTION_FLAG<>'D'";
		sql+=" AND B.ACTION_FLAG<>'D' AND C.ACTION_FLAG<>'D' AND D.ACTION_FLAG<>'D'";
		sql+=")";
		if(!StringUtils.isEmpty(funcName)){
	    	sql+=" AND C.FUNC_NAME LIKE '%"+funcName+"%'";
	    }
	    if(!StringUtils.isEmpty(funcShowName)){
	    	sql+=" AND C.FUNC_SHOW_NAME LIKE '%"+funcShowName+"%'";
	    }
		int total = total(jdbcTemplate,sql);
		
		sql+=" ORDER BY `GROUP` ";
		sql+=" limit "+(pageNo*pageSize)+","+pageSize;
		List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
		
		List<UserFuncVO> volist = new ArrayList<UserFuncVO>();
		for( Map<String, Object> rs:results ){
			UserFuncVO userFuncVO = new UserFuncVO();
			userFuncVO.setFuncId( (int) rs.get("FUNC_ID"));
			userFuncVO.setFuncName( (String) rs.get("FUNC_NAME"));
			userFuncVO.setFuncShowName((String) rs.get("FUNC_SHOW_NAME"));
			userFuncVO.setAction("<a href='#' class='add'>[增加]</a>");
			volist.add(userFuncVO);
		}
		pageVO.setDatas(volist);
		pageVO.setTotalCount(total);
		return pageVO;
	}

	public void addUserFunc(int funcId, String userAccount, String actionUser) {
		String sql="";
		sql+="INSERT INTO MMC.T_USER_FUNC(USER_ACCOUNT, FUNC_ID, ACTION_FLAG, ACTION_USER,";
		sql+="UPDATE_TIME, CREATE_TIME)";
		sql+="VALUES(?,?,?,?,?,?)";
		jdbcTemplate.update(sql, userAccount,funcId,"I",actionUser,new Date(),new Date());
	}

	public void delUserFunc(int funcId, String userAccount, String actionUser) {
		String sql="";
		sql+="UPDATE MMC.T_USER_FUNC SET ACTION_FLAG=?,UPDATE_TIME=?,ACTION_USER=? WHERE FUNC_ID=? AND USER_ACCOUNT=?";
		jdbcTemplate.update(sql,"D",new Date(),actionUser,funcId,userAccount);
	}

	public PageObject<UserRoleVO> queryUserRole(int pageNo, int pageSize,
			String userAccount) {
		PageObject<UserRoleVO> pageVO = new PageObject<UserRoleVO>();
		String sql="SELECT A.ROLE_ID,A.ROLE_NAME ";
		sql+=" FROM MMC.T_USER_ROLE B ,MMC.T_ROLE A WHERE A.ROLE_ID=B.ROLE_ID AND B.USER_ACCOUNT=?";
		sql+=" AND A.ACTION_FLAG<>'D' AND B.ACTION_FLAG<>'D'";
		int total = total(jdbcTemplate, sql, userAccount);
		pageVO.setTotalCount(total);
		sql+=" ORDER BY B.UPDATE_TIME DESC ";
		sql+=" limit "+(pageNo*pageSize)+","+pageSize;
		List<Map<String, Object>> results = jdbcTemplate.queryForList(sql,userAccount);
		
		List<UserRoleVO> volist = new ArrayList<UserRoleVO>();
		for( Map<String, Object> rs:results ){
			UserRoleVO userRoleVO = new UserRoleVO();
			userRoleVO.setRoleId((int) rs.get("ROLE_ID"));
			userRoleVO.setRoleName( (String) rs.get("ROLE_NAME"));
			userRoleVO.setAction("<a href='#' class='del'>[删除]</a>");
			volist.add(userRoleVO);
		}
		pageVO.setDatas(volist);
		return pageVO;
	}

	public PageObject<UserRoleVO> queryUserWithoutRole(int pageNo,
			int pageSize, String userAccount) {
		PageObject<UserRoleVO> pageVO = new PageObject<UserRoleVO>();
		String sql="SELECT A.ROLE_ID,A.ROLE_NAME FROM MMC.T_ROLE A WHERE NOT EXISTS(";
		sql+=" SELECT 1 FROM MMC.T_USER_ROLE B WHERE A.ROLE_ID=B.ROLE_ID AND B.USER_ACCOUNT=?";
		sql+=" AND B.ACTION_FLAG<>'D') AND A.ACTION_FLAG<>'D'";
		int total = total(jdbcTemplate, sql, userAccount);
		pageVO.setTotalCount(total);
		sql+=" ORDER BY A.ROLE_NAME DESC ";
		sql+=" limit "+(pageNo*pageSize)+","+pageSize;
		List<Map<String, Object>> results = jdbcTemplate.queryForList(sql,userAccount);
		
		List<UserRoleVO> volist = new ArrayList<UserRoleVO>();
		for( Map<String, Object> rs:results ){
			UserRoleVO userRoleVO = new UserRoleVO();
			userRoleVO.setRoleId((int) rs.get("ROLE_ID"));
			userRoleVO.setRoleName( (String) rs.get("ROLE_NAME"));
			userRoleVO.setAction("<a href='#' class='add'>[分配]</a>");
			volist.add(userRoleVO);
		}
		pageVO.setDatas(volist);
		return pageVO;
	}

	public void delUserRole(int roleId, String userAccount, String actionUser) {
		String sql="";
		sql+="UPDATE MMC.T_USER_ROLE SET ACTION_FLAG=?,UPDATE_TIME=?,ACTION_USER=? WHERE ROLE_ID=? AND USER_ACCOUNT=?";
		jdbcTemplate.update(sql,"D",new Date(),actionUser,roleId,userAccount);
	}
	
	public void addUserRole(int roleId, String userAccount, String actionUser) {
		String sql="";
		sql+="INSERT INTO MMC.T_USER_ROLE(USER_ACCOUNT, ROLE_ID, UPDATE_TIME, CREATE_TIME,";
		sql+="ACTION_FLAG, ACTION_USER)";
		sql+="VALUES(?,?,?,?,?,?)";
		jdbcTemplate.update(sql, userAccount,roleId,new Date(),new Date(),"I",actionUser);
	}

}
