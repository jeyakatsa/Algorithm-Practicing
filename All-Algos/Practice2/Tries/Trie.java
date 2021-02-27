public class Trie {
    public static void main (String[] args) {
        Trie t = new Trie();

    }

    Trie[] children;
    boolean isEndWord; //Will be true if the node represents the end of word
    static final int AlPHABET_SIZE = 26; // Total # of English Alphabets = 26
    Trie() {
        this.isEndWord = false;
        this.children = new Trie[AlPHABET_SIZE];
    }

    //function to mark the current Node as Leaf
    public void markAsLeaf(){
        this.isEndWord = true;
    }

    //function to mark the currentNode as Leaf
    public void unMarkAsLeaf() {
        this.isEndWord = false;
    }
}