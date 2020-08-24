package com.wilson;

public class BinaryTree{
    Node root;

    public void addNode(int key, String name){
        Node newNode = new Node(key, name);
        if (root == null){
            root = newNode;
        } else {
            Node currentNode = root;
            Node parent;
            while (true){
                parent = currentNode;
                if (key < currentNode.key){
                    currentNode = currentNode.leftChild;
                    if (currentNode == null){
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    currentNode = currentNode.rightChild;
                    if (currentNode == null){
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public void inOrderTraverseTree(Node node){
        if (node != null){
            inOrderTraverseTree(node.leftChild);
            System.out.println(node);
            inOrderTraverseTree(node.rightChild);
        }
    }

    public String maxValue(Node node){
        Node current = node;
        while (current.rightChild != null){
            current = current.rightChild;
        }
        return current.name;
    }
}

class Node {
    int key;
    String name;

    Node leftChild;
    Node rightChild;

    public Node(int key, String name) {
        this.key = key;
        this.name = name;
    }

    public String toString(){
        return name + " has a key " + key;
    }
}