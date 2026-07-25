package session4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class Student {
    private String name;
    private int grade;
    private int age;

    public Student(String name, int grade, int age){
        this.name = name;
        this.grade = grade;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}


public class App {
    static void main() {
        List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));

//        List<Integer> filteredList = new ArrayList<>();
//        for (int num : nums) {
//            if (num%2 == 0) filteredList.add(num);
//        }
//        System.out.println(filteredList);

//        List<Integer> filteredStreamList;
//        Stream<Integer> streamChain = nums.stream()
//                .filter((num) -> num%2 == 0)
//                .map((num) -> num*2);
//        filteredStreamList = streamChain.collect(Collectors.toList());
//        System.out.println(filteredStreamList);

//        List<List<Integer>> structList = Arrays.asList(
//                new ArrayList<>(Arrays.asList(1, 2)),
//                new ArrayList<>(Arrays.asList(3, 4)),
//                new ArrayList<>(Arrays.asList(5, 6))
//        );
//
//        structList.stream()
//                .flatMap(List::stream)
//                .forEach(System.out::println);


//        int sum = nums.stream().reduce(0, Integer::sum);
//        System.out.println(sum);

        List<Student> students = new ArrayList<>(List.of(
                new Student("a", 1, 5),
                new Student("b", 1, 6),
                new Student("c", 1, 4),
                new Student("d", 2, 6),
                new Student("e", 2, 7),
                new Student("f", 3, 7),
                new Student("g", 3, 8)
        ));
//
//        Map<Integer, List<Student>> studentsGrouped = students.stream()
//                .collect(Collectors.groupingBy(Student::getGrade));
//
//        for (Map.Entry<Integer, List<Student>> e : studentsGrouped.entrySet()){
//            List<Student> tempStud = e.getValue();
//            System.out.println(
//                    "Key: " + e.getKey()
//            );
//            tempStud.stream().forEach((stud) -> System.out.print(stud.getName() + " "));
//            System.out.println();
//        }

//        students.parallelStream().forEachOrdered((student) -> System.out.println(student.getName() + " " + student.getGrade()));


        students.stream()
                .filter((student) -> student.getGrade() == 3 && student.getAge() == 7)
                .forEach((student) -> System.out.println(student.getName()));


        students.parallelStream() // parallel processing
                .filter((student) -> student.getGrade() == 3 && student.getAge() == 7)
                .forEach((student) -> System.out.println(student.getName()));




    }
}
