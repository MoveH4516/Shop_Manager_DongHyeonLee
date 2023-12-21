package dao;

import java.util.ArrayList;
import vo.Item;

public class ItemDAO {
	private ArrayList<vo.Item> iList;
	private ArrayList<vo.Item> cateList;
	private Utils.InputManger u;
	
	public ArrayList<vo.Item> getIList() {
		return iList;
	}
	
	public String saveData() {
		if (iList.size() == 0) return "";
		String data = "";
		for (Item i : iList) {
			data += i.saveData();
		}
		return data;
	}
	
	public void loadData(String data) {
		String[] temp = data.split("\n");
		for (int i = 0; i < temp.length; i++) {
			String[] temp2 = temp[i].split("/");
			Item item = new Item(temp2[0], Integer.parseInt(temp2[1]), temp2[2]);
			iList.add(item);
		}
	}
	
	public ItemDAO() {
		iList = new ArrayList<vo.Item>();
		u = new Utils.InputManger();
		cateList = new ArrayList<vo.Item>();
	}
	
	public void addItem() {
		String name = u.getValStr("아이템 이름");
		if (checkItem(name) != -1) {
			System.out.println("중복 아이템 존재");
			return;
		}
		int price = u.getValInt("가격", 0, 1000000);
		String category = u.getValStr("카테고리");
		if (checkCategory(category) == -1) {
			System.out.println("일치하는 카테고리가 없습니다.");
			return;
		}
		vo.Item item = new vo.Item(name, price, category);
		iList.add(item);
	}
	
	public void removeItem() {
		String name = u.getValStr("삭제할 아이템");
		int idx = checkItem(name);
		if (idx == -1) {
			System.out.println("일치하는 아이템이 없습니다.");
			return;
		}
		iList.remove(idx);
		System.out.println("삭제 완료");
	}
	
	public void addCategory() {
		String cate = u.getValStr("카테고리 이름");
		if (checkCategory(cate) != -1) {
			System.out.println("카테고리 중복");
			return;
		}
		cateList.add(new vo.Item(cate));
	}
	
	public void removeCategory() {
		String cate = u.getValStr("삭제할 카테고리");
		int idx = checkCategory(cate);
		if (idx == -1) {
			System.out.println("일치하는 카테고리가 없습니다.");
			return;
		}
		for (int i = 0; i < iList.size(); i++) {
			if (iList.get(i).getCategory().equals(cate)) {
				iList.remove(i);
				i--;
			}
		}
		cateList.remove(idx);
		System.out.println("삭제완료");
	}
	
	private int checkCategory(String category) {
		int num = -1;
		for (int i = 0; i < cateList.size(); i++) {
			if (cateList.get(i).getCategory().equals(category)) {
				num = i;
				return num;
			}
		}
		return num;
	}
	
	private int checkItem(String name) {
		int num = -1;
		for (int i = 0; i < iList.size(); i++) {
			if (iList.get(i).getName().equals(name)) {
				num = i;
				return num;
			}
		}
		return num;
	}
	
}
