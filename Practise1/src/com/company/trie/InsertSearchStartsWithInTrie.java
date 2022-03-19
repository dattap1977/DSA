package com.company.trie;

public class InsertSearchStartsWithInTrie {
    private Node root = new Node();
    class Node{
        Node[] links = new Node[26];
        boolean flag;
        boolean containsKey(char ch){
            return links[ch - 'a'] != null;
        }
        void put(char ch,Node node){
            links[ch - 'a'] = node;
        }

        Node get(char ch){
            return links[ch - 'a'];
        }

        void setEnd(){
            flag = true;
        }

        boolean isEnd(){
            return flag;
        }
    }

    public void insert(String word){
        Node node = root;
        for(int i=0; i < word.length(); i++){
            if (!node.containsKey(word.charAt(i))){
                Node newNode = new Node();
                node.put(word.charAt(i),newNode);
            }
            node = node.get(word.charAt(i));
        }
        node.setEnd();
    }

    public boolean search(String word){
        Node node = root;
        for(int i=0; i < word.length(); i++){
            if (!node.containsKey(word.charAt(i))){
                return false;
            }
            node = node.get(word.charAt(i));
        }

        if (node.isEnd()){
            return true;
        }
        return false;
    }

    public boolean startsWith(String word){
        Node node = root;
        for(int i=0; i < word.length(); i++){
            if (!node.containsKey(word.charAt(i))){
                return false;
            }
            node = node.get(word.charAt(i));
        }

        return true;
    }
    public static void main(String[] args){
        InsertSearchStartsWithInTrie trie = new InsertSearchStartsWithInTrie();
        trie.insert("apple");
        trie.insert("apps");
        trie.insert("apxl");
        boolean found = trie.search("apple");
        System.out.println("found = " + found);
        found = trie.search("app");
        System.out.println("found = " + found);
        found = trie.search("apps");
        System.out.println("found = " + found);
        found = trie.search("apxl");
        System.out.println("found = " + found);
        found = trie.startsWith("app");
        System.out.println("startsWith app = " + found);
    }
}
