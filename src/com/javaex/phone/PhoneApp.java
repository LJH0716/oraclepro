package com.javaex.phone;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) throws IOException {

		boolean action = true;
		Scanner sc = new Scanner(System.in);

		PhoneDao phoneDao = new PhoneDao();

		System.out.println("****************************************");
		System.out.println("*            전화번호 관리 프로그램           *");
		System.out.println("****************************************");
		System.out.println("");

		while (action) {
			System.out.println("");
			System.out.print("1.리스트  2.등록  3.수정  4.삭제  5.검색  6.종료");
			System.out.println("");
			System.out.println("-----------------------------------------");
			System.out.print(">메뉴번호: ");
			
			int num = sc.nextInt();

			switch (num) {
			
			case 1:
				System.out.println("<1.리스트>");
				
				List<PersonVo> phoneList = phoneDao.phoneSelect();
				for(int i = 0; i<phoneList.size(); i++) {
					
					PersonVo personVo = phoneList.get(i);
					System.out.println(personVo.getPersonId()+". "+personVo.getName()+"  "+personVo.getHp()+"  "+personVo.getCompany());
					
				}
				break;

			case 2:
				System.out.println("<2.등록>");
				
				System.out.print("이름 > ");
				String iname = sc.next();
				
				System.out.print("휴대전화 > ");
				String ihp = sc.next();
				
				System.out.print("회사번호 > ");
				String icompany = sc.next();
				
				PersonVo in = new PersonVo(iname, ihp, icompany);

				int incount = phoneDao.phoneInsert(in);
				
				System.out.println("[" + incount+"건 등록되었습니다.]");
				
				break;
				
			case 3:
				System.out.println();
				System.out.println("<3.수정>");
				System.out.println();
				
				sc.nextLine();
				
				System.out.print("번호 > ");
				int upersonId = sc.nextInt();
				
				sc.nextLine();
				
				System.out.print("이름 > ");
				String uname = sc.nextLine();
				
				System.out.print("휴대전화 > ");
				String uhp = sc.nextLine();
				
				System.out.print("회사번호 > ");
				String ucompany = sc.nextLine();
				
				PersonVo up = new PersonVo(upersonId, uname, uhp, ucompany);
				
				int upcount = phoneDao.phoneUpdate(up);
				
				System.out.println("[" + upcount+"건 수정되었습니다.]");
				
				break;

			case 4:
				System.out.println("<4.삭제>");
				
				System.out.print("> 번호 : ");
				int dpersonId = sc.nextInt();
				
				
				int dcount = phoneDao.phoneDelete(dpersonId);

				System.out.println("[" + dcount+"건 삭제되었습니다.]");
				
				
				break;

			case 5:
				System.out.println("<5.검색>");
				System.out.print("검색어 > ");
			
				
				break;

			case 6:
				System.out.println("<6.종료>");
				
				action = false;
				
				break;

			default:
				System.out.println("[다시 입력해 주세요.]");
				
				break;

			}

		}

		System.out.println("****************************************");
		System.out.println("*                감사합니다               *");
		System.out.println("****************************************");
		System.out.println("");

		
		sc.close();
	}

}
