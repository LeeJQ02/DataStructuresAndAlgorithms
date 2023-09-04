import java.util.Scanner;

public class BinarySearchTree {
  static Scanner input = new Scanner(System.in);
	Node root;

	public void addNode(int key, String name) {
		Node newNode = new Node(key, name);
		if (root == null) {// If there is no root this becomes root
			root = newNode;
		} else {
      //Set root as the Node when traverse the tree
			Node selectedNode = root;
			Node parent;
			while (true) {
        // root is the top parent 
				parent = selectedNode;
				if (key < selectedNode.key) { // the node is added to the left
          // select left child
					selectedNode = selectedNode.leftChild;
					if (selectedNode == null) {// If the left child has no children
            // then place the new node on the left
						parent.leftChild = newNode;
						return; 
					}
				} else { // the node is added to the right
					selectedNode = selectedNode.rightChild;
					if (selectedNode == null) {// If the right child has no children
            // then place the new node on the right
						parent.rightChild = newNode;
						return; 
					}
				}
			}
		}
	}

  public static void traversal() { // print menu
		System.out.println();
		System.out.println("Please a Number to Select Method of Displaying:");
		System.out.println("1 - Inorder Traversal");
		System.out.println("2 - Preorder Traversal");
		System.out.println("3 - Postorder Traversal");
		System.out.println("4 - Search");
		System.out.println("0 - End");
	}
  
	public void inOrderTraverseTree(Node selectedNode) {
		if (selectedNode != null) {
      // Traverse the left node
			inOrderTraverseTree(selectedNode.leftChild);
      // Visit the currently focused on node
			System.out.println(selectedNode);
      // Traverse the right node
			inOrderTraverseTree(selectedNode.rightChild);
		}
	}

	public void preorderTraverseTree(Node selectedNode) {
		if (selectedNode != null) {
			System.out.println(selectedNode);
			preorderTraverseTree(selectedNode.leftChild);
			preorderTraverseTree(selectedNode.rightChild);
		}
	}

	public void postOrderTraverseTree(Node selectedNode) {
		if (selectedNode != null) {
			postOrderTraverseTree(selectedNode.leftChild);
			postOrderTraverseTree(selectedNode.rightChild);
			System.out.println(selectedNode);
		}
	}

	public Node findNode(int key) {
    // Start at the top of the tree
		Node selectedNode = root;
		while (selectedNode.key != key) {// While Node not found, keep looping
			if (key < selectedNode.key) {
        // Shift the selected Node to the left child
				selectedNode = selectedNode.leftChild;
			} else {
        // Shift the selected Node to the right child
				selectedNode = selectedNode.rightChild;
			}
			if (selectedNode == null){// The node not found
				return null;
      }
		}
		return selectedNode;
	}
  
	public boolean remove(int key) {
		// Start at the top of the tree
		Node selectedNode = root;
		Node parent = root;

		// This will tell us whether to search to the right or left
		boolean isItALeftChild = true;

		while (selectedNode.key != key) {// While Node not found, keep looping
			parent = selectedNode;
      
			if (key < selectedNode.key) {// If less selected Node should search to the left
				isItALeftChild = true;
				// Shift the selected Node to the left child
				selectedNode = selectedNode.leftChild;
			} else {// Greater than selected Node so go to the right
				isItALeftChild = false;
				// Shift the selected Node to the right child
				selectedNode = selectedNode.rightChild;
			}
			if (selectedNode == null){// The node wasn't found
				return false;
      }
		}
		if (selectedNode.leftChild == null && selectedNode.rightChild == null) {// If Node doesn't have children delete it
			if (selectedNode == root){ // If root delete it
				root = null;
      }else if (isItALeftChild){// If it was marked as a left child of the parent delete it in its parent
				parent.leftChild = null;
      }else{// If it was marked as a right child of the parent delete it in its parent
				parent.rightChild = null;
      }
		}
		else if (selectedNode.rightChild == null) { // If no right child
			if (selectedNode == root){
				root = selectedNode.leftChild;
      }else if (isItALeftChild){// If selected Node was on the left of parent move the selected Node left child up to the parent node
				parent.leftChild = selectedNode.leftChild;
      }else{// If selected Node was on the right of parent move the selected Node left child up to the parent node
				parent.rightChild = selectedNode.leftChild;
      }
		}
		else if (selectedNode.leftChild == null) { // If no left child
			if (selectedNode == root){
				root = selectedNode.rightChild;
      }else if (isItALeftChild){// If selected Node was on the left of parent move the selected Node right child up to the parent node
				parent.leftChild = selectedNode.rightChild;
      }else{// If selected Node was on the right of parent move the selected Node right child up to the parent node
				parent.rightChild = selectedNode.rightChild;
      }
		}
		// Two children, find the deleted nodes replacement
		else {
			Node replacement = getReplacementNode(selectedNode);
	
			if (selectedNode == root){// If the selectedNode is root replace root with the replacement
				root = replacement;
      }else if (isItALeftChild){// If the deleted node was a left child make the replacement the left child
				parent.leftChild = replacement;
      }else{// If the deleted node was a right child make the replacement the right child
				parent.rightChild = replacement;
			  replacement.leftChild = selectedNode.leftChild;
      }
    }
		return true;
	}

	public Node getReplacementNode(Node replacedNode) {
		Node replacementParent = replacedNode;
		Node replacement = replacedNode;
		Node selectedNode = replacedNode.rightChild;

		while (selectedNode != null) {// While there are no more left children
			replacementParent = replacement;
			replacement = selectedNode;
			selectedNode = selectedNode.leftChild;
		}
		// If the replacement isn't the right child move the replacement into the parents
		// leftChild slot and move the replaced nodes
		// right child into the replacements rightChild
		if (replacement != replacedNode.rightChild) {
			replacementParent.leftChild = replacement.rightChild;
			replacement.rightChild = replacedNode.rightChild;
		}
		return replacement;
	}

  public static void runBST() {
    try{
    BinarySearchTree BST = new BinarySearchTree();
    System.out.println("Welcome To Binary Search Tree!");
    System.out.println();
    System.out.println("Please Enter Number of Nodes:");
    			int node = input.nextInt();
    			for(int num=1; num<node+1; num++) {
            System.out.println();
    				System.out.println("Please add node " + num + ":");
            System.out.println("Please enter key for node " + num + ":");
    				int key = input.nextInt();
            System.out.println("Please enter name for node " + num + ":");
    				String name = input.next();
    				BST.addNode(key, name);
    			}
    
    			while(true) {
    				traversal();
              int method = input.nextInt();
                  if (method == 1) {
                    BST.inOrderTraverseTree(BST.root);
                  }else if (method == 2) {
                    BST.preorderTraverseTree(BST.root);
                  }else if (method == 3 ) {
                    BST.postOrderTraverseTree(BST.root);
                  }else if(method == 4) { 
                    System.out.println();
                    System.out.println("Please Enter Node Number to Search: ");
                    int searchNode = input.nextInt();
                    System.out.println(BST.findNode(searchNode));
                  }else if (method == 0 ) {
                    System.out.println();
                    System.out.println("Programme Ended! Thank You!");
                    break;
                  } else{
                    System.out.println("Invalid Input!");
                  }
              }
  }catch(Exception e){
      System.out.println("Something went wrong.");
      System.out.println("The program is terminated.");
  }
  }
    

class Node {
	int key;
	String name;
	Node leftChild;
	Node rightChild;

	Node(int key, String name) {
		this.key = key;
		this.name = name;
	}

	public String toString() {
		return name + " has the key " + key;
  }
	}
}

