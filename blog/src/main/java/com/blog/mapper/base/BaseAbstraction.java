package com.blog.mapper.base;

import javax.swing.text.html.parser.Entity;
import java.util.List;

abstract class BaseAbstraction  implements  BaseMapper{

    @Override
    public void save(Entity t) {

    }

    @Override
    public List<Entity> findAll() {
        return null;
    }

    @Override
    public Entity findById() {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
