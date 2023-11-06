package geodes.relis.dsl.rsc;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.XtextPackage;
import org.eclipse.xtext.XtextStandaloneSetup;

import com.google.inject.Injector;

import geodes.relis.dsl.interfaces.Dsl;
import geodes.relis.xtextParser.XtextModelParser;
import geodes.relis.xtextParser.XtextModelParserConfig;

public class RscDslImpl implements Dsl {
    private String grammarModelPath;

    public RscDslImpl(String grammarModelPath) {
        this.grammarModelPath = grammarModelPath;
    }

    public Resource getModel() {
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
