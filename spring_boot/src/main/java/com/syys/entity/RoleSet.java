package com.syys.entity;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleSet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mid;
	private String role_set;
}
