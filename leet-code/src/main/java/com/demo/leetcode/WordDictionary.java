package com.demo.leetcode;

/**
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search("..d"); // return True
 * wordDictionary.search("b.."); // return True
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-add-and-search-words-data-structure
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class WordDictionary {
    private TrieNode root = new TrieNode();

    public WordDictionary() {

    }
    public void addWord(String word) {
        char[] chars = word.toCharArray();
        TrieNode node = root;
        for (char c : chars) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEnd =true;
    }

    public boolean search(String word) {

        return doSearch(word, 0, root);
    }

    private boolean doSearch(String word, int index, TrieNode root) {
        if (word.length() == index) {
            return root.isEnd;
        }
        char c = word.charAt(index);
        if (Character.isLetter(c)) {
            int childrenIndex = c - 'a';
            TrieNode child  = root.children[childrenIndex];
            if ( child != null && doSearch(word, index+1, child)){
                 return true;
            }
        } else {
            for (int i=0;i<26;i++){
                TrieNode child = root.children[i];
                if ( child != null && doSearch(word, index+1, child)){
                    return true;
                }
            }
        }
        return false;
    }


    class TrieNode {
        boolean isEnd;
        TrieNode[] children = new TrieNode[26];

        public TrieNode() {
            this.isEnd = false;
        }
    }

    public static void main(String[] args) {
        WordDictionary dictionary = new WordDictionary();
        dictionary.addWord("bad");
        dictionary.addWord("dad");
        dictionary.addWord("mad");
        System.out.println(dictionary.search("pad"));
        System.out.println(dictionary.search("bad"));
        System.out.println(dictionary.search(".ad"));
        System.out.println(dictionary.search("..d"));
        System.out.println(dictionary.search(".f."));
        System.out.println(dictionary.search("b.."));

    }
}
