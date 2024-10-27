package model;

public class Reader {
    String rcode;
    String name;
    int byear;

    public Reader(String rcode, String name, int byear) {
        this.rcode = rcode;
        this.name = name;
        this.byear = byear;
    }

    public String getRcode() {
        return rcode;
    }

    public String getName() {
        return name;
    }

    public int getByear() {
        return byear;
    }

    public void setRcode(String rcode) {
        this.rcode = rcode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setByear(int byear) {
        this.byear = byear;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-20s | %4d", rcode, name, byear);
    }

}
