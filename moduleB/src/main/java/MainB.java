import com.modularization.ccp.MainA;
import com.modularization.ccp.MainC;
import com.modularization.ccp.RequestContract;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.util.Optional;
import java.util.ServiceLoader;

class MainB {

    class RequestContractImpl implements RequestContract {

        @Override
        public String getValue() {
            return "WORKS";
        }
    }

    void dependencyWithModuleAWithSPI() {
        ServiceLoader.load(MainA.class)
                .forEach(mainA -> System.out.println(mainA.main(new RequestContractImpl())));
    }

    void dependencyWithModuleCWithSPINotFound() {
        ServiceLoader.load(MainC.class)
                .forEach(mainA -> System.out.println(mainA.mainC()));
    }

    void dependencyWithModuleA() {
        Reflections reflections = new Reflections("com.modularization.ccp");
        Optional<Optional<?>> any = reflections.getSubTypesOf(MainA.class)
                .stream()
                .map(clazz -> {
                    try {
                        MainA mainA = clazz.newInstance();
                        return Optional.of(mainA.main(new RequestContractImpl()));
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return Optional.empty();
                }).findAny();
        if (any.isPresent() && any.get().isPresent()) {
            System.out.println(any.get().get());
        }

    }


    void dependencyWithModuleANotFound() {
        Reflections reflections = new Reflections("bla.foo");
        Optional<Optional<String>> any = reflections.getSubTypesOf(MainA.class)
                .stream()
                .map(clazz -> {
                    try {
                        MainA mainA = clazz.newInstance();
                        return Optional.of(mainA.main(new RequestContractImpl()));
                    } catch (InstantiationException | IllegalAccessException e) {
                        return Optional.of("Error in reflection process " + e);
                    }
                }).findAny();
        if (any.isPresent() && any.get().isPresent()) {
            System.out.println(any.get().get());
        } else {
            System.out.println("Not implementation of interface MainA founded.");
        }

    }

    void dependencyWithModuleCNotFoundSinceNoDependency() {
        Reflections reflections = new Reflections("com.modularization.ccp");
        Optional<Optional<String>> any = reflections.getSubTypesOf(MainC.class)
                .stream()
                .map(clazz -> {
                    try {
                        MainC mainC = clazz.newInstance();
                        return Optional.of(mainC.mainC());
                    } catch (InstantiationException | IllegalAccessException e) {
                        return Optional.of("Error in reflection process " + e);
                    }
                }).findAny();
        if (any.isPresent() && any.get().isPresent()) {
            System.out.println(any.get().get());
        } else {
            System.out.println("Not implementation of interface MainC founded. Did you add the dependency");
        }

    }

}
