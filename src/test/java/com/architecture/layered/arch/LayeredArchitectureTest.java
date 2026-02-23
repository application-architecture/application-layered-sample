package com.architecture.layered.arch;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.architecture.layered")
class LayeredArchitectureTest {

    // 1. Presentation не зависит от Infrastructure
    // Контроллеры не должны знать про JPA, JDBC, репозитории
    @ArchTest
    static final ArchRule presentationShouldNotDependOnInfrastructure =
            noClasses()
                    .that().resideInAPackage("..presentation..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("..infrastructure..");

    // 2. Application не зависит от Infrastructure реализаций
    // Сервисы знают только об интерфейсах, не о JdbcWriteRepository и т.д.
    @ArchTest
    static final ArchRule applicationShouldNotDependOnInfrastructureImpl =
            noClasses()
                    .that().resideInAPackage("..application..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("..infrastructure.impl..");

    // 3. Application не зависит от Presentation
    // Сервисы не знают про контроллеры, Request, Response DTO
    @ArchTest
    static final ArchRule applicationShouldNotDependOnPresentation =
            noClasses()
                    .that().resideInAPackage("..application..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("..presentation..");

    // 4. Domain не зависит ни от кого
    // Чистая доменная модель без Spring, JPA, HTTP
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

    // 5. Контроллеры зависят только от интерфейсов сервисов
    // Не от UserCommandService, а от CommandUseCase
    @ArchTest
    static final ArchRule controllersShouldDependOnUseCaseInterfaces =
            noClasses()
                    .that().resideInAPackage("..presentation..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("..application.impl..");
}
