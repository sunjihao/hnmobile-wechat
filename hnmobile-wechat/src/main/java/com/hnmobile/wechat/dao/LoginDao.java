package com.hnmobile.wechat.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hnmobile.wechat.mvc.SpringContext;
import com.hnmobile.wechat.mvc.model.UserLoginLogVO;
import com.hnmobile.wechat.mvc.model.UserPasswdResetLogVO;
import com.hnmobile.wechat.mvc.model.UserVO;

public class LoginDao extends JdbcBaseDao{

	public UserVO getUserByName(String userAccount ){
		
		String sql="SELECT T.USER_ACCOUNT,T.PASSWD,T.UPDATE_TIME,T.CREATE_TIME,T.LOCKED,B.ROLE_ID ";
		sql+=" FROM MMC.T_USER T,MMC.T_USER_ROLE B WHERE T.USER_ACCOUNT=B.USER_ACCOUNT AND T.USER_ACCOUNT=?";
		List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, userAccount);
		
		UserVO uvo = new UserVO();
		for( Map<String, Object> rs:results ){
			String passwd = (String) rs.get("PASSWD");
			Date updateTime = (Date) rs.get("UPDATE_TIME");
			Date createTime = (Date) rs.get("CREATE_TIME");
			String lock = (String) rs.get("LOCKED");
			int roleId = (int) rs.get("ROLE_ID");
			
			uvo.setPasswd(passwd);
			uvo.setUpdateTime(updateTime);
			uvo.setCreateTime(createTime);
			uvo.setUserAccount(userAccount);
			uvo.setLock(lock);
			uvo.setRoleId(roleId);
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
		sql+="  LOCKED =  ? ";
		sql+="WHERE USER_ACCOUNT =  ?";
		
		jdbcTemplate.update(sql, 
				uvo.getPasswd(),uvo.getUpdateTime(),
				uvo.getCreateTime(),uvo.getUserAccount(),uvo.getLock());
		
	}
	
	public void inserLoginLog(UserLoginLogVO userLoginLogVO ){
	    String sql="INSERT INTO MMC.T_USER_LOGIN_LOG";
	    sql+="(USER_ACCOUNT,LOGIN_IP,LOGIN_TIME,LOGIN_ERROR_TIME,LAST_LOGIN_RESULT,LOGIN_DEVICE_ID,ACTION_FLAG)";
	    sql+="VALUES(?,?,?,?,?,?,'I')";
	    
	    jdbcTemplate.update(sql,userLoginLogVO.getUserAccount(),
	    		userLoginLogVO.getLoginIp(),userLoginLogVO.getLoginTime(),userLoginLogVO.getLoginErrorTime(),
	    		userLoginLogVO.getLastLoginResult(),userLoginLogVO.getLoginDeviceId());
	    
	}
	
	public void insertResetPasswdLog(UserPasswdResetLogVO userPasswdResetLogVO ){
        String sql="INSERT INTO MMC.T_USER_PASSWD_RESET_LOG";
        sql+="(USER_ACCOUNT,RESET_IP,RESET_DEVICE_ID,RESET_TIME,RESET_ERROR_TIME,RESET_RESULT,ACTION_FLAG)";
        sql+="VALUES(?,?,?,?,?,?,'I')";
        
        jdbcTemplate.update(sql,userPasswdResetLogVO.getUserAccount(),userPasswdResetLogVO.getResetIp(),
                userPasswdResetLogVO.getResetDeviceId(),userPasswdResetLogVO.getResetTime(),
                userPasswdResetLogVO.getResetErrorTime(),userPasswdResetLogVO.getResetResult());
        
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
	    sql+=",LOGIN_DEVICE_ID FROM MMC.T_USER_LOGIN_LOG WHERE USER_ACCOUNT=? ORDER BY LOGIN_TIME DESC  ";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, userAccount);
		for( Map<String, Object> rs:results ){
			UserLoginLogVO uvo = new UserLoginLogVO();
			int loginLogId = (int) rs.get("LOGIN_LOG_ID");
			String loginIP = (String) rs.get("LOGIN_IP");
			Date loginTime = (Date) rs.get("LOGIN_TIME");
			String loginDeviceId = getStringValue(rs.get("LOGIN_DEVICE_ID"));
			int loginErrorTime = (int) rs.get("LOGIN_ERROR_TIME");
			int lastLoginResult = (int) rs.get("LAST_LOGIN_RESULT");
			
			uvo.setLoginDeviceId(loginDeviceId);
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
	
	public UserPasswdResetLogVO getResetPasswdLog( String userAccount ){
        String sql="";
        sql+="SELECT RESET_LOG_ID,USER_ACCOUNT,RESET_IP,RESET_DEVICE_ID,RESET_TIME,RESET_ERROR_TIME ";
        sql+=",RESET_RESULT,ACTION_FLAG FROM MMC.T_USER_PASSWD_RESET_LOG WHERE USER_ACCOUNT=? ORDER BY RESET_TIME DESC  ";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, userAccount);
        for( Map<String, Object> rs:results ){
            UserPasswdResetLogVO uvo = new UserPasswdResetLogVO();
            int resetLogId = (int) rs.get("RESET_LOG_ID");
            userAccount = (String) rs.get("USER_ACCOUNT");
            String resetIp = (String) rs.get("RESET_IP");
            String resetDeviceId = (String)(rs.get("RESET_DEVICE_ID"));
            String resetResult = (String)rs.get("RESET_RESULT");
            Date resetTime = (Date) rs.get("RESET_TIME");
            int resetErrorTime = (int) rs.get("RESET_ERROR_TIME");
            
            uvo.setResetLogId(resetLogId);
            uvo.setUserAccount(userAccount);
            uvo.setResetIp(resetIp);
            uvo.setResetDeviceId(resetDeviceId);
            uvo.setResetTime(resetTime);
            uvo.setResetErrorTime(resetErrorTime);
            uvo.setResetResult(resetResult);
            return uvo;
        }
        return null;
    }
	
	public static void main( String[]args ){
		LoginDao dao = SpringContext.ctx.getBean("loginDao", LoginDao.class);
		UserVO uvo = dao.getUserByName("admin");
		System.out.println(uvo);
	}
	
}
