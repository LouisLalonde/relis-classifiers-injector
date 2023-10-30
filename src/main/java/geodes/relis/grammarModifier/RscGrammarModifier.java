package geodes.relis.grammarModifier;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.google.inject.Injector;

import geodes.relis.xtextParser.XtextParserFromFileConfig;
import geodes.relis.xtextParser.XtextModelParser;

// import Relis.RelisPackage;
import org.eclipse.xtext.XtextPackage;
import org.eclipse.xtext.XtextStandaloneSetup;
import org.springframework.stereotype.Service;

@Service
public class RscGrammarModifier {

	public void execute(String grammarModelPath) {
		// EList<EObject> relisProjectObjects = this.getRelisModelObjects();
		EList<EObject> rscGrammarObjects = this.getRcsGrammarModelObjects(grammarModelPath);
		System.out.println(rscGrammarObjects);
	}

	// public EList<EObject> getRelisModelObjects() {
	// 	String modelReference = "GenesExpressionProject.trl";
	// 	RelisTextualStandaloneSetup relisTextualStandaloneSetup = new RelisTextualStandaloneSetup();
	// 	Injector injector = relisTextualStandaloneSetup.createInjectorAndDoEMFRegistration();
	// 	XtextParserFromFileConfig relisParserConfig = new XtextParserFromFileConfig(
	// 			RelisPackage.eNS_URI,
	// 			RelisPackage.eINSTANCE.getName(),
	// 			modelReference,
	// 			injector);
	// 	XtextModelParser relisModelParser = new XtextModelParser(relisParserConfig);

	// 	return relisModelParser.parse();
	// }

	public EList<EObject> getRcsGrammarModelObjects(String modelPath) {
		XtextStandaloneSetup xtextStandaloneSetup = new XtextStandaloneSetup();
		Injector injector = xtextStandaloneSetup.createInjectorAndDoEMFRegistration();
		XtextParserFromFileConfig relisParserConfig = new XtextParserFromFileConfig(
				XtextPackage.eNS_URI,
				XtextPackage.eINSTANCE.getName(),
				modelPath,
				injector);
		XtextModelParser relisModelParser = new XtextModelParser(relisParserConfig);

		return relisModelParser.parse();
	}
}