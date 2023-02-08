// generated with ast extension for cup
// version 0.8
// 7/1/2023 10:51:58


package rs.ac.bg.etf.pp1.ast;

public class NumConstFactor extends Factor {

    private Integer numValue;

    public NumConstFactor (Integer numValue) {
        this.numValue=numValue;
    }

    public Integer getNumValue() {
        return numValue;
    }

    public void setNumValue(Integer numValue) {
        this.numValue=numValue;
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
        buffer.append("NumConstFactor(\n");

        buffer.append(" "+tab+numValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NumConstFactor]");
        return buffer.toString();
    }
}
