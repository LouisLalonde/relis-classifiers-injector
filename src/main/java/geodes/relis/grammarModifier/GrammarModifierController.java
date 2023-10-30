package geodes.relis.grammarModifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GrammarModifierController {
    private final RscGrammarModifier rscGrammarModifier;

    @Autowired
    public GrammarModifierController(RscGrammarModifier rscGrammarModifier) {
        this.rscGrammarModifier = rscGrammarModifier;
    }

    public RscGrammarModifier getRscGrammarModifier() {
        return this.rscGrammarModifier;
    }
}
