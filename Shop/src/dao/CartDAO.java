package dao;

import java.util.ArrayList;
import vo.Cart;

public class CartDAO {
	private ArrayList<vo.Cart> cList;
	private Utils.InputManger u;
	
	public CartDAO() {
		cList = new ArrayList<vo.Cart>();
		u = new Utils.InputManger();
	}
	
	public String saveData() {
		if (cList.size() == 0) return "";
		String data = "";
		for (Cart c : cList) {
			data += c.saveData();
		}
		return data;
	}
	
	public void loadData(String data) {
		String[] temp = data.split("\n");
		for (int i = 0; i < temp.length; i++) {
			String[] temp2 = temp[i].split("/");
			Cart c = new Cart(temp2[0], temp2[1]);
			cList.add(c);
		}
	}
	
	public ArrayList<vo.Cart> getCList() {
		return cList;
	}

	public void printCart() {
		System.out.println("아이디 \t상품");
		for (int i = 0; i < cList.size(); i++) {
			System.out.println(cList.get(i));
		}
	}
	
	public void printMyCart(UserDAO uDAO, int log) {
		System.out.println(uDAO.getUList().get(log).getId() + "의 장바구니");
		for (int i = 0; i < cList.size(); i++) {
			if (uDAO.getUList().get(log).getId().equals(cList.get(i).getUserId())) {
				System.out.println(cList.get(i));
			}
		}
	}
	
	public void addCart(UserDAO uDAO, ItemDAO iDAO, int log) {
		String item = u.getValStr("구매할 아이템");
		if (checkItem(iDAO, item) == -1) {
			System.out.println("일치하는 아이템이 없습니다.");
			return;
		}
		cList.add(new vo.Cart(uDAO.getUList().get(log).getId(), item));
		System.out.println(cList.get(cList.size() - 1) + "추가 완료");
	}
	
	public void removeCart(UserDAO uDAO, ItemDAO iDAO, int idx) {
		int num = u.getValInt("[1]전체삭제 [2]특정 상품 삭제 [3]최근 상품 삭제", 1, 3);
		if (num == 1) {
			for (int i = 0; i < cList.size(); i++) {
				if (uDAO.getUList().get(idx).getId().equals(cList.get(i).getUserId())) {
					cList.remove(i);
					i--;
				}
			}
		} else if (num == 2) {
			String item = u.getValStr("삭제할 아이템");
			if (checkItem(iDAO, item) == -1) {
				System.out.println("해당 상품이 없습니다.");
				return;
			}
			for (int i = 0; i < cList.size(); i++) {
				if (item.equals(cList.get(i).getItemName()) && uDAO.getUList().get(idx).getId().equals(cList.get(i).getUserId())) {
					cList.remove(i);
					i--;
				}
			}
		} else {
			cList.remove(cList.size() - 1);
		}
	}
	
	public int purchase(ItemDAO iDAO, UserDAO uDAO, int log) {
		int money = 0;
		for (int i = 0; i < cList.size(); i++) {
			if (cList.get(i).getUserId().equals(uDAO.getUList().get(log).getId())) {
				for (int j = 0; j < iDAO.getIList().size(); j++) {
					if (iDAO.getIList().get(j).getName().equals(cList.get(i).getItemName())) {
						money += iDAO.getIList().get(j).getPrice();
					}
				}
			}
		}
		for (int i = 0; i < cList.size(); i++) {
			if (uDAO.getUList().get(log).getId().equals(cList.get(i).getUserId())) {
				cList.remove(i);
				i--;
			}
		}
		System.out.println("총 구매 금액 : " + money);
		return money;
	}
	
	private int checkItem(ItemDAO iDAO, String item) {
		int num = -1;
		for (int i = 0; i < iDAO.getIList().size(); i++) {
			if (item.equals(iDAO.getIList().get(i).getName())) {
				num = i;
				return num;
			}
		}
		return num;
	}
}
