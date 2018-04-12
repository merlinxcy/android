package com.example.xcy_m.test1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.*;

public class NetMain {
    public NetMain() {
    }
    public static String replaceNow(String ptin, String originHex, String replaceHex) {
        // replace
//        String originHex = "31 34 39 2e 31 35 34 2e 31 36 37 2e 39 31";
//        String replaceHex = "31 34 39 2e 31 35 34 2e 31 36 37 2e 39 31";
        int replaceHexLength = replaceHex.replace(" ", "").length();
//        System.out.println();
//        System.out.println(replaceHexLength/2);
        String replaceLength = Integer.toHexString(replaceHexLength/2).toUpperCase();
        if(replaceLength.length() != 2) {
            replaceLength = "0" + replaceLength;
        }
//        System.out.println(replaceLength);
        int IndexOfReplaceHex = ptin.indexOf(originHex.toUpperCase());
//        System.out.println(IndexOfReplaceHex);
//        System.out.println(ptin.substring(IndexOfReplaceHex-2, IndexOfReplaceHex));
        String originHexLStr = ptin.substring(IndexOfReplaceHex-3, IndexOfReplaceHex);
        String oriHex = originHexLStr + originHex;
        String repHex = replaceLength + " " + replaceHex;
//        System.out.println(repHex);
        System.out.println("replace NOW --");
        System.out.println(oriHex.toUpperCase());
        System.out.println(repHex.toUpperCase());
        ptin = ptin.replace(oriHex, repHex);//replace it now
//        System.out.println(ptin.indexOf(repHex.toUpperCase()));
        System.out.println("replace Over --");
        return ptin;
    }
    public static void mainF(){
        try
        {

            String path = "/data/data/org.telegram.messenger/files/tgnet.dat";
            String path2 = "/data/data/org.telegram.messenger/files/tgnetchange.dat";
            RandomAccessFile fileIn = new RandomAccessFile(new File(path),"rws");
            RandomAccessFile fileOut = new RandomAccessFile(new File(path2),"rws");
            int lengthInputFile = (int) fileIn.length();
            byte input[]= new byte[lengthInputFile];

            // action
            fileIn.read(input);
//	         String ptin = Hex.byte2HexStr(input);
            String ptin = CHex.byte2HexStr(input, lengthInputFile);
            System.out.println(ptin);
            //replace
            String originHex[] = {
                    "31 34 39 2e 31 35 34 2e 31 36 37 2e 39 31",
                    "31 34 39 2e 31 35 34 2e 31 37 35 2e 35 30",
                    "31 34 39 2e 31 35 34 2e 31 36 35 2e 31 32 30",
                    "31 34 39 2e 31 35 34 2e 31 36 37 2e 39 31",
                    "31 34 39 2e 31 35 34 2e 31 37 35 2e 31 30 30"
            };
            String replaceHex = "39 38 2e 31 34 32 2e 31 34 31 2e 31 37 35";
            for(int i = 0;i<originHex.length;i++) {
                try {
                    ptin = ServiceForNet.replaceNow(ptin, originHex[i].toUpperCase(), replaceHex.toUpperCase());
                }
                catch(Exception e) {

                }
            }

            byte output[] = CHex.hexStr2Bytes(ptin);
            System.out.println(ptin);
//	         fileOut.write(Hex.hexStr2Bytes("ffs"));
            fileOut.write(output);
            fileIn.close();
            fileOut.close();
            FileManager.deleteDir(new File("/data/data/org.telegram.messenger/files/tgnet.dat"));
            FileManager.copyFile("/data/data/org.telegram.messenger/files/tgnetchange.dat", "/data/data/org.telegram.messenger/files/tgnet.dat");
        }catch(IOException i)
        {
            i.printStackTrace();
            return;
        }catch(Exception e)
        {
//	         System.out.println("Employee class not found");
            e.printStackTrace();
            return;
        }
    }

}
