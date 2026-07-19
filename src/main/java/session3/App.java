package session3;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class Student {
    private String name;
    private int roll;

    public Student(String name, int roll) {
        this.name=name;
        this.roll=roll;
    }

    public String getName() {
        return name;
    }

    public int getRoll() {
        return roll;
    }
}

public class App {
    static void main() {

        /* Wrapper Classes
            - Integer : int
            - String : char[]
            - Character : char
            - Double : double
            - Boolean : boolean
         */

        List<Integer> arr = new LinkedList<>(List.of(1, 3, 4, 5));

        arr.set(3, 10);

//        arr.remove(1);
        arr.remove(new Integer(3));

        arr.add(8);

//        for (int a : arr){
//            System.out.println(a);
//        }


        // ==================== SETs in Collection ==================== //

        Set<Integer> mySet = new HashSet<>(); // Unordered Set - Ordering doesn't matter
        Set<Integer> mySet2 = new LinkedHashSet<>();    // Ordered Set - Ordering is preserved
        Set<Integer> mySet3 = new TreeSet<>(); // Sorted Set - all the elements are automatically sorted

        /*
        *   LinkedHashSet
        *   [prev, key, value, next]
        *
        * */

        mySet.add(1);
        mySet.add(4);
        mySet.add(4);
        mySet.add(3);
        mySet.add(5);
        mySet.add(6);
        mySet.add(7);

//        if (mySet.contains(5)) System.out.println("Exists");

        Set<Student> studentSet = new TreeSet<>(
                (a, b) -> Integer.compare(a.getRoll(), b.getRoll())
        );

        studentSet.add(new Student("Rick", 4));
        studentSet.add(new Student("Ram", 1));
        studentSet.add(new Student("Ricky", 3));
        studentSet.add(new Student("Raj", 2));
        studentSet.add(new Student("Monty", 5));

//        for (Student a : studentSet){
//            System.out.println("name: " + a.getName() + " rollno: " +  a.getRoll());
//        }


        // ==================== Maps in Collection ==================== //

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Hello");
        map.put(2, "World");
        map.put(3, "Surprise");
        map.put(4, "Toys");
        map.put(5, "cards");
        System.out.println(map.get(1));

//        if (map.containsKey(1)) System.out.println("Yes");

        for (Map.Entry<Integer, String> ele : map.entrySet()){
            System.out.println(" " + ele.getKey() + ele.getValue());
        }


        Queue<String> pq = new PriorityQueue<>();
        pq.add("Geeks");
        pq.add("For");
        pq.add("Geeks");

        // use Type safe Iterator
        Iterator<String> iterator = pq.iterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }

}
