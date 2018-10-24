package com.pr.amaz.dao.user;

import java.util.Date;

public class User {
	//用户主键
	private String userId;
	//用户名称
	private String userName;
	//用户性别
	private String userSex;
	//真实姓名
	private String reallyName;
	//创建时间
	private Date creatTime;
	//权限级别
	private String powerLevel;
	//角色名称
	private String roleName;
	//邮件地址
	private String email;
	//电话号码
	private String phone;
	//拓展字段1
	private String attribute1;
	//拓展字段2
	private String attribute2;
	//拓展字段3
	private String attribnte3;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getReallyName() {
		return reallyName;
	}
	public void setReallyName(String reallyName) {
		this.reallyName = reallyName;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public String getPowerLevel() {
		return powerLevel;
	}
	public void setPowerLevel(String powerLevel) {
		this.powerLevel = powerLevel;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAttribute1() {
		return attribute1;
	}
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}
	public String getAttribute2() {
		return attribute2;
	}
	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}
	public String getAttribnte3() {
		return attribnte3;
	}
	public void setAttribnte3(String attribnte3) {
		this.attribnte3 = attribnte3;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  "[userId=" + userId +
				  ",userName=" + userName +
				  ",userSex=" + userSex +
				  ",reallyName=" + reallyName +
				  ",creatTime=" + creatTime +
				  ",powerLevel=" + powerLevel +
				  ",roleName=" + roleName +
				  ",email=" + email +
				  ",phone=" + phone +
				  ",attribute1=" + attribute1 + 
				  ",attribute2=" + attribute2 +
				  ",attribnte3=" + attribnte3 +"]";
	}
	

}
