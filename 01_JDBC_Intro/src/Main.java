import com.mysql.cj.xdevapi.PreparableStatement;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

//       Scanner scanner =new Scanner(System.in);
//       System.out.print("Password: ");
//       scanner.nextLine();
//

        //Connect to DB
        Properties credentials = new Properties();
        credentials.setProperty("user", "root");
        credentials.setProperty("password","root");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni",credentials);
        
        //Execute Query

        PreparedStatement updateStatement = connection.prepareStatement("UPDATE employees SET first_name = ? WHERE employee_id = ?");
        updateStatement.setString(1,"Changed");
        updateStatement.setLong(2,3);

//        boolean updateResult = updateStatement.execute();
        int updateResult= updateStatement.executeUpdate();
        System.out.println(updateResult);

        if(updateResult>1) {
            connection.rollback();
        }


        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM  employees WHERE salary > ?  LIMIT 10");
        preparedStatement.setDouble(1,17000.0);
        ResultSet resultSet = preparedStatement.executeQuery();

        //Print Result

        while (resultSet.next()) {
            long id =resultSet.getLong("employee_id");
//            resultSet.getLong(1);
            String firstName = resultSet.getString("first_name");
            double salary = resultSet.getDouble("salary");
            Timestamp hireDate = resultSet.getTimestamp("hire_date");

            System.out.printf("%d - %s %.2f %s%n",
                    id, firstName, salary, hireDate);

        }
    }
}