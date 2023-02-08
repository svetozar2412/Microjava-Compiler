// generated with ast extension for cup
// version 0.8
// 7/1/2023 10:51:58


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Designator Designator);
    public void visit(MethodDecl MethodDecl);
    public void visit(Factor Factor);
    public void visit(Mulop Mulop);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(ConstDecl ConstDecl);
    public void visit(DesignatorAssignmentStatement DesignatorAssignmentStatement);
    public void visit(Expr Expr);
    public void visit(FormalParamList FormalParamList);
    public void visit(SingleVarDecl SingleVarDecl);
    public void visit(FormPars FormPars);
    public void visit(DesignatorList DesignatorList);
    public void visit(VarDeclList VarDeclList);
    public void visit(SingleConstDecl SingleConstDecl);
    public void visit(VarDecl VarDecl);
    public void visit(OptionalDesignator OptionalDesignator);
    public void visit(ConstDeclList ConstDeclList);
    public void visit(Addop Addop);
    public void visit(DataDecl DataDecl);
    public void visit(FormalParamDecl FormalParamDecl);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(Statement Statement);
    public void visit(LocalVarDeclarationList LocalVarDeclarationList);
    public void visit(Term Term);
    public void visit(StatementList StatementList);
    public void visit(DataDeclList DataDeclList);
    public void visit(Mod Mod);
    public void visit(Div Div);
    public void visit(Mul Mul);
    public void visit(Sub Sub);
    public void visit(Add Add);
    public void visit(ArrayName ArrayName);
    public void visit(ArrayDesignator ArrayDesignator);
    public void visit(ScalarDesignator ScalarDesignator);
    public void visit(DesignatorFactor DesignatorFactor);
    public void visit(NewArrayFactor NewArrayFactor);
    public void visit(ExprFactor ExprFactor);
    public void visit(BoolConstFactor BoolConstFactor);
    public void visit(CharConstFactor CharConstFactor);
    public void visit(NumConstFactor NumConstFactor);
    public void visit(SingleFactorTerm SingleFactorTerm);
    public void visit(MultipleFactorTerm MultipleFactorTerm);
    public void visit(NegSingleTermExpr NegSingleTermExpr);
    public void visit(SingleTermExpr SingleTermExpr);
    public void visit(MultipleTermExpr MultipleTermExpr);
    public void visit(EmptyOptionalDesignator EmptyOptionalDesignator);
    public void visit(NotEmptyOptionalDesignator NotEmptyOptionalDesignator);
    public void visit(SingleOptionalDesignator SingleOptionalDesignator);
    public void visit(MultipleOptionalDesignators MultipleOptionalDesignators);
    public void visit(ExprAssignmentError ExprAssignmentError);
    public void visit(DesignatorAssignmentError DesignatorAssignmentError);
    public void visit(DesignatorAssignment DesignatorAssignment);
    public void visit(DesignatorAssignmentWithSquareBrackets DesignatorAssignmentWithSquareBrackets);
    public void visit(IncDesignatorStmt IncDesignatorStmt);
    public void visit(DecDesignatorStmt DecDesignatorStmt);
    public void visit(DesignatorAssignmentStmt DesignatorAssignmentStmt);
    public void visit(ReadStmt ReadStmt);
    public void visit(ExtendedPrintStmt ExtendedPrintStmt);
    public void visit(PrintStmt PrintStmt);
    public void visit(DesignatorStmt DesignatorStmt);
    public void visit(NoStatements NoStatements);
    public void visit(Statements Statements);
    public void visit(ArrayFormalParamDecl ArrayFormalParamDecl);
    public void visit(ScalarFormalParamDecl ScalarFormalParamDecl);
    public void visit(SingleFormalParamDecl SingleFormalParamDecl);
    public void visit(FormalParamDecls FormalParamDecls);
    public void visit(NoFormParam NoFormParam);
    public void visit(FormParams FormParams);
    public void visit(SingleLocal SingleLocal);
    public void visit(MultipleLocals MultipleLocals);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(VoidMethodDeclarationWithoutLocals VoidMethodDeclarationWithoutLocals);
    public void visit(VoidMethodDeclaration VoidMethodDeclaration);
    public void visit(NoMethodDeclarations NoMethodDeclarations);
    public void visit(MethodDeclarations MethodDeclarations);
    public void visit(Type Type);
    public void visit(BoolConstDeclaration BoolConstDeclaration);
    public void visit(CharConstDeclaration CharConstDeclaration);
    public void visit(NumConstDeclaration NumConstDeclaration);
    public void visit(SingleConstDeclaration SingleConstDeclaration);
    public void visit(MultipleConstDeclarations MultipleConstDeclarations);
    public void visit(VarDeclarationError VarDeclarationError);
    public void visit(ArrayVarDeclaration ArrayVarDeclaration);
    public void visit(ScalarVarDeclaration ScalarVarDeclaration);
    public void visit(SingleVarDeclaration SingleVarDeclaration);
    public void visit(MultipleVarDeclarations MultipleVarDeclarations);
    public void visit(VarDeclType VarDeclType);
    public void visit(ErrorVarDeclarations ErrorVarDeclarations);
    public void visit(VarDeclarations VarDeclarations);
    public void visit(ConstDeclType ConstDeclType);
    public void visit(ConstDeclarations ConstDeclarations);
    public void visit(VarDataDecl VarDataDecl);
    public void visit(ConstDataDecl ConstDataDecl);
    public void visit(NoDataDeclarations NoDataDeclarations);
    public void visit(DataDeclarations DataDeclarations);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
