package com.example.demo.dao;


import com.example.demo.domain.DeptDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:35:39
 */
@Mapper
public interface DeptDao {

	DeptDO get(String deptId);
	
	List<DeptDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeptDO dept);
	
	int update(DeptDO dept);
	
	int remove(String deptId);
	
	int batchRemove(String[] deptIds);
	
	Long[] listParentDept();
	
	int getDeptUserNumber(String deptId);

    List<DeptDO> getDeptIdsByLx(String type);
}
