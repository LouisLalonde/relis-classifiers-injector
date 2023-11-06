package geodes.relis.grammarModifier;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import geodes.relis.dsl.relis.RelisDslImpl;
import geodes.relis.dsl.rsc.RscDslImpl;

import org.springframework.stereotype.Service;

@Service
public class RscGrammarModifier {

	public void execute(String grammarModelPath, String relisModelPath) {
		RelisDslImpl relisDsl = new RelisDslImpl(relisModelPath);
		RscDslImpl rscDsl = new RscDslImpl(grammarModelPath);
		EList<EObject> relisProjectCategories = relisDsl.getCategories();
		Resource rscGrammarObjects = rscDsl.getModel();
	}
}