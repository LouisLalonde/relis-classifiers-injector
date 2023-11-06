package geodes.relis.dsl.relis;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.xtext.language.relis.RelisStandaloneSetup;
import org.xtext.language.relis.relis.RelisPackage;
import org.xtext.language.relis.relis.impl.CategoryImpl;

import com.google.inject.Injector;

import geodes.relis.dsl.interfaces.Dsl;
import geodes.relis.xtextParser.XtextModelParser;
import geodes.relis.xtextParser.XtextModelParserConfig;

public class RelisDslImpl implements Dsl {
    private String relisModelPath;

    public RelisDslImpl(String relisModelPath) {
        this.relisModelPath = relisModelPath;
    }

    public Resource getModel() {
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

    public EList<EObject> getCategories() {
        EList<EObject> modelObject = getModelObjects();
        EList<EObject> eObjects = new BasicEList<EObject>();

        for (EObject obj : modelObject) {
            if (obj instanceof CategoryImpl) {
                eObjects.add(obj);
            }
        }

        return eObjects;
    }
}
