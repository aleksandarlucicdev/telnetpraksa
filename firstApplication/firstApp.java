import java.sql.*;
class FirstApp{
    public static void main(String ... args){
	try{
	    String dbUrl="jdbc:mysql://localhost:3306/student?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
	    String username = "root";
	    String password = "password";

	    Connection myConnection=DriverManager.getConnection(dbUrl, username, password);
	    Statement myStatement=myConnection.createStatement();

	    ResultSet myResultSet=myStatement.executeQuery("Select * from students");

	    while(myResultSet.next()){
		System.out.println("Student fullname:" + myResultSet.getString("firstname")+" "+ myResultSet.getString("lastname"));
	    }
	}catch (Exception e){
	    System.out.println(e.getMessage());
	}
    }
}
