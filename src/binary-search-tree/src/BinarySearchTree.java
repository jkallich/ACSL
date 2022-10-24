import java.util.ArrayList;

class BinarySearchTree {
    Node root;
    char prefixChar = ' ';

    public String buildTree(String str) {
        String retValue = "NA";
        System.out.println("Adding: " + str);
        String str2 = str.toUpperCase();
        for (int i = 0; i < str2.length(); i++) {
            char val = str2.charAt(i);
            if (val == ' ') {
                continue;
            }
            addNode(root, val);
            // System.out.println("Added : " + val);
        }
        return retValue;
    }

    public void addNode(Node node, char val) {

        if (node == null) {
            node = new Node(val);
            this.root = node;
            return;
        }

        if (val == node.data) {
            node.count++;
        } else if (val < node.data) {
            if (node.left == null) {
                node.left = new Node(val);
            } else {
                addNode(node.left, val);
            }
        } else {
            if (node.right == null) {
                node.right = new Node(val);
            }  else {
                addNode(node.right, val);
            }
        }
    }

    public Node search(char val) {
        Node tNode = root;
        while (tNode != null) {
            if (val == tNode.data)
                return tNode;
            else {
                tNode = (val < tNode.data) ? tNode.left : tNode.right;
            }
        }
        return null;
    }

    public int duplicates (String input) {

        input= input.toUpperCase();
        input= input.replace("\\s+", "");
        input= input.replace(" ", "");

        root= new Node(input.charAt(0));

        for (int i = 1; i < input.length(); i++) {
            char ltr= input.charAt(i);
            addNode(root, ltr);
        }

        int count= 0;
        count+= search2(root);

        return count;
    }

    public int search2 (Node node) {

        if (node == null) {
            return 0;
        }
        int count= 0;
        if (node.left == null && node.right != null) {
            count+= node.count;
        } else if (node.right == null && node.left != null) {
            count+= node.count;
        }

        count+= search2(node.right);
        count+= search2(node.left);

        return count;
    }

    void printTree(Node node, String prefix) {
        if (node == null)
            return;

        System.out.println(prefix + " " + node.data);
        printTree(node.left, prefix + prefixChar + prefixChar);
        printTree(node.right, prefix + prefixChar + prefixChar);
    }
}
