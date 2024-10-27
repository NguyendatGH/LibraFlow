package Node_model;

import model.Reader;

public class ReaderNode {
    public Reader readerInfor;
    public ReaderNode next;

    public ReaderNode(){

    }

    public ReaderNode(Reader reader, ReaderNode node){
        this.readerInfor = reader;
        next = node;
    }

}
