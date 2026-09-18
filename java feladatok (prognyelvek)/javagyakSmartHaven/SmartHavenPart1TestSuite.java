import org.junit.platform.suite.api.*;
import org.junit.jupiter.api.*;

@SelectClasses({
	SmartHavenPart1TestSuite.StructuralTests.class,
	SmartHavenPart1TestSuite.FunctionalTests.class,
})
@Suite(failIfNoTests=false) public class SmartHavenPart1TestSuite {
	@SelectClasses({
        CategoryStructureTest.class,

        IotFunctionStructureTest.class,
        BaseModelStructureTest.class,
    })
    @Suite(failIfNoTests=false) @Tag("structural") public static class StructuralTests {}

    @SelectClasses({
        smarthaven.util.BaseModelTest.class,
    })
    @Suite(failIfNoTests=false) @Tag("functional") public static class FunctionalTests {}
}
