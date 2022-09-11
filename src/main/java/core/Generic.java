package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generic {
    private class Animal {
        private String name;

        Animal(String name) {
            this.name = name;
        }

        String getName() {
            return this.name;
        }
    }

    private class Cat extends Animal {
        private String name;

        Cat(String name) {
            super(name);
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private List<Cat> getCats() {
        return List.of(new Cat("American Short Hair"),
                        new Cat("British Short Hair"));
    }

    private List<Animal> getAnimals() {
        return new ArrayList<>(Arrays.asList(new Cat("American Short Hair"),
                new Cat("British Short Hair")));
    }

    /**
     **   CSPE (Consumer Super and Producer Extend principle)
     **/
    public void generic() {
        // PE principle
        // or we say it's read-only
        List<? extends Animal> animals1 = getCats();
        animals1.forEach(a -> System.out.println(a.getName()));
        // animals1.add(new Cat("meow"));  <-- this line will cause compilation error

        // CS principle
        // or we say it's write-only
        List<? super Cat> animals2 = getAnimals();
        animals2.add(new Cat("Siam Hairless"));
        // animals2.forEach(a -> System.out.println(a.getName())); <-- this line will cause compilation error
    }
}
