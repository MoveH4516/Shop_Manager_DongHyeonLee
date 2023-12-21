package vo;

public class Cart {
	private String userId; // 구입한 유저 id
	private String itemName; // 구입한 아이템
	
	public Cart(String userId, String itemName) {
		this.userId = userId;
		this.itemName = itemName;
	}
	@Override
	public String toString() {
		String data = userId + "\t" + itemName + "\t";
		return data;
	}
	public String getUserId() {
		return userId;
	}
	public String getItemName() {
		return itemName;
	}
	public String saveData() {
		return "%s/%s\n".formatted(userId, itemName);
	}
}
