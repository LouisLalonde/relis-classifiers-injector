package geodes.relis.xtextParser;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

public class XtextModelParser {
	public XtextParserFromFileConfig xtextParserFromFileConfig;
	
	public XtextModelParser(XtextParserFromFileConfig xtextParserFromFileConfig) {
		this.xtextParserFromFileConfig = xtextParserFromFileConfig;
	}
	
    public EList<EObject> parse() {
        // Initialize Xtext
        Injector injector = this.xtextParserFromFileConfig.getInjector();
        XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
        resourceSet.getPackageRegistry().put(this.xtextParserFromFileConfig.geteNS_URI(),
        		this.xtextParserFromFileConfig.geteINSTANCE());
        
        // Load the Xtext grammar
        Resource resource = resourceSet.getResource(org.eclipse.emf.common.util.URI.
        		createFileURI(this.xtextParserFromFileConfig.getModelPath()), true);
        TreeIterator<EObject> iterator = resource.getAllContents();
        EList<EObject> eObjects = new BasicEList<EObject>();
        while (iterator.hasNext()) {
            EObject obj = iterator.next();
            eObjects.add(obj);
        }
        return eObjects;
    }
}