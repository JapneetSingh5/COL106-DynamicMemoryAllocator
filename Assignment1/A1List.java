// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List
public class A1List extends List {

    private A1List next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        A1List newNode = new A1List(address, size, key); // Initialize a newNode with the given address, size and key

        newNode.next = this.next;
        newNode.prev = this;
        this.next.prev = newNode;
        this.next = newNode;

        return newNode; //return the inserted newNode
    }

    public boolean Delete(Dictionary d) 
    {   
        A1List iterator1 = this;
        A1List iterator2 = this;
        while(iterator1.next !=null){ //if currentNode.next is null, currentNode is the tailSentinel 
            if((d.key == iterator1.key)&&(d.size == iterator1.size)&&(d.address==iterator1.address)){ //keys of the input node and currentNode match
                iterator1.prev.next = iterator1.next;
                iterator1.next.prev = iterator1.prev;
                return true;
            }
            iterator1 = iterator1.next;
        } 
        while(iterator2.prev !=null){ //if currentNode.next is null, currentNode is the headSentinel 
            if((d.key == iterator2.key)&&(d.size == iterator2.size)&&(d.address==iterator2.address)){ //keys of the input node and currentNode match
                iterator2.prev.next = iterator2.next;
                iterator2.next.prev = iterator2.prev;
                return true;
            }
            iterator2 = iterator2.prev;
        } 

        return false;
    }

    public A1List Find(int k, boolean exact)
    { 
        A1List iterator1 = this;
        A1List iterator2 = this;
        A1List approxMatch = null;
        if(exact){
            while(iterator1.next!=null){
                if(k == iterator1.key){ //keys of the input node and currentNode match
                    return iterator1;
                }
                iterator1 = iterator1.next;
            }
            while(iterator2.prev!=null){
                if(k==iterator2.key){
                    return iterator2;
                }
                iterator2 = iterator2.prev;
            }
            return null; //if we reach this line, exact match wasnt found
        }else{
            iterator1 = this;
            iterator2 = this;
            while(iterator1.next!=null){
                if(k<=iterator1.key){
                    if(approxMatch==null || approxMatch.key>=iterator1.key){
                        approxMatch = iterator1;
                        // System.out.println("Matched "+ "Node Key "+ iterator1.key + " Size Needed: " + k);  
                    } 
                }
                iterator1 = iterator1.next;
            }
            while(iterator2.prev!=null){
                if(k<=iterator1.key){
                    if(approxMatch==null || approxMatch.key>=iterator1.key){
                        approxMatch = iterator1;
                        // System.out.println("Matched "+ "Node Key "+ iterator2.key + " Size Needed: " + k);  
                    }   
                }
                iterator2 = iterator2.prev;
            }
            return approxMatch;
        }
    }

    public A1List getFirst()
    {
        A1List currentNode = this;
        if(currentNode.prev==null){ //this is the headSentinel
            if(currentNode.next.next==null){
                return null;
            }else{
                return currentNode.next;
            }
        }else{
            while(currentNode.prev.prev!=null){
                currentNode = currentNode.prev;
            }
            return currentNode;
        }
    }
    
    public A1List getNext() 
    {   
        if(this.next!=null && this.next.next==null){  //this is the tailSentinel
            return null;
        }else{
            return this.next;
        }
    }

    public boolean sanity()
    {
        //We check if the list has a loop, we also check some invariants on every node as we traverse the list
        A1List fastIterator = this.getFirst();
        A1List slowIterator = this.getFirst();
        if(fastIterator==null && slowIterator==null){ //list is empty => list is sane
            // System.out.println("Empty list");
            return true;
        }
        //first, we check if we have a circular loop in the list
        while(fastIterator!=null){
            fastIterator = (fastIterator.getNext() == null) ? null : fastIterator.getNext().getNext();
            slowIterator = slowIterator.getNext();
            if(fastIterator!=null && slowIterator!=null && fastIterator==slowIterator){
                // System.out.println(fastIterator.key);
                // System.out.println(slowIterator.key);
                // System.out.println("Loopedlist");
                return false;
            }
        }
        A1List currentNode = this.getFirst();
        //second, we check for node invariant: node.next.prev=node
        while(currentNode.getNext()!=null){
            if(currentNode.next.prev!=currentNode){
                // System.out.println("Invariant false in list");
                return false;
            }
            currentNode = currentNode.getNext();
        }
        return true;
    }

    // public static void main(String[] args){  //function to debug the class methods
    //     A1List list = new A1List();
    //     list.Insert(10, 10, 10);
    //     list.Insert(20, 20, 20);
    //     list.Insert(30, 30, 30);
    //     list.Insert(16, 30, 16);
    //     list.Insert(14, 30, 14);
    //     list.Insert(12, 30, 12);
    //     list.Insert(37, 30, 37);
    //     // list.getFirst().next = new A1List(23,23,23);
    //     // list.getFirst().next.next = list.getFirst();
    //     int count = 0;
    //     System.out.println(list.sanity());
    //     for(A1List dic = list.getFirst(); dic!=null; dic =dic.getNext()){
    //         count++;
    //         System.out.println(dic.address + " Count: " + count);
    //     }
        
    // }

}


