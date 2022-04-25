package com.rest.employee.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
	private Long roleId;
    private String roleName;
    private String roleCode;

}
