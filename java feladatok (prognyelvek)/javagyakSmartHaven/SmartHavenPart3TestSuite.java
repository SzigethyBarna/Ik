import org.junit.platform.suite.api.*;
import org.junit.jupiter.api.*;

import smarthaven.SmartHaven;
import smarthaven.SmartHavenTest;

@SelectClasses({
	SmartHavenPart3TestSuite.StructuralTests.class,
	SmartHavenPart3TestSuite.FunctionalTests.class,
})
@Suite(failIfNoTests=false) public class SmartHavenPart3TestSuite {
	@SelectClasses({
       SmartHavenStructureTest.class,
    })
    @Suite(failIfNoTests=false) @Tag("structural") public static class StructuralTests {}

    @SelectClasses({
       SmartHavenTest.class,
    })
    @Suite(failIfNoTests=false) @Tag("functional") public static class FunctionalTests {}
}