// generated with ast extension for cup
// version 0.8
// 7/1/2023 10:51:58


package rs.ac.bg.etf.pp1.ast;

public class DesignatorAssignmentError extends DesignatorAssignmentStatement {

    private Designator Designator;
    private ExprAssignmentError ExprAssignmentError;

    public DesignatorAssignmentError (Designator Designator, ExprAssignmentError ExprAssignmentError) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.ExprAssignmentError=ExprAssignmentError;
        if(ExprAssignmentError!=null) ExprAssignmentError.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public ExprAssignmentError getExprAssignmentError() {
        return ExprAssignmentError;
    }

    public void setExprAssignmentError(ExprAssignmentError ExprAssignmentError) {
        this.ExprAssignmentError=ExprAssignmentError;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(ExprAssignmentError!=null) ExprAssignmentError.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(ExprAssignmentError!=null) ExprAssignmentError.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(ExprAssignmentError!=null) ExprAssignmentError.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorAssignmentError(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprAssignmentError!=null)
            buffer.append(ExprAssignmentError.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorAssignmentError]");
        return buffer.toString();
    }
}
