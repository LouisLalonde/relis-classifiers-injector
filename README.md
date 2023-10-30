# relis-classifiers-injector

## 🔍 Overview

This repository handles the *Relis* classification variable injection business logic for the [relis-statistical-classification](https://github.com/LouisLalonde/relis-statistical-classification) (*RSC*) DSL.

It allows users to fit their *Relis* project classification variables into the *RSC* Xtext grammar. In consequence, when creating models of the *RSC* DSL, users have predefined choices for their classification variables.

It works by parsing a Relis project model with the org.eclipse.emf library, extracting the classification variables, transforming them to the classification variables specifications of the *RSC* DSL
and injecting them into a new instance of the *RSC*.

**Primary authors:** Louis Lalonde [@louislalonde](https://github.com/LouisLalonde) and Hanz Schepens [@Wickkawizz](https://github.com/Wickkawizz)
