import java.util.Arrays;

public class Trie {
    public static void main (String[] args) {
        //Input keys (use only a through z and lower case)
        String keys[] = {"the", "a", "there", "answer", "any", "by",
         "bye", "their","abc"};
        String output[] = {"Not present in trie", "Present in trie"}; 
        Trie t = new Trie();

        System.out.println("Keys to insert: " + Arrays.toString(keys) + "\n");

        //Construct trie
        int i;
        for(i = 0; i < keys.length; i++) {
            t.insert(keys[i]);
        }

        //Search for different keys
        if(t.search("the") == true)
            System.out.println("the -- " + output[1]);
        else System.out.println("the -- " + output[0]);    

    }

    public class TrieNode {
        TrieNode[] children;
        boolean isEndWord; //Will be true if the node represents the end of word
        static final int AlPHABET_SIZE = 26; // Total # of English Alphabets = 26
    
        //Constructor & Node
        TrieNode() {
            this.isEndWord = false;
            this.children = new TrieNode[AlPHABET_SIZE];
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

    private TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    //Function to get the index of a character 't'
    public int getIndex(char t) {
        return t - 'a';
    }

    //Function to insert a key, value pair in the Trie
    public void insert(String key) {
        if(key == null) {
            System.out.println("Null Key can not be Inserted!");
            return;
        }
        key = key.toLowerCase(); //Keys are stored in lowercase
        TrieNode currentNode = this.root;
        int index = 0; //to store character index

        //Iterate the Trie with given character index,
        //If null, create TrieNode go down level
        for (int level = 0; level < key.length(); level++) {
            index = getIndex(key.charAt(level));
            if(currentNode.children[index] == null) {
                currentNode.children[index] = new TrieNode();
            }
            currentNode = currentNode.children[index];
        }
        //Mark the end character as leaf node
        currentNode.markAsLeaf();

    }

    //Function to search given key in Trie
    public boolean search(String key) {
        if(key == null)
            return false; //Null key

        key = key.toLowerCase();
        TrieNode currentNode = this.root;
        int index = 0;
        
        //Iterate the Trie with given character index,
        //If it is null at any point then we stop and return false
        //We will return true only if we reach leadNode and have traversed the
        //Trie based on the length of the key

        for(int level = 0; level < key.length(); level++) {
            index = getIndex(key.charAt(level));
            if(currentNode.children[index] == null)
                return false;
            currentNode = currentNode.children[index];    
        }
        if (currentNode.isEndWord)
            return true;

        return false;    
    }

}