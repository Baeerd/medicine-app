package com.app.user.entity;

import com.app.common.entity.AbstractEntity;
import com.app.common.entity.Constant;

import java.math.BigDecimal;
import java.util.Date;

public class User extends AbstractEntity{

    private String username;

    private String password;

    private String sex;

    private String name;

    private String userType;
    
    private String userTypeView;

    private String email;

    private String phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
        this.userTypeView = Constant.dataConfigMap.get(Constant.USER_TYPE).get(userType);
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

	public String getUserTypeView() {
		return userTypeView;
	}
    
}
