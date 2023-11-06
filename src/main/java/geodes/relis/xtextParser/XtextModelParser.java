package geodes.relis.xtextParser;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResourceSet;
import com.google.inject.Injector;

public class XtextModelParser {
    public XtextModelParserConfig xtextParserFromFileConfig;

    public XtextModelParser(XtextModelParserConfig xtextParserFromFileConfig) {
        this.xtextParserFromFileConfig = xtextParserFromFileConfig;
    }

    public Resource parse() {
        // Initialize Xtext
        Injector injector = this.xtextParserFromFileConfig.getInjector();
        XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
        resourceSet.getPackageRegistry().put(this.xtextParserFromFileConfig.geteNS_URI(),
                this.xtextParserFromFileConfig.geteINSTANCE());

        // Load the Xtext grammar
        return resourceSet.getResource(
                org.eclipse.emf.common.util.URI.createFileURI(this.xtextParserFromFileConfig.getModelPath()), true);
    }
}