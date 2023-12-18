package vo;

public class Item {
	public String name;
	int price;
	public String category; // 카테고리 // 육류 , 과자 , 어류 , 과일 등등
	public Item(String name, int price, String category) {
		this.name = name;
		this.price = price;
		this.category = category;
	}
	
	public Item(String category) {
		this.category = category;
	}
}
