package ens.smartcity.graphicaldsl;

import com.intellij.uiDesigner.core.Spacer;
import ens.smartcity.model.data.Data;
import ens.smartcity.model.iofile.CSVMeasurement;
import ens.smartcity.model.sensor.Mesurement;
import ens.smartcity.model.sensor.Sensor;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MainWindow {
    private JPanel panel1;
    private JButton openCSVFileButton;
    private JButton openJSONFileButton;
    private JButton realTimeSimulationButton;
    private JButton sendDataButton;
    private JTable tableData;
    private JButton addTailoringButton;
    private JList listData;
    private JButton sampleDataButton;
    private JButton newSensorButton;

    private List<Sensor> sensorList = new ArrayList<>();
    private List<Data> dataList = new ArrayList<>();

    private DefaultListModel listDataModel;
    private DefaultTableModel tableDataModel;

    public MainWindow() {

        listDataModel = new DefaultListModel();
        listData.setModel(listDataModel);

        tableDataModel = new DefaultTableModel();
        tableData.setModel(tableDataModel);

        tableDataModel.addColumn("time");
        tableDataModel.addColumn("value");
        tableDataModel.addColumn("sensor");

        listData.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                for (Data d : dataList) {
                    if (d.getName() == listData.getSelectedValue().toString()) {
                        printMesurement(d);
                    }
                }
            }
        });
        openCSVFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV File", "csv"));

                Integer ret = fileChooser.showOpenDialog(null);

                if (ret == JFileChooser.APPROVE_OPTION) {
                    CSVMeasurement csvFile = new CSVMeasurement(fileChooser.getSelectedFile().getPath());
                    Data csvData = csvFile.OpenFile(0, 1, 2, false);
                    listDataModel.addElement(csvData.getName());
                    dataList.add(csvData);

                }
            }
        });
        MainWindow this2 = this;
        sampleDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SimulateLaw dialog = new SimulateLaw(this2);
                Data d = dialog.afficher();
                listDataModel.addElement(d.getName());
            }
        });
        addTailoringButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                AddTailoring at = new AddTailoring(this2);
                at.afficher();
            }
        });
        newSensorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddSensor ad = new AddSensor(this2);
                ad.afficher();
            }
        });
        sendDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for (Data d : dataList) {
                    if (d.getName() == listData.getSelectedValue().toString()) {
                        d.sendData();
                    }
                }
            }
        });
        realTimeSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                RealTime rt = new RealTime(this2);
                rt.afficher();
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public List<Sensor> getSensorList() {
        return sensorList;
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    private void printMesurement(Data d) {
        tableDataModel.setNumRows(0);
        for (Mesurement m : d.getMesurements()) {
            Vector a = new Vector();
            a.add(m.getTime().toString());
            a.add(m.getValue().toString());
            a.add(m.getOwner().getName());
            tableDataModel.addRow(a);
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Liberation Sans", -1, 28, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("SmartCity DSL (used by Paris, NY, London, Tokyo, ...)");
        panel1.add(label1, BorderLayout.NORTH);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout(0, 0));
        panel1.add(panel2, BorderLayout.CENTER);
        final JToolBar toolBar1 = new JToolBar();
        panel2.add(toolBar1, BorderLayout.NORTH);
        openCSVFileButton = new JButton();
        openCSVFileButton.setText("Open CSV File");
        toolBar1.add(openCSVFileButton);
        openJSONFileButton = new JButton();
        openJSONFileButton.setText("Open JSON File");
        toolBar1.add(openJSONFileButton);
        newSensorButton = new JButton();
        newSensorButton.setText("New Sensor");
        toolBar1.add(newSensorButton);
        final Spacer spacer1 = new Spacer();
        toolBar1.add(spacer1);
        addTailoringButton = new JButton();
        addTailoringButton.setText("Add Tailoring");
        toolBar1.add(addTailoringButton);
        final Spacer spacer2 = new Spacer();
        toolBar1.add(spacer2);
        sampleDataButton = new JButton();
        sampleDataButton.setText("Simulation");
        toolBar1.add(sampleDataButton);
        final JSeparator separator1 = new JSeparator();
        toolBar1.add(separator1);
        realTimeSimulationButton = new JButton();
        realTimeSimulationButton.setText("Real Time Simulation");
        toolBar1.add(realTimeSimulationButton);
        final JToolBar toolBar2 = new JToolBar();
        panel2.add(toolBar2, BorderLayout.SOUTH);
        sendDataButton = new JButton();
        sendDataButton.setText("Send Data");
        toolBar2.add(sendDataButton);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout(0, 0));
        panel2.add(panel3, BorderLayout.WEST);
        final JScrollPane scrollPane1 = new JScrollPane();
        panel3.add(scrollPane1, BorderLayout.EAST);
        listData = new JList();
        listData.setSelectionMode(0);
        scrollPane1.setViewportView(listData);
        final JLabel label2 = new JLabel();
        label2.setText("List of Data:");
        panel3.add(label2, BorderLayout.NORTH);
        tableData = new JTable();
        panel2.add(tableData, BorderLayout.CENTER);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
