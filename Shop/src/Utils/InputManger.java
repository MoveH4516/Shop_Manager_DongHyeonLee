package Utils;

import java.util.Scanner;

public class InputManger {
	private Scanner sc = new Scanner(System.in);
	
	public int getValInt(String msg, int start, int end) {
		int num = 0;
		while (true) {
			try {
				System.out.println(msg);
				num = sc.nextInt();
				if (num < start || num > end) {
					System.out.printf("%d ~ %d 숫자 입력\n");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("숫자만 입력");
				sc.nextLine();
			}
		}
		return num;
	}
	
	
	
	public String getValStr(String msg) {
		System.out.println(msg);
		String data = sc.next();
		return data;
	}
	
}
