package assignment5;
import java.util.Scanner;
public class Assignment5 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[]args) {
		Scanner s = new Scanner(System.in);
		String inputExpression = "";
		while (!inputExpression.equals(" ")){
			inputExpression = s.nextLine();
			BinaryTree tree = binaryInfixTreeBuilder(inputExpression);
			tree.inOrder(null);
			System.out.println("DIFFERENTIATION OF " + tree.infixString + ":");
			System.out.println(diff(tree));

		}
	}

	// takes a infix expression string as input
	// returns a binary tree that corresponds to original expression
	public static BinaryTree binaryInfixTreeBuilder(String infixStr) {
		
		BinaryTree infixTree = new BinaryTree(' ', null, null);
		for (int i = 0; i < infixStr.length(); i++){
			char token = infixStr.charAt(i);
			if (infixTree.focusNode == null)
				infixTree.focusNode = infixTree.root;

			if (token == '(')
				infixTree.descendLeft();

			else if (token == '+' || token == '-' || token == '*' || token == '/' || token == '^'){
				if (!(infixTree.focusNode.data == ' ')){
					infixTree.ascendParent();
				}
					infixTree.setFocus(token);
					infixTree.descendRight();
			}

			else if (token >= '0' && token <= '9' || Character.isLetter(token)){
				infixTree.setFocus(token);
				infixTree.ascendParent();
			}

			else if (token == ')'){
				infixTree.ascendParent();
			}

			else if (token == ' '){
			}
		}
		return infixTree;
	}

	public static String diff(BinaryTree inputTree){

		Node current = inputTree.root;

		if (current.data == ' ')
			current = current.childL;
                
                BinaryTree left = new BinaryTree(current.childL.data, current.childL.childL, current.childL.childR);
                BinaryTree right = new BinaryTree(current.childR.data, current.childR.childL, current.childR.childR);
                left.inOrder(null);
                right.inOrder(null);
                String RightS = right.infixString;
                String LeftS = left.infixString;

		// base cases, if root = constant or = 'x'
                if(current.data == 'x' && current.childL == null && current.childR == null){
                    return "1";
                }
                else if(current.data == '*'&& current.childL.data == 'x'&&current.childR.data == 'x')
                    return "1*1";
                
                else if(current.data <= '9' && current.data >= '0'&& current.childL == null && current.childR == null)
                    return "0";
                
                else if (current.childL.data >='0' && current.childL.data <= '9' && current.childR.data == 'x' && current.data == '*'){
                    return current.childL.data + " * 1"; 
                }
                
                else if (Character.toLowerCase(current.childR.data) < 'x' || Character.toLowerCase(current.childR.data) > 'x'){
                    if(current.data == '*'){
                        return "0";
                    }
                }

		else if (Character.toLowerCase(current.data) == 'x' && current.parent.data != '*'){
			return "1";
                }
		// if '+', creates two trees from each of the children
		// these trees are each differentiated and added together
                else if (current.data == '+')
                    return diff(left) + "+" + diff(right);
		
                //Case for if operator is '-'
                else if (current.data == '-')
                    return diff(left) + "-" + diff(right);
                //Case for if operator is '*'
                else if (current.data == '*')
                    return "((" + LeftS + "*" + diff(right) + ") + (" + RightS + "*" + diff(left)+ "))";
                //Case for if operator is '/'
                else if (current.data == '/')
                    return "(((" + diff(left) + "*" + RightS + ")" + "-" +"(" + diff(right) + "*" + LeftS + ")) / (" + RightS + "^2))";
                    //Case for if operator is '^'
                else if (current.data == '^')
                    return RightS + "* (" + LeftS + "^ (" + RightS + "-1)) + (" + diff(left) + ")";

		

		return "";
	}


    
    static class Node {
	Node parent;
	char data;
	Node childL;
	Node childR;

	public Node(Node p, char d, Node cL, Node cR){
		parent = p;
		data = d;
		childL = cL;
		childR = cR;
	}

	public boolean isLeaf(){
		return (data >= '0' && data <= '9');
	}
    }    
	
    
   static class BinaryTree {
	
	Node root = new Node(null, ' ', null, null);
	Node focusNode;
	String infixString = "";
	String infixStringalt = "";
	
	public BinaryTree() {
		root = null;
	}

	public BinaryTree(char rootItem, Node childL, Node childR) {
		root = new Node(null, rootItem, childL, childR);
	}
	
	// descends into left child of focusNode
	// creates empty node if there isn't one initalized
	public void descendLeft() {
		if (focusNode.childL == null){
			Node newNode = new Node(focusNode, ' ', null, null);
			focusNode.childL = newNode;
		}
		focusNode = focusNode.childL;
	}

	// descends into right child of focusNode
	// creates empty node if there isn't one initalized
	public void descendRight() {
		if (focusNode.childR == null){
			Node newNode = new Node(focusNode, ' ', null, null);
			focusNode.childR = newNode;
		}
		focusNode = focusNode.childR;
	}
	
	// ascends to the parent of the focusNode
	// if it has no parent (it is root), the tree
	// structure is modified
	public void ascendParent() {
		if (focusNode.parent == null){
			Node newNode = new Node(null, ' ', focusNode, null);
			focusNode.parent = newNode;
			root = focusNode.parent;
		}
		focusNode = focusNode.parent;
	}

	
	// sets the current focusNode's data element
	public void setFocus(char token) {
		focusNode.data = token;
	}

	// checks if a specified node is a left child
	public boolean isChildL(Node node){
		if (node.parent != null)
			return (node == node.parent.childL);
		return false;
	}

	// checks if a specified node is a right child
	public boolean isChildR(Node node){
		if (node.parent != null)
			return (node == node.parent.childR);
		return false;
	}

	// prints the contents of the tree inorder, 
	// correctly parenthesized
	public void inOrder(Node node) {
		Node current;
		if (node == null)
			node = root;
		current = node;

		if (current.childL != null)
			inOrder(current.childL);

		if (current.isLeaf() && isChildL(current) && current.parent.childR != null){
				if (current.parent.childR.isLeaf())
					infixString += "(";
		}
		infixString += current.data;

		if (current.isLeaf() && isChildR(current) && current.parent.childL != null){
				if (current.parent.childL.isLeaf())
					infixString += ")";
		}
		if (current.childR != null)
			inOrder(current.childR);
	}

    }
}
