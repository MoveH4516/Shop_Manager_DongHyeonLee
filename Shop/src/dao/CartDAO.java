package dao;

import java.util.ArrayList;

public class CartDAO {
	public ArrayList<vo.Cart> cList;
	private Utils.InputManger u;
	public CartDAO() {
		cList = new ArrayList<vo.Cart>();
		u = new Utils.InputManger();
	}
	
	public void printCart() {
		System.out.println("아이디 \t상품");
		for (int i = 0; i < cList.size(); i++) {
			System.out.println(cList.get(i));
		}
	}
	
	public void printMyCart(UserDAO uDAO, int log) {
		System.out.println(uDAO.uList.get(log).id + "의 장바구니");
		for (int i = 0; i < cList.size(); i++) {
			if (uDAO.uList.get(log).id.equals(cList.get(i).userId)) {
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
		cList.add(new vo.Cart(uDAO.uList.get(log).id, item));
		System.out.println(cList.get(cList.size() - 1) + "추가 완료");
	}
	
	public void removeCart(UserDAO uDAO, ItemDAO iDAO, int idx) {
		int num = u.getValInt("[1]전체삭제 [2]특정 상품 삭제 [3]최근 상품 삭제", 1, 3);
		if (num == 1) {
			for (int i = 0; i < cList.size(); i++) {
				if (uDAO.uList.get(idx).id.equals(cList.get(i).userId)) {
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
				if (item.equals(cList.get(i).itemName) && uDAO.uList.get(idx).id.equals(cList.get(i).userId)) {
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
			if (cList.get(i).userId.equals(uDAO.uList.get(log).id)) {
				for (int j = 0; j < iDAO.iList.size(); j++) {
					if (iDAO.iList.get(j).name.equals(cList.get(i).itemName)) {
						money += iDAO.iList.get(j).price;
					}
				}
			}
		}
		System.out.println("총 구매 금액 : " + money);
		return money;
	}
	
	private int checkItem(ItemDAO iDAO, String item) {
		int num = -1;
		for (int i = 0; i < iDAO.iList.size(); i++) {
			if (item.equals(iDAO.iList.get(i).name)) {
				num = i;
				return num;
			}
		}
		return num;
	}
}
