package com.tool.plaform.entity;

public class UserQuery
{
    /**登录名称*/
    private String loginName;
    
    /**登录密码*/
    private String password;

    /**是否允许登录*/
    private Boolean enable;

    /**注册开始时间*/
    private String createStartTime;
    
    /**注册结束时间*/
    private String createEndTime;
    
	public String getLoginName()
	{
		return loginName;
	}

	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}
	
	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Boolean getEnable()
	{
		return enable;
	}

	public void setEnable(Boolean enable)
	{
		this.enable = enable;
	}

	public String getCreateStartTime()
	{
		return createStartTime;
	}

	public void setCreateStartTime(String createStartTime)
	{
		this.createStartTime = createStartTime;
	}

	public String getCreateEndTime()
	{
		return createEndTime;
	}

	public void setCreateEndTime(String createEndTime)
	{
		this.createEndTime = createEndTime;
	}

	@Override
	public String toString()
	{
		return "UserQuery [loginName=" + loginName + ", password=" + password + ", enable=" + enable
				+ ", createStartTime=" + createStartTime + ", createEndTime=" + createEndTime + "]";
	}
	
}
