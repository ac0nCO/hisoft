package com.neuedu.his.controller;

import com.neuedu.his.db.DataBase;
import com.neuedu.his.model.Department;

import java.util.ArrayList;

/**
 * 科室操作类
 */
public class DepartmentController {
    /**
     *查询所有科室信息
     */
    public ArrayList<Department> findAllDepartment(){
        return DataBase.departmentTable;
    }

    /**
     * 根据部门id，查找部门
     * @param dp_id
     * @return
     */
    public Department findDepartmentById(int dp_id){
        Department department = new Department();
        for (Department department1 : DataBase.departmentTable){
            if (department1.getDid()==dp_id){
                department = department1;
            }
        }
        return department;
    }
}
