import java.util.Scanner;

public class Main {
	static Scanner input = new Scanner(System.in);
	
	public static void menu() {// print menu
    System.out.println("Welcome to Dijkstra Algorithm and Binary Search Tree Generator!");
		System.out.println("Please Enter a Number to Select:");
		System.out.println("1 - Dijkstra Algorithm");
		System.out.println("2 - Binary Search Tree:");
	}

	public static void main(String args[]) {
		menu();
		int x = input.nextInt();
		if(x == 1) {
			Dijkstra D = new Dijkstra();
      D.runD();
    }else if(x ==2){
      BinarySearchTree BST = new BinarySearchTree();
      BST.runBST();		
		}else {
			System.out.println("Invalid input");
      System.out.println("------------------------------------------------------------------");
      System.out.println();
      menu();
		}
  }
}





