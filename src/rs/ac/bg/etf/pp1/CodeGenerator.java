package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.Add;
import rs.ac.bg.etf.pp1.ast.Addop;
import rs.ac.bg.etf.pp1.ast.ArrayDesignator;
import rs.ac.bg.etf.pp1.ast.ArrayFormalParamDecl;
import rs.ac.bg.etf.pp1.ast.ArrayName;
import rs.ac.bg.etf.pp1.ast.BoolConstFactor;
import rs.ac.bg.etf.pp1.ast.CharConstFactor;
import rs.ac.bg.etf.pp1.ast.DecDesignatorStmt;
import rs.ac.bg.etf.pp1.ast.Designator;
import rs.ac.bg.etf.pp1.ast.DesignatorAssignment;
import rs.ac.bg.etf.pp1.ast.DesignatorAssignmentWithSquareBrackets;
import rs.ac.bg.etf.pp1.ast.DesignatorFactor;
import rs.ac.bg.etf.pp1.ast.Div;
import rs.ac.bg.etf.pp1.ast.EmptyOptionalDesignator;
import rs.ac.bg.etf.pp1.ast.ExtendedPrintStmt;
import rs.ac.bg.etf.pp1.ast.Factor;
import rs.ac.bg.etf.pp1.ast.IncDesignatorStmt;
import rs.ac.bg.etf.pp1.ast.MethodTypeName;
import rs.ac.bg.etf.pp1.ast.Mod;
import rs.ac.bg.etf.pp1.ast.Mul;
import rs.ac.bg.etf.pp1.ast.Mulop;
import rs.ac.bg.etf.pp1.ast.MultipleFactorTerm;
import rs.ac.bg.etf.pp1.ast.MultipleTermExpr;
import rs.ac.bg.etf.pp1.ast.NegSingleTermExpr;
import rs.ac.bg.etf.pp1.ast.NewArrayFactor;
import rs.ac.bg.etf.pp1.ast.NotEmptyOptionalDesignator;
import rs.ac.bg.etf.pp1.ast.NumConstFactor;
import rs.ac.bg.etf.pp1.ast.OptionalDesignator;
import rs.ac.bg.etf.pp1.ast.PrintStmt;
import rs.ac.bg.etf.pp1.ast.ReadStmt;
import rs.ac.bg.etf.pp1.ast.ScalarDesignator;
import rs.ac.bg.etf.pp1.ast.ScalarFormalParamDecl;
import rs.ac.bg.etf.pp1.ast.Sub;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.ac.bg.etf.pp1.ast.VoidMethodDeclaration;
import rs.ac.bg.etf.pp1.ast.VoidMethodDeclarationWithoutLocals;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	
	public CodeGenerator() {
		super();
		MJTab.find("printBoolObj").setAdr(Code.pc);
		printBoolAddr=MJTab.find("printBoolObj").getAdr();
		this.printBoolInit();
		MJTab.find("readBoolObj").setAdr(Code.pc);
		readBoolAddr=MJTab.find("readBoolObj").getAdr();
		this.readBoolInit();
	}
	
	private void printBoolInit()
	{
		Code.put(Code.enter);
		Code.put(2);
		Code.put(4);
		Code.put(Code.load_n+0);
		Code.put(Code.const_1);
		Code.put(Code.jcc+Code.ne);
		Code.put2(8);
		Code.put(Code.const_4);
		Code.put(Code.store_3);
		Code.put(Code.jmp);
		Code.put2(5);
		Code.put(Code.const_5);
		Code.put(Code.store_3);
		Code.put(Code.load_1);
		Code.put(Code.load_3);
		Code.put(Code.sub);
		Code.put(Code.store_2);
		Code.put(Code.load_2);
		Code.put(Code.const_n+0);
		Code.put(Code.jcc+Code.le);
		Code.put2(16);
		Code.loadConst(32);
		Code.put(Code.const_n+0);
		Code.put(Code.bprint);
		Code.put(Code.inc);
		Code.put(2);
		Code.put(255);
		Code.put(Code.jmp);
		Code.put2(-15);
		Code.put(Code.load_n+0);
		Code.put(Code.const_1);
		Code.put(Code.jcc+Code.ne);
		Code.put2(34);
		Code.loadConst(116);
		Code.put(Code.const_n+0);
		Code.put(Code.bprint);
		Code.loadConst(114);
		
		Code.put(Code.const_n+0);
		Code.put(Code.bprint);
		Code.loadConst(117);
		Code.put(Code.const_n+0);
		Code.put(Code.bprint);
		Code.loadConst(101);
		Code.put(Code.const_n+0);
		Code.put(Code.bprint);
		Code.put(Code.jmp);
		Code.put2(38);
		Code.loadConst(102);
		Code.put(Code.const_n+0);
		Code.put(Code.bprint);
		Code.loadConst(97);
		Code.put(Code.const_n+0);
		Code.put(Code.bprint);
		Code.loadConst(108);
		Code.put(Code.const_n+0);
		Code.put(Code.bprint);
		Code.loadConst(115);
		Code.put(Code.const_n+0);
		Code.put(Code.bprint);
		Code.loadConst(101);
		Code.put(Code.const_n+0);
		Code.put(Code.bprint);
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	private void readBoolInit()
	{
		// MJTab.readBoolMethod.setAdr(Code.pc);

        Code.put(Code.enter);
        Code.put(0);
        Code.put(4);

        Code.put(Code.const_5);
        Code.put(Code.newarray);
        Code.put(0);
        Code.put(Code.store_n);

        Code.put(Code.const_n);
        Code.put(Code.store_1);

        Code.put(Code.load_1);
        Code.put(Code.const_5);
        Code.put(Code.jcc + Code.ge);
        Code.put2(14);

        Code.put(Code.load_n);
        Code.put(Code.load_1);
        Code.put(Code.bread);
        Code.put(Code.bastore);

        Code.put(Code.load_n);
        Code.put(Code.load_1);
        Code.put(Code.baload);
        Code.put(Code.store_2);
        Code.put(Code.jmp);
        Code.put2(5);

        Code.put(Code.bread);
        Code.put(Code.store_2);

        Code.put(Code.load_1);
        Code.put(Code.const_1);
        Code.put(Code.add);
        Code.put(Code.store_1);

        Code.put(Code.load_2);
        Code.put(Code.const_);
        Code.put4(13);
        Code.put(Code.jcc + Code.eq);
        Code.put2(6);
        Code.put(Code.jmp);
        Code.put2(-31);

        Code.put(Code.bread);
        Code.put(Code.store_2);

        int skipAddress = 46;
        for (int i = 0; i < "true".length(); i++) {
            Code.put(Code.load_n);
            Code.load(new Obj(Obj.Con, "", MJTab.intType, i, 0));
            Code.put(Code.baload);
            Code.load(new Obj(Obj.Con, "", MJTab.charType, "true".charAt(i), 0));
            Code.put(Code.jcc + Code.ne);
            Code.put2(skipAddress);
            skipAddress -= 11;
        }
        Code.put(Code.load_1);
        Code.put(Code.const_5);
        Code.put(Code.jcc + Code.ne);
        Code.put2(8);
        Code.put(Code.const_1);
        Code.put(Code.store_3);
        Code.put(Code.jmp);
        Code.put2(82);

        skipAddress = 61;
        for (int i = 0; i < "false".length(); i++) {
            Code.put(Code.load_n);
            Code.load(new Obj(Obj.Con, "", MJTab.intType, i, 0));
            Code.put(Code.baload);
            Code.load(new Obj(Obj.Con, "", MJTab.charType, "false".charAt(i), 0));
            Code.put(Code.jcc + Code.ne);
            Code.put2(skipAddress);
            skipAddress -= 11;
        }
        Code.put(Code.load_1);
        Code.put(Code.const_);
        Code.put4(6);
        Code.put(Code.jcc + Code.ne);
        Code.put2(8);
        Code.put(Code.const_n);
        Code.put(Code.store_3);
        Code.put(Code.jmp);
        Code.put2(13);

        Code.put(Code.const_n);
        Code.put(Code.store_1);

        Code.put(Code.const_1);
        Code.put(Code.const_1);
        Code.put(Code.jcc + Code.ne);
        Code.put2(6);
        Code.put(Code.jmp);
        Code.put2(-166);

        Code.put(Code.load_3);
        Code.put(Code.exit);
        Code.put(Code.return_);
	}
	
	
	private int mainPc;
	private int designatorCounter;
	private Struct boolType=Tab.find("bool").getType();
	private int printBoolAddr,readBoolAddr;

	public int getMainPc() {
		return mainPc;
	}

	public void visit(PrintStmt printStmt) {
		if (printStmt.getExpr().struct == Tab.intType) {
			Code.loadConst(0);
			Code.put(Code.print);
		} else if (printStmt.getExpr().struct == Tab.charType){
			Code.loadConst(0);
			Code.put(Code.bprint);
		} else if (printStmt.getExpr().struct == boolType){
			Code.loadConst(0);
			Code.put(Code.call);
			Code.put2(printBoolAddr-Code.pc+1);
		}
	}

	public void visit(ExtendedPrintStmt printStmt) {
		if (printStmt.getExpr().struct == Tab.intType) {
			Code.loadConst(printStmt.getNumValue());
			Code.put(Code.print);
		} else if (printStmt.getExpr().struct == Tab.charType){
			Code.loadConst(printStmt.getNumValue());
			Code.put(Code.bprint);
		} else if (printStmt.getExpr().struct == boolType){
			Code.loadConst(printStmt.getNumValue());
			Code.put(Code.call);
			Code.put2(printBoolAddr-Code.pc+1);
		}
	}

	public void visit(ReadStmt readStmt) {
		if (readStmt.getDesignator().obj.getKind() == Obj.Elem) {
			if (readStmt.getDesignator().obj.getType() == Tab.intType) {
				Code.put(Code.read);
				Code.put(Code.astore);
			} else if (readStmt.getDesignator().obj.getType() == Tab.charType) {
				Code.put(Code.bread);
				Code.put(Code.bastore);
			} else if (readStmt.getDesignator().obj.getType() == boolType) {
				Code.put(Code.call);
				Code.put2(readBoolAddr-Code.pc+1);
				Code.put(Code.astore);
			}
		} else {
			if (readStmt.getDesignator().obj.getType() == Tab.intType)
				Code.put(Code.read);
			else if (readStmt.getDesignator().obj.getType() == Tab.charType)
				Code.put(Code.bread);
			else if (readStmt.getDesignator().obj.getType() == boolType)
			{
				Code.put(Code.call);
				Code.put2(readBoolAddr-Code.pc+1);
			}
			Code.store(readStmt.getDesignator().obj);
		}
	}

	public void visit(DesignatorAssignmentWithSquareBrackets assignment) {
		designatorCounter = 0;
	}

	public void visit(EmptyOptionalDesignator designator) {
		designatorCounter++;
	}

	public void visit(NotEmptyOptionalDesignator designator) {
		SyntaxNode node = designator.getParent();
		while (node.getClass() != DesignatorAssignmentWithSquareBrackets.class) {
			node = node.getParent();
		}
		DesignatorAssignmentWithSquareBrackets assignment = (DesignatorAssignmentWithSquareBrackets) node;
		Code.load(assignment.getDesignator().obj);
		Code.loadConst(designatorCounter);
		if (assignment.getDesignator().obj.getType().getElemType() == Tab.charType) {
			Code.put(Code.baload);
		} else {
			Code.put(Code.aload);
		}
		NotEmptyOptionalDesignator temp = (NotEmptyOptionalDesignator) designator;
		Code.store(temp.getDesignator().obj);
		designatorCounter++;
	}

	public void visit(IncDesignatorStmt stmt) {
		if (stmt.getDesignator().obj.getLevel() > 0 && stmt.getDesignator().obj.getKind() == Obj.Var) {
			Code.put(Code.inc);
			Code.put(stmt.getDesignator().obj.getAdr());
			Code.put(1);
		} else {
			Code.loadConst(1);
			Code.put(Code.add);
			Code.store(stmt.getDesignator().obj);
		}
	}

	public void visit(DecDesignatorStmt stmt) {
		if (stmt.getDesignator().obj.getLevel() > 0 && stmt.getDesignator().obj.getKind() == Obj.Var) {
			Code.put(Code.inc);
			Code.put(stmt.getDesignator().obj.getAdr());
			Code.put(-1);
		} else {
			Code.loadConst(1);
			Code.put(Code.sub);
			Code.store(stmt.getDesignator().obj);
		}
	}

	public void visit(NegSingleTermExpr expr) {
		Code.put(Code.neg);
	}

	public void visit(MultipleTermExpr expr) {
		if (expr.getAddop() instanceof Add) {
			Code.put(Code.add);
		} else if (expr.getAddop() instanceof Sub) {
			Code.put(Code.sub);
		}
	}

	public void visit(MultipleFactorTerm term) {
		if (term.getMulop() instanceof Mul) {
			Code.put(Code.mul);
		} else if (term.getMulop() instanceof Div) {
			Code.put(Code.div);
		} else if (term.getMulop() instanceof Mod) {
			Code.put(Code.rem);
		}
	}

	public void visit(NumConstFactor numFactor) {
		Code.loadConst(numFactor.getNumValue());
	}

	public void visit(CharConstFactor charFactor) {
		Code.loadConst(charFactor.getCharValue());
	}

	public void visit(BoolConstFactor boolFactor) {
		Code.loadConst(boolFactor.getBoolValue().equals("true") ? 1 : 0);
	}

	public void visit(MethodTypeName methodTypeName) {

		if ("main".equals(methodTypeName.getMethodName())) {
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);

		Code.put(Code.enter);
		Code.put(methodTypeName.obj.getLevel());
		Code.put(methodTypeName.obj.getLocalSymbols().size());
	}

	public void visit(VoidMethodDeclaration methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	public void visit(VoidMethodDeclarationWithoutLocals methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	public void visit(DesignatorAssignment assignment) {
		Code.store(assignment.getDesignator().obj);
	}

	public void visit(ScalarDesignator designator) {
		SyntaxNode node = designator.getParent();
		while (node != null && node.getClass() != DesignatorFactor.class && node.getClass() != DesignatorAssignmentWithSquareBrackets.class
				&& node.getClass() != NotEmptyOptionalDesignator.class) {
			node = node.getParent();
		}

		if (node == null || node.getClass() == DesignatorFactor.class) {
			SyntaxNode parent = designator.getParent();

			if (designator.obj.getLevel() > 0 && designator.obj.getKind() == Obj.Var
					&& (IncDesignatorStmt.class == parent.getClass() || DecDesignatorStmt.class == parent.getClass())) {
				return;
			}

			if (DesignatorAssignment.class != parent.getClass()
					&& DesignatorAssignmentWithSquareBrackets.class != parent.getClass()
					&& ReadStmt.class != parent.getClass()) {
				Code.load(designator.obj);
			}
		}
	}

	/*
	 * IncDesignatorStmt DecDesignatorStmt ReadStmt
	 * DesignatorAssignmentWithSquareBrackets DesignatorFactor DesignatorAssignment
	 * NotEmptyOptionalDesignator
	 */

	public void visit(ArrayDesignator designator) {
		SyntaxNode node = designator.getParent();
		while (node != null && node.getClass() != DesignatorFactor.class && node.getClass() != DesignatorAssignmentWithSquareBrackets.class) {
			node = node.getParent();
		}

		if (node == null || node.getClass() == DesignatorFactor.class) {
			SyntaxNode parent = designator.getParent();
			if (IncDesignatorStmt.class == parent.getClass() || DecDesignatorStmt.class == parent.getClass()) {
				Code.put(Code.dup2);
			}
			if (DesignatorAssignment.class != parent.getClass() && ReadStmt.class != parent.getClass()) {
				Code.load(designator.obj);
			}
		}
	}

	public void visit(ArrayName name) {
		// if (name.getParent().getParent().getClass() !=
		// NotEmptyOptionalDesignator.class) {
		Obj arrayObj = ((ArrayDesignator) (name.getParent())).obj;

		if (arrayObj.getLevel() == 0) {
			Code.put(Code.getstatic);
			Code.put2(arrayObj.getAdr());
		} else {
			if (0 <= arrayObj.getAdr() && arrayObj.getAdr() <= 3)
				Code.put(Code.load_n + arrayObj.getAdr());
			else {
				Code.put(Code.load);
				Code.put(arrayObj.getAdr());
			}
		}
		// }
	}

	public void visit(NewArrayFactor factor) {
		Code.put(Code.newarray);
		if (factor.getType().struct == Tab.charType) {
			Code.put(0);
		} else {
			Code.put(1);
		}
	}
}
