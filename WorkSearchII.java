import java.util.*;
class Solution {

    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word = null;
    }

}
public class WorkSearchII{
    public static void main(String args[]){
        Solution sol = new Solution();
        char board[][] = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String words[] = {"oath","pea","eat","rain"};
        System.out.println(sol.findWords(board, words));
    }
}
