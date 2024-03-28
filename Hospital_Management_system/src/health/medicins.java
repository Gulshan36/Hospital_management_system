package health;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class medicins extends JFrame implements ActionListener {

    JFrame frame1;
    
    JLabel l0, l2;
    JComboBox c1;
    JButton b1;
    Connection con;
    ResultSet rs, rs1;
    Statement st, st1;
    PreparedStatement pst;
    String ids;
    static JTable table;
    String[] columnNames = {"NAME" , "COMPANY","EXPIRYDATE","COST"};
    String from;
    medicins() {
        setLocationRelativeTo(null);

        l0 = new JLabel("SEARCH MEDICINE");
        l0.setForeground(new Color(0, 0, 160));
        l0.setFont(new Font("Tahoma", Font.BOLD, 20));
        b1 = new JButton("Submit");
        b1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        l0.setBounds(145, 43, 196, 40);
        b1.setBounds(245, 119, 150, 30);
        b1.addActionListener(this);
        setTitle("Fetching Details....");
        getContentPane().setLayout(null);


        setVisible(true);
        setSize(500, 265);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(l0);;
        getContentPane().add(b1);
        try {
        	Class.forName("com.mysql.jdbc.Driver");
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
            st = con.createStatement();
            rs = st.executeQuery("select name from medicins");
            Vector v = new Vector();
            while (rs.next()) {
                ids = rs.getString(1);
                v.add(ids);
            }
            c1 = new JComboBox(v);
            c1.setBounds(56, 119, 150, 30);
            getContentPane().add(c1);
            st.close();
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
        	System.out.println(e);
        }
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            showTableData();
        }
    }
    public void showTableData() {
        frame1 = new JFrame("Database Search Result");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.getContentPane().setLayout(new BorderLayout());
        //TableModel tm = new TableModel();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        //DefaultTableModel model = new DefaultTableModel(tm.getData1(), tm.getColumnNames());
        //table = new JTable(model);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        from = (String) c1.getSelectedItem();
        //String textvalue = textbox.getText();
        String name = "";
        String company= "";
        String expirydate = "";
        String cost = "";
        try {
        	Class.forName("com.mysql.jdbc.Driver");
    		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
            pst = con.prepareStatement("select * from medicins where name='" + from + "'");
            ResultSet rs = pst.executeQuery();
            int i = 0;
            if (rs.next()) {
            	name = rs.getString("name");
            	company = rs.getString("company");
            	expirydate = rs.getString("expirydate");
            	cost = rs.getString("cost");
               
                model.addRow(new Object[]{name, company,expirydate,cost});
                i++;
            }
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (i == 1) {
                System.out.println(i + " Record Found");
            } else {
                System.out.println(i + " Records Found");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        frame1.getContentPane().add(scroll);
        frame1.setVisible(true);
        frame1.setSize(400, 300);
        frame1.setLocationRelativeTo(null);

    }
    public static void main(String args[]) {
        new medicins();
    }
}
