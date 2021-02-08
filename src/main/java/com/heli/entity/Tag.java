package com.heli.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/1/30 19:54
 *
 * @author heli
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tag {

    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();
}
