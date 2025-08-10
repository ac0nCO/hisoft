package com.neuedu.his.view;

import com.neuedu.his.controller.DataBaseController;
import com.neuedu.his.controller.DoctorController;
import com.neuedu.his.controller.DrInpatientController;
import com.neuedu.his.controller.PatientController;
import com.neuedu.his.db.DataBase;
import com.neuedu.his.model.*;
import com.neuedu.his.utils.ContactUtils;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 医生视图
 * @author 孙续洋
 * @date 7/25
 */
public class DoctorView {

    private Scanner scanner = new Scanner(System.in);
    private BasePatient basePatient = new BasePatient();
    private DoctorController doctorController = new DoctorController();
    private DataBaseController dataBaseController = new DataBaseController();
    private PatientController patientController = new PatientController();
    private DrInpatientController drInpatientController = new DrInpatientController();
    static boolean exe = true;


    /**
     * 医生登录方法
     * @date 7/25
     */
    public void login() {
        System.out.print("|+ 请输入门诊医生账号: |");
        //获取账号
        String account = scanner.next();
        System.out.print("|+ 请输入门诊医生密码: |");
        String password = scanner.next();
        int result = doctorController.login(account, password);
        if (result == -1 || result == -2) {
            System.out.println("<---【警告】账号和密码为非法输入--->");
        } else if (result == 200) {
            System.out.println("<-----------登录成功----------->");
            while (exe){
                showDoctorMenu(doctorController.findDoctorByAc(account));
            }
        } else {
            System.out.println("<--------【警告】登录失败-------->");
        }
    }


    /**
     * 医生菜单方法
     * @date 7/25
     * @param doctor
     */
    public void showDoctorMenu(Doctor doctor) {
        System.out.println("\n|------------医生菜单------------|");
        System.out.println("          1.查看预约              ");
        System.out.println("          0.退出到主菜单           ");
        System.out.println("          9.任意键退出系统         ");
        System.out.println("|--Username:"+doctor.getName()+"\tUserid:"+doctor.getId()+"\tDate:"+ ContactUtils.getDate()+"\t--|");
        System.out.print("|+ 请输入您的选择: |");
        //获取输入内容
        String input = scanner.next();
        switch (input) {
            case "1":
                //查看预约流程
                doctorMenu(doctor);
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
     * 预约菜单方法
     * @date 7/25
     * @param doctor
     */
    public void doctorMenu(Doctor doctor) {
        //显示预约患者菜单
        System.out.println("|--------预约患者菜单--------|");
        //通过
        ArrayList<BasePatient> basePatientList = patientController.findPatientApt(doctor);
        doctor.setPtCount(basePatientList.size());
        if (doctor.getPtCount() == 0) {
            System.out.println("\n\n\n------【暂无患者预约!】------\n\n\n");
        } else {
            for (BasePatient patient : basePatientList) {
                System.out.println("|--患者编号:" + patient.getId() + "\t 患者姓名:" + patient.getName() + "\t 患者性别:" + patient.getSex()
                        + "\t 患者年龄:" + patient.getAge()
                        + "\t 诊断状态:" + patientController.isDiagnosis(patient.isDiagnosis()) + "\t 住院状态:"
                        + patientController.isInpatient(patient.getInpatientT()) + "\t --|");
            }
            //显示患者量
            System.out.println("|--【预约患者量】<" + doctor.getPtCount() + ">\t【已诊断患者量】<" + doctorController.diagnosisCount(basePatientList) + ">\t【候诊患者量】<"
                    + (doctor.getPtCount() - doctorController.diagnosisCount(basePatientList)) + ">\t--|");

            //提示用户选择患者
            basePatient.setName("");
            try {
                while (basePatient.getName().equals("")) {
                    System.out.print("|+ 请输入您选择的患者编号: |");
                    String patientChoose = scanner.next();
                    basePatient = patientController.patientChoose(patientChoose, basePatientList);
                    if (basePatient == null) {
                        System.out.println("|-【警告】您输入了错误的编号-|");
                    }
                }
            } catch (Exception e) {
                System.out.println("|-【警告】您输入了错误的编号-|");
                scanner.nextLine();
            }

            //显示患者信息
            System.out.println("--------患者信息--------");
            System.out.println("\n\n|--患者编号:" + basePatient.getId() + "\t 患者姓名:" + basePatient.getName() + "\t 患者性别:" + basePatient.getSex() + "\t 患者年龄:" + basePatient.getAge()
                    + "\t 诊断状态:" + patientController.isDiagnosis(basePatient.isDiagnosis()) + "\t 住院状态:"
                    + patientController.isInpatient(basePatient.getInpatientT()) + "\t--|"
                    + "\n|--患者状况--|" + basePatient.getPersonalStatement() + "\n|--诊断信息--|" + basePatient.getDiagnosis());
            patientController.medInfo(basePatient);
            patientController.testInfo(basePatient);
            diagMenu(doctor);

        }
    }
    public void diagMenu(Doctor doctor){
        //患者信息操作菜单
        System.out.println(" --------诊疗菜单------- ");
        System.out.println("|       1.自述病情      |");
        System.out.println("|       2.诊断病情      |");
        System.out.println("|       3.开药菜单      |");
        System.out.println("|       4.检查菜单      |");
        System.out.println("|       0.返回患者菜单   |");
        System.out.println("|     任意键退出系统     |");
        System.out.println(" ---------------------- ");
        System.out.print("|+ 请输入您的选择: |");
        int choose = scanner.nextInt();
        switch (choose){
            case 1:
                //输入患者自述
                System.out.print("|+ 请输入患者自述: |");
                basePatient.setPersonalStatement(scanner.next());
                System.out.println("|----患者状况----|\n<\t"+basePatient.getPersonalStatement()+"\t>");
                System.out.println("----------------------------\n\n\n");
                basePatient.setDiagnosis(true);
                dataBaseController.db_Patient_fix(basePatient);
                DataBase.db_PatientUD();
                diagMenu(doctor);
                break;
            case 2:
                //输入医生诊断
                if ("None".equals(basePatient.getPersonalStatement()) || "".equals(basePatient.getPersonalStatement())){
                    System.out.println("----【警告】非法操作，未录入患者状况！----");
                    diagMenu(doctor);
                    break;
                }else {
                    System.out.print("|+ 请输入诊断情况: |");
                    basePatient.setDiagnosis(scanner.next());
                    System.out.println("|----诊断结果----|\n<\t"+basePatient.getDiagnosis()+"\t>");
                    System.out.println("----------------------------\n\n\n");
                    basePatient.setDiagnosis(true);
                    dataBaseController.db_Patient_fix(basePatient);
                    DataBase.db_PatientUD();
                    diagMenu(doctor);
                    break;
                }
            case 3:
                //药品列表
                System.out.println("|--------------药品列表--------------|");
                for (Medicine medicine : DataBase.medicineTable){
                    System.out.println("<"+medicine.getId()+">\t"+medicine.getName()+"\t\t[价格] "+medicine.getPrice()+" (元/盒)");
                }
                System.out.println("-------------------------------------");
                System.out.print("|+ 请输入药品编号: |");
                String medChoose = scanner.next();
                System.out.print("|+ 请输入数量: |");
                int medCount = scanner.nextInt();
                patientController.addPTMed(medChoose,medCount,basePatient);
                System.out.print("-------------------------------------");
                patientController.medInfo(basePatient);
                diagMenu(doctor);
                break;
            case 4:
                //检查列表
                System.out.println("|----------检查列表----------|");
                for (Test test : DataBase.testTable){
                    System.out.println("<"+test.getId()+">\t"+test.getName()+"\t[价格] "+test.getPrice()+" (元/项)");
                }
                System.out.println("-------------------------------------");
                System.out.print("|+ 请输入检查编号: |");
                String testid = scanner.next();
                patientController.addPTtest(testid,basePatient);
                System.out.print("-------------------------------------");
                patientController.testInfo(basePatient);
                diagMenu(doctor);
                break;
            case 0:
                doctorMenu(doctor);
                break;
            default:
                System.out.println("---------祝您身体健康，再见---------");
                System.exit(0);
                break;
        }
        //System.out.println(a);
    }





}
