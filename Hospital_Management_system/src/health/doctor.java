package health;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class doctor {

	JFrame frame;
	/**
	 * @wbp.nonvisual location=-17,-16
	 */DefaultTableModel model;

	private final JPanel panel = new JPanel();
	private JTextField id;
	private JTextField name;
	private JTextField specilist;
	private JTextField qualification;
	private JTextField age;
	private JTextField roomno;
	private JTextField gender;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doctor window = new doctor();
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
	public doctor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.info);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NAME");
		lblNewLabel.setBounds(70, 80, 68, 29);
		lblNewLabel.setBackground(SystemColor.controlText);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblSpecilist = new JLabel("SPECILIST");
		lblSpecilist.setBounds(70, 110, 78, 29);
		lblSpecilist.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSpecilist.setBackground(Color.BLACK);
		frame.getContentPane().add(lblSpecilist);
		
		JLabel lblQualification = new JLabel("QUALIFICATION");
		lblQualification.setBounds(70, 140, 117, 29);
		lblQualification.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblQualification.setBackground(Color.BLACK);
		frame.getContentPane().add(lblQualification);
		
		JLabel lblNewLabel_2_1 = new JLabel("AGE");
		lblNewLabel_2_1.setBounds(70, 170, 68, 29);
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_2_1.setBackground(Color.BLACK);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("ROOM NUMBER");
		lblNewLabel_2_2.setBounds(70, 200, 117, 29);
		lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_2_2.setBackground(Color.BLACK);
		frame.getContentPane().add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("GENDER");
		lblNewLabel_2_3.setBounds(70, 230, 68, 29);
		lblNewLabel_2_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_2_3.setBackground(Color.BLACK);
		frame.getContentPane().add(lblNewLabel_2_3);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(70, 47, 68, 29);
		lblId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblId.setBackground(Color.BLACK);
		frame.getContentPane().add(lblId);
		
		id = new JTextField();
		id.setBounds(194, 53, 150, 19);
		frame.getContentPane().add(id);
		id.setColumns(10);
		
		name = new JTextField();
		name.setBounds(194, 82, 150, 19);
		name.setColumns(10);
		frame.getContentPane().add(name);
		
		specilist = new JTextField();
		specilist.setBounds(194, 110, 150, 19);
		specilist.setColumns(10);
		frame.getContentPane().add(specilist);
		
		qualification = new JTextField();
		qualification.setBounds(194, 140, 150, 19);
		qualification.setColumns(10);
		frame.getContentPane().add(qualification);
		
		age = new JTextField();
		age.setBounds(194, 170, 150, 19);
		age.setColumns(10);
		frame.getContentPane().add(age);
		
		roomno = new JTextField();
		roomno.setBounds(194, 200, 150, 19);
		roomno.setColumns(10);
		frame.getContentPane().add(roomno);
		
		gender = new JTextField();
		gender.setBounds(194, 230, 150, 19);
		gender.setColumns(10);
		frame.getContentPane().add(gender);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(354, 47, 700, 483);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		
		model = new DefaultTableModel();
		scrollPane.setViewportView(table);
		Object[] column = {"ID", "NAME","SPECILIST","QUALIFICATION","AGE","ROOM NO","GENDER"};
		Object[] row = new Object[7];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		
		
		
		JButton btnadd = new JButton("ADD");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id.getText().equals("") ||name.getText().equals("") ||specilist.getText().equals("") ||qualification.getText().equals("") ||age.getText().equals("") ||roomno.getText().equals("") ||gender.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please fill all the information");
				}
				else
				{
				row[0] = id.getText();
				row[1] = name.getText();
				row[2] = specilist.getText();
				row[3] = qualification.getText();
				row[4] = age.getText();
				row[5] = roomno.getText();
				row[6] = gender.getText();
				
				model.addRow(row);
				try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");

                String query = "INSERT INTO doctor values('" + row[0] + "','" + row[1] + "','" + row[2] + "','" +
                		row[3] + "','" + row[4] + "','" + row[5] + "','" + row[6] + "')";

                Statement sta = connection.createStatement();
                sta.executeUpdate(query);
				id.setText("");
				name.setText("");
				specilist.setText("");
				qualification.setText("");
				age.setText("");
				roomno.setText("");
				gender.setText("");
				JOptionPane.showMessageDialog(null,"Doctor information stored successfully");
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Error", null, JOptionPane.ERROR_MESSAGE);
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
					if(i>=0)
					{
					
					model.removeRow(i);
					try {
		                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
		                String query = "delete from doctor where id ='" + id.getText() + "'";
		                Statement sta = connection.createStatement();
		                PreparedStatement pst = connection.prepareStatement(query);
		                pst.execute();
						id.setText("");
						name.setText("");
						specilist.setText("");
						qualification.setText("");
						age.setText("");
						roomno.setText("");
						gender.setText("");
						}catch(Exception e1) {
							System.out.println(e1);
						}
					JOptionPane.showMessageDialog(null,"Doctor information Deleted successfully");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Cant Delete!");
					}
				}
		});
		btnDelete.setBounds(211, 370, 116, 52);
		frame.getContentPane().add(btnDelete);
		
		JButton btnSearch = new JButton("UPDATE");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
   
                	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
                    String query ="update doctor set ID='" + id.getText() + 
                    		"',NAME='" + name.getText() + 
                    		"',SPECILIST='" + specilist.getText() + 
                    		"',QUALIFICATION='" + qualification.getText() + 
                    		"',AGE='" + age.getText() + 
                    		"',ROOMNUMBER='" + roomno.getText() + 
                    		"',GENDER='" + gender.getText() + 
                    		"' where ID = '" + id.getText() + "'";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.execute();
                    JOptionPane.showMessageDialog(null,"Data updated!!!");
                    pst.close();
                }
                catch(Exception ex)
                {
                	 JOptionPane.showMessageDialog(null,ex);
                }


            }
        });
		
		btnSearch.setBounds(70, 445, 117, 52);
		frame.getContentPane().add(btnSearch);
		
		JButton btnExit = new JButton("CLEAR");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				id.setText("");
				name.setText("");
				specilist.setText("");
				qualification.setText("");
				age.setText("");
				roomno.setText("");
				gender.setText("");
				
			}
		});
		btnExit.setBounds(211, 445, 116, 52);
		frame.getContentPane().add(btnExit);
		
		JLabel lblNewLabel_1 = new JLabel("DOCTOR DETAILS");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setForeground(new Color(0, 0, 160));
		lblNewLabel_1.setBounds(500, 10, 169, 27);
		frame.getContentPane().add(lblNewLabel_1);
		frame.setBounds(150, 150, 1100, 618);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
        
}
