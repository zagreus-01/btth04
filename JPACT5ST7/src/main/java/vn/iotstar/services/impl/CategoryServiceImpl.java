package vn.iotstar.services.impl;

import java.util.List;

import vn.iotstar.dao.impl.CategoryDaoImpl;
import vn.iotstar.entity.Category;
import vn.iotstar.services.CategoryService;
import vn.iotstar.dao.CategoryDao;

public class CategoryServiceImpl implements CategoryService {
	
	CategoryDao category = new CategoryDaoImpl();

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return category.findAll();
	}

	@Override
	public void create(Category category) {
		// TODO Auto-generated method stub
		this.category.create(category);
		
		
	}

	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		this.category.update(category);
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		try {
			category.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Category findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
