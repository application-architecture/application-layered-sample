package com.architecture.layered.arch;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.architecture.layered")
class LayeredArchitectureTest {

    @ArchTest
    static final ArchRule presentationShouldNotDependOnInfrastructure =
            noClasses()
                    .that().resideInAPackage("..presentation..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("..infrastructure..");

    @ArchTest
    static final ArchRule applicationShouldNotDependOnInfrastructureImpl =
            noClasses()
                    .that().resideInAPackage("..application..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("..infrastructure.impl..");

    @ArchTest
    static final ArchRule applicationShouldNotDependOnPresentation =
            noClasses()
                    .that().resideInAPackage("..application..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("..presentation..");

    @ArchTest
    static final ArchRule domainShouldNotDependOnAnything =
            noClasses()
                    .that().resideInAPackage("..domain..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage(
                            "..presentation..",
                            "..application..",
                            "..infrastructure.."
                    );

    @ArchTest
    static final ArchRule controllersShouldDependOnUseCaseInterfaces =
            noClasses()
                    .that().resideInAPackage("..presentation..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("..application.impl..");
}