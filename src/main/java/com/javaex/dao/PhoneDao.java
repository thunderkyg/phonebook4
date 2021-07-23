package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.PersonVo;

public class PhoneDao {
	
	//Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "phonedb";
	String pw = "phonedb";
	
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	private void close() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}
	
	//SelectAll
	public List<PersonVo> getPersonList() {

		// DB에서 리스트 가져옴
		List<PersonVo> arrayList = new ArrayList<PersonVo>();

		getConnection();

		try {

			String query = "";
			query += " select person_id, ";
			query += "		 name, ";
			query += "       hp, ";
			query += "       company ";
			query += " from person ";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String Name = rs.getString("name");
				String Hp = rs.getString("hp");
				String Company = rs.getString("company");

				PersonVo personVo = new PersonVo(personId, Name, Hp, Company);

				arrayList.add(personVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return arrayList;
	}
	
	//Insert
	public int personInsert(PersonVo personVo) {
		int count = -1;

		getConnection();

		try {
			String query = "";
			query += " insert into person ";
			query += " values(seq_person_id.nextval, ?, ?, ?) ";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());

			count = pstmt.executeUpdate();

			// 결과처리
			System.out.println(count + "건이 등록되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return count;
	}
	
	
	//Update
	public int personUpdate(PersonVo personVo) {
		int count = -1;

		getConnection();

		try {
			String query = "";
			query += " update person ";
			query += " set name = ?, ";
			query += "     hp = ?, ";
			query += "     company = ? ";
			query += " where person_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			pstmt.setInt(4, personVo.getPersonId());

			count = pstmt.executeUpdate();
			// 결과처리
			System.out.println(count + "건이 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return count;
	}
	
	//Delete
	public int personDelete(int deleteNo) {
		int count = -1;

		getConnection();

		try {
			String query = "";
			query += " delete from person ";
			query += " where person_id = ? ";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, deleteNo);

			count = pstmt.executeUpdate();
			System.out.println(count + "건이 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return count;
	}
	//Search
	
	public List<PersonVo> personSearch(String search) {

		// DB에서 리스트 가져옴
		List<PersonVo> arrayList = new ArrayList<PersonVo>();

		getConnection();

		try {

			String query = "";
			query += " select person_id, ";
			query += "		 name, ";
			query += "       hp, ";
			query += "       company ";
			query += " from person ";
			query += " where person_id like '%" + search + "%'";
			query += " or name like '%" + search + "%'";
			query += " or hp like '%" + search + "%'";
			query += " or company like '%" + search + "%'";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String Name = rs.getString("name");
				String Hp = rs.getString("hp");
				String Company = rs.getString("company");

				PersonVo personVo = new PersonVo(personId, Name, Hp, Company);

				arrayList.add(personVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return arrayList;
	}
	
	public PersonVo getPerson (int personId) {
		
		PersonVo personVo = null;
		getConnection();
		
		try {
			//Query
			String query = "";
			query += " select person_id, ";
			query += " 		  name, ";
			query += " 		  hp, ";
			query += " 		  company ";
			query += " from person ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personId);
			rs = pstmt.executeQuery();
			

			while(rs.next()) {
				int pid = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				personVo = new PersonVo(pid, name, hp, company);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return personVo;
	}

}
