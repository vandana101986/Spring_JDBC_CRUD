package com.gl.springJdbc.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;	
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.gl.springJdbc.entity.Employee;

public class SpringJdbcMain {

	
	//for establishing/releasing connection
	static JdbcTemplate jdbcTemplateObj;
	//for fetching the data source from DB
	static SimpleDriverDataSource dataSourceObj;
	
	//configure DB- url & login credentials to connect to DB
	static String USERNAME = "root";
	static String PASSWORD = "Admin";
	static String URL = "jdbc:mysql://localhost:3306/springJDBC";
	
	public static SimpleDriverDataSource getDBConnection() {
		
		//get the data source from DB
		dataSourceObj = new SimpleDriverDataSource();
		
		try {
			dataSourceObj.setDriver(new com.mysql.cj.jdbc.Driver());
			dataSourceObj.setUrl(URL);
			dataSourceObj.setUsername(USERNAME);
			dataSourceObj.setPassword(PASSWORD);
			
		}catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return dataSourceObj;
	}
	public static void main(String[] args) {

		//With the help of data source, create connection
		jdbcTemplateObj =new JdbcTemplate(getDBConnection());
		
		System.out.println("The Connection : "+jdbcTemplateObj);
		
		if(null != jdbcTemplateObj) {
			
		
		//Creation of Prepared queries
		//4.SQL INSERT query.
		String insertQuery = "INSERT into Employee (name, email, address, phoneno) VALUES (?,?,?,?)";
			for(int i=1;i<5;i++) {
				jdbcTemplateObj.update(insertQuery, "Employee"+i,"Employee"+i+"@gmail.com","Bombay","9742552665");
			}
		
	//5.SQL UPDATE query
			String updateQuery = "UPDATE Employee SET email = ? WHERE name = ?";
			jdbcTemplateObj.update(updateQuery, "vandana@gmail.com", "Employee1");
		
		
	//6.SQL READ query.
			String readQuery = "SELECT * FROM Employee";
			
			List<Employee> employeeList = jdbcTemplateObj.query(readQuery, new RowMapper<Employee>() 
			{
				public Employee mapRow(ResultSet result, int rownum) throws SQLException
				{
					Employee emp = new Employee();
					
					emp.setName(result.getString("name"));
					emp.setEmail(result.getString("email"));
					emp.setAddress(result.getString("address"));
					emp.setPhoneno(result.getString("phoneno"));
					
					return emp;
				}
			});		
			System.out.println("Employee List :");
			System.out.println(employeeList);
		
	 		//7.SQL DELETE query.
			String deleteQuery = "DELETE FROM Employee where name = ?";
			jdbcTemplateObj.update(deleteQuery, "Employee2");	
			}
		
		//8.Close the connection.
		else
		{
			System.out.println("Please check connection");
		}
	}
}


