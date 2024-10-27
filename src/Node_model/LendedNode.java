package Node_model;

import model.Lended;

public class LendedNode {
    public Lended lended;
    public LendedNode next;

    public LendedNode() {

    }

    public LendedNode(Lended lended, LendedNode p) {
        this.lended = lended;
        this.next = p;
    }


}
