package com.hnmobile.wechat.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.hnmobile.wechat.mvc.model.PageObject;
import com.hnmobile.wechat.mvc.model.RoleFuncVO;
import com.hnmobile.wechat.mvc.model.RoleVO;

public class RoleDao extends JdbcBaseDao{

	public PageObject<RoleVO> queryRole(int pageNo, int pageSize,String roleId) {
		PageObject<RoleVO> pageVO = new PageObject<RoleVO>();
		String sql="";
		sql+="SELECT A.ROLE_ID,A.ROLE_NAME FROM LOTTERY.T_ROLE A WHERE A.ACTION_FLAG<>'D'";
		if(!StringUtils.isEmpty(roleId)){
			sql+=" AND A.ROLE_ID="+roleId;
		}
		int total = total(jdbcTemplate,sql);
		
		sql+=" ORDER BY UPDATE_TIME DESC";
		sql+=" limit "+(pageNo*pageSize)+","+pageSize;
		List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
		
		List<RoleVO> volist = new ArrayList<RoleVO>();
		for( Map<String, Object> rs:results ){
			RoleVO roleVO = new RoleVO();
			roleVO.setRoleId( (Integer) rs.get("ROLE_ID"));
			roleVO.setRoleName((String) rs.get("ROLE_NAME"));
			volist.add(roleVO);
		}
		pageVO.setDatas(volist);
		pageVO.setTotalCount(total);
		return pageVO;
	}
	
	public PageObject<RoleFuncVO> queryRoleFunc(int pageNo, int pageSize,String roleId,String funcShowName,String funcName) {
		
		PageObject<RoleFuncVO> pageVO = new PageObject<RoleFuncVO>();
		String sql="SELECT B.FUNC_ID,B.FUNC_NAME,B.FUNC_SHOW_NAME FROM LOTTERY.T_ROLE_FUNC A,LOTTERY.T_FUNC B ";
		sql+=" WHERE A.FUNC_ID=B.FUNC_ID AND A.ACTION_FLAG<>'D' AND B.ACTION_FLAG<>'D'";
		if(!StringUtils.isEmpty(roleId)){
			sql+=" AND A.ROLE_ID="+roleId;
		}
		if(!StringUtils.isEmpty(funcShowName)){
			sql+=" AND B.FUNC_SHOW_NAME like '%"+funcShowName+"%'";
		}
		if(!StringUtils.isEmpty(funcName)){
			sql+=" AND B.FUNC_NAME like '%"+funcName+"%'";
		}
		total(pageVO,jdbcTemplate,sql);
		sql+=" ORDER BY FUNC_SHOW_NAME";
		sql+=" limit "+(pageNo*pageSize)+","+pageSize;
		List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
		
		List<RoleFuncVO> volist = new ArrayList<RoleFuncVO>();
		for( Map<String, Object> rs:results ){
			RoleFuncVO roleVO = new RoleFuncVO();
			roleVO.setFuncId( (Integer) rs.get("FUNC_ID"));
			roleVO.setFuncName((String) rs.get("FUNC_NAME"));
			roleVO.setFuncShowName((String) rs.get("FUNC_SHOW_NAME"));
			roleVO.setAction("<a href='#' name='del' class='del'>[删除]</a>");
			volist.add(roleVO);
		}
		pageVO.setDatas(volist);
		return pageVO;
	}

	public PageObject<RoleFuncVO> queryRoleWithoutFunc(int pageNo,int pageSize, 
			String roleId,String funcShowName,String funcName) {
		PageObject<RoleFuncVO> pageVO = new PageObject<RoleFuncVO>();
		String sql="SELECT A.FUNC_ID,A.FUNC_NAME,A.FUNC_SHOW_NAME FROM LOTTERY.T_FUNC A ";
		sql+=" WHERE A.ACTION_FLAG<>'D'";
		if(!StringUtils.isEmpty(roleId)){
			sql+=" AND NOT EXISTS(";
			sql+=" SELECT 1 FROM LOTTERY.T_ROLE_FUNC B WHERE A.FUNC_ID=B.FUNC_ID AND B.ACTION_FLAG<>'D'";
			sql+=" AND B.ROLE_ID="+roleId+") ";
		}
		if(!StringUtils.isEmpty(funcShowName)){
			sql+=" AND A.FUNC_SHOW_NAME like '%"+funcShowName+"%'";
		}
		if(!StringUtils.isEmpty(funcName)){
			sql+=" AND A.FUNC_NAME like '%"+funcName+"%'";
		}
		total(pageVO,jdbcTemplate,sql);
		sql+=" ORDER BY FUNC_SHOW_NAME";
		sql+=" LIMIT "+(pageNo*pageSize)+","+pageSize;
		List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
		
		List<RoleFuncVO> volist = new ArrayList<RoleFuncVO>();
		for( Map<String, Object> rs:results ){
			RoleFuncVO roleVO = new RoleFuncVO();
			roleVO.setFuncId( (Integer) rs.get("FUNC_ID"));
			roleVO.setFuncName((String) rs.get("FUNC_NAME"));
			roleVO.setFuncShowName((String) rs.get("FUNC_SHOW_NAME"));
			roleVO.setAction("<a href='#' name='add' class='add'>[授权]</a>");
			volist.add(roleVO);
		}
		pageVO.setDatas(volist);
		return pageVO;
	}

	public void editRole(String roleId,String roleName){
		String sql="UPDATE LOTTERY.T_ROLE SET ROLE_NAME=?,UPDATE_TIME=?,ACTION_FLAG=? WHERE ROLE_ID=?";
		jdbcTemplate.update(sql, roleName,new Date(),"U",roleId);
	}

	public void addRole(String roleName) {
		String sql="INSERT INTO LOTTERY.T_ROLE(ROLE_NAME,UPDATE_TIME,CREATE_TIME,ACTION_FLAG)";
		sql+="VALUES(?,?,?,?)";
		jdbcTemplate.update(sql, roleName,new Date(),new Date(),"I");
	}

	public void delRole(String roleId) {
		String sql="UPDATE LOTTERY.T_ROLE SET UPDATE_TIME=?,ACTION_FLAG=? WHERE ROLE_ID=?";
		jdbcTemplate.update(sql, new Date(),"D",roleId);
	}

	public void delRoleFunc(String roleId, String funcId) {
		String sql="UPDATE LOTTERY.T_ROLE_FUNC SET ACTION_FLAG=?,UPDATE_TIME=? WHERE ROLE_ID=? AND FUNC_ID=?";
		jdbcTemplate.update(sql,"D",new Date(),roleId,funcId);
	}

	public void addRoleFunc(String roleId, String funcId) {
		String sql="INSERT INTO LOTTERY.T_ROLE_FUNC(ROLE_ID,FUNC_ID,UPDATE_TIME,CREATE_TIME,ACTION_FLAG)";
		sql+="VALUES(?,?,?,?,?)";
		jdbcTemplate.update(sql,roleId,funcId,new Date(),new Date(),"I");
	}

}
