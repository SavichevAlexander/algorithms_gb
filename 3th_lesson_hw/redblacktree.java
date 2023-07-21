import java.util.Scanner;

class node {

  node left, right;
  int data;

// red ==> true, black ==> false
  boolean color;

  node(int data) {
    this.data = data;
    left = null;
    right = null;

// The new node that is created is always red.
    color = true;
  }
}

public class LLRBTREE {

  private static node root = null;

  // A function to rotate a node counterclockwise.
  node rotateLeft(node myNode) {
    System.out.printf("Rotate left!\n");
    node child = myNode.right;
    node childLeft = child.left;

    child.left = myNode;
    myNode.right = childLeft;

    return child;
  }

  // A function to rotate a node clockwise.
  node rotateRight(node myNode) {
    System.out.printf("Rotate right!\n");
    node child = myNode.left;
    node childRight = child.right;

    child.right = myNode;
    myNode.left = childRight;

    return child;
  }

  // A function to check if a node is red or not.
  boolean isRed(node myNode) {
    if (myNode == null) {
      return false;
    }
    return (myNode.color == true);
  }

  // Function to change the color of two nodes.
  void swapColors(node node1, node node2) {
    boolean temp = node1.color;
    node1.color = node2.color;
    node2.color = temp;
  }

  node insert(node myNode, int data) {
// Regular embed code for any binary file
    if (myNode == null) {
      return new node(data);
    }

    if (data < myNode.data) {
      myNode.left = insert(myNode.left, data);
    } else if (data > myNode.data) {
      myNode.right = insert(myNode.right, data);
    } else {
      return myNode;
    }

// 1st case
// When the right child is red and the left child is black or does not exist.
    if (isRed(myNode.right) && !isRed(myNode.left)) {
// Rotate node left
      myNode = rotateLeft(myNode);
// Swap child node colors should always be red
      swapColors(myNode, myNode.left);
    }

// 2nd case
// When the left child as well as the left grandson are highlighted in red
    if (isRed(myNode.left) && isRed(myNode.left.left)) {
// Rotate node to the right
      myNode = rotateRight(myNode);
      swapColors(myNode, myNode.right);
    }

// 3th case
// When both left and right children are colored red.
    if (isRed(myNode.left) && isRed(myNode.right)) {
// Invert the color of the node's left and right children.
      myNode.color = !myNode.color;
// Change color to black.
      myNode.left.color = false;
      myNode.right.color = false;
    }

    return myNode;
  }

  // Bypass in order
  void inorder(node node) {
    if (node != null)
    {
      inorder(node.left);
      char c = '●';
      if (node.color == false)
        c = '◯';
      System.out.print(node.data + ""+c+" ");
      inorder(node.right);
    }
  }

  public static void main(String[] args) {

    LLRBTREE node = new LLRBTREE();
    Scanner scan = new Scanner(System.in);

    char ch;
    do {
      System.out.println("Enter an integer");

      int num = scan.nextInt();
      root = node.insert(root, num);

      node.inorder(root);
      System.out.println("\nDo you want to continue? (Enter y or n)");
      ch = scan.next().charAt(0);
    } while (ch == 'Y' || ch == 'y');
  }
}