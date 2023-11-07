package geodes.relis.dsl.rsc;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.XtextStandaloneSetup;

import com.google.inject.Injector;

import Relis.RelisPackage;
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
                RelisPackage.eNS_URI,
                RelisPackage.eINSTANCE.getName(),
                grammarModelPath,
                injector);

        XtextModelParser relisModelParser = new XtextModelParser(relisParserConfig);
        return relisModelParser.parse();
    }

    public EList<EObject> getEObjects(Resource resource) {
        TreeIterator<EObject> iterator = resource.getAllContents();
        EList<EObject> eObjects = new BasicEList<EObject>();

        while (iterator.hasNext()) {
            EObject obj = iterator.next();
            eObjects.add(obj);
        }
        return eObjects;
    }

    public EList<EObject> getModelObjects() {
        Resource resource = getModel();
        return getEObjects(resource);
    }
}
