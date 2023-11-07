package geodes.relis.grammarModifier;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.EnumLiteralDeclaration;
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.TypeRef;
import org.eclipse.xtext.XtextFactory;
import org.eclipse.xtext.XtextStandaloneSetup;
import org.eclipse.xtext.serializer.impl.Serializer;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

import geodes.relis.dsl.relis.RelisDslImpl;
import geodes.relis.dsl.rsc.RscDslImpl;

import org.springframework.stereotype.Service;

import com.google.inject.Injector;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.impl.EnumRuleImpl;

@Service
public class RscGrammarModifier {

	public void execute(String grammarModelPath, String relisModelPath) {
		RelisDslImpl relisDsl = new RelisDslImpl(relisModelPath);
		RscDslImpl rscDsl = new RscDslImpl(grammarModelPath);
		EList<EObject> relisProjectCategories = relisDsl.getCategories();
		EList<EObject> rscObjects = rscDsl.getModelObjects();
		Resource rscGrammar = rscDsl.getModel();
		// Resource modifiedRscGrammar = createEnumRelisCategories(rscGrammar);
		serializeXtextGrammar(rscGrammar);
	}

	public Resource createEnumRelisCategories(Resource rscGrammar) {
		EEnum myNewEnum = EcoreFactory.eINSTANCE.createEEnum();
		myNewEnum.setName("MyNewEnum");
		// Define literals for the enum
		EEnumLiteral literal1 = EcoreFactory.eINSTANCE.createEEnumLiteral();
		literal1.setName("LITERAL1");
		literal1.setValue(0); // ordinal value

		EEnumLiteral literal2 = EcoreFactory.eINSTANCE.createEEnumLiteral();
		literal2.setName("LITERAL2");
		literal2.setValue(1); // ordinal value

		// Add literals to the enum
		myNewEnum.getELiterals().add(literal1);
		myNewEnum.getELiterals().add(literal2);

		// Add the new EEnum to the EPackage
		// rscGrammar.getContents().get(0).eContents().add(myNewEnum);
		return rscGrammar;
	}

	public void serializeXtextGrammar(Resource rscGrammar) {
		XtextStandaloneSetup xtextStandaloneSetup = new XtextStandaloneSetup();
		Injector injector = xtextStandaloneSetup.createInjectorAndDoEMFRegistration();
		Serializer serializer = injector.getInstance(Serializer.class);

		// ResourceSet resourceSet = new ResourceSetImpl();
		// Resource resource = resourceSet.createResource(rscGrammar.getURI());
		// resource.getContents().add(rscGrammar.getContents().get(0));
		Grammar grammar = (Grammar) rscGrammar.getContents().get(0);

		// Assuming grammar is already loaded and is the root of your grammar model

		// Create the enum
		EEnum myNewEnum = EcoreFactory.eINSTANCE.createEEnum();
		myNewEnum.setName("MyNewEnum");

		// Define literals for the enum
		EEnumLiteral literalA = EcoreFactory.eINSTANCE.createEEnumLiteral();
		literalA.setName("Nominal");
		literalA.setValue(0); // ordinal value

		EEnumLiteral literalB = EcoreFactory.eINSTANCE.createEEnumLiteral();
		literalB.setName("Continuous");
		literalB.setValue(1); // ordinal value

		// Create the corresponding EnumRule for Xtext grammar
		EnumRule enumRule = XtextFactory.eINSTANCE.createEnumRule();
		enumRule.setName("MyNewType");

		Keyword continuousKeyword = XtextFactory.eINSTANCE.createKeyword();
		continuousKeyword.setValue("Continuous");

		Keyword nominalKeyword = XtextFactory.eINSTANCE.createKeyword();
		nominalKeyword.setValue("Nominal");

		// Create the corresponding EnumLiteralDeclarations for the Xtext EnumRule
		EnumLiteralDeclaration enumLiteralDeclA = XtextFactory.eINSTANCE.createEnumLiteralDeclaration();
		// enumLiteralDeclA.setEnumLiteral(literalA);
		enumLiteralDeclA.setLiteral(continuousKeyword);

		EnumLiteralDeclaration enumLiteralDeclB = XtextFactory.eINSTANCE.createEnumLiteralDeclaration();
		enumLiteralDeclB.setEnumLiteral(literalB);
		enumLiteralDeclB.setLiteral(nominalKeyword);

		// Create an Alternatives construct which will hold the EnumLiteralDeclarations
		Alternatives alternatives = XtextFactory.eINSTANCE.createAlternatives();
		alternatives.getElements().add(enumLiteralDeclA);
		alternatives.getElements().add(enumLiteralDeclB);

		// Set the alternatives in the EnumRule
		enumRule.setAlternatives(alternatives);

		// Finally, add the new EnumRule to the grammar
		grammar.getRules().add(enumRule);

		// EEnum myNewEnum = EcoreFactory.eINSTANCE.createEEnum();
		// myNewEnum.setName("MyNewEnum");
		// Define literals for the enum
		// EEnumLiteral eeliteral1 = EcoreFactory.eINSTANCE.createEEnumLiteral();
		// eeliteral1.setName("LITERAL1");
		// eeliteral1.setValue(0); // ordinal value

		// EEnumLiteral eeliteral2 = EcoreFactory.eINSTANCE.createEEnumLiteral();
		// eeliteral2.setName("LITERAL2");
		// eeliteral2.setValue(1); // ordinal value

		// EnumRule enumRule = XtextFactory.eINSTANCE.createEnumRule();
		// enumRule.setName("MyNewEnumRule");

		// // Create a new Alternatives construct
		// Alternatives alternatives = XtextFactory.eINSTANCE.createAlternatives();

		// // Create EnumLiteralDeclarations for each literal
		// EnumLiteralDeclaration literal1 =
		// XtextFactory.eINSTANCE.createEnumLiteralDeclaration();
		// literal1.setEnumLiteral(eeliteral1);

		// EnumLiteralDeclaration literal2 =
		// XtextFactory.eINSTANCE.createEnumLiteralDeclaration();
		// literal2.setEnumLiteral(eeliteral2);

		// // Add literals to Alternatives
		// alternatives.getElements().add(literal1);
		// alternatives.getElements().add(literal2);
		// // alternatives.getElements().add(literal2);

		// // Set the Alternatives as the EnumRule's alternatives
		// enumRule.setAlternatives(alternatives);

		// grammar.getRules().add(enumRule);

		// // Add literals to the enum as you have in your previous examples.

		// // Create a new EnumRule using XtextFactory
		// EnumRule enumRule = XtextFactory.eINSTANCE.createEnumRule();
		// enumRule.setName("MyNewEnumRule");
		// grammar.getRules().add(enumRule);

		// EnumLiteralDeclaration enumLiteralDeclaration =
		// XtextFactory.eINSTANCE.createEnumLiteralDeclaration();
		// enumLiteralDeclaration.setEnumLiteral(literal1);

		// enumRule.setAlternatives(enumLiteralDeclaration);

		// Check if the resource has contents
		if (!rscGrammar.getContents().isEmpty()) {
			EObject eObject = rscGrammar.getContents().get(0);

			// Serialize the EObject to a String
			try {
				String result = serializer.serialize(eObject);
				// Save the result to a file
				Files.write(Paths.get("./output/rscGrammar.tlr"),
						result.getBytes(StandardCharsets.UTF_8));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}