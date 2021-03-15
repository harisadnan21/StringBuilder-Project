public class LinkStrand implements IDnaStrand{
    public LinkStrand(String source) {
        initialize(source);

    }
    public LinkStrand(){
        this("");

    }


    private class Node{
        String info;
        Node next;
        public Node(String s){
            info = s;
            next = null;

        }

    }
    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;
    private int myIndex;
    private Node myCurrent;
    private int myLocalIndex;

    public String toString(){
        StringBuilder string = new StringBuilder();
        Node input = myFirst;

        while(input != null){
            string.append(input.info);
            input = input.next;

        }
        return string.toString();


    }



    @Override
    public long size() {
        return mySize;

    }

    @Override
    public void initialize(String source) {

        myFirst = new Node(source);
        myLast = myFirst;
        mySize = myFirst.info.length();
        myAppends = 0 ;
        myIndex = 0 ;
        myCurrent = myFirst;
        myLocalIndex = 0;
    }

    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }

    @Override
    public IDnaStrand append(String dna) {
        myLast.next = new Node(dna);
        myLast = myLast.next;
        mySize = mySize + dna.length();
        myAppends++;
        return this;
    }
    public void frontAppend(String s){
        Node n = new Node(s);
        n.next = myFirst;
        myFirst = n;
        mySize = mySize + myFirst.info.length();

    }
    @Override
    public IDnaStrand reverse() {

        LinkStrand str = new LinkStrand();
        Node track = myFirst;
        while(track != null){
            StringBuilder copy = new StringBuilder(track.info);
            copy.reverse();
            str.frontAppend(copy.toString());
            track = track.next;
        }
        return str;
    }

    @Override
    public int getAppendCount() {
        return myAppends;
    }

    @Override
    public char charAt(int index) {

        /*Node track = myFirst;
        int count = 0;
        int dex = 0;
        while(track!= null){
            count = count + track.info.length();
            track = track.next;
        }
        track = myFirst;*/
        if (index >= mySize || index < 0){
            throw new IndexOutOfBoundsException();
        }
        if (myIndex > index){
            myCurrent = myFirst;
            myIndex = 0;
            myLocalIndex = 0;

        }
        while(myIndex != index){
            myIndex++;
            myLocalIndex++;
            if (myLocalIndex >= myCurrent.info.length()){
                myCurrent = myCurrent.next;
                myLocalIndex = 0;

            }
        }
        return myCurrent.info.charAt(myLocalIndex);


        /*
        if(index > count){
            throw new IndexOutOfBoundsException();
        }
        else{
            if(index< myIndex){
                count = 0;
                while(count - 1< index) {
                    count = count + track.info.length();
                    track = track.next;
                }
            }
            else{
                count = myIndex;
                track = myCurrent;

                while(count - 1 < index){
                    count = count + track.info.length();
                    track = track.next;

                }
            }
            myLocalIndex = myIndex - count;
            myIndex = index;
            myCurrent = track;

            return track.info.charAt(myLocalIndex);
*/



    }
}
