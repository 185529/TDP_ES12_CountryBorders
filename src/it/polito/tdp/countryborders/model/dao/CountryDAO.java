package it.polito.tdp.countryborders.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.countryborders.model.Country;
import it.polito.tdp.countryborders.model.CountryPair;

public class CountryDAO {

	public boolean confinanti(Country c1, Country c2) {

		final String sql = "SELECT state1no, state2no FROM contiguity WHERE state1no=? AND state2no=? AND conttype=1";
		
		try {
			
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, c1.getcCode());
			st.setInt(2, c2.getcCode());
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()){
				
				rs.close();
				conn.close();
				
				return true;
				
			}
			
			rs.close();
			conn.close();
			
			return false;
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

		return false;
	}

	public List<Country> listCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Country> listAdiacenti(Country c) {

		final String sql = "SELECT country.CCode, country.StateAbb, country.StateNme " +
				"FROM contiguity, country "+
				"WHERE contiguity.state2no = country.CCode "+
				"AND contiguity.state1no=? "+
				"AND contiguity.conttype=1";

		try {
			
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, c.getcCode());
			
			ResultSet res = st.executeQuery();
			
			List<Country> list = new ArrayList<>();
			
			while(res.next()) {
				Country c2 = new Country(res.getInt("CCode"), res.getString("StateAbb"), res.getString("StateNme"));
			//	c2 = countryIdMap.put(c2);
				list.add(c2);
			}
			
			res.close();
			conn.close();
			
			return list;
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
			
		}
		
	}

	public List<CountryPair> listCountryAdiacenti() {
		
		final String sql = "SELECT c1.CCode as CCode1, c1.StateAbb as StateAbb1, c1.StateNme as StateNme1, " +
				"c2.CCode as CCode2, c2.StateAbb as StateAbb2, c2.StateNme as StateNme2 " +
				"FROM contiguity, country c1, country c2 " +
				"WHERE contiguity.state1no = c1.CCode " +
				"AND contiguity.state2no = c2.CCode " +
				"AND contiguity.conttype=1";

		try {
			
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
						
			ResultSet res = st.executeQuery();
			
			List<CountryPair> list = new ArrayList<>();
			
/*			while(res.next()) {
				Country c1 = countryIdMap.get(res.getInt("CCode1"));
				if(c1==null) {
					c1 = new Country(res.getInt("CCode1"), res.getString("StateAbb1"), res.getString("StateNme1"));
					c1 = countryIdMap.put(c1);
				}
				
				Country c2 = countryIdMap.get(res.getInt("CCode2"));
				if(c2==null) {
					c2 = new Country(res.getInt("CCode2"), res.getString("StateAbb2"), res.getString("StateNme2"));
					c2 = countryIdMap.put(c1);
				}

				list.add(new CountryPair(c1, c2));
			}*/
			
			res.close();
			conn.close();
			
			return list;
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
			
		}
		
	}

}
