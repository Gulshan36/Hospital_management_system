import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataRetrievalFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public DataRetrievalFrame() {
        setTitle("Data Retrieval Example");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);

        // Add columns to the table model
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Specialist");
        tableModel.addColumn("Qualification");
        tableModel.addColumn("Age");
        tableModel.addColumn("Room No");
        tableModel.addColumn("Gender");

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        // Retrieve data from the database and populate the table
        retrieveDataFromDatabase();
    }

    private void retrieveDataFromDatabase() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM doctor");

            while (resultSet.next()) {
                String id = resultSet.getString("ID");
                String name = resultSet.getString("NAME");
                String specialist = resultSet.getString("SPECILIST");
                String qualification = resultSet.getString("QUALIFICATION");
                String age = resultSet.getString("AGE");
                String roomNo = resultSet.getString("ROOMNUMBER");
                String gender = resultSet.getString("GENDER");

                // Add a new row to the table with the retrieved data
                tableModel.addRow(new String[]{id, name, specialist, qualification, age, roomNo, gender});
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DataRetrievalFrame frame = new DataRetrievalFrame();
            frame.setVisible(true);
        });
    }
}
