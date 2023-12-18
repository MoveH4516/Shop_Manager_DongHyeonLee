package Controller;

public class ShopController {
	
	private Utils.InputManger u;
	private dao.UserDAO uDAO;
	private dao.CartDAO cDAO;
	private dao.ItemDAO iDAO;
	private int log;
	public ShopController() {
		u = new Utils.InputManger();
		uDAO = new dao.UserDAO();
		cDAO = new dao.CartDAO();
		iDAO = new dao.ItemDAO();
		log = -1;
	}
	
	private void admin() {
		System.out.println("관리자 모드");
		while (true) {
			System.out.println("[1.아이템관리] [2.카테고리관리] [3.장바구니관리] [4.유저관리] [0.뒤로가기] ");
			int sel3 = u.getValInt("메뉴선택", 0, 4);
			if (sel3 == 0) {
				break;
			} else if (sel3 == 1) {
				int num = u.getValInt("[1]아이템 추가 [2]아이템 삭제", 1, 2);
				if (num == 1) iDAO.addItem();
				else iDAO.removeItem();
			} else if (sel3 == 2) {
				int num = u.getValInt("[1]카테고리 추가 [2]카테고리 삭제", 1, 2);
				if (num == 1) iDAO.addCategory();
				else iDAO.removeCategory();
			} else if (sel3 == 3) {
				cDAO.printCart();
			} else {
				uDAO.printUser();
			}
		}
	}
	
	private void loginMenu() {
		while (log != -1) {
			System.out.println("[1.쇼핑] [2.장바구니목록] [0.뒤로가기]");
			int sel2 = u.getValInt("메뉴선택", 0, 2);
			if (sel2 == 0) {
				log = -1;
			} else if (sel2 == 1) {
				while (true) {
					int sel3 = u.getValInt("[1]추가 [2]삭제 [3]구입 [0]뒤로가기", 0, 3);
					if (sel3 == 0) break;
					else if (sel3 == 1) {
						cDAO.addCart(uDAO, iDAO, log);
					} else if (sel3 == 2) {
						cDAO.removeCart(uDAO, iDAO, log);
					} else {
						cDAO.purchase(iDAO, uDAO, log);
					}
				}
				
			} else if (sel2 == 2) {
				cDAO.printMyCart(uDAO, log);
			}
		}
	}
	
	public void run() {
		while (true) {
			System.out.println("[1.가입] [2.탈퇴] [3.로그인] [4.로그아웃]" + "\n[5.관리자] [0.종료] ");
			int sel = u.getValInt("메뉴선택", 0, 5);
			if (sel == 0) {
				break;
			} else if (sel == 1) {
				uDAO.addUser();
			} else if (sel == 2) {
				uDAO.removeUser(cDAO);
			} else if (sel == 3) {
				log = uDAO.login();
			} else if (sel == 4) {
				log = -1;
			} else if (sel == 5) {
				admin();
			}
			loginMenu();
		}
	}
	// 	System.out.println("[1.가입] [2.탈퇴] [3.로그인] [4.로그아웃]" + "\n[100.관리자] [0.종료] ");  
	
	// 	System.out.println("[1.쇼핑] [2.장바구니목록] [0.뒤로가기]");
	
	// 	System.out.println("[1.내 장바구니] [2.삭제] [3.구입] [0.뒤로가기]");
}
// 	System.out.println("[1.아이템관리] [2.카테고리관리] [3.장바구니관리] [4.유저관리] [0.뒤로가기] ");