package ens.smartcity.graphicaldsl;

import ens.smartcity.model.data.GenerateData;
import ens.smartcity.model.sensor.Sensor;

import javax.swing.*;
import java.awt.event.*;

public class RealTime extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBoxSensor;
    private JTextField textFieldInterval;
    private JTextField textFieldDuration;

    private MainWindow parent;

    public RealTime(MainWindow parent) {

        this.parent = parent;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        for(Sensor s: parent.getSensorList()) {
            comboBoxSensor.addItem(s.getName());
        }

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {

        Sensor s = new Sensor(0, "", "");
        for (Sensor s1 : parent.getSensorList()) {
            if (s1.getName() == comboBoxSensor.getSelectedItem().toString()) {
                s = s1;
            }
        }

        GenerateData.generateRealTime(s, new Integer(textFieldInterval.getText()), new Integer(textFieldDuration.getText()));

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
