package geodes.relis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import geodes.relis.grammarModifier.RscGrammarModifier;

@Configuration
@ComponentScan(basePackages = "geodes.relis")
public class App {
    private static final String grammarModelPath = "lib/relis-statistical-classifiers/RelisTextual.xtext";
    private static final String relisModelPath = "models/model_transformation.relis";

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
        RscGrammarModifier rscGrammarModifier = ctx.getBean(RscGrammarModifier.class);
        rscGrammarModifier.execute(grammarModelPath, relisModelPath);
    }
}
