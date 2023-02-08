// generated with ast extension for cup
// version 0.8
// 7/1/2023 10:51:58


package rs.ac.bg.etf.pp1.ast;

public class DesignatorAssignmentStmt extends DesignatorStatement {

    private DesignatorAssignmentStatement DesignatorAssignmentStatement;

    public DesignatorAssignmentStmt (DesignatorAssignmentStatement DesignatorAssignmentStatement) {
        this.DesignatorAssignmentStatement=DesignatorAssignmentStatement;
        if(DesignatorAssignmentStatement!=null) DesignatorAssignmentStatement.setParent(this);
    }

    public DesignatorAssignmentStatement getDesignatorAssignmentStatement() {
        return DesignatorAssignmentStatement;
    }

    public void setDesignatorAssignmentStatement(DesignatorAssignmentStatement DesignatorAssignmentStatement) {
        this.DesignatorAssignmentStatement=DesignatorAssignmentStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorAssignmentStatement!=null) DesignatorAssignmentStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorAssignmentStatement!=null) DesignatorAssignmentStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorAssignmentStatement!=null) DesignatorAssignmentStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorAssignmentStmt(\n");

        if(DesignatorAssignmentStatement!=null)
            buffer.append(DesignatorAssignmentStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorAssignmentStmt]");
        return buffer.toString();
    }
}
