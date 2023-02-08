// generated with ast extension for cup
// version 0.8
// 7/1/2023 10:51:58


package rs.ac.bg.etf.pp1.ast;

public class DataDeclarations extends DataDeclList {

    private DataDeclList DataDeclList;
    private DataDecl DataDecl;

    public DataDeclarations (DataDeclList DataDeclList, DataDecl DataDecl) {
        this.DataDeclList=DataDeclList;
        if(DataDeclList!=null) DataDeclList.setParent(this);
        this.DataDecl=DataDecl;
        if(DataDecl!=null) DataDecl.setParent(this);
    }

    public DataDeclList getDataDeclList() {
        return DataDeclList;
    }

    public void setDataDeclList(DataDeclList DataDeclList) {
        this.DataDeclList=DataDeclList;
    }

    public DataDecl getDataDecl() {
        return DataDecl;
    }

    public void setDataDecl(DataDecl DataDecl) {
        this.DataDecl=DataDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DataDeclList!=null) DataDeclList.accept(visitor);
        if(DataDecl!=null) DataDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DataDeclList!=null) DataDeclList.traverseTopDown(visitor);
        if(DataDecl!=null) DataDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DataDeclList!=null) DataDeclList.traverseBottomUp(visitor);
        if(DataDecl!=null) DataDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DataDeclarations(\n");

        if(DataDeclList!=null)
            buffer.append(DataDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DataDecl!=null)
            buffer.append(DataDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DataDeclarations]");
        return buffer.toString();
    }
}
