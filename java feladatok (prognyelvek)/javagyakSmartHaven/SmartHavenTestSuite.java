import org.junit.platform.suite.api.*;

@SelectClasses({
	SmartHavenPart1TestSuite.class,
	SmartHavenPart2TestSuite.class,
	SmartHavenPart3TestSuite.class,
})
@Suite(failIfNoTests=false) public class SmartHavenTestSuite {

}
