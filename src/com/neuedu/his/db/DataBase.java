package com.neuedu.his.db;

import com.neuedu.his.controller.DepartmentController;
import com.neuedu.his.controller.PatientController;
import com.neuedu.his.model.*;
import com.neuedu.his.utils.FileOperationUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 数据库连接类
 * @author 孙续洋
 * @date 7/24
 * @tips 使用前一定要写入各个数据库的本地绝对路径！
 */
public class DataBase {

    public static ArrayList<Register> registerTable = new ArrayList<>();
    public static ArrayList<Department> departmentTable = new ArrayList<>();
    public static ArrayList<Doctor> doctorTable = new ArrayList<>();
    public static ArrayList<BasePatient> basePatientTable = new ArrayList<>();
    public static ArrayList<DrInpatient> drInpatientTable = new ArrayList<>();
    public static ArrayList<PharmacyDr> pharmacyDrTable = new ArrayList<>();
    public static  ArrayList<Medicine> medicineTable = new ArrayList<>();
    public static ArrayList<Test> testTable = new ArrayList<>();
    public static ArrayList<SickBed> sickBedTable = new ArrayList<>();
    public static ArrayList<ExamDr> examDrTable = new ArrayList<>();
    public static ArrayList<Charger> chargerTable = new ArrayList<>();

    /**
     * 初始化连接各数据库
     */
    static {
        //创建化验数据库
        db_Test();
        //连接挂号员数据库
        try {
            db_Register("C:\\Users\\sun\\Desktop\\HISDB\\db_register.txt");//使用前请先校验数据库绝对路径
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //创建科室信息表
        createDepartmentTable();
        //连接医生数据库
        try {
            db_Doctor("C:\\Users\\sun\\Desktop\\HISDB\\db_doctor.txt");//使用前请先校验数据库绝对路径
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //连接药品数据库
        try {
            db_Medicine("C:\\Users\\sun\\Desktop\\HISDB\\db_medicine.txt");//使用前请先校验数据库绝对路径
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        //连接患者数据库
        try {
            db_Patient("C:\\Users\\sun\\Desktop\\HISDB\\db_patient.txt");//使用前请先校验数据库绝对路径
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //连接住院部病床数据库
        try{
            db_Sickbed("C:\\Users\\sun\\Desktop\\HISDB\\db_sickbed.txt");//使用前请先校验数据库绝对路径
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        //连接住院部员工数据库
        try {
            db_dr_Inpatient("C:\\Users\\sun\\Desktop\\HISDB\\db_dr_Inpatient.txt");//使用前请先校验数据库绝对路径
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //连接药局医生数据库
        try {
            db_Pharmacy("C:\\Users\\sun\\Desktop\\HISDB\\db_pharmacy.txt");//使用前请先校验数据库绝对路径
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //连接检验科医生数据库
        try {
            db_examDr("C:\\Users\\sun\\Desktop\\HISDB\\db_examDr.txt");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        //连接收费人员数据库
        try {
            db_charger("C:\\Users\\sun\\Desktop\\HISDB\\db_charger.txt");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 科室信息表，启动时加载
     */
    //创建科室信息表，并创建初始数据
    public static void createDepartmentTable() {
        //创建科室对象
        Department department01 = new Department();
        department01.setDid(1);
        department01.setName("神经内科");
        departmentTable.add(department01);
        Department department02 = new Department();
        department02.setDid(2);
        department02.setName("呼吸内科");
        departmentTable.add(department02);
        Department department03 = new Department();
        department03.setDid(3);
        department03.setName("耳鼻喉科");
        departmentTable.add(department03);
        Department department04 = new Department();
        department04.setDid(4);
        department04.setName("骨科");
        departmentTable.add(department04);
        Department department05 = new Department();
        department05.setDid(5);
        department05.setName("心血管科");
        departmentTable.add(department05);
        Department department06 = new Department();
        department06.setDid(6);
        department06.setName("中医科");
        departmentTable.add(department06);
    }

    /**
     * 医生数据库读取
     * @author 孙续洋
     * @date 7/24
     * @param dir
     * @throws IOException
     */
    public static void db_Doctor(String dir) throws IOException {
        ArrayList<String> doctorList = FileOperationUtils.read(dir);
        int size = doctorList.size();
        for (int i = 0; i < size/14; i++) {
            Doctor doctor = new Doctor();
            doctor.setName(doctorList.get(14 * i));
            doctor.setSex(doctorList.get(14 * i + 1));
            doctor.setId(doctorList.get(14 * i + 2));
            doctor.setDepartment(departmentTable.get(Integer.parseInt(doctorList.get(14 * i + 3))-1));
            doctor.setGrade(doctorList.get(14 * i + 4));
            doctor.setAccount(doctorList.get(14 * i + 5));
            doctor.setPassword(doctorList.get(14 * i + 6));
            HashMap<Integer, String> doctorplan = new HashMap<>();
            doctorplan.put(1, doctorList.get(14 * i + 7));
            doctorplan.put(2, doctorList.get(14 * i + 8));
            doctorplan.put(3, doctorList.get(14 * i + 9));
            doctorplan.put(4, doctorList.get(14 * i + 10));
            doctorplan.put(5, doctorList.get(14 * i + 11));
            doctorplan.put(6, doctorList.get(14 * i + 12));
            doctorplan.put(7, doctorList.get(14 * i + 13));
            doctor.setWorkplan(doctorplan);
            doctorTable.add(doctor);
        }
    }

    /**
     * 挂号员数据库读取
     * @author 孙续洋
     * @date 7/23
     * @param dir
     * @throws IOException
     */
    public static void db_Register(String dir) throws IOException {
        ArrayList<String> registerList = FileOperationUtils.read(dir);
        int size = registerList.size();
        for (int i = 0; i < size/4; i++) {
            Register register = new Register();
            register.setName(registerList.get(4*i));
            register.setId(registerList.get(4*i+1));
            register.setAccount(registerList.get(4*i+2));
            register.setPassword(registerList.get(4*i+3));
            registerTable.add(register);
        }
    }

    /**
     * 患者数据库读取
     * @author 孙续洋
     * @date 7/26
     * @param dir
     * @throws IOException
     */
    public static void db_Patient(String dir) throws IOException {
        ArrayList<String> patientList = FileOperationUtils.read(dir);
        DepartmentController departmentController = new DepartmentController();
        int size = patientList.size();
        for (int i = 0; i < size/22; i++) {
            BasePatient patient = new BasePatient();
            patient.setName(patientList.get(22*i));
            patient.setSex(patientList.get(22*i+1));
            patient.setAge(patientList.get(22*i+2));
            patient.setId(patientList.get(22*i+3));
            patient.setDepartment(departmentController.findDepartmentById(Integer.parseInt(patientList.get(22*i+4))));
            for (Doctor doctor : doctorTable){
                if (doctor.getId().equals(patientList.get(22*i+5))){
                    patient.setDoctor(doctor);
                    break;
                }
            }
            patient.setPersonalStatement(patientList.get(22*i+6));
            patient.setDiagnosis(patientList.get(22*i+7));
            if ("已诊断".equals(patientList.get(22*i+8))){
                patient.setDiagnosis(true);
            }else {
                patient.setDiagnosis(false);
            }
            patient.setInpatientT(Integer.parseInt(patientList.get(22*i+9)));
            HashMap<Medicine,Integer> medicineList = new HashMap<>();
            medicineList.put(medicineTable.get(0),Integer.parseInt(patientList.get(22*i+10)));
            medicineList.put(medicineTable.get(1),Integer.parseInt(patientList.get(22*i+11)));
            medicineList.put(medicineTable.get(2),Integer.parseInt(patientList.get(22*i+12)));
            medicineList.put(medicineTable.get(3),Integer.parseInt(patientList.get(22*i+13)));
            medicineList.put(medicineTable.get(4),Integer.parseInt(patientList.get(22*i+14)));
            medicineList.put(medicineTable.get(5),Integer.parseInt(patientList.get(22*i+15)));
            medicineList.put(medicineTable.get(6),Integer.parseInt(patientList.get(22*i+16)));
            medicineList.put(medicineTable.get(7),Integer.parseInt(patientList.get(22*i+17)));
            patient.setMedicine(medicineList);
            HashMap<Test,String> testList = new HashMap<>();
            testList.put(testTable.get(0),patientList.get(22*i+18));
            testList.put(testTable.get(1),patientList.get(22*i+19));
            testList.put(testTable.get(2),patientList.get(22*i+20));
            testList.put(testTable.get(3),patientList.get(22*i+21));
            patient.setTest(testList);
            basePatientTable.add(patient);
        }
    }

    /**
     * 住院部员工数据库读取
     * @author 孙续洋
     * @date 7/24
     * @param dir
     * @throws IOException
     */
    public static void db_dr_Inpatient(String dir) throws IOException {
        ArrayList<String> drInpatientList = FileOperationUtils.read(dir);
        int size = drInpatientList.size();
        for (int i = 0; i < size/4; i++) {
            DrInpatient drInpatient = new DrInpatient();
            drInpatient.setName(drInpatientList.get(4*i));
            drInpatient.setId(drInpatientList.get(4*i+1));
            drInpatient.setAccount(drInpatientList.get(4*i+2));
            drInpatient.setPassword(drInpatientList.get(4*i+3));
            drInpatientTable.add(drInpatient);
        }
    }

    /**
     * 药局医生数据库读取
     * @author 孙续洋
     * @date 7/24
     * @param dir
     * @throws IOException
     */
    public static void db_Pharmacy(String dir) throws IOException {
        ArrayList<String> pharmacyDrList = FileOperationUtils.read(dir);
        int size = pharmacyDrList.size();
        for (int i = 0; i < size/4; i++) {
            PharmacyDr pharmacyDr = new PharmacyDr();
            pharmacyDr.setName(pharmacyDrList.get(4*i));
            pharmacyDr.setId(pharmacyDrList.get(4*i+1));
            pharmacyDr.setAccount(pharmacyDrList.get(4*i+2));
            pharmacyDr.setPassword(pharmacyDrList.get(4*i+3));
            pharmacyDrTable.add(pharmacyDr);
        }
    }


    /**
     * 患者读取方法（用于更新数据）
     * @author 孙续洋
     * @date 7/25
     * @return
     * @throws IOException
     */
    public static void db_PatientUD(){
        try{
        ArrayList<String> patientList = FileOperationUtils.read("C:\\Users\\sun\\Desktop\\HISDB\\db_patient.txt");
        DepartmentController departmentController = new DepartmentController();
        basePatientTable.clear();
        int size = patientList.size();
        for (int i = 0; i < size/22; i++) {
            BasePatient patient = new BasePatient();
            patient.setName(patientList.get(22 * i));
            patient.setSex(patientList.get(22 * i + 1));
            patient.setAge(patientList.get(22 * i + 2));
            patient.setId(patientList.get(22 * i + 3));
            patient.setDepartment(departmentController.findDepartmentById(Integer.parseInt(patientList.get(22 * i + 4))));
            for (Doctor doctor : doctorTable) {
                if (doctor.getId().equals(patientList.get(22 * i + 5))) {
                    patient.setDoctor(doctor);
                    break;
                }
            }
            patient.setPersonalStatement(patientList.get(22 * i + 6));
            patient.setDiagnosis(patientList.get(22 * i + 7));
            if ("已诊断".equals(patientList.get(22 * i + 8))) {
                patient.setDiagnosis(true);
            } else {
                patient.setDiagnosis(false);
            }
            patient.setInpatientT(Integer.parseInt(patientList.get(22 * i + 9)));
            HashMap<Medicine, Integer> medicineList = new HashMap<>();
            medicineList.put(medicineTable.get(0), Integer.parseInt(patientList.get(22 * i + 10)));
            medicineList.put(medicineTable.get(1), Integer.parseInt(patientList.get(22 * i + 11)));
            medicineList.put(medicineTable.get(2), Integer.parseInt(patientList.get(22 * i + 12)));
            medicineList.put(medicineTable.get(3), Integer.parseInt(patientList.get(22 * i + 13)));
            medicineList.put(medicineTable.get(4), Integer.parseInt(patientList.get(22 * i + 14)));
            medicineList.put(medicineTable.get(5), Integer.parseInt(patientList.get(22 * i + 15)));
            medicineList.put(medicineTable.get(6), Integer.parseInt(patientList.get(22 * i + 16)));
            medicineList.put(medicineTable.get(7), Integer.parseInt(patientList.get(22 * i + 17)));
            patient.setMedicine(medicineList);
            HashMap<Test, String> testList = new HashMap<>();
            testList.put(testTable.get(0), patientList.get(22 * i + 18));
            testList.put(testTable.get(1), patientList.get(22 * i + 19));
            testList.put(testTable.get(2), patientList.get(22 * i + 20));
            testList.put(testTable.get(3), patientList.get(22 * i + 21));
            patient.setTest(testList);
            basePatientTable.add(patient);
        }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 药品数据库获取
     * @author 孙续洋
     * @date 7/25
     * @param dir
     * @throws IOException
     */
    public static void db_Medicine(String dir) throws IOException {
        ArrayList<String> medicineList = FileOperationUtils.read(dir);
        int size = medicineList.size();
        for (int i = 0; i < size/3; i++) {
            Medicine medicine = new Medicine();
            medicine.setName(medicineList.get(3*i));
            medicine.setPrice(Integer.parseInt(medicineList.get(3*i+1)));
            medicine.setId(medicineList.get(3*i+2));
            medicineTable.add(medicine);
        }
    }

    /**
     * 检查数据库获取
     * @author 孙续洋
     * @date 7/25
     */
    public static void db_Test(){
        Test test01 = new Test();
        test01.setName("血常规化验");
        test01.setPrice(200);
        test01.setId("1");
        testTable.add(test01);
        Test test02 = new Test();
        test02.setName("尿常规化验");
        test02.setPrice(300);
        test02.setId("2");
        testTable.add(test02);
        Test test03 = new Test();
        test03.setName("肝功能检查");
        test03.setPrice(350);
        test03.setId("3");
        testTable.add(test03);
        Test test04 = new Test();
        test04.setName("CT照像检查");
        test04.setPrice(500);
        test04.setId("4");
        testTable.add(test04);
    }

    /**
     * 病床数据库读取
     * author:王思汉
     * @param dir
     * @throws IOException
     */
    public static void db_Sickbed(String dir) throws IOException{
        ArrayList<String> sickbedList= FileOperationUtils.read(dir);
        PatientController basePatientController=new PatientController();
        int size = sickbedList.size();
        for(int i=0;i<size/4;i++){
            SickBed sickBed=new SickBed();
            sickBed.setNumber(sickbedList.get(4*i));
            sickBed.setState(sickbedList.get(4*i+1));
            sickBed.setBasePatient(basePatientController.patientChoose(sickbedList.get(4*i+2),DataBase.basePatientTable));
            sickBed.setTime(sickbedList.get(4*i+3));
            sickBedTable.add(sickBed);
        }
    }

    /**
     * 病床数据库更新
     * @author 孙续洋
     * @date 7/26
     * @throws IOException
     */
    public static void db_SickbedUD() throws IOException {
        ArrayList<String> sickbedList= FileOperationUtils.read("C:\\Users\\sun\\Desktop\\HISDB\\db_sickbed.txt");
        PatientController basePatientController=new PatientController();
        sickBedTable.clear();
        int size = sickbedList.size();
        for(int i=0;i<size/4;i++){
            SickBed sickBed=new SickBed();
            sickBed.setNumber(sickbedList.get(4*i));
            sickBed.setState(sickbedList.get(4*i+1));
            sickBed.setBasePatient(basePatientController.patientChoose(sickbedList.get(4*i+2),DataBase.basePatientTable));
            sickBed.setTime(sickbedList.get(4*i+3));
            sickBedTable.add(sickBed);
        }
    }


    /**
     * 检查人员数据库读取
     * @param dir
     * @throws IOException
     */
    public static void db_examDr(String dir) throws IOException {
        ArrayList<String> examdrList = FileOperationUtils.read(dir);
        int size = examdrList.size();
        for (int i = 0; i < size/3; i++) {
            ExamDr examDr = new ExamDr();
            examDr.setName(examdrList.get(3*i));
            examDr.setAccount(examdrList.get(3*i+1));
            examDr.setPassword(examdrList.get(3*i+2));
            examDrTable.add(examDr);
        }
    }

    /**
     * 收费员数据库读取
     * @param dir
     * @throws IOException
     */
    public static void db_charger(String dir) throws IOException {
        ArrayList<String> chargerList = FileOperationUtils.read(dir);
        int size = chargerList.size();
        for (int i = 0; i < size/3; i++) {
            Charger charger = new Charger();
            charger.setName(chargerList.get(3*i));
            charger.setAccount(chargerList.get(3*i+1));
            charger.setPassword(chargerList.get(3*i+2));
            chargerTable.add(charger);
        }
    }
}
