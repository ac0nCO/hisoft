package com.neuedu.his.view;
import com.neuedu.his.controller.MedicineController;
import com.neuedu.his.controller.PatientController;
import com.neuedu.his.controller.PharmcySystemViewController;
import com.neuedu.his.db.DataBase;
import com.neuedu.his.model.BasePatient;
import com.neuedu.his.model.Medicine;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @data 07-26
 * author:钱程
 * 药房护士登录系统及操作系统
 */
public class PharmcySystemView{
    MedicineController medicineController = new MedicineController();

    public MedicineController getMedicineController() {
        return medicineController;
    }

    private Scanner scanner=new Scanner(System.in);
        private PharmcySystemViewController pharmcySystemViewController=new PharmcySystemViewController();
        /**
         * 药房护士员登录界面
         */
        public void login() {
            //通过PharmcySystemViewController里的login实现进入
            System.out.println("|--请输入药房护士账号：|");//获取账号
            String account = scanner.next();
            System.out.println("|--请输入药房护士密码：|");//获取密码
            String password = scanner.next();
            //将账号和密码作为参数传入controller层，去对比和处理
            int resulte = pharmcySystemViewController.login(account, password);
            if (resulte == -1 || resulte == -2) {
                System.out.println("【警告】账号密码非法输入");
            } else if (resulte == 200) {
                //继续向下流程：显示挂号科室
                System.out.println("-------------------");
                System.out.println("-------登录成功------");
                showPharmacyDrMenu();
            } else {
                //登录不成功
                System.out.println("----【警告】登录失败---");

            }
        }
        //显示药房人员主窗口
        private void showPharmacyDrMenu(){
            System.out.println("-------药房护士：功能菜单------");
            System.out.println("     1.缴费操作");
            System.out.println("     2.查询药品信息");
            System.out.println("     0.任意键退出系统");
            System.out.println("|----请输入您的选择：|");
            // 功能菜单部分

            int choice = scanner.nextInt();
            scanner.nextLine();
            // 消耗掉nextInt之后的换行符

            switch (choice) {
                case 1:
                    ArrayList<BasePatient> basePatients = medicineController.medPTList();
                    if ("-200".equals(basePatients.get(0).getId())){
                        System.out.println("无人需要缴费");
                        break;
                        //调用表单，如果无人需要缴费，则输出”无人需要缴费“
                    }
                    for (BasePatient basePatient : basePatients){
                        System.out.println("患者编号:"+basePatient.getId()+
                                           "患者姓名:"+basePatient.getName());
                    }
                    System.out.println("请选择患者");
                    String Id = scanner.next();
                    PatientController basePatientController = new PatientController();
                    BasePatient basePatient = new BasePatient();
                    try {
                        basePatient= basePatientController.patientChoose(Id,basePatients);
                        basePatientController.medInfo(basePatient);
                    }catch (Exception e){
                        System.out.println("输入了错误的患者编号，患者不存在");
                        showPharmacyDrMenu();
                        //判断患者编号是否存在
                    }
                        System.out.println("\n-------缴费清单如下--------");
                        System.out.println("患者需缴纳的药品费用：" + basePatientController.ptMedFee(basePatient));
                        System.out.println("        1.选择缴费");
                        System.out.println("        0.返回上一级菜单");
                        int paymentChoice = scanner.nextInt();
                        scanner.nextLine();

                        // 消耗掉nextInt之后的换行符

                        if (paymentChoice == 1) {
                            System.out.println("       患者的余额为:" + (1000 - basePatientController.ptMedFee(basePatient)));
                            System.out.println("患者已缴费成功，谢谢合作，现在即将返回主菜单。");
                            showPharmacyDrMenu();
                            //计算需要缴纳的费用并显示患者账户余额
                            break;
                        } else if (paymentChoice == 0) {
                            // 返回上一级菜单
                            showPharmacyDrMenu();
                            break;
                    }
                        break;

                case 2:
                    // 查询药品信息
                    System.out.println("|--------------药品列表--------------|");
                    for (Medicine medicine : DataBase.medicineTable){
                        System.out.println("<"+medicine.getId()+">\t"+medicine.getName()+"\t\t[价格] "+medicine.getPrice()+" (元/盒)");
                    }
                    System.out.println("-------------------------------------");
                    System.out.print("|----输入任意数字返回：|");
                    scanner.nextLine();
                    // 消耗掉nextInt之后的换行符
                    showPharmacyDrMenu();
                    break;

                case 0:
                    // 退出系统
                    System.out.println("谢谢使用，再见！");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("无效的选择，请重新输入。");
                    //返回上一级菜单
                    showPharmacyDrMenu();
                    break;
            }
        }

    }
