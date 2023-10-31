package geodes.relis.dsl.rsc;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.XtextPackage;
import org.eclipse.xtext.XtextStandaloneSetup;
import org.springframework.stereotype.Service;

import com.google.inject.Injector;

import geodes.relis.dsl.interfaces.Dsl;
import geodes.relis.xtextParser.XtextModelParser;
import geodes.relis.xtextParser.XtextModelParserConfig;

@Service
public class RscDsl implements Dsl {
    public EList<EObject> getModelObjects(String grammarModelPath) {
        XtextStandaloneSetup xtextStandaloneSetup = new XtextStandaloneSetup();
        Injector injector = xtextStandaloneSetup.createInjectorAndDoEMFRegistration();
        XtextModelParserConfig relisParserConfig = new XtextModelParserConfig(
                XtextPackage.eNS_URI,
                XtextPackage.eINSTANCE.getName(),
                grammarModelPath,
                injector);

        XtextModelParser relisModelParser = new XtextModelParser(relisParserConfig);
        return relisModelParser.parse();
    }
}
