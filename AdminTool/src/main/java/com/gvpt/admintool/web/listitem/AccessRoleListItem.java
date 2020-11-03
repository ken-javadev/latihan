/*
 * Created on 4 Jul 2017 ( Time 14:21:41 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.gvpt.admintool.web.listitem;

import com.gvpt.admintool.bean.AccessRole;
import com.gvpt.admintool.web.common.ListItem;

public class AccessRoleListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public AccessRoleListItem(AccessRole accessRole) {
		super();
		this.value = "" + accessRole.getAccessRoleId();
		this.label = accessRole.getRoleTitle();
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String getLabel() {
		return label;
	}

}