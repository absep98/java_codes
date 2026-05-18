class TrieNode{
    TrieNode children[] = new TrieNode[26];
    boolean eow;
}

class Trie{
    private TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    public void insert(String word){
        TrieNode node = root;
        for(char ch : word.toCharArray()){
            int idx = ch - 'a';
            if(node.children[idx] == null){
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.eow = true;
    }

    public boolean search(String word){
        TrieNode node = root;
        for(char ch : word.toCharArray()){
            int idx = ch - 'a';
            if(node.children[idx] == null){
                return false;
            }
            node = node.children[idx];
        }
        return node != null && node.eow;
    }

}

public class TrieCode {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("ape");

        System.out.println(trie.search("apple")); // true
        System.out.println(trie.search("app"));   // true
        System.out.println(trie.search("ap"));  // true
    }
}