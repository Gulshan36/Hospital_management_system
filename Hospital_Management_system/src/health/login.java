package health;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JLabel choiceLabel = new JLabel("USERTYPE");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    String[] optionsToChoose = {"Admin", "Doctor"};
    JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);

    // JButton jButton = new JButton("Done");
    JButton loginButton = new JButton("LOGIN");
    JButton cancelButton = new JButton("CANCEL");
    JButton facilitiesButton = new JButton("FACILITIES");
    JButton medicinesButton = new JButton("MEDICINES");

    //JCheckBox showPassword=new JCheckBox("Show Password");
    LoginFrame() {
        //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
    }

    public void setLayoutManager() {
        container.setLayout(null);

    }

    public void setLocationAndSize() {
        //Setting location and Size of each components using setBounds() method.
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 200, 100, 30);
        choiceLabel.setBounds(50, 250, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 200, 150, 30);
        // showPassword.setBounds(150,250,150,30);
        loginButton.setBounds(50, 320, 110, 30);
        cancelButton.setBounds(200, 320, 110, 30);
        facilitiesButton.setBounds(50, 390, 110, 30);
        medicinesButton.setBounds(200, 390, 110, 30);

        jComboBox.setBounds(150, 250, 150, 30);
        // jButton.setBounds(200,350,100,30);

    }

    public void addComponentsToContainer() {
        //Adding each components to the Container
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(choiceLabel);
        container.add(userTextField);
        container.add(passwordField);
        //container.add(showPassword);
        container.add(loginButton);
        container.add(cancelButton);
        container.add(facilitiesButton);
        container.add(medicinesButton);

        container.add(jComboBox);
        //container.add(jButton);
        loginButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String data = jComboBox.getItemAt(jComboBox.getSelectedIndex());

                if (data == "Admin") {
                    adminpage window1 = new adminpage();
                    window1.frame1.setVisible(true);

                }
                if (data == "Doctor") {
                    doctor window = new doctor();
                    window.frame.setVisible(true);
                }

            }
        });

        facilitiesButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                new laboratory();

            }
        });

        medicinesButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                new medicins();

            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

public class login {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        LoginFrame frame2 = new LoginFrame();
        frame2.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 10));
        frame2.setTitle("Login Form");
        frame2.setVisible(true);
        frame2.setBounds(600, 100, 373, 600);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setResizable(false);

        JLabel lblNewLabel = new JLabel("HEALTH CARE MANAGEMENT SYSTEM");
        lblNewLabel.setForeground(new Color(0, 0, 160));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(24, 66, 314, 64);
        frame2.getContentPane().add(lblNewLabel);

    }

}
