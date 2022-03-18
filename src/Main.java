import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> surnames = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    surnames.get(new Random().nextInt(surnames.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long underageCount = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();

        List<String> soldierList = persons.stream()
                .filter(person -> person.getAge() >= 18 || person.getAge() < 27)
                .map(person -> person.getSurname())
                .collect(Collectors.toList());

        List<Person> workerList = persons.stream()
                .filter(person ->
                    (person.getSex() == Sex.MAN && person.getAge() >= 18 && person.getAge() < 65) ||
                    (person.getSex() == Sex.WOMAN && person.getAge() >= 18 && person.getAge() < 60)
                )
                .filter(person -> person.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(person -> person.getSurname()))
                .collect(Collectors.toList());

        System.out.println(underageCount);
        System.out.println(soldierList.subList(0, 5));
        System.out.println(workerList.subList(0, 5));
    }
}
