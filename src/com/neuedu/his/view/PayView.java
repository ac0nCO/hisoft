package com.neuedu.his.view;



import com.neuedu.his.controller.PatientController;
import com.neuedu.his.controller.PayController;
import com.neuedu.his.db.DataBase;
import com.neuedu.his.model.BasePatient;

import java.util.ArrayList;
import java.util.Scanner;

public class PayView {
    private Scanner scanner=new Scanner(System.in);
    private BasePatient basePatient=new BasePatient();
    private PatientController patientController=new PatientController();
    private PayController payController=new PayController();
    static boolean exe=true;

    /**
     * 收费员登录
     */
    public void login(){
        System.out.println("|--请输入收费员账号：|");//获取账号
        String account=scanner.next();
        System.out.println("|--请输入收费员密码：|");//获取密码
        String password=scanner.next();
        int result = PayController.login(account, password);
        if (result == -1 || result == -2) {
            System.out.println("<---【警告】账号和密码为非法输入--->");
        } else if (result == 200) {
            System.out.println("<-----------登录成功----------->");
            while (exe){
                PaymentMenu();
            }
        } else {
            System.out.println("<--------【警告】登录失败-------->");
        }
    }

    /**
     * 收费菜单
     */
    public  void PaymentMenu(){
        System.out.println("--------缴费菜单--------");
        System.out.println("     1.查看待缴费病人     ");
        System.out.println("      0.退出到主菜单      ");
        System.out.println("     9.任意键退出系统     ");
        //获取输入内容
        String input=scanner.next();
        switch (input) {
            case "1":
                //查看缴费名单
                PatientPayment();
                break;
            case "0":
                //退出到主菜单
                exe = false;
                break;
            default:
                System.out.println("---------祝您身体健康，再见---------");
                System.exit(0);
                break;
        }
    }

    /**
     * 查看待缴费病人
     */
    public void PatientPayment() {
        //显示缴费名单
        System.out.println("-------待缴费病人名单--------");

        for (BasePatient basePatient : DataBase.basePatientTable) {
            System.out.println("|--患者编号:" + basePatient.getId() + "|--患者姓名:" + basePatient.getName());
        }
        System.out.println("|----选择病人id查看缴费金额:");
        String id=scanner.next();
            Payment(id);
    }
    public void Payment(String id){
        PayController payController=new PayController();
        System.out.println("|-----该病人需要缴费:"+payController.Pay(id)+"----|");
        System.out.println("|----请选择下一步操作-----|");
        System.out.println("|----1.缴费完成-----|");
        System.out.println("|----2.返回缴费名单--|");
        String choose=scanner.next();
        switch (choose){
            case "1":
                System.out.println("|----该病人已缴费,感谢工作-----|");
                break;
            case "2":
               PatientPayment();
            }
        }




}
