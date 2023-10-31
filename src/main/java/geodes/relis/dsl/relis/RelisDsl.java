package geodes.relis.dsl.relis;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.springframework.stereotype.Service;
import org.xtext.language.relis.RelisStandaloneSetup;
import org.xtext.language.relis.relis.RelisPackage;

import com.google.inject.Injector;

import geodes.relis.dsl.interfaces.Dsl;
import geodes.relis.xtextParser.XtextModelParser;
import geodes.relis.xtextParser.XtextModelParserConfig;

@Service
public class RelisDsl implements Dsl {

    public EList<EObject> getModelObjects(String relisModelPath) {
        RelisStandaloneSetup relisStandaloneSetup = new RelisStandaloneSetup();
        Injector injector = relisStandaloneSetup.createInjectorAndDoEMFRegistration();
        XtextModelParserConfig relisParserConfig = new XtextModelParserConfig(
                RelisPackage.eNS_URI,
                RelisPackage.eINSTANCE.getName(),
                relisModelPath,
                injector);

        XtextModelParser relisModelParser = new XtextModelParser(relisParserConfig);
        return relisModelParser.parse();
    }
}
