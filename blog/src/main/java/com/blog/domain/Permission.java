package com.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.DocFlavor;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission  implements Serializable {

    private String id;
    private String permissionName;
    private String url;

    private List<Role> roles;

}
