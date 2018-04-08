import org.junit.jupiter.api.Test;

class MainBTest {


    private MainB mainB = new MainB();


    @Test
    void dependencyWithModuleAWithSPI() {
        mainB.dependencyWithModuleAWithSPI();
    }

    @Test
    void dependencyWithModuleAWithSPINotFound() {
        mainB.dependencyWithModuleCWithSPINotFound();
    }

    @Test
    void dependencyWithModuleA() {
        mainB.dependencyWithModuleA();

    }


    @Test
    void dependencyWithModuleANotFound() {
        mainB.dependencyWithModuleANotFound();
    }

    @Test
    void dependencyWithModuleCNotFoundSinceNoDependency() {
        mainB.dependencyWithModuleCNotFoundSinceNoDependency();
    }

}
