package com.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.security.PrivateKey;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log implements Serializable {

    private String id;
    private String method;
    private String time;
    private String url;
    private String username;


    @Override
    public String toString() {
        return "Log{" +
                "id='" + id + '\'' +
                ", method='" + method + '\'' +
                ", time='" + time + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

}
