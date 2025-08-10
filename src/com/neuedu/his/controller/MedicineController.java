package com.neuedu.his.controller;

import com.neuedu.his.db.DataBase;
import com.neuedu.his.model.BasePatient;
import com.neuedu.his.model.Doctor;
import com.neuedu.his.model.Medicine;

import java.util.ArrayList;

public class MedicineController {
    public Medicine findmedById(String id){
        Medicine medicine1 = new Medicine();
        medicine1.setId("-100");
        for (Medicine medicine : DataBase.medicineTable){
            if (medicine.getId().equals(id)){
                return medicine;
            }
        }
        return medicine1;
    }


    public ArrayList<BasePatient> medPTList(){
        ArrayList<BasePatient> basePatients = new ArrayList<>();
        BasePatient basePatient1 = new BasePatient();
        int b = 0 ;
        for (BasePatient basePatient : DataBase.basePatientTable){
            int a =0;
            for (Medicine medicine : DataBase.medicineTable){
                if (basePatient.getMedicine().get(medicine)!=0){
                    a++;
                    b++;
                }
            }
            if (a!=0){
                basePatients.add(basePatient);
            }
        }
        if (b!=0){
            return basePatients;
        }
        basePatient1.setId("-200");
        basePatients.add(basePatient1);
        return basePatients;
    }
}
