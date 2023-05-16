package com.java.spring.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;
@Repository
public class Studentdao {
	
	@Autowired
	JdbcTemplate template;
	 
	public JSONObject studentall()
	{
		
		CallableStatementCreator callableStatementCreator = new CallableStatementCreator() {
			
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				CallableStatement cst = con.prepareCall("{call getAllStudentDetails(?)}");
				cst.registerOutParameter(1, OracleTypes.CURSOR);
				return cst;
			}
		};
		//**********************************************************************************************
		CallableStatementCallback<JSONObject> callableStatementCallback = new CallableStatementCallback<JSONObject>() {

			public JSONObject doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				 JSONArray jarray = new JSONArray();
				 JSONObject jsonresult = new JSONObject();
				JSONObject js = null;
				cs.execute();
				ResultSet rs = (ResultSet) cs.getObject(1);
				try {
					while (rs.next()) {
						js = new JSONObject();
						js.put("rollno", rs.getInt(1));
						js.put("name", rs.getString(2));
						js.put("marks", rs.getInt(3));
						jarray.put(js);
					}
					jsonresult.put("data", jarray);
					rs.close();
					cs.close();

				} catch (Exception e) {
					// TODO: handle exception
				}
				return jsonresult;
			}
		};
		//***********************************************************************************************************
	
		JSONObject jsonresult=template.execute(callableStatementCreator,callableStatementCallback);
		return jsonresult;
	}

}
