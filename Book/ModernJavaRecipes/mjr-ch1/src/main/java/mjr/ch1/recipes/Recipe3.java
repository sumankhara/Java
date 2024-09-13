package mjr.ch1.recipes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Constructor References
public class Recipe3 {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(new Person("Grace Hopper"), new Person("Barbara Liskov"),
                new Person("Ada Lovelace"), new Person("Karen Jones"));

        //Converting a list of persons to a list of names
        List<String> names = people.stream()
                .map(p -> p.getName())
                .collect(Collectors.toList());
        System.out.println(names);

        //or alternatively
        names = people.stream()
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println(names);

        //Transforming strings into Person instances
        List<Person> personList = names.stream()
                .map(name -> new Person(name))
                .collect(Collectors.toList());
        System.out.println(personList);

        //or alternatively
        personList = names.stream()
                .map(Person::new)
                .collect(Collectors.toList());
        System.out.println(personList);

        //Converting a list to a stream and back
        Person before = new Person("Grace Hopper");

        List<Person> people1 = Stream.of(before)
                .collect(Collectors.toList());

        Person after = people1.get(0);

        System.out.println(before == after);

        before.setName("Grace Murray Hopper");

        System.out.println("Grace Murray Hopper".equals(after.getName()));

        //Using copy constructor
        List<Person> people2 = Stream.of(before)
                .map(Person::new)
                .collect(Collectors.toList());

        after = people2.get(0);

        System.out.println(before == after);

        //varargs constructor
        personList = names.stream()
                .map(name -> name.split(" "))
                .map(Person::new)
                .collect(Collectors.toList());
        System.out.println(personList);

        //Creating an array of Person references
        Person[] persons = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);
        System.out.println(Arrays.asList(persons));
    }
}

class Person {
    private String name;

    public Person() {}

    public Person(String name) {
        this.name = name;
    }

    public Person(String... names) {
        this.name = Arrays.stream(names)
                .collect(Collectors.joining(" "));
    }

    public Person(Person p) {
        this.name = p.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
