// generated with ast extension for cup
// version 0.8
// 7/1/2023 10:51:58


package rs.ac.bg.etf.pp1.ast;

public class BoolConstFactor extends Factor {

    private String boolValue;

    public BoolConstFactor (String boolValue) {
        this.boolValue=boolValue;
    }

    public String getBoolValue() {
        return boolValue;
    }

    public void setBoolValue(String boolValue) {
        this.boolValue=boolValue;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BoolConstFactor(\n");

        buffer.append(" "+tab+boolValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BoolConstFactor]");
        return buffer.toString();
    }
}
