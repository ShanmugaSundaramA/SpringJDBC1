package com.sundar.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class StudentAddressResultSetExtractor implements ResultSetExtractor<Map<String, List<String>>> {

	@Override
	public Map<String, List<String>> extractData(ResultSet res) throws SQLException, DataAccessException {

		Map<String, List<String>> gropByAddrss = new HashMap<>();

		while (res.next()) {

			String address = res.getString("Student_Address");
			String name = res.getString("Student_Name");

			List<String> names = gropByAddrss.get(address);
			
			if (names == null) {
				
				List<String> newNames = new ArrayList<>();
				newNames.add(name);
				gropByAddrss.put(address, newNames);
			
			} else {
				names.add(res.getString("Student_Name"));
			}
		}
		return gropByAddrss;
	}

}
