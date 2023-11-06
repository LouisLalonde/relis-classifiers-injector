package geodes.relis.xtextParser;

import com.google.inject.Injector;

public class XtextModelParserConfig {
	private String eNS_URI;
	private String eINSTANCE;
	private String modelPath;
	private Injector injector;
	
	public XtextModelParserConfig(String eNS_URI, String eINSTANCE, String modelPath, Injector injector) {
		this.eNS_URI = eNS_URI;
		this.eINSTANCE = eINSTANCE;
		this.modelPath = modelPath;
		this.injector = injector;
	}
	
	public String geteNS_URI() {
		return eNS_URI;
	}
	
	public String geteINSTANCE() {
		return eINSTANCE;
	}
	
	public String getModelPath() {
		return modelPath;
	}
	
	public Injector getInjector() {
		return injector;
	}
	
	public void seteNS_URI(String eNS_URI) {
		this.eNS_URI = eNS_URI;
	}
	
	public void seteINSTANCE(String eINSTANCE) {
		this.eINSTANCE = eINSTANCE;
	}
	
	public void setModelPath(String modelPath) {
		this.modelPath = modelPath;
	}
	
	public void setInjector(Injector injector) {
		this.injector = injector;
	}
}
