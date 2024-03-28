package health;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import javax.swing.border.CompoundBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class patient {

    JFrame frame;
    /**
     * @wbp.nonvisual location=-17,-16
     */
    DefaultTableModel model;

    private final JPanel panel = new JPanel();

    private JTable table;
    private JScrollPane scrollPane;
    private JTextField patname;
    private JTextField patage;
    private JTextField patdisease;
    private JTextField patadmit;
    private JTextField patcontact;
    private JTextField patgender;
    private JTextField patid;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    patient window = new patient();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public patient() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.info);
        frame.getContentPane().setLayout(null);

        JLabel patnamelab = new JLabel("NAME");
        patnamelab.setBounds(70, 80, 68, 29);
        patnamelab.setBackground(SystemColor.controlText);
        patnamelab.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        frame.getContentPane().add(patnamelab);

        JLabel patagelab = new JLabel("AGE");
        patagelab.setBounds(70, 110, 78, 29);
        patagelab.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        patagelab.setBackground(Color.BLACK);
        frame.getContentPane().add(patagelab);

        JLabel patdiseaselab = new JLabel("DISEASE");
        patdiseaselab.setBounds(70, 140, 117, 29);
        patdiseaselab.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        patdiseaselab.setBackground(Color.BLACK);
        frame.getContentPane().add(patdiseaselab);

        JLabel patadmitlab = new JLabel("ADMITSTATUS");
        patadmitlab.setBounds(70, 170, 106, 29);
        patadmitlab.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        patadmitlab.setBackground(Color.BLACK);
        frame.getContentPane().add(patadmitlab);

        JLabel contactlab = new JLabel("CONTACT");
        contactlab.setBounds(70, 200, 117, 29);
        contactlab.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        contactlab.setBackground(Color.BLACK);
        frame.getContentPane().add(contactlab);

        JLabel patgenderlab = new JLabel("GENDER");
        patgenderlab.setBounds(70, 230, 68, 29);
        patgenderlab.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        patgenderlab.setBackground(Color.BLACK);
        frame.getContentPane().add(patgenderlab);

        JLabel patidlab = new JLabel("ID");
        patidlab.setBounds(70, 47, 68, 29);
        patidlab.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        patidlab.setBackground(Color.BLACK);
        frame.getContentPane().add(patidlab);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(354, 47, 700, 483);
        frame.getContentPane().add(scrollPane);

        table = new JTable();

        model = new DefaultTableModel();
        scrollPane.setViewportView(table);
        Object[] column = {"ID", "NAME", "AGE", "DISEASE", "ADMITSTATUS", "CONTACT", "GENDER"};
        Object[] row = new Object[7];
        model.setColumnIdentifiers(column);
        table.setModel(model);

        JButton btnadd = new JButton("ADD");
        btnadd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (patid.getText().equals("") || patname.getText().equals("") || patage.getText().equals("") || patdisease.getText().equals("") || patadmit.getText().equals("") || patcontact.getText().equals("") || patgender.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all the information");
                } else {
                    row[0] = patid.getText();
                    row[1] = patname.getText();
                    row[2] = patage.getText();
                    row[3] = patdisease.getText();
                    row[6] = patadmit.getText();
                    row[4] = patcontact.getText();
                    row[5] = patgender.getText();

                    model.addRow(row);
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");

                        String query = "INSERT INTO patient values('" + row[0] + "','" + row[1] + "','" + row[2] + "','"
                                + row[3] + "','" + row[4] + "','" + row[5] + "','" + row[6] + "')";

                        Statement sta = connection.createStatement();
                        sta.executeUpdate(query);
                        patid.setText("");
                        patname.setText("");
                        patage.setText("");
                        patdisease.setText("");
                        patadmit.setText("");
                        patcontact.setText("");
                        patgender.setText("");
                        JOptionPane.showMessageDialog(null, "Patient information stored successfully");
                    } catch (Exception e1) {
                        //JOptionPane.showMessageDialog(null, "Error", null, JOptionPane.ERROR_MESSAGE);
                        System.out.println(e1);
                    }
                }

            }
        });
        btnadd.setBounds(70, 370, 117, 52);
        frame.getContentPane().add(btnadd);

        JButton btnDelete = new JButton("DELETE");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = table.getSelectedRow();
                if (i >= 0) {

                    model.removeRow(i);
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
                        String query = "delete from patient where id ='" + patid.getText() + "'";
                        Statement sta = connection.createStatement();
                        PreparedStatement pst = connection.prepareStatement(query);
                        pst.execute();
                        patid.setText("");
                        patname.setText("");
                        patage.setText("");
                        patdisease.setText("");
                        patadmit.setText("");
                        patcontact.setText("");
                        patgender.setText("");
                    } catch (Exception e1) {
                        System.out.println(e1);
                    }
                    JOptionPane.showMessageDialog(null, "Patient information Deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Cant Delete!");
                }
            }
        });
        btnDelete.setBounds(211, 370, 116, 52);
        frame.getContentPane().add(btnDelete);

        JButton btnSearch = new JButton("UPDATE");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
                    String query = "update patient set ID='" + patid.getText()
                            + "',NAME='" + patname.getText()
                            + "',AGE='" + patage.getText()
                            + "',DISEASE='" + patdisease.getText()
                            + "',ADMITSTATUS='" + patadmit.getText()
                            + "',CONTACT='" + patcontact.getText()
                            + "',GENDER='" + patgender.getText()
                            + "' where ID = '" + patid.getText() + "'";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Data updated!!!");
                    pst.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }

            }
        });

        btnSearch.setBounds(70, 445, 117, 52);
        frame.getContentPane().add(btnSearch);

        JButton btnExit = new JButton("CLEAR");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                patid.setText("");
                patname.setText("");
                patage.setText("");
                patdisease.setText("");
                patadmit.setText("");
                patcontact.setText("");
                patgender.setText("");

            }
        });
        btnExit.setBounds(211, 445, 116, 52);
        frame.getContentPane().add(btnExit);

        patid = new JTextField();
        patid.setBounds(194, 53, 150, 19);
        frame.getContentPane().add(patid);

        patname = new JTextField();
        patname.setBounds(194, 86, 150, 19);
        frame.getContentPane().add(patname);

        patage = new JTextField();
        patage.setBounds(194, 116, 150, 19);
        frame.getContentPane().add(patage);

        patdisease = new JTextField();
        patdisease.setBounds(194, 146, 150, 19);
        frame.getContentPane().add(patdisease);

        patadmit = new JTextField();
        patadmit.setBounds(194, 176, 150, 19);
        frame.getContentPane().add(patadmit);

        patcontact = new JTextField();
        patcontact.setBounds(194, 206, 150, 19);
        frame.getContentPane().add(patcontact);

        patgender = new JTextField();
        patgender.setBounds(194, 236, 150, 19);
        frame.getContentPane().add(patgender);

        JLabel lblNewLabel = new JLabel("PATIENT DETAILS");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setForeground(new Color(0, 0, 160));
        lblNewLabel.setBounds(500, 10, 157, 27);
        frame.getContentPane().add(lblNewLabel);
        frame.setBounds(150, 150, 1100, 618);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
