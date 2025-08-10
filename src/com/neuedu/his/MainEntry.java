package com.neuedu.his;

import com.neuedu.his.controller.TestController;
import com.neuedu.his.db.DataBase;
import com.neuedu.his.view.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 程序的主入口
 * @date:07/23
 *作者
 */
public class MainEntry {

    //成员变量
    private static Scanner sc = new Scanner(System.in);
    //创建挂号员窗口对象
    RegistrationView registrationView = new RegistrationView();
    //创建医生窗口对象
    DoctorView doctorView = new DoctorView();
    PharmcySystemView pharmcySystemView = new PharmcySystemView();
    DrInpatientView drInpatientView = new DrInpatientView();
    TestView testView = new TestView();
    PayView payView = new PayView();


    /**
     * 主方法：程序的唯一入口
     * @param args
     */
    public static void main(String[] args) throws IOException {
        System.out.println("\n" +
                "  _    _  _____   _____ \n" +
                " | |  | ||_   _| / ____|\n" +
                " | |__| |  | |  | (___  \n" +
                " |  __  |  | |   \\___ \\ \n" +
                " | |  | | _| |_  ____) |\n" +
                " |_|  |_||_____||_____/ \n" );
        try {
            Thread.sleep(2000);
        }catch (Exception e){

        }
        System.out.println("---------------------------------------------");
        System.out.println("|             欢迎进入HIS云医院系统             |");
        System.out.println("                 Version:1.0                ");
        MainEntry entry = new MainEntry();
        while (true){
            entry.mainMenu();
        }
    }

    /**
     * 主菜单
     */
    public void mainMenu() throws IOException {
        System.out.println("---------------------------------------------");
        System.out.println("|                请选择您的身份:               |");
        System.out.println("                  1.挂号收费员                 ");
        System.out.println("                  2.门诊医生                  ");
        System.out.println("                  3.药房                      ");
        System.out.println("                  4.住院部                    ");
        System.out.println("                  5.化验检查科                 ");
        System.out.println("                  6.收费部                     ");
        System.out.println("                  0.退出系统                   ");
        System.out.println("---------------------------------------------");
        System.out.print("|+ 请输入您的选择: |");
        try {
            int choose = sc.nextInt();
            switch (choose){
                case 1://挂号员登录
                    //业务逻辑
                    registrationView.login();
                    break;
                case 2://医生登录
                    doctorView.login();
                    break;
                case 3://药房
                    pharmcySystemView.login();
                    break;
                case 4://住院部
                    drInpatientView.login();
                    break;
                case 5://化验检查
                    testView.login();
                    break;
                case 6://收费
                    payView.login();
                    break;

                case 0://退出
                    System.out.println("----------------祝您身体健康，再见--------------");
                    System.exit(0);
                    break;
                default:
                    System.out.println("---------您当前输入的菜单不存在，请重新选择---------");
                    break;
            }
        }catch (Exception e){
            sc.nextLine();
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println("-------------您当前非法输入,请重新选择------------");
        }
    }
}
