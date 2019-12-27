package com.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.EAN;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    private String id;
    private String roleName;
    private Integer status;
    private List<Permission> permissions;

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", roleName='" + roleName + '\'' +
                ", status=" + status +
                ", permissions=" + permissions +
                '}';
    }
}
