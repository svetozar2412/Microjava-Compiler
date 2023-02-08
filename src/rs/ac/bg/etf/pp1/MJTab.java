package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;
import rs.etf.pp1.symboltable.visitors.SymbolTableVisitor;

public class MJTab extends Tab {
	
	public static class MJDumpSymbolTableVisitor extends DumpSymbolTableVisitor
	{
		
		@Override
		public void visitObjNode(Obj objToVisit) {
			//output.append("[");
			switch (objToVisit.getKind()) {
			case Obj.Con:  output.append("Con "); break;
			case Obj.Var:  output.append("Var "); break;
			case Obj.Type: output.append("Type "); break;
			case Obj.Meth: output.append("Meth "); break;
			case Obj.Fld:  output.append("Fld "); break;
			case Obj.Prog: output.append("Prog "); break;
			}
			
			output.append(objToVisit.getName());
			output.append(": ");
			
			if ((Obj.Var == objToVisit.getKind()) && "this".equalsIgnoreCase(objToVisit.getName()))
				output.append("");
			else
				objToVisit.getType().accept(this);
			
			output.append(", ");
			if(objToVisit.getKind()==Obj.Con && objToVisit.getType().getKind()==Struct.Bool)
				output.append(objToVisit.getAdr()==1?"true":"false");
			else
				output.append(objToVisit.getAdr());
			
			output.append(", ");
			output.append(objToVisit.getLevel() + " ");
					
			if (objToVisit.getKind() == Obj.Prog || objToVisit.getKind() == Obj.Meth) {
				output.append("\n");
				nextIndentationLevel();
			}
			

			for (Obj o : objToVisit.getLocalSymbols()) {
				output.append(currentIndent.toString());
				o.accept(this);
				output.append("\n");
			}
			
			if (objToVisit.getKind() == Obj.Prog || objToVisit.getKind() == Obj.Meth) 
				previousIndentationLevel();

			//output.append("]");
			
		}
		
		@Override
		public void visitStructNode(Struct structToVisit) {
			switch (structToVisit.getKind()) {
			case Struct.None:
				output.append("notype");
				break;
			case Struct.Int:
				output.append("int");
				break;
			case Struct.Char:
				output.append("char");
				break;
			case Struct.Bool:
				output.append("bool");
				break;
			case Struct.Array:
				output.append("Arr of ");
				
				switch (structToVisit.getElemType().getKind()) {
				case Struct.None:
					output.append("notype");
					break;
				case Struct.Int:
					output.append("int");
					break;
				case Struct.Char:
					output.append("char");
					break;
				case Struct.Bool:
					output.append("bool");
					break;
				case Struct.Class:
					output.append("Class");
					break;
				}
				break;
			case Struct.Class:
				output.append("Class [");
				for (Obj obj : structToVisit.getMembers()) {
					obj.accept(this);
				}
				output.append("]");
				break;
			}

		}
	}

	
	public static void dump(SymbolTableVisitor stv) {
		System.out.println("=====================TABELA SIMBOLA=========================");
		if (stv == null)
			stv = new MJDumpSymbolTableVisitor();
		for (Scope s = currentScope; s != null; s = s.getOuter()) {
			s.accept(stv);
		}
		System.out.println(stv.getOutput());
	}
	
	public static void init()
	{
		Tab.init();
		Struct boolType = new Struct(Struct.Bool);
    	Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
    	
    	Obj printBoolObj;
    	Tab.currentScope.addToLocals(printBoolObj=new Obj(Obj.Meth, "printBoolObj", noType, 0, 1));
		{
			Obj temp;
			openScope();
			currentScope.addToLocals(temp=new Obj(Obj.Var, "boolValue", boolType, 0, 1));
			temp.setFpPos(0);
			currentScope.addToLocals(temp=new Obj(Obj.Var, "width", intType, 0, 1));
			temp.setFpPos(1);
			currentScope.addToLocals(new Obj(Obj.Var, "empty", intType, 0, 1));
			currentScope.addToLocals(new Obj(Obj.Var, "length", intType, 0, 1));
			printBoolObj.setLocals(currentScope.getLocals());
			closeScope();
		}
		
		Obj readBoolObj;
		Tab.currentScope.addToLocals(readBoolObj=new Obj(Obj.Meth, "readBoolObj", boolType, 0, 1));
		{
			openScope();
			currentScope.addToLocals(new Obj(Obj.Var, "inp", new Struct(Struct.Array, charType), 0, 1));
			currentScope.addToLocals(new Obj(Obj.Var, "i", intType, 0, 1));
			currentScope.addToLocals(new Obj(Obj.Var, "skip", charType, 0, 1));
			currentScope.addToLocals(new Obj(Obj.Var, "result", boolType, 0, 1));
			readBoolObj.setLocals(currentScope.getLocals());
			closeScope();
		}
		//Tab.currentLevel = -1;
	}
}
