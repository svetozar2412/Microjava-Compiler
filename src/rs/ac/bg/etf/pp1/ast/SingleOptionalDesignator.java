// generated with ast extension for cup
// version 0.8
// 7/1/2023 10:51:58


package rs.ac.bg.etf.pp1.ast;

public class SingleOptionalDesignator extends DesignatorList {

    private OptionalDesignator OptionalDesignator;

    public SingleOptionalDesignator (OptionalDesignator OptionalDesignator) {
        this.OptionalDesignator=OptionalDesignator;
        if(OptionalDesignator!=null) OptionalDesignator.setParent(this);
    }

    public OptionalDesignator getOptionalDesignator() {
        return OptionalDesignator;
    }

    public void setOptionalDesignator(OptionalDesignator OptionalDesignator) {
        this.OptionalDesignator=OptionalDesignator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalDesignator!=null) OptionalDesignator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalDesignator!=null) OptionalDesignator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalDesignator!=null) OptionalDesignator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleOptionalDesignator(\n");

        if(OptionalDesignator!=null)
            buffer.append(OptionalDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleOptionalDesignator]");
        return buffer.toString();
    }
}
