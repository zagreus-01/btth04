package vn.iotstar.dao;

import vn.iotstar.entity.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> findAll();
    List<Category> findByUserId(int userId);
    Category findById(int id);
    void create(Category category);
    void update(Category category);
    void delete(int id);
}