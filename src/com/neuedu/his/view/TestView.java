package com.neuedu.his.view;

import com.neuedu.his.controller.DataBaseController;
import com.neuedu.his.controller.PatientController;
import com.neuedu.his.controller.TestController;
import com.neuedu.his.db.DataBase;
import com.neuedu.his.model.BasePatient;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author 周俊泽
 * @date 07/25
 *
 */



public class TestView {
    private Scanner scanner=new Scanner(System.in);
    private TestController testController=new TestController();
    private PatientController patientController=new PatientController();
    private BasePatient basePatient=new BasePatient();
    static boolean exe= true;

    /**
     * 化验员登录
     */
    public void login(){
        System.out.println("|--请输入化验员账号：|");//获取账号
        String account=scanner.next();
        System.out.println("|--请输入化验员密码：|");//获取密码
        String password=scanner.next();
        int resulte=testController.login(account,password);
        if (resulte==-1){
            System.out.println("【警告】账号密码非法输入");
        }else if (resulte==-2){
            System.out.println("111");
        }else if (resulte==200){
            //继续向下流程：显示挂号科室
            System.out.println("-------------------");
            System.out.println("-------登录成功------");
            ToTest();
            //examination();

        }else {
            //登录不成功
            System.out.println("----【警告】登录失败---");
        }
    }

    /**
     * 待化验患者
     */
    public void ToTest() {
        System.out.println("待检查患者列表:");
        TestController testController=new TestController();
        ArrayList<BasePatient> basePatients =testController.testPTList();
        for (BasePatient basePatient:basePatients){
            System.out.println(basePatient.getId()+" "+basePatient.getName());
        }
        System.out.println("|0.退出系统|");
        System.out.println("请输入患者编号:");
        String id = scanner.next();
        if ("0".equals(id)){
            System.exit(0);
        }
        TestProject(id,basePatients);

    }

    public  void TestProject(String id,ArrayList<BasePatient> basePatients){
        PatientController patientController=new PatientController();
        BasePatient basePatient = patientController.patientChoose(id,basePatients);
        patientController.testInfo(basePatient);
        System.out.println("|0.返回上级菜单|");
        System.out.println("请输入检查项目:");
        String testId =scanner.next();
        if ("0".equals(testId)){
            ToTest();
        }
        examination(testId,basePatient,basePatients);
    }
    /**
     * 化验结果录入
     */

    public void examination(String testId,BasePatient basePatient,ArrayList<BasePatient> basePatients){
        DataBaseController dataBaseController=new DataBaseController();
        System.out.println("------进行化验检查------");
        System.out.println("------输入"+ DataBase.testTable.get(Integer.parseInt(testId)-1).getName()+"结果");
        String test =scanner.next();
        basePatient.getTest().put(DataBase.testTable.get(Integer.parseInt(testId)-1),test);
        dataBaseController.db_Patient_fix(basePatient);
        DataBase.db_PatientUD();
        TestProject(basePatient.getId(),basePatients);

    }

}
