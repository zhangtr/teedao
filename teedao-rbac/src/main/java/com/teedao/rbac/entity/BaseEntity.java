package com.teedao.rbac.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * @Description: 实体基类，实体对象需要继承此对象
 * @author Leo Wu
 * @date 2016年10月24日  上午10:44:17
 * @version 1.0
 */
public class BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}

