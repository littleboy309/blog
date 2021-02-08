package com.heli.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description: blog 实体类
 * date: 2021/1/30 19:36
 * @author heli
 * @since JDK 1.8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Blog {

    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 首图
     */
    private String firstPicture;
    /**
     * 标签
     */
    private String flag = "原创";
    /**
     * 浏览次数
     */
    private Integer views;
    /**
     * 赞赏开关
     */
    private boolean appreciation;
    /**
     * 转载开关
     */
    private boolean shareStatement;
    /**
     * 评论开关
     */
    private boolean commentabled;
    /**
     * 发布开关
     */
    private boolean published;
    /**
     * 推荐开关
     */
    private boolean recommend;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 这个属性用来在mybatis中进行连接查询的
     */
    private Long typeId;
    private Long userId;

    /**
     * 获取多个标签的id
     */
    private String tagIds;

    private String description;

    /**
     * 与其他类关联属性
     */
    private Type type;

    private User user;

    private List<Tag> tags = new ArrayList<>();

    private List<Comment> comments = new ArrayList<>();

    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }

    /**
     * 将tags集合转换为tagIds字符串形式：“1,2,3”,用于编辑博客时显示博客的tag
     */
    private String tagsToIds(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for(Tag tag: tags){
                if(flag){
                    ids.append(",");
                }else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else {
            return tagIds;
        }
    }
}
