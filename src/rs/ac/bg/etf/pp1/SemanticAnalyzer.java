package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;

public class SemanticAnalyzer extends VisitorAdaptor {

	int printCallCount = 0;
	int varDeclCount = 0,constDeclCount=0;
	Obj currentMethod = null;
	Struct currentVarDeclType = null, currentConstDeclType = null;
	int currentMethodNumberOfFormals=0;
	List<Designator> designatorAssignmentList=new ArrayList<Designator>();
	Struct boolType;
	Struct intArrayType,charArrayType,boolArrayType;
	boolean returnFound = false;
	boolean errorDetected = false;
	private Obj main = null;
	int nVars;
	
	int methodCounter;
	int scalarGlobalsCounter;
	int constCounter;
	int arrayGlobalsCounter;
	int localsInMainCounter;
	int statementsInMainCounter;
	
	Logger log = Logger.getLogger(getClass());
	
	public void printDetails()
	{
		log.info(methodCounter+"\tmethods in program");
		log.info(scalarGlobalsCounter+"\tglobal variables");
		log.info(constCounter+"\tglobal constants");
		log.info(arrayGlobalsCounter+"\tglobal arrays");
		log.info(localsInMainCounter+"\tlocal variables in main");
		log.info(statementsInMainCounter+"\tstatements in main");
	}

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public void visit(DesignatorStmt stmt)
	{
		if(currentMethod!=null && currentMethod==main)
		{
			statementsInMainCounter++;
		}
	}
	
//	public void visit(VarDecl varDecl){
//		currentVarDeclType = null;
//	}
	
//	public void visit(ConstDecl constDecl){
//		currentConstDeclType = null;
//		constCounter++;
//	}
	
	public void visit(VarDeclType varDeclType){
		currentVarDeclType = varDeclType.getType().struct;
	}
	
	public void visit(ConstDeclType constDeclType){
		currentConstDeclType = constDeclType.getType().struct;
	}
	
	public void visit(NumConstDeclaration numConstDecl) {
		boolean error=false;
		Obj obj = Tab.currentScope().findSymbol(numConstDecl.getConstName());
    	if(obj != null){
			report_error("Greska na liniji " + numConstDecl.getLine()+ " : ime "+numConstDecl.getConstName()+" je vec deklarisano! ", null);
			error=true;
    	}
    	
    	if(!(currentConstDeclType.equals(Tab.intType)))
		{
			report_error("Greska na liniji "+ numConstDecl.getLine()+" : numericki literal "+numConstDecl.getNumValue()+" se ne moze dodeliti konstanti "+numConstDecl.getConstName()+".", null);
			error=true;
		}
		
		if(!error)
		{
			report_info("Deklarisana celobrojna konstanta "+ numConstDecl.getConstName(), numConstDecl);
			Obj constNode = Tab.insert(Obj.Con, numConstDecl.getConstName(), Tab.intType);
			constNode.setAdr(numConstDecl.getNumValue());
			
		}
		constCounter++;
	}
	
	public void visit(CharConstDeclaration charConstDecl) {
		boolean error=false;
		Obj obj = Tab.currentScope().findSymbol(charConstDecl.getConstName());
    	if(obj != null){
			report_error("Greska na liniji " + charConstDecl.getLine()+ " : ime "+charConstDecl.getConstName()+" je vec deklarisano! ", null);
			error=true;
    	}
    	
		if(!(currentConstDeclType.equals(Tab.charType)))
		{
			report_error("Greska na liniji "+ charConstDecl.getLine()+" : znakovni literal "+charConstDecl.getCharValue()+" se ne moze dodeliti konstanti "+charConstDecl.getConstName()+".", null);
			error=true;
		}
		
		if(!error)
		{
			report_info("Deklarisana znakovna konstanta "+ charConstDecl.getConstName(), charConstDecl);
			Obj constNode = Tab.insert(Obj.Con, charConstDecl.getConstName(), Tab.charType);
			constNode.setAdr(charConstDecl.getCharValue());
			
		}
		constCounter++;
	}
	
	public void visit(BoolConstDeclaration boolConstDecl) {
		boolean error=false;
		Obj obj = Tab.currentScope().findSymbol(boolConstDecl.getConstName());
    	if(obj != null){
			report_error("Greska na liniji " + boolConstDecl.getLine()+ " : ime "+boolConstDecl.getConstName()+" je vec deklarisano! ", null);
			error=true;
    	}
    	
		if(!(currentConstDeclType.equals(boolType)))
		{
			report_error("Greska na liniji "+ boolConstDecl.getLine()+" : logicki literal "+boolConstDecl.getBoolValue()+" se ne moze dodeliti konstanti "+boolConstDecl.getConstName()+".", null);
			error=true;
		}
		
		if(!error)
		{
			report_info("Deklarisana logicka konstanta "+ boolConstDecl.getConstName(), boolConstDecl);
			Obj constNode = Tab.insert(Obj.Con, boolConstDecl.getConstName(), boolType);
			constNode.setAdr(boolConstDecl.getBoolValue().equals("true")?1:0);
			
		}
		constCounter++;
	}
	
	public void visit(ScalarVarDeclaration varDecl){
		Obj obj = Tab.currentScope().findSymbol(varDecl.getVarName());
    	if(obj != null){
			report_error("Greska na liniji " + varDecl.getLine()+ " : ime "+varDecl.getVarName()+" je vec deklarisano! ", null);
    	}
    	else
    	{
    		report_info("Deklarisana skalarna promenljiva "+ varDecl.getVarName(), varDecl);
    		Obj varNode = Tab.insert(Obj.Var, varDecl.getVarName(), currentVarDeclType);
    		varDeclCount++;
    	}
    	if(currentMethod==null)
			scalarGlobalsCounter++;
		else if(currentMethod==main)
			localsInMainCounter++;
	}
	
	public void visit(ArrayVarDeclaration varDecl){
		Obj obj = Tab.currentScope().findSymbol(varDecl.getVarName());
    	if(obj != null){
			report_error("Greska na liniji " + varDecl.getLine()+ " : ime "+varDecl.getVarName()+" je vec deklarisano! ", null);
			return;
    	}
		Struct arrayStruct=null;
		if(currentVarDeclType.equals(Tab.intType))
		{
			if(intArrayType==null) intArrayType=new Struct(Struct.Array,Tab.intType);
			arrayStruct=intArrayType;
		}
		else if(currentVarDeclType.equals(Tab.charType))
		{
			if(charArrayType==null) charArrayType=new Struct(Struct.Array,Tab.charType);
			arrayStruct=charArrayType;
		}
		else if(currentVarDeclType.equals(boolType))
		{
			if(boolArrayType==null) boolArrayType=new Struct(Struct.Array,boolType);
			arrayStruct=boolArrayType;
		}
		
		if(arrayStruct==null) report_error("Tip nizovske promenljive "+ varDecl.getVarName()+" nije validan za niz.", varDecl);
		else
		{
			report_info("Deklarisana nizovska promenljiva "+ varDecl.getVarName(), varDecl);
			Obj varNode = Tab.insert(Obj.Var, varDecl.getVarName(), arrayStruct);
			varDeclCount++;
		}
		if(currentMethod==null)
			arrayGlobalsCounter++;
		else if(currentMethod==main)
			localsInMainCounter++;
		
	}
	
	public void visit(ScalarFormalParamDecl formalParamDecl){
		currentVarDeclType=formalParamDecl.getType().struct;
		Obj obj = Tab.currentScope().findSymbol(formalParamDecl.getVarName());
    	if(obj != null){
			report_error("Greska na liniji " + formalParamDecl.getLine()+ " : ime "+formalParamDecl.getVarName()+" je vec deklarisano! ", null);
			return;
    	}
		report_info("Deklarisana skalarna promenljiva "+ formalParamDecl.getVarName(), formalParamDecl);
		Obj varNode = Tab.insert(Obj.Var, formalParamDecl.getVarName(), currentVarDeclType);
		varNode.setFpPos(currentMethodNumberOfFormals);
		currentMethodNumberOfFormals++;
		varDeclCount++;
		currentVarDeclType=null;
	}
	
	public void visit(ArrayFormalParamDecl formalParamDecl){
		currentVarDeclType=formalParamDecl.getType().struct;
		Obj obj = Tab.currentScope().findSymbol(formalParamDecl.getVarName());
    	if(obj != null){
			report_error("Greska na liniji " + formalParamDecl.getLine()+ " : ime "+formalParamDecl.getVarName()+" je vec deklarisano! ", null);
			return;
    	}
		
		Struct arrayStruct=null;
		if(currentVarDeclType.equals(Tab.intType))
		{
			if(intArrayType==null) intArrayType=new Struct(Struct.Array,Tab.intType);
			arrayStruct=intArrayType;
		}
		else if(currentVarDeclType.equals(Tab.charType))
		{
			if(charArrayType==null) charArrayType=new Struct(Struct.Array,Tab.charType);
			arrayStruct=charArrayType;
		}
		else if(currentVarDeclType.equals(boolType))
		{
			if(boolArrayType==null) boolArrayType=new Struct(Struct.Array,boolType);
			arrayStruct=boolArrayType;
		}
		
		if(arrayStruct==null) report_error("Tip nizovske promenljive "+ formalParamDecl.getVarName()+" nije validan za niz.", formalParamDecl);
		else
		{
			report_info("Deklarisana nizovska promenljiva "+ formalParamDecl.getVarName(), formalParamDecl);
			Obj varNode = Tab.insert(Obj.Var, formalParamDecl.getVarName(), arrayStruct);
			varNode.setFpPos(currentMethodNumberOfFormals);
			currentMethodNumberOfFormals++;
		}
		varDeclCount++;
		currentVarDeclType=null;
	}
	
    public void visit(PrintStmt printStmt) {
		printCallCount++;
		Struct s = printStmt.getExpr().struct;
		if(!(s == Tab.charType || s == Tab.intType || s == boolType))
		{
			report_error("Greska na liniji "+ printStmt.getLine()+" : tip argumenta print funkcije mora biti int,char ili bool.", null);
		}
		if(currentMethod!=null && currentMethod==main)
		{
			statementsInMainCounter++;
		}
	}
    
    public void visit(ExtendedPrintStmt printStmt) {
		printCallCount++;
		Struct s = printStmt.getExpr().struct;
		if(!(s == Tab.charType || s == Tab.intType || s == boolType))
		{
			report_error("Greska na liniji "+ printStmt.getLine()+" : tip argumenta print funkcije mora biti int,char ili bool.", null);
		}
		if(currentMethod!=null && currentMethod==main)
		{
			statementsInMainCounter++;
		}
	}
    
    public void visit(ReadStmt readStmt) {
		Struct s = readStmt.getDesignator().obj.getType();
		if(!(s == Tab.charType || s == Tab.intType || s == boolType))
		{
			report_error("Greska na liniji "+ readStmt.getLine()+" : tip argumenta read funkcije mora biti int,char ili bool.", null);
		}
		if(readStmt.getDesignator().obj.getKind()!=Obj.Var && readStmt.getDesignator().obj.getKind()!=Obj.Elem)
		{
			report_error("Greska na liniji "+ readStmt.getLine()+" : tip argumenta read funkcije mora biti promenljiva ili element niza.", null);
		}
		if(currentMethod!=null && currentMethod==main)
		{
			statementsInMainCounter++;
		}
	}
    
    public void visit(ProgName progName){
    	boolType=Tab.find("bool").getType();
    	progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
    	Tab.openScope();
    }
    
    public void visit(Program program){
    	nVars = Tab.currentScope.getnVars();
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	Tab.closeScope();
    	if(main==null)
    	{
    		report_error("Greska : Program ne sadrzi main funkciju.", null);
    	}
    	else
    	{
    		if(main == Tab.noObj  
    			||	main.getKind() != Obj.Meth 
    			||	main.getType() != Tab.noType
    			||	main.getLevel() != 0) 
    			report_error("Greska: Program mora sadrzati funkciju main bez formalnih parametara.", null);
    	}
    }
    
    public void visit(Type type){
    	Obj typeNode = Tab.find(type.getTypeName());
    	if(typeNode == Tab.noObj){
    		report_error("Greska na liniji "+ type.getLine()+" Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", null);
    		type.struct = Tab.noType;
    	}else{
    		if(Obj.Type == typeNode.getKind()){
    			type.struct = typeNode.getType();
    		}else{
    			report_error("Greska na liniji "+type.getLine()+": Ime " + type.getTypeName() + " ne predstavlja tip!", type);
    			type.struct = Tab.noType;
    		}
    	}
    }
    
    public void visit(MethodTypeName methodTypeName){
    	
    	methodTypeName.obj = currentMethod = Tab.insert(Obj.Meth, methodTypeName.getMethodName(), Tab.noType);
    	methodCounter++;
    	if(methodTypeName.getMethodName().equals("main"))
    	{
    		main=currentMethod;
    	}
    	currentMethodNumberOfFormals=0;
    	Tab.openScope();
		report_info("Obradjuje se funkcija " + methodTypeName.getMethodName(), methodTypeName);
    }
    
    public void visit(VoidMethodDeclaration methodDecl){
    	Tab.chainLocalSymbols(currentMethod);
    	Tab.closeScope();
    	currentMethod.setLevel(currentMethodNumberOfFormals);
    	currentMethod = null;
    }
    
    public void visit(VoidMethodDeclarationWithoutLocals methodDecl){
    	Tab.chainLocalSymbols(currentMethod);
    	Tab.closeScope();
    	currentMethod.setLevel(currentMethodNumberOfFormals);
    	currentMethod = null;
    }
    
    public void visit(ScalarDesignator varDesignator){
    	Obj obj = Tab.find(varDesignator.getVarName());
    	if(obj == Tab.noObj){
			report_error("Greska na liniji " + varDesignator.getLine()+ " : ime "+varDesignator.getVarName()+" nije deklarisano! ", null);
    	}
    	else
    	{
    		DumpSymbolTableVisitor dstv=new DumpSymbolTableVisitor();
    		dstv.visitObjNode(obj);
    		report_info("Pretraga na "+varDesignator.getLine()+"("+varDesignator.getVarName()+"), nadjeno "+ dstv.getOutput() ,null);
    	}
    	varDesignator.obj = obj;
    }
    
    public void visit(ArrayDesignator arrayDesignator){
    	Obj obj = Tab.find(arrayDesignator.getArrayName().getVarName());
    	if(obj == Tab.noObj){
			report_error("Greska na liniji " + arrayDesignator.getLine()+ " : ime "+arrayDesignator.getArrayName().getVarName()+" nije deklarisano! ", null);
    	}
    	else if(obj.getKind() != Obj.Var || obj.getType().getKind()!=Struct.Array){
			report_error("Greska na liniji " + arrayDesignator.getLine()+ " : ime "+arrayDesignator.getArrayName().getVarName()+" nije nizovska promenljiva! ", null);
    	}
    	if(arrayDesignator.getExpr().struct!=Tab.intType)
    	{
    		report_error("Greska na liniji " + arrayDesignator.getLine()+ " : Tip indeksa mora biti celobrojni!", null);
    	}

    	if(obj.getType().getKind()==Struct.Array)
    	{
    		arrayDesignator.obj = new Obj(Obj.Elem,obj.getName(),obj.getType().getElemType());
    		arrayDesignator.obj.setAdr(obj.getAdr());
    		arrayDesignator.obj.setFpPos(obj.getFpPos());
    		arrayDesignator.obj.setLevel(obj.getLevel());
    	}
    	else
    		arrayDesignator.obj=obj;
    }
    
    public void visit(NegSingleTermExpr expression) {
    	expression.struct=expression.getTerm().struct;
    	if(expression.struct!=Tab.intType){
			report_error("Greska na liniji "+ expression.getLine()+" : ocekivan tip je celobrojni za operaciju negacije.", null);
			expression.struct = Tab.noType;
    	}
    }
    
    public void visit(MultipleTermExpr multipleTermExpr) {
    	Struct addopExprStruct = multipleTermExpr.getExpr().struct;
    	Struct termStruct = multipleTermExpr.getTerm().struct;
    	if(addopExprStruct.equals(termStruct) && addopExprStruct == Tab.intType){
    		multipleTermExpr.struct = addopExprStruct;
    	}else{
			report_error("Greska na liniji "+ multipleTermExpr.getLine()+" : nekompatibilni tipovi u izrazu za sabiranje.", null);
			multipleTermExpr.struct = Tab.noType;
    	}
    }
    
    public void visit(SingleTermExpr singleTermExpr) {
    	singleTermExpr.struct=singleTermExpr.getTerm().struct;
    }
    
    public void visit(MultipleFactorTerm multipleFactorTerm) {
    	Struct factorStruct = multipleFactorTerm.getFactor().struct;
    	Struct termStruct = multipleFactorTerm.getTerm().struct;
    	if(factorStruct.equals(termStruct) && factorStruct == Tab.intType){
    		multipleFactorTerm.struct = factorStruct;
    	}else{
			report_error("Greska na liniji "+ multipleFactorTerm.getLine()+" : nekompatibilni tipovi u izrazu za mnozenje.", null);
			multipleFactorTerm.struct = Tab.noType;
    	}
    }
    
    public void visit(SingleFactorTerm singleFactorTerm) {
    	singleFactorTerm.struct=singleFactorTerm.getFactor().struct;
    }
    
    public void visit(NumConstFactor numConstFactor){
    	numConstFactor.struct=Tab.intType;
    }
    
    public void visit(CharConstFactor charConstFactor){
    	charConstFactor.struct=Tab.charType;
    }
    
    public void visit(BoolConstFactor boolConstFactor){
    	boolConstFactor.struct=boolType;
    }
    
    public void visit(ExprFactor exprFactor){
    	exprFactor.struct=exprFactor.getExpr().struct;
    }
    
    public void visit(NewArrayFactor newArrayFactor){
		if(!(newArrayFactor.getType().struct == Tab.charType || newArrayFactor.getType().struct == Tab.intType || newArrayFactor.getType().struct == boolType))
		{
			report_error("Greska na liniji "+ newArrayFactor.getLine()+" : Elementi niza moraju biti prostog tipa!", null);
		}
    	if(newArrayFactor.getExpr().struct!=Tab.intType)
    	{
    		report_error("Greska na liniji " + newArrayFactor.getLine()+" : broj elemenata niza mora biti celobrojni podatak!", null);
    	}
    	
    	if(newArrayFactor.getType().struct==Tab.intType)
    	{
    		newArrayFactor.struct=intArrayType;
    	}
    	else if(newArrayFactor.getType().struct==Tab.charType)
    	{
    		newArrayFactor.struct=charArrayType;
    	}
    	else if(newArrayFactor.getType().struct==boolType)
    	{
    		newArrayFactor.struct=boolArrayType;
    	}
    	else
    	{
    		newArrayFactor.struct=Tab.noType;
    	}
    }
    
    public void visit(DesignatorFactor designatorFactor){
    		designatorFactor.struct=designatorFactor.getDesignator().obj.getType();
    }
    
    public void visit(DesignatorAssignment assignment){
    	if(!assignment.getExpr().struct.assignableTo(assignment.getDesignator().obj.getType()))
    		report_error("Greska na liniji " + assignment.getLine() + " : " + " nekompatibilni tipovi u dodeli vrednosti! ", null);
    	if(!(assignment.getDesignator().obj.getKind()==Obj.Var || assignment.getDesignator().obj.getKind()==Obj.Elem))
    		report_error("Greska na liniji " + assignment.getLine() + " : " + " sa leve strane znaka jednakosti se mora nalaziti promenljiva ili element niza! ", null);
    }
    
    public void visit(IncDesignatorStmt statement){
    	if(!(statement.getDesignator().obj.getType()==Tab.intType && (statement.getDesignator().obj.getKind()==Obj.Var || statement.getDesignator().obj.getKind()==Obj.Elem)))
    		report_error("Greska na liniji " + statement.getLine() + " : " + "inkrementiranje zahteva da je promenljiva celobrojna! ", null);
    }
    
    public void visit(DecDesignatorStmt statement){
    	if(!(statement.getDesignator().obj.getType()==Tab.intType && (statement.getDesignator().obj.getKind()==Obj.Var || statement.getDesignator().obj.getKind()==Obj.Elem)))
    		report_error("Greska na liniji " + statement.getLine() + " : " + "dekrementiranje zahteva da je promenljiva celobrojna! ", null);
    }
    
    public void visit(DesignatorAssignmentWithSquareBrackets assignment)
    {
    	if(!(assignment.getDesignator().obj.getType().getKind()==Struct.Array))
    	{
    		report_error("Greska na liniji " + assignment.getLine() + " : " + " identifikator "+assignment.getDesignator().obj.getName()+" nije niz! ", null);
    		designatorAssignmentList.clear();
    		return;
    	}
    	
    	for(Designator d:designatorAssignmentList)
    	{
    		if(!assignment.getDesignator().obj.getType().getElemType().assignableTo(d.obj.getType()))
        		report_error("Greska na liniji " + assignment.getLine() + " : " + " nekompatibilni tipovi promenljivih "+assignment.getDesignator().obj.getName()+" i "+d.obj.getName()+" u dodeli vrednosti! ", null);
    	}
    	designatorAssignmentList.clear();
    }
    
    
    public void visit(NotEmptyOptionalDesignator designator)
    {
    	if(!(designator.getDesignator().obj.getKind()==Obj.Var || designator.getDesignator().obj.getKind()==Obj.Elem))
    		report_error("Greska na liniji " + designator.getLine() + " : " + " identifikator "+designator.getDesignator().obj.getName()+" nije niti promenljiva,niti element niza! ", null);
    	else {
    		designatorAssignmentList.add(designator.getDesignator());
    	}
    }
    
    public boolean passed(){
    	return !errorDetected;
    }
}
