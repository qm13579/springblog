package com.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Blog {

    private String id;
    private String title;
    private UserInfo userInfo;
    private Date date;
    private String context;
    private Integer status;             //0：草稿 1：发布 2：下架
//    private List<UserInfo> likesList;  //点赞
//    private List<UserInfo> markerList; //收藏

    @Override
    public String toString() {
        return "Blog{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", userInfo=" + userInfo +
                ", date=" + date +
                ", context='" + context + '\'' +
                ", status=" + status +
                '}';
    }
}
