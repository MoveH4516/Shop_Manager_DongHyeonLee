package vo;

public class Cart {
	public String userId; // 구입한 유저 id
	public String itemName; // 구입한 아이템
	
	public Cart(String userId, String itemName) {
		this.userId = userId;
		this.itemName = itemName;
	}
	@Override
	public String toString() {
		String data = userId + "\t" + itemName + "\t";
		return data;
	}
	
}
