package ens.smartcity;

import ens.smartcity.graphicaldsl.*;
import javax.swing.*;
import java.util.Date;

public class Main {

  public static void main(String[] args) {

    JFrame jFrame = new JFrame("SmartCity");
    jFrame.setContentPane(new MainWindow().$$$getRootComponent$$$());
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setSize(450, 300);
    jFrame.setVisible(true);



    System.out.println(new Date());
  }
}
