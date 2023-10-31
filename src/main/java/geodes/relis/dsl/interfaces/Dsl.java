package geodes.relis.dsl.interfaces;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

public interface Dsl {
        public EList<EObject> getModelObjects(String modelPath);
}
