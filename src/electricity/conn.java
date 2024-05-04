package electricity;
import java.sql.*;
public class conn {
    Connection c;
    Statement s;
    conn(){
        try{
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","amlanshanker2005");
            s = c.createStatement();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
