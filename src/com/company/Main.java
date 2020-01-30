package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        new Main().run();
	// write your code here
    }

    private void run() {
//        System.out.println(IntStream.iterate(1, x -> x * 2).limit(10).sum());
        List<Integer> collect = IntStream.iterate(5, x -> ((x * 3) + 5) ).mapToObj(x -> x).limit(10).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    private void run7() {
        createList().stream()
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                //.sorted((p1,p2)->Integer.compare(p1.getAge(),p2.getAge()))
                .forEach(System.out::println);
    }

    private void run6() {
        Map<Boolean, List<Person>> collect = createList().stream()
                .collect(Collectors.partitioningBy(p -> p.getSex() == 'm'));
            collect.get(true).forEach(System.out::println);
        System.out.println("------------");
        collect.get(false).forEach(System.out::println);
    }

    private void run5() {
        createList().stream().
                filter(p->p.getAge()<50).
                collect(Collectors.toSet()).
                forEach(p-> System.out.println(p));

    }

    private void run4() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(IntStream.generate(() -> scanner.nextInt()).limit(5).sum()); //скобки нужны если входящих 0
    }

    private void run3() {
        List<Person> people = createList();
        Optional<String> first = people.stream().filter(p-> {
            return p.getAge() > 25;
        }).
                filter(p-> {
                    return p.getAge() < 100;
                }).
                map(person -> person.getName()).
                findFirst();
        first.ifPresent(x -> System.out.println(x));
        System.out.println(first.orElse("ZZZZ"));
    }

    private void run2() {
        List<Person> people = createList();
        //map ПРЕОБРАЗУЕТ входящий параметр в ... данном случае сначала строку потом длину строки
        people.stream().filter(p-> {
            return p.getAge() > 25;
        }).
                filter(p-> {
                    return p.getAge() < 100;
                }).
                map(person -> person.getName()).
                mapToInt(s -> s.length()).
                forEach(p-> {
                    System.out.println(p);
                });
          //      forEach(System.out::println); // NE STRIM   :: функциональный интерфейс консьюмер. Конец стрима!



//        people.forEach(p-> System.out.println(p));
//        people.forEach(System.out::println); // NE STRIM   :: функциональный интерфейс консьюмер.
    }

    private List<Person> createList() {
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("Gans",13,'m'));
        people.add(new Person("Peter",42,'f'));
        people.add(new Person("Fans",111,'f'));
        people.add(new Person("Cans",34,'n'));
        return people;
    }
}
