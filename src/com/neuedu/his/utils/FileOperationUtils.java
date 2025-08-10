package com.neuedu.his.utils;

import java.io.*;
import java.util.ArrayList;

/**
 * 文件操作工具类
 * @author 孙续洋
 * @date 7/23
 */
public class FileOperationUtils {
    /**
     * 数据文件读取
     * @date 7/23
     * @param dir
     * @return ArrayList
     * @throws IOException
     */
    public static ArrayList<String> read(String dir) throws IOException {
        File file = new File(dir);
        if (!file.exists()){
            file.createNewFile();
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        ArrayList<String> list = new ArrayList<>();
        try {
            while ((line=bufferedReader.readLine())!=null){
                //System.out.println(line);
                String[] arr = line.split("\\s+");
                for (String s : arr){
                    list.add(s);
                }
                //System.out.println();
            }
            //System.out.println(list);
        } catch (IOException e) {
            System.out.println("读取的数据格式有误！");
            bufferedReader.close();
            throw new RuntimeException(e);
        }
        bufferedReader.close();
        return list;
    }

    /**
     * 数据文件写入
     * @date 7/23
     * @param dir
     * @param info
     * @throws IOException
     */
    public static void write(String dir,String info,boolean notCover) throws IOException {
        File file = new File(dir);
        //System.out.println(file.length());
        try {
            if (!file.exists() || null == file || 0 == file.length()){
                boolean a = file.createNewFile();
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,false));
                try {
                    bufferedWriter.write(info);
                    bufferedWriter.flush();
                }catch (IOException e){
                    System.out.println("写入的数据格式有误！");
                    bufferedWriter.close();
                    throw new RuntimeException(e);
                }
                bufferedWriter.close();
                return;
            }
        }catch (IOException e){
            System.out.println("数据库地址错误！");
            return;
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,notCover));
        try {
            bufferedWriter.newLine();
            bufferedWriter.write(info);
            bufferedWriter.flush();
        }catch (IOException e){
            System.out.println("写入的数据格式有误！");
            bufferedWriter.close();
            throw new RuntimeException(e);
        }
        bufferedWriter.close();
    }


    public static boolean deleteFile(String dir){
        File file = new File(dir);
        if (!file.exists()){
            return false;
        }
        return file.delete();
    }


}
