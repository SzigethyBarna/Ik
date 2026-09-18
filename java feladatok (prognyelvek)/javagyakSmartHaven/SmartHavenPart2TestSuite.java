import org.junit.platform.suite.api.*;
import org.junit.jupiter.api.*;

@SelectClasses({
	SmartHavenPart2TestSuite.StructuralTests.class,
	SmartHavenPart2TestSuite.FunctionalTests.class,
})
@Suite(failIfNoTests=false) public class SmartHavenPart2TestSuite {
	@SelectClasses({
        UnsupportedDeviceExceptionStructureTest.class,
        ActionDeviceStructureTest.class,

        InteractiveDeviceStructureTest.class,
    })
    @Suite(failIfNoTests=false) @Tag("structural") public static class StructuralTests {}

    @SelectClasses({
        smarthaven.device.ActionDeviceTest.class,
        smarthaven.device.InteractiveDeviceTest.class,
    })
    @Suite(failIfNoTests=false) @Tag("functional") public static class FunctionalTests {}
}


