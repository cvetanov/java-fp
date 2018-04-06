package patterns.design;

import java.util.function.Consumer;

/**
 * From Devoxx talk by @venkat_s.
 *
 * Reminds me of React render-props.
 */
public class ExecuteAroundMethod {

    public static void main(String[] args) {
        Resource.use(resource -> resource.operation().anotherOperation());
    }

}

class Resource {

    // prevent instance creation
    private Resource() {
        System.out.println("Someone wants to use me, I will 'spawn' myself");
    }

    // do something
    public Resource operation() {
        System.out.println("I did something");
        return this;
    }

    // do another thing
    public Resource anotherOperation() {
        System.out.println("I did another thing");
        return this;
    }

    // clean up
    private void close() {
        System.out.println("I am done, I will close myself now");
    }

    public static void use(Consumer<Resource> consumer) {
        final Resource resource = new Resource();
        try {
            consumer.accept(resource);
        } finally {
            resource.close();
        }
    }
}
