# relis-classifiers-injector

## 🔍 Overview

This repository handles the *[ReLiS](https://github.com/geodes-sms/relis)* project classification variables injection business logic for the [relis-statistical-classification](https://github.com/LouisLalonde/relis-statistical-classification) (*RSC*) DSL.

It allows users to fit their *ReLiS* project classification variables into the *RSC* Xtext grammar. In consequence, when creating models of the *RSC* DSL, users have predefined choices for their classification variables.

It works by parsing a Relis project model with the org.eclipse.emf.ecore library, extracting the classification variables, transforming them to the classification variables specifications of the *RSC* DSL
and injecting them into a new instance of the *RSC*.

**Primary authors:** Louis Lalonde [@louislalonde](https://github.com/LouisLalonde) and Hanz Schepens [@Wickkawizz](https://github.com/Wickkawizz)
