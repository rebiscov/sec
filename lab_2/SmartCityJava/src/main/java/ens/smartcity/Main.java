package ens.smartcity;

import javax.swing.JFrame;

import ens.smartcity.graphicaldsl.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    public static void main(String[] args) {

        JFrame jFrame = new JFrame("SmartCity");
        jFrame.setContentPane(new MainWindow().$$$getRootComponent$$$());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(450, 300);
        jFrame.setVisible(true);


        System.out.println(System.currentTimeMillis());

    }


}