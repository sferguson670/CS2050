public class myLL {
    //PIV
    public Node myHead;
    public int mySize;

    //Constructor
    public myLL() {
        myHead = null;
        mySize = 0;
    }

    public String toString()
    //toString method
    {
        String list = "";
        for (Node temp = myHead; temp != null; temp = temp.getNext())
            list+=(" " + temp.getMyValue());
        return list;
    }

    public void addFront(Countries value)
    //insert value at front
    {
        Node newNode = new Node(value, myHead);
        myHead = newNode;
        mySize++;
    }
    public class Node {
        private Countries myData;
        private Node myNext;

        public Node(Countries data, Node next)
        //the constructor for country type linked list
        {
            myData = data;
            myNext = next;
        }

        public void setMyValue(Countries myData) {
            this.myData = myData;
        }

        public void setMyNext(Node myNext) {
            this.myNext = myNext;
        }

        public Countries getMyValue() {
            return myData;
        }

        public Node getNext() {
            return myNext;
        }

        public String toString() {
            return "" + myData;
        }
    }
}
