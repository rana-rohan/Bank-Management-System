package bank.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Jdbc
{
    Connection connection;
    Statement statement;
    public Jdbc()
    {
        try
        {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root","1234");
            statement = connection.createStatement();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
