package com.neuedu.his.view;



import com.neuedu.his.controller.DrInpatientController;
import com.neuedu.his.controller.SickBedController;
import com.neuedu.his.db.DataBase;
import com.neuedu.his.model.SickBed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 住院部医生视图层
 * 作者：王思汉
 * date:7/26
 */
public class DrInpatientView {
    private DrInpatientController drInpatientController=new DrInpatientController();
    private SickBedController sickBedController=new SickBedController();
    private Scanner scanner=new Scanner(System.in);
    //定义住院部护士登录部分视图层
    public  void login() throws IOException {
        System.out.println("------------------------------------------");
        System.out.println("      ***【住院部护士之登录】***");
        System.out.println("---------请输入住院部护士账号---");
        String account= scanner.next();
        System.out.println("---------请输入住院部护士密码---");
        String password= scanner.next();
        //将账号密码作为参数转入controller层，让他进行对比处理
        int result= drInpatientController.login(account,password);
        if(result==-1||result==-2){
            System.out.println("--警告--账号密码非法输入，请按要求输入");
        }
        else if(result==200){
            System.out.println("     ！！！登录成功！！！");
            //调用住院部护士操作主菜单
            showDrInpatientMenu();
        }
        else{
            System.out.println("！！！登录失败，账号密码错误！！！");
        }
    }
    //定义住院部护士操作主菜单视图层
    private void showDrInpatientMenu() throws IOException {
        //System.out.println("------------------------------------------");
        System.out.println("*******住院部护士-功能菜单*******");
        System.out.println("|        1、查询床位操作");//查询床位编号的占有信息与病人信息
        System.out.println("|        2、删除床位操作");//病人出院，该床位编号由占用状态改为空
        System.out.println("|        3、修改床位操作");//将该为病人的床位编号由“1”改为“2"(两种情况占->空 互换)
        System.out.println("|        4、添增床位操作");//给该为患者一个床位编号，与住院时长
        System.out.println("|        0、退出到主菜单");
        System.out.println("|        9、任意键退出到系统");
        System.out.println("|--------请输入你的选择:");
        String input = scanner.next();
        switch (input) {
            case "1":
                CheckMenu();
                //调用查找方法视图层
                showDrInpatientMenu();
                //操作后返回
                break;
            case "2":
                DeleteMenu();
                //调用删除方法视图层
                showDrInpatientMenu();
                //操作后返回
                break;
            case "3":
                ChangeMenu();
                //调用修改方法视图层
                showDrInpatientMenu();
                //操作后返回
                break;
            case "4":
                AdditionMenu();
                //调用增添方法视图层
                showDrInpatientMenu();
                //操作后返回
                break;
            case "0":
                break;
            default:
                System.out.println("退出成功，祝您身体健康，再见！");
                System.exit(0);
                break;
        }
    }
    //定义查找方法视图层
    private void CheckMenu() throws IOException {
        System.out.println("------------------------------------------");
        System.out.println("          -**床位信息如下**-");
        SickBedController sickBedController=new SickBedController();
        for (SickBed sickBed: DataBase.sickBedTable)
        {
            System.out.println("-----床位编号"+sickBed.getNumber()+"\t床位状态："+sickBed.getState()+"-----");
        }
        System.out.print("------如您要查看床位具体信息，请输入床位编号：");
        String number=scanner.next();
        //调用Controller层方法
        System.out.println(sickBedController.findTheSickbed(number));
    }
    //定义删除方法视图层
    private void DeleteMenu() throws IOException {
        SickBedController sickBedController=new SickBedController();
        System.out.println("------------------------------------------");
        System.out.println("------请输入您要取消的床位：");
        String choose=scanner.next();
        System.out.println("------请输入当前病人id：");
        String id=scanner.next();
        //调用Controller层方法
        System.out.println(sickBedController.deleteSickbed(choose,id));
    }
    //定义修改方法视图层
    private void ChangeMenu() throws IOException {
        SickBedController sickBedController=new SickBedController();
        System.out.println("------------------------------------------");
        System.out.println("------请输入要修改的床位编号：");
        String choose = scanner.next();
        System.out.println("------请输入该床位病人id：");
        String id=scanner.next();
        System.out.println("------请输入要改到床位编号：");
        String after = scanner.next();
        //调用Controller层方法
        System.out.println(sickBedController.changeSickbed(choose,id,after));
    }
    //定义增添方法视图层
    private void AdditionMenu() throws IOException {
        SickBedController sickBedController=new SickBedController();
        System.out.println("------------------------------------------");
        System.out.println("------请输入要添增的床位编号：");
        String choose=scanner.next();
        System.out.println("------请输入住院患者id：");
        String id=scanner.next();
        System.out.println("------请输入住院时长：");
        String time=scanner.next();
        //调用Controller层方法
        System.out.println(sickBedController.additionSickbed(choose,id,time));
    }
}
