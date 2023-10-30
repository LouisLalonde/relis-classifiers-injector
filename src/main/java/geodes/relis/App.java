package geodes.relis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import geodes.relis.grammarModifier.GrammarModifierController;

@Configuration
@ComponentScan(basePackages = "geodes.relis")
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
        GrammarModifierController grammarModifierController = ctx.getBean(GrammarModifierController.class);
        String modelReference = "models/RelisTextual.xtext";
        grammarModifierController.getRscGrammarModifier().execute(modelReference);
    }
}
