package com.example.demo.server;


import com.example.demo.domain.DeptDO;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-27 14:28:36
 */
public interface DeptService {

    DeptDO get(String deptId);

    List<DeptDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(DeptDO sysDept);

    int update(DeptDO sysDept);

    int remove(String deptId);

    int batchRemove(String[] deptIds);

   /* Tree<DeptDO> getTree(Map<String, Object> map);*/

    boolean checkDeptHasUser(String deptId);

    List<DeptDO> getCurSubDepts(String deptid);

    List<String> getCurSubDeptIds(String deptid);

    List<DeptDO> getDeptIdsByLx(String type);
}
