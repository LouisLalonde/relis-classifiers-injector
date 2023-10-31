package geodes.relis.grammarModifier;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import geodes.relis.dsl.relis.RelisDsl;
import geodes.relis.dsl.rsc.RscDsl;

import org.springframework.stereotype.Service;

@Service
public class RscGrammarModifier {
	private final RelisDsl relisDsl;
	private final RscDsl rscDsl;

	public RscGrammarModifier(RelisDsl relisDsl, RscDsl rscDsl) {
		this.relisDsl = relisDsl;
		this.rscDsl = rscDsl;
	}

	public void execute(String grammarModelPath, String relisModelPath) {
		EList<EObject> relisProjectObjects = this.relisDsl.getModelObjects(relisModelPath);
		EList<EObject> rscGrammarObjects = this.rscDsl.getModelObjects(grammarModelPath);
	}
}