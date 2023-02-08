package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;

public class Compiler {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}

	public static void tsdump() {
		MJTab.dump(new MJTab.MJDumpSymbolTableVisitor());
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Logger log = Logger.getLogger(Compiler.class);

		if (args.length < 2) {
			log.error("Greska: Potrebno je uneti 2 argumenta,naziv src fajla i naziv obj fajla!");
			return;
		}

		Reader br = null;
		try {
			File sourceCode = new File(args[0]);

			if (!sourceCode.exists()) {
				log.error("Src fajl [" + sourceCode.getAbsolutePath() + "] nije pronadjen!");
				return;
			}

			log.info("Kompajliranje src fajla: " + sourceCode.getAbsolutePath());

			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);

			MJParser p = new MJParser(lexer);
			Symbol s = p.parse(); // pocetak parsiranja

			if (!p.errorDetected) {
				Program prog = (Program) (s.value);
				// ispis sintaksnog stabla
				log.info("=================SINTAKSNO STABLO=================");
				log.info(prog.toString(""));
				log.info("=================SEMANTICKA ANALIZA=================");

				MJTab.init();
				// ispis prepoznatih programskih konstrukcija
				SemanticAnalyzer v = new SemanticAnalyzer();
				prog.traverseBottomUp(v);

				log.info("=================SINTAKSNA ANALIZA=================");
				v.printDetails();
				tsdump();
				
				if (v.passed()) {
					File objFile = new File(args[1]);
					if (objFile.exists())
						objFile.delete();

					CodeGenerator codeGenerator = new CodeGenerator();
					prog.traverseBottomUp(codeGenerator);
					Code.dataSize = v.nVars;
					Code.mainPc = codeGenerator.getMainPc();
					Code.write(new FileOutputStream(objFile));
					log.info("Generisanje koda je uspesno!");
				} else {
					log.error("Semanticka analiza NIJE uspesna!");
				}
			}
			else
			{
				log.error("Parsiranje NIJE izvrseno uspesno!");
			}
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e1) {
					log.error(e1.getMessage(), e1);
				}
		}
	}

}
