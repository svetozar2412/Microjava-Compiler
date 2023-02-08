// generated with ast extension for cup
// version 0.8
// 7/1/2023 10:51:58


package rs.ac.bg.etf.pp1.ast;

public class MultipleLocals extends LocalVarDeclarationList {

    private LocalVarDeclarationList LocalVarDeclarationList;
    private VarDecl VarDecl;

    public MultipleLocals (LocalVarDeclarationList LocalVarDeclarationList, VarDecl VarDecl) {
        this.LocalVarDeclarationList=LocalVarDeclarationList;
        if(LocalVarDeclarationList!=null) LocalVarDeclarationList.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public LocalVarDeclarationList getLocalVarDeclarationList() {
        return LocalVarDeclarationList;
    }

    public void setLocalVarDeclarationList(LocalVarDeclarationList LocalVarDeclarationList) {
        this.LocalVarDeclarationList=LocalVarDeclarationList;
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(LocalVarDeclarationList!=null) LocalVarDeclarationList.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(LocalVarDeclarationList!=null) LocalVarDeclarationList.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(LocalVarDeclarationList!=null) LocalVarDeclarationList.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleLocals(\n");

        if(LocalVarDeclarationList!=null)
            buffer.append(LocalVarDeclarationList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleLocals]");
        return buffer.toString();
    }
}
