package com.blog.mapper.base;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public interface BaseMapper {

    void save(Entity t);

    List<Entity> findAll();

    Entity findById();

    void delete(String id);
}
