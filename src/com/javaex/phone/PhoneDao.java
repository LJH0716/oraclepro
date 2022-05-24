package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class PhoneDao {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";

	// 생성자

	// 메서드 getter/setter

	// 메서드 일반

	// --DB연결 메서드
	private void getConnection() {

		try {
			// JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// --자원정리 메서드
	private void close() {

		try {

			if (rs != null) {
				rs.close();
			}
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
	
	// 1. phone 리스트 메서드
		public List<PersonVo> phoneSelect() {

			// 리스트 만들기
			List<PersonVo> phoneList = new ArrayList<PersonVo>();

			getConnection();

			try {

				// 3. SQL문 준비 / 바인딩 / 실행

				// SQL문 준비
				String query = "";
				query += " select person_id ";
				query += "        , name ";
				query += "        , hp ";
				query += "        , company ";
				query += " from person ";
				

				// 바인딩
				pstmt = conn.prepareStatement(query);

				// 실행
				rs = pstmt.executeQuery();

				// 4.결과처리
				// 반복문으로 Vo 만들기 List에 추가하기
				while (rs.next()) {

					int personId = rs.getInt("person_id");
					String name = rs.getString("name");
					String hp = rs.getString("hp");
					String company = rs.getString("company");
					
					PersonVo personVo = new PersonVo(personId, name, hp, company);

					phoneList.add(personVo);

				}

			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

			close();

			return phoneList;

		}


	//2. phone 등록 메서드
	public int phoneInsert(PersonVo personVo) {

		int count = -1;

		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행

			// SQL문 준비
			String query = "";
			query += " insert into person ";
			query += " values (seq_person_id.nextval, ?, ?, ?) ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			
			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("count " + count + "건이 등록 되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return count;

	}

	//3. phone 수정 메서드
	public int phoneUpdate(PersonVo personVo) {

		int count = -1;
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행

			// SQL문 준비
			String query = "";
			query += " update person";
			query += " set hp = ?";
			query += "     ,company = ? ";
			query += " where person_id = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getHp());
			pstmt.setString(2, personVo.getCompany());
			pstmt.setInt(3, personVo.getPersonId());
			

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("count " + count + "건이 수정 되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return count;

	}


	//4. phone 삭제 메서드
	public int phoneDelete(int personId) {

		int count = -1;
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행

			// SQL문 준비
			String query = "";
			query += " delete from person ";
			query += " where person_id = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personId);

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("count " + count + "건이 삭제 되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return count;

	}

	// 5.phone 검색 메서드

	

}
