package com.example.demo.server.impl;


import com.example.demo.dao.DeptDao;
import com.example.demo.domain.DeptDO;
import com.example.demo.server.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptDao sysDeptMapper;

	/*@Autowired
	private DictService dictService;*/

	@Override
	public DeptDO get(String deptId){
		return sysDeptMapper.get(deptId);
	}

	@Override
	public List<DeptDO> list(Map<String, Object> map){
		List<DeptDO> list=sysDeptMapper.list(map);
		/*for (DeptDO deptdo:list) {
			if(StringUtils.isNotBlank(deptdo.getDwxz())){
				deptdo.setDwxz(getDwxzText(deptdo.getDwxz()));
			}
		}*/
		return list;
	}

	public String getDwxzText(String dwxz) {
		String str="";
	/*	str=dictService.getName("YW_DWXZ", dwxz);*/
		return	str;
	}


	@Override
	public int count(Map<String, Object> map){
		return sysDeptMapper.count(map);
	}

	@Override
	public int save(DeptDO sysDept){
		sysDept.setDeptId(UUID.randomUUID().toString().replace("-",""));
		return sysDeptMapper.save(sysDept);
	}

	@Override
	public int update(DeptDO sysDept){
		return sysDeptMapper.update(sysDept);
	}

	@Override
	public int remove(String deptId){
		return sysDeptMapper.remove(deptId);
	}

	@Override
	public int batchRemove(String[] deptIds){
		return sysDeptMapper.batchRemove(deptIds);
	}

	/*@Override
	public Tree<DeptDO> getTree(Map<String, Object> param) {
		List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
		List<DeptDO> sysDepts = sysDeptMapper.list(param);
		for (DeptDO sysDept : sysDepts) {
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(sysDept.getDeptId().toString());
			tree.setParentId(sysDept.getParentId().toString());
			tree.setText(sysDept.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("dwxz",sysDept.getDwxz());
			tree.setAttributes(map);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<DeptDO> t = BuildTree.build(trees);
		return t;
	}*/

	@Override
	public boolean checkDeptHasUser(String deptId) {
		// TODO Auto-generated method stub
		//查询部门以及此部门的下级部门
		int result = sysDeptMapper.getDeptUserNumber(deptId);
		return result==0?true:false;
	}

	/**
	 * 获取当前部门及子部门
	 * @param deptid 当前部门ID
	 * @return
	 */
	@Override
	public List<DeptDO> getCurSubDepts(String deptid){

		List<DeptDO> targetList = new ArrayList<DeptDO>();
		List<DeptDO> allList = null;
		HashMap map = new HashMap();

		allList = list(map);
		DeptDO curDept = get(deptid);
		targetList.add(curDept);

		getSubDepts(deptid,allList,targetList);
		return  targetList;
	}

	/**
	 * 递归获取子部门
	 * @param deptid
	 * @param allList 全部部门列表
	 * @param targetList 子部门列表
	 */
	public void getSubDepts(String deptid,List<DeptDO> allList,List<DeptDO> targetList){

		if(deptid == null){
			return ;
		}
		Iterator it = allList.iterator();
		while(it.hasNext()){
			DeptDO deptDO = (DeptDO)it.next();
			if(deptDO == null){
				continue;
			}
			if(deptid.equals(deptDO.getParentId())){
				targetList.add(deptDO);
				getSubDepts(deptDO.getDeptId(),allList,targetList);
			}
		}
	}

	/**
	 * 获取当前部门及子部门ID
	 * @param deptid
	 * @return
	 */
    @Override
	public List<String> getCurSubDeptIds(String deptid) {
		List<DeptDO> partList = getCurSubDepts(deptid);
		List<String> targetList = new ArrayList<>();
		for(DeptDO dd : partList){
			if(dd!=null){
				targetList.add(dd.getDeptId());
			}
		}
		return  targetList;
	}

	@Override
	public List<DeptDO> getDeptIdsByLx(String type) {
		return sysDeptMapper.getDeptIdsByLx(type);
	}
}
