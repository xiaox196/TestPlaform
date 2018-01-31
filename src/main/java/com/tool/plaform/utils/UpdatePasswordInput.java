package com.tool.plaform.utils;

public class UpdatePasswordInput
{
    /**旧登录密码*/
	private String oldPassword;
    
    /**新登录密码*/
	private String newPassword;

	public String getOldPassword()
	{
		return oldPassword;
	}

	public void setOldPassword(String oldPassword)
	{
		this.oldPassword = oldPassword;
	}

	public String getNewPassword()
	{
		return newPassword;
	}

	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}

	@Override
	public String toString()
	{
		return "UpdatePasswordInput [oldPassword=" + oldPassword + ", newPassword=" + newPassword + "]";
	}
}
