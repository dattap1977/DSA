package com.company.trie;

public class InsertCountWordsCountPrefixErase {
    private Node root;
    public InsertCountWordsCountPrefixErase(){
        root = new Node();
    }
    public static void main(String[] args){
       InsertCountWordsCountPrefixErase trie = new InsertCountWordsCountPrefixErase();
       trie.insert("apple");
       trie.insert("apple");

       trie.insert("apps");
       trie.insert("apps");

       System.out.println(trie.countWordsInTrie("apple"));
       System.out.println(trie.countWordsInTrie("apps"));
       System.out.println(trie.countWordsStartsWith("app"));
       trie.erase("apple");
        System.out.println(trie.countWordsInTrie("apple"));
    }

    class Node{
        Node[] links = new Node[26];
        int countEndWith = 0;
        int countPrefix = 0;

        boolean containsKey(char ch){
            return links[ch - 'a'] != null;
        }
        void put(char ch, Node node){
            links[ch - 'a'] = node;
        }

        Node get(char ch){
            return links[ch - 'a'];
        }

        void setPrefix(){
            countPrefix++;
        }
        int getPrefix(){
            return countPrefix;
        }
        void deceasePrefix(){
            countPrefix--;
        }

        void decreaseEnd(){
            countEndWith--;
        }

        int getEnd(){
            return countEndWith;
        }
        void setEnd(){
            countEndWith++;
        }
    }

    public void insert(String word){
        Node node = root;
        for(int i=0; i < word.length(); i++){
            if (!node.containsKey(word.charAt(i))){
                node.put(word.charAt(i),new Node());
                //node.setPrefix();
            }

           node.setPrefix();
           node = node.get(word.charAt(i));
        }

        node.setEnd();
    }

    int countWordsInTrie(String word){
        Node node = root;
        for(int i=0; i < word.length(); i++){
            if (!node.containsKey(word.charAt(i))){
                return 0;
            }

            node = node.get(word.charAt(i));
        }

        //node.setEnd();

        return node.getEnd();
    }

    int countWordsStartsWith(String prefix){
        Node node = root;
        for(int i=0; i < prefix.length(); i++){
            if (!node.containsKey(prefix.charAt(i))){
                return 0;
            }
            node = node.get(prefix.charAt(i));
        }

        //node.setEnd();

        return node.getPrefix();
    }

    void erase(String word){
        Node node = root;

        for(int i=0; i < word.length(); i++){
            if (node.containsKey(word.charAt(i))){
                node.deceasePrefix();
                node = node.get(word.charAt(i));
            }
            else
                return;
        }

        node.decreaseEnd();
    }
}
