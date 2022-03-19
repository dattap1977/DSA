package com.company.trie;

//A string is called complete if it contains all the prefixes in the array including the string itself
// In case of collision return the lexicographically smallest one.

import java.util.Scanner;

public class LongestCompleteString {
       private Node root ;
       public LongestCompleteString(){
              root = new Node();
       }
       class Node{
              Node[] links = new Node[26];
              boolean flag;
              boolean containsKey(char ch){
                     return links[ch - 'a'] != null;
              }
              void put(char ch, Node node){
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
       public static void main(String[] args){
              Scanner s = new Scanner(System.in);
              int N = s.nextInt();

              String[] array = new String[N];
              LongestCompleteString trie = new LongestCompleteString();
              for(int i=0; i < N; i++){
                     array[i] = s.next();
                     trie.insert(array[i]);
              }
              int max = -1;
              String maxString = "";
              for(int i=0; i < N; i++){
                     if (trie.check(array[i])){
                           if (max < array[i].length()){
                                  max = array[i].length();
                                  maxString = array[i];
                           }
                           else{
                                  if (max == array[i].length() && array[i].compareTo(maxString) < 0){
                                         maxString = array[i];
                                  }
                           }
                     }
              }

              System.out.println("maxString = " + maxString);
       }

        void insert(String word){
              Node node = root;
              for(int i=0; i < word.length(); i++){
                     if (!node.containsKey(word.charAt(i))){
                            node.put(word.charAt(i),new Node());
                     }
                     node = node.get(word.charAt(i));
              }

              node.setEnd();

       }

       boolean check(String word){
              Node node = root;
              boolean flag = true;
              for(int i=0; i < word.length(); i++){
                     if (node.containsKey(word.charAt(i))){
                            node = node.get(word.charAt(i));
                            flag = flag & node.isEnd();
                     }
                     else{
                            return false;
                     }
              }

              return flag;
       }
}
