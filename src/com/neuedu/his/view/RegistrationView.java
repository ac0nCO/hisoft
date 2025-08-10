package com.neuedu.his.view;

import com.neuedu.his.controller.DepartmentController;
import com.neuedu.his.controller.DoctorController;
import com.neuedu.his.controller.RegistrationController;
import com.neuedu.his.model.BasePatient;
import com.neuedu.his.model.Department;
import com.neuedu.his.model.Doctor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 挂号员视图
 * @date:7/23
 *
 */
public class RegistrationView {
    private Scanner sc = new Scanner(System.in);
    private RegistrationController registrationController = new RegistrationController();
    private BasePatient basePatient = new BasePatient();
    private DepartmentController departmentController = new DepartmentController();
    private DoctorController doctorController = new DoctorController();
    static boolean exe = true;

    /**
     * 挂号员的登录页面
     */
    public void login() throws IOException {
        System.out.print("|+ 请输入挂号护士账号: |");
        //获取账号
        String account = sc.next();
        System.out.print("|+ 请输入挂号护士密码: |");
        //获取密码
        String password = sc.next();
        //将账号和密码作为参数传入对应的controller层，并进行对比和处理
        int result = registrationController.login(account,password);
        if (result == -1 || result ==-2){
            System.out.println("<---【警告】账号和密码为非法输入--->");
        }else if(result == 200){
            System.out.println("<-----------登录成功----------->");
            while (exe){
                showRegisterMenu();
            }
        }else {
            System.out.println("<--------【警告】登录失败-------->");
        }
    }
    //显示挂号人员主窗口
    public void showRegisterMenu() throws IOException {
        System.out.println("[-----------挂号人员功能菜单-----------]");
        System.out.println("            1.挂号操作                ");
        System.out.println("            0.退出到主菜单             ");
        System.out.println("            9.任意键退出系统           ");
        System.out.print("|+ 请输入您的选择: |");
        //获取输入内容
        String input = sc.next();
        switch (input){
            case "1":
                //挂号流程
                registerMenu();
                break;
            case "0":
                //退出到主菜单
                exe = false;
                break;
            default:
                //退出系统
                System.out.println("----------------祝您身体健康，再见---------------");
                System.exit(0);
                break;

        }
    }

    /**
     * 挂号的菜单
     * @throws IOException
     */
    private void registerMenu() throws IOException {
        //显示科室
        System.out.println("---------------科室如下:--------------");
        //通过
        ArrayList<Department> departlist = departmentController.findAllDepartment();
        //显示科室信息
        for(Department department : departlist){
            System.out.println("|--科室编号:"+department.getDid()+"\t 科室名字:"+department.getName()+"\t --|");
        }
        //提示用户选择科室
        System.out.print("|+ 请输入您选择的科室: |");
        int departChoose = sc.nextInt();
        //显示坐诊医生
        ArrayList<Doctor> doctorList = doctorController.findDoctorsByDid(departChoose);
        for (Doctor doctor : doctorList){
            System.out.println("|--工号:"+doctor.getId()+"\t 医生姓名:"+doctor.getName()+"\t 医生等级:"+doctor.getGrade()+
                    "\t 医生科室:"+doctor.getDepartment().getName()+"--|");
        }
        //提示用户选择坐诊医生
        String dr_id = "";
        while (doctorController.doctorChoose(dr_id,departChoose)==0){
            System.out.print("|+ 请输入您选择的医生工号: |");
            dr_id = sc.next();
            if (doctorController.doctorChoose(dr_id,departChoose)==0){
                System.out.println("|-【警告】您输入了错误的编号-|");
            }
        }
        //录入患者信息
        System.out.println("|-----请录入患者信息:----|");
        System.out.print("|+ 患者姓名: |");
        basePatient.setName(sc.next());
        System.out.print("|+ 患者年龄: |");
        basePatient.setAge(sc.next());
        System.out.print("|+ 患者性别: |");
        basePatient.setSex(sc.next());
        boolean isSuccess = registrationController.inputInfo(basePatient,"C:\\Users\\sun\\Desktop\\HISDB\\db_patient.txt",dr_id,departChoose);
        //提示成功或失败
        if (isSuccess){
            System.out.println("|--录入成功--|");
        }else {
            System.out.println("|--录入失败--|");
        }
    }
}
