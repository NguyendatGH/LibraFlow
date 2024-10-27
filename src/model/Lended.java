package model;

public class Lended {
    String bcode;
    String rcode;
    int state;

    public Lended(String bcode, String rcode, int state) {
        this.bcode = bcode;
        this.rcode = rcode;
        this.state = state;
    }

    public String getBcode(){
        return this.bcode;
    }

    public String getRcode(){
        return this.rcode;
    }
    public int getState(){
        return this.state;
    }

    public void setBCode(String bcode){
        this.bcode = bcode;
    }
    public void setRcode(String rcode){
        this.rcode = rcode;
    }

    public void setState(int currentState){
        this.state = currentState;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-10s | %d", bcode, rcode, state);
    }
}

