package assignment5;

 //@author Danny
 
public class Assignment5 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
    }
    public static class Node{
        char token;
  
        public static Node leftChild;
        public static Node rightChild;
        public static Node parent;
        
        Node(char token){
        this.token = token; 
        }
        
        public char getToken(){
        return  token;
        }
    }
    
    public static class BinaryTree{
        public static Node root;
        public void addNode(char token)
        {
            Node newNode = new Node(token);
            //creating a root with an empty value
            if (root == null)
            {
                root = newNode;
            }
            else{
                Node current = new Node(token);
                current = root;
                Node parent;
            
            //If statement to be followed if token is a '('
            if (token == '('){
               newNode = current.leftChild; 
            }
            //If statement to be followed if token is an operator
            //**Code here is work in progress
            if (token == '/'|| token== '*'|| token=='+'|| token == '-'){
                current.token = token;
            }
            //If statement to be followed if token is ')"
            if (token == ')'){
                current = current.parent;
            }
            else{
                //**Code here is work in progress.
                //token will be last possibility: int 0-9
                current.token = token;
            }
            root = new Node(token);
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
