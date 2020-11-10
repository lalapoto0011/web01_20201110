package test;

import javax.swing.JOptionPane;

public class Test03 {

	public static void main(String[] args) {
		
		Command insert = () -> {
	         System.out.println("InsertCommand exec ..");
	      };

		Command delete = () -> { //람다 표현
				System.out.println(" DeleteCommand exec .. "); //오버라이딩 선언 없어도 오버라이딩이 됨? -> Lambda 표현식
		};
		
		Command update = new UpdateCommand();

		
		String cmd = JOptionPane.showInputDialog("1.insert, 2.delete, 3.update 4.quit"); //메뉴 구성을 하기 불편하니 이렇게 붙임?
		//1.insert, 2.delete, 3.update 4.quit
		//1번 누르면 인서트할거고 2번 누르면 딜리트 작업 할거고~~ 4번 누르면 종료할거고
		switch (cmd) {
		case "1" : //insert
			insert.exec();
			break;
			
		case "2" : //delete
			delete.exec();
			break;
			
		case "3" : //update
			update.exec();
			break;
			
		case "4" : //quit
			System.out.println("Quit");
			return;
			
		default :
			System.out.println("메뉴 선택이 잘못되었습니다.");
			break;
		}
	}

}

@FunctionalInterface //인터페이스 중에서도 앱스트랫이 하나만 있는거
interface Command { //이게 클래스.... --> 오버라이딩의 강제성이 없음
	public abstract void exec(); //바디가 없는 메소드, 미아적인 코드, 선언부는 있지만 바디부가 없음->문법오류 -> abstract 붙임 -> 일반 클래스에 절대 들어갈 수 없음
	 //객체생성 불가능 - 미아적인 코드라서. exec()가 뭔지 모르니까(?)
	//abstract 생략해도 ok (인터페이스에는 애초에 추상메소드만 오니까)
}

class DeleteCommand implements Command {
	   public void exec() {
	      System.out.println("DeleteCommand exec ..");
	   }
	}


class UpdateCommand implements Command {

	@Override
	public void exec() { //
		// TODO Auto-generated method stub
		System.out.println("UpdateCommand exec ..");
	} //오버라이딩 하지 않았을 경우 하라고 에러가 나는 거임. 반드시 에러 남. 강제성
	
}