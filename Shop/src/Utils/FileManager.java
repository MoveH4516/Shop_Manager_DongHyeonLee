package Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import dao.CartDAO;
import dao.ItemDAO;
import dao.UserDAO;

public class FileManager {
	// cart.txt
	// user.txt
	// item.txt
	private static final String CUR_PATH = System.getProperty("user.dir") + "\\src\\_Main\\"; 

	private static String loadFile(String filename) {
		String data = "";
		try (FileReader fr = new FileReader(CUR_PATH + filename);
				BufferedReader br = new BufferedReader(fr)) {
			while (true) {
				String s = br.readLine();
				if (s == null) break;
				data += s + "\n";
			}
			data = data.substring(0, data.length() - 1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public void loadFile (CartDAO cartDAO, ItemDAO itemDAO, UserDAO userDAO) {
		String userData = loadFile("User.txt");
		String cartData = loadFile("Cart.txt");
		String itemData = loadFile("Item.txt");
		
		userDAO.loadData(userData);
		cartDAO.loadData(cartData);
		itemDAO.loadData(itemData);
	}
	
	public void saveFile(CartDAO cartDAO, ItemDAO itemDAO, UserDAO userDAO) {
		getData("User.txt", userDAO.saveData());
		getData("Cart.txt", cartDAO.saveData());
		getData("Item.txt", itemDAO.saveData());
	}
	
	private static void getData(String filename, String data) {
		try (FileWriter fw = new FileWriter(CUR_PATH + filename)) {
			fw.write(data);
			System.out.println("데이터 저장");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
