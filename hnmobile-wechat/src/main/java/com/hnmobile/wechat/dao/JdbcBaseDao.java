package com.hnmobile.wechat.dao;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hnmobile.wechat.mvc.SpringContext;
import com.hnmobile.wechat.mvc.model.PageObject;

public class JdbcBaseDao {

    protected JdbcTemplate jdbcTemplate;
    
    public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

	public void setDataSource( DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public void test(){
		String sql="SELECT 1 CT";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		System.out.println( map.get("CT") );
	}
	
	public int total( String sql,Object... param ){
		return jdbcTemplate.queryForInt(sql,param);
	}
	
	public int total( JdbcTemplate jdbcTemplate,String sql,Object... param ){
		sql=sql.replaceFirst("SELECT.+?FROM", "SELECT COUNT(1) FROM");
		return total(sql,param);
	}
	
	public void total( PageObject<?> pageObject,JdbcTemplate jdbcTemplate,String sql,Object... param ){
		sql=sql.replaceFirst("SELECT.+?FROM", "SELECT COUNT(1) FROM");
		int total=total(sql,param);
		pageObject.setTotalCount(total);
	}
	
	protected String getMemberSelects(){
		String sql=" T_MEMBER.MEMBER_ACCOUNT,T_MEMBER.MEMBER_NAME,T_MEMBER.BIRTHDAY,";
		sql+=" T_MEMBER.BIRTHDAY_TYPE,T_MEMBER.REGISTER_TIME,T_MEMBER.TEL,T_MEMBER.SEX,";
		sql+=" T_MEMBER.UPDATE_TIME,T_MEMBER.CREATE_TIME,T_MEMBER.ACTION_FLAG,T_MEMBER.ACTION_USER ";
		return sql;
	}
	
	protected String getMerchantSelects(){
		String sql=" T_MERCHANT.MERCHANT_ACCOUNT,T_MERCHANT.MERCHANT_NAME,T_MERCHANT.MERCHANT_SHORT_NAME,T_MERCHANT.MERCHANT_ADDR,T_MERCHANT.MERCHANT_TEL,";
		sql+=" T_MERCHANT.MERCHANT_DESCRIBE,T_MERCHANT.CATALOG_CODE,T_MERCHANT.CATALOG_NAME,T_MERCHANT.UPDATE_TIME,T_MERCHANT.CREATE_TIME,T_MERCHANT.MEMBER_NEED_APP ";
		return sql;
	}
	
	protected String getMemberCardSelects(){
		String sql="";
		sql+=" T_MEMBER_CARD.MEMBER_CARD_ID,T_MEMBER_CARD.MEMBER_ACCOUNT,T_MEMBER_CARD.MERCHANT_ACCOUNT,T_MEMBER_CARD.CARD_TYPE,";
		sql+=" T_MEMBER_CARD.CARD_PIC_SIDE1_URL,T_MEMBER_CARD.CARD_PIC_SIDE2_URL,T_MEMBER_CARD.CARD_STATUS,T_MEMBER_CARD.CONSUME_SUM,T_MEMBER_CARD.POINT,T_MEMBER_CARD.CARD_VALID_DATE,";
		sql+=" T_MEMBER_CARD.GET_CARD_TIME,T_MEMBER_CARD.SEND_CARD_USER,T_MEMBER_CARD.ACTION_USER,T_MEMBER_CARD.ACTION_FLAG,";
		sql+=" T_MEMBER_CARD.UPDATE_TIME,T_MEMBER_CARD.CREATE_TIME,T_MEMBER_CARD.MEMBER_CLASS,T_MEMBER_CARD.CURRENT_POINT, ";
		sql+=" T_MEMBER_CARD.MEMBER_CARD_NUMBER";
		return sql;
	}
	
	protected String getAutionSelects(){
		String sql="";
		sql+=" T_AD_AUTION.AD_AUTION_ID,T_AD_AUTION.MEMBER_ACCOUNT,T_AD_AUTION.MERCHANT_ACCOUNT,T_AD_AUTION.MERCHANT_ALIAS,T_AD_AUTION.MERCHANT_CATALOG_NAME, ";
		sql+=" T_AD_AUTION.MEMBER_CARD_CONSUME_SUM,T_AD_AUTION.MEMBER_CARD_POINT,T_AD_AUTION.IS_VYING,";
		sql+=" T_AD_AUTION.MEMBER_CONSUME_SUM,T_AD_AUTION.MEMBER_CLASS,T_AD_AUTION.MEMBER_FEATURE,T_AD_AUTION.MERCHANT_AUTION_PRICE, ";
		sql+=" T_AD_AUTION.MERCHANT_AUTION_TIME,T_AD_AUTION.MERCHANT_AUTION,T_AD_AUTION.VYING_MERCHANT_ACCOUNT,";
		sql+=" T_AD_AUTION.VYING_MERCHANT_NAME,T_AD_AUTION.VYING_MERCHANT_CATALOG,T_AD_AUTION.AD_PERIODS_BEGIN, ";
		sql+=" T_AD_AUTION.AD_PERIODS_END,T_AD_AUTION.SYS_RECOMMEND_PRICE,T_AD_AUTION.SYS_RECOMMEND_TIME,T_AD_AUTION.UPDATE_TIME,";
		sql+=" T_AD_AUTION.CREATE_TIME,T_AD_AUTION.ACTION_FLAG,T_AD_AUTION.ACTION_USER,T_AD_AUTION.AUTION_STATUS ";
		return sql;
	}
	
	protected String getVyingSelects(){
		String sql="";
		sql+=" T_AD_VYING.AD_VYING_ID, T_AD_VYING.VYING_PRICE,T_AD_VYING.AD_CONTENT,T_AD_VYING.VYING_TIME ";
		return sql;
	}
	
	protected String getMerchantCardSelects(){
		String sql=" T_MERCHANT_CARD.MERCHANT_CARD_ID,";
		sql+=" T_MERCHANT_CARD.CARD_SIDE1_PIC_URL,T_MERCHANT_CARD.CARD_SIDE2_PIC_URL ";
		return sql;
	}
	
	protected String getMerchantCardUpgradeRuleSetSelects(){
		String sql=" T_MERCHANT_CARD_UPGRADE_RULE.POINT_THRESHOLD";
		return sql;
	}
	
	protected String getCardTypeSelects(){
		String sql=" T_CARD_TYPE.CARD_TYPE,T_CARD_TYPE.CARD_TYPE_NAME ";
		return sql;
	}
	
	protected String getMemberCardPointRewardSelects(){
		String sql="";
		sql+=" T_MEMBER_CARD_POINT_REWARD.POINT_REWARD_ID, T_MEMBER_CARD_POINT_REWARD.MEMBER_ACCOUNT, T_MEMBER_CARD_POINT_REWARD.MERCHANT_ACCOUNT,";
		sql+=" T_MEMBER_CARD_POINT_REWARD.ORIGINAL_POINT, T_MEMBER_CARD_POINT_REWARD.LEFT_POINT, T_MEMBER_CARD_POINT_REWARD.REWARD_POINT, T_MEMBER_CARD_POINT_REWARD.REWARD_DETAIL,";
		sql+=" T_MEMBER_CARD_POINT_REWARD.REWARD_DATETIME, T_MEMBER_CARD_POINT_REWARD.ACTION_USER, T_MEMBER_CARD_POINT_REWARD.ACTION_FLAG, T_MEMBER_CARD_POINT_REWARD.UPDATE_TIME, T_MEMBER_CARD_POINT_REWARD.CREATE_TIME";
	    return sql;
	}
	
	protected String getMerchantMsgSelects(){
		String sql="";
		sql+="T_AD_PUB.MERCHANT_MSG_ID, T_AD_PUB.MERCHANT_ACCOUNT, T_AD_PUB.MERCHANT_NAME,";
		sql+="T_AD_PUB.MSG_TITLE, T_AD_PUB.MSG_CONTENT, T_AD_PUB.MSG_TYPE,T_AD_PUB.STATUS,";
		sql+="T_AD_PUB.PUBLIC_SCOPE, T_AD_PUB.ACTION_FLAG,T_AD_PUB.ACTION_USER,T_AD_PUB.UPDATE_TIME,";
		sql+="T_AD_PUB.CREATE_TIME,T_AD_PUB.READ_PEPL_COUNT,T_AD_PUB.NOT_READ_PEPL_COUNT,";
		sql+="T_AD_PUB.BUY_MERCHANT_NAME,T_AD_PUB.BUY_MERCHANT_ACCOUNT";
	    return sql;
	}
	
	protected String getStringValue(Object value) {
	    if (value==null) return null;
	    return value+"";
	}
	
	public static void main( String[]args ){
		
		JdbcBaseDao dao = SpringContext.ctx.getBean("jdbcBaseDao", JdbcBaseDao.class);
		
		dao.test();
		
	}

//	public List<String> getUserMerchantAccounts() {
//		return userMerchantAccounts;
//	}
//
//	public void setUserMerchantAccounts(List<String> userMerchantAccounts) {
//		this.userMerchantAccounts = userMerchantAccounts;
//	}
}
