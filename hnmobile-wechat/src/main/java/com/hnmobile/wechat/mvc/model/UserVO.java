package com.hnmobile.wechat.mvc.model;

import java.util.Date;
import java.util.List;

public class UserVO {
	
	private String userAccount;
	private String passwd;
	private Date updateTime;
	private Date registerTime;
	private String registerTimeStr;
	private Date createTime;
	private String lock;
	private String roleName;
	private int roleId;
	private String sex;
	private String sexName;
	private String tel;
	private String userCname;
	private String birthday;
	private String birthdayType;
	private String birthdayTypeName;
	private List<String> merchantAccount;

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public List<String> getMerchantAccount() {
		return merchantAccount;
	}

	public void setMerchantAccount(List<String> merchantAccount) {
		this.merchantAccount = merchantAccount;
	}

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserCname() {
        return userCname;
    }

    public void setUserCname(String userCname) {
        this.userCname = userCname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRegisterTimeStr() {
        return registerTimeStr;
    }

    public void setRegisterTimeStr(String registerTimeStr) {
        this.registerTimeStr = registerTimeStr;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthdayType() {
        return birthdayType;
    }

    public void setBirthdayType(String birthdayType) {
        this.birthdayType = birthdayType;
    }

    public String getBirthdayTypeName() {
        return birthdayTypeName;
    }

    public void setBirthdayTypeName(String birthdayTypeName) {
        this.birthdayTypeName = birthdayTypeName;
    }

}
