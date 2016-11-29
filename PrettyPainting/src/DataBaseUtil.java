import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class DataBaseUtil {
	
	public Statement statement;
	public Connection con;
	
	public DataBaseUtil()
	 {
		 String url = "jdbc:mysql://localhost:3306/csc791_gaimage";
		 String username = "root";
		 String password = "root";
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			statement = con.createStatement();
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	
	public ResultSet query(String sql) {
        ResultSet result = null;

        try
        {
            result = statement.executeQuery(sql);
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
	
	
	public void executeSql(String sql) {
        try
        {
            statement.execute(sql);
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	public void disconnect()
	{
		 try {
			statement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public static String intArr2String(int[] input)
	{
		String resultStr= Arrays.toString(input).replaceAll("\\[|\\]| ", "");
		return resultStr;
	}

	public static int[] string2IntArr(String input)
	{
		String[] tpst=input.split(",");
		int[] result= new int[tpst.length];
		for (int i = 0; i < tpst.length; i++) {

			result[i]=Integer.parseInt(tpst[i]);
		}

		return result;
	}
	
}
