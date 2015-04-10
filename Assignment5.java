package assignment5;

/**
 *
 * @author Danny
 */
public class Assignment5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
    }
    public static class Node{
        String token;
  
        public static Node leftChild;
        public static Node rightChild;
        
        Node(String token){
        this.token = token; 
        }
        
        public String getToken(){
        return  token;
        }
    }
    
    public static class BinaryTree{
        public Node root;
        public void addNode(String token)
        {
            Node newNode = new Node(token);
            root = new Node(token);
            if (root == null)
            {
                root = newNode;
            }
            else{
                
            }
    }
    public void inOrder(Node focusNode){
        if(focusNode != null)
        {
            inOrder(focusNode.leftChild);
            System.out.println(focusNode);
            inOrder(focusNode.rightChild);
        }
        return;
    }
    }
}
