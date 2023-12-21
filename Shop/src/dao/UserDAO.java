package dao;

import java.util.ArrayList;
import vo.User;

public class UserDAO {
	private ArrayList<vo.User> uList;
	private Utils.InputManger u;
	
	public ArrayList<vo.User> getUList() {
		return uList;
	}

	public UserDAO() {
		uList = new ArrayList<vo.User>();
		u = new Utils.InputManger();
	}
	
	public void addUser() {
		String id = u.getValStr("id");
		if (checkid(id) != -1) {
			System.out.println("아이디 중복");
			return;
		}
		String pw = u.getValStr("pw");
		String name = u.getValStr("name");
		vo.User user = new User(id, pw, name);
		uList.add(user);
	}
	
	public void removeUser(dao.CartDAO cDAO) {
		String id = u.getValStr("삭제할 아이디");
		int idx = checkid(id);
		if (idx == -1) {
			System.out.println("일치하는 아이디가 없습니다.");
			return;
		}
		for (int i = 0; i < cDAO.getCList().size(); i++) {
			if (uList.get(idx).getId().equals(cDAO.getCList().get(i).getUserId())) {
				cDAO.getCList().remove(i);
				i--;
			}
		}
		uList.remove(idx);
		System.out.println("탈퇴완료");
	}
	
	private int checkid(String id) {
		int num = -1;
		for (int i = 0; i < uList.size(); i++) {
			if (uList.get(i).getId().equals(id)) {
				num = i;
				break;
			}
		}
		return num;
	}
	
	public int login() {
		String id = u.getValStr("id");
		int idx = checkid(id);
		if (idx == -1) {
			System.out.println("아이디가 일치하지 않습니다.");
			return -1;
		}
		String pw = u.getValStr("pw");
		if (!pw.equals(uList.get(idx).getPw())) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return -1;
		}
		System.out.println(uList.get(idx).getId() + " 로그인 완료");
		return idx;
	}
	
	public void printUser() {
		System.out.println("아이디 \t비밀번호 \t닉네임");
		for (int i = 0; i < uList.size(); i++) {
			System.out.print(uList.get(i));
		}
	}
	
}
