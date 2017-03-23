package org.marsplatform.core.common.service;

import java.util.List;
import java.util.UUID;

import org.marsplatform.core.common.dao.JeecgBaseDao;
import org.marsplatform.core.util.ClassReflectUtils;
import org.marsplatform.core.common.dao.BasePage;


public abstract class BaseService<T>{
	
	public abstract JeecgBaseDao<T> getDao();

	
	public void add(T t)  throws Exception{
		//设置主键.字符类型采用UUID,数字类型采用自增
		ClassReflectUtils.setIdKeyValue(t,"id",UUID.randomUUID().toString());
		getDao().add(t);
	}
	
	public void update(T t)  throws Exception{
		getDao().update(t);
	}
	
	
	public void updateBySelective(T t){
		getDao().updateBySelective(t);
	}
	
	public void delete(Object... ids) throws Exception{
		if(ids == null || ids.length < 1){
			return;
		}
		for(Object id : ids ){
			getDao().delete(id);
		}
	}
	
	public int queryByCount(BasePage page)throws Exception{
		return getDao().queryByCount(page);
	}
	
	public List<T> queryByList(BasePage page) throws Exception{
		Integer rowCount = queryByCount(page);
		page.getPager().setRowCount(rowCount);
		return getDao().queryByList(page);
	}

	public T queryById(Object id) throws Exception{
		return getDao().queryById(id);
	}
}