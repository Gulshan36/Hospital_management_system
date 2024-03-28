package health;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

public class adminpage {

	JFrame frame1;

	/**
	 * Launch the application.
     * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
                    try {
                        adminpage window1 = new adminpage();
                        window1.frame1.setVisible(true);
                    } catch (Exception e) {
                    }
                });
	}

	/**
	 * Create the application.
	 */
	public adminpage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame1 = new JFrame();
		frame1.getContentPane().setForeground(new Color(0, 0, 160));
		frame1.setBounds(600,300, 501, 270);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ADMIN PORTAL");
		lblNewLabel.setForeground(new Color(0, 0, 160));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(142, 47, 211, 46);
		frame1.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("DOCTOR DETAILS");
		btnNewButton.addActionListener((ActionEvent e) -> {
                    frame1.setVisible(false);
                    doctor window = new doctor();
                    window.frame.setVisible(true);
                });
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBounds(52, 126, 172, 52);
		frame1.getContentPane().add(btnNewButton);
		
		JButton btnPatientDetails = new JButton("PATIENT DETAILS");
		btnPatientDetails.addActionListener((ActionEvent e) -> {
                    frame1.setVisible(false);
                    patient window = new patient();
                    window.frame.setVisible(true);
                });
		btnPatientDetails.setForeground(new Color(0, 0, 0));
		btnPatientDetails.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnPatientDetails.setBounds(253, 126, 172, 52);
		frame1.getContentPane().add(btnPatientDetails);
	}
}
