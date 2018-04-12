package com.example.xcy_m.test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by xcy_m on 2018/4/12.
 */

public class FileManager {
    /**
     * 将文件拷贝到指定目录
     * @param oldAddress 文件所在目录(文件的全路径)
     * @param newAddress 指定目录(包含复制文件的全名称)
     * @throws Exception
     */
    public static void copyFile(String oldAddress, String newAddress) throws Exception {
        FileInputStream input=new FileInputStream(oldAddress);
        FileOutputStream output=new FileOutputStream(newAddress);//注意：newAddress必须包含文件名字，比如说将D:/AAA文件夹下的文件"a.xml"复制到D:\test目录下，则newAddress必须为D:\test\a.xml
    //oldAddress必须是a.xml文件的全路径，即D:\AAA\a.xml,否则就会报IO异常的错误
        int in=input.read();
        while(in!=-1){
            output.write(in);
            in=input.read();
        }
        input.close();
        output.close();
    }

    /**
     * 删除指定目录及其文件
     * @param dir 删除的文件夹
     * @return
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                File file = new File(dir+ File.separator+children[i]);
                file.delete();
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
