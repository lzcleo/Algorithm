package leetcode.Other;

/**
 * @author leolu
 * @create 2020-04-03-11:57
 *  前缀树的应用场景
 *  1 自动补全 2 拼写检查 3 IP路由（最长前缀匹配）
 **/
public class TrieTree {
    private TrieNode root;
    /** Initialize your data structure here. */
    public TrieTree() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null)
            return;
        char[] chs = word.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i ++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
            node.path ++;
        }
        node.end ++;
    }

    /** Returns if the word is in the trie. */
    public int search(String word) {
        if (word == null) {
            return 0;
        }
        char[] chs = word.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.end;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix == null)
            return false;
        char[] chs = prefix.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < index; i ++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null)
                return false;
            node = node.nexts[index];
        }
        return true;
    }

    public void delete(String word) {
        if (search(word) != 0) {
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (--node.nexts[index].path == 0) {
                    node.nexts[index] = null;
                    return;
                }
                node = node.nexts[index];
            }
            node.end--;
        }
    }

}
class TrieNode{
    int path; //若有路径通过一次此节点则+1
    int end;  //若有路径以此节点结束则+1
    public TrieNode[] nexts;

    public TrieNode(){
        path = 0;
        end = 0;
        nexts = new TrieNode[26];
    }
}