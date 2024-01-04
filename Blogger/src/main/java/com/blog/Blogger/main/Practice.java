package com.blog.Blogger.main;

import com.blog.Blogger.main.PostDto;
import javax.persistence.criteria.CriteriaBuilder;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Practice {
    public static void main(String[] args) {

List<Post> post=Arrays.asList(new Post("bbb",81899),
        new Post("aaa",71986),new Post("ccc",81899));
        Map<Double, List<Post>> groups = post.stream().collect(Collectors.groupingBy(Post::getSalary));

        for (Map.Entry<Double,List<Post>> entry:groups.entrySet()) {
            double salary= entry.getKey();
           List<Post> postList= entry.getValue();
            System.out.println("Employees with salary "+salary+":");
            for (Post post1:postList) {
                System.out.println(post1.getName());
            }
        }
    }
//        Post post1=new Post();
//        post1.setId(1);
//        post1.setTitle("aa");
//        post1.setContent("aaa");
//        Post post2=new Post();
//        post2.setId(1);
//        post2.setTitle("bb");
//        post2.setContent("bbb");
//        Post post3=new Post();
//        post3.setId(1);
//        post3.setTitle("cc");
//        post3.setContent("ccc");
////        1 method to call
////        PostDto dto1 = mapToDto(post1);
////        PostDto dto2 = mapToDto(post2);
////        PostDto dto3 = mapToDto(post3);
//
//        // 2 method to call
//        List<Post> posts= Arrays.asList(post1,post2,post3);
//        List<PostDto> dtos = posts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
////        below example is method reference to call method instead of lambda's expression
////        List<PostDto> dtos = posts.stream().map(Practice :: mapToDto).collect(Collectors.toList());
//        System.out.println(dtos);
//    }
//           static PostDto mapToDto(Post post){
//        PostDto dto= new PostDto();
//                dto.setId(post.getId());
//        dto.setTitle(post.getTitle());
//        dto.setContent(post.getContent());
//        return dto;
//            }
        }

//    List<Integer> data= Arrays.asList(3,2,0,1);
//    Function<Integer, Double> divideByTwo = a -> a / 2.0;
//    List<Double> collect = data.stream().map(divideByTwo).collect(Collectors.toList());
//System.out.println(collect);
//    Employee emp1= new Employee();
//        emp1.setName("mike");
//                emp1.setDesc("se");
//
//                Employee emp2= new Employee();
//                emp2.setName("stallin");
//                emp2.setDesc("se");
//
//                Employee emp3= new Employee();
//                emp3.setName("mithun");
//                emp3.setDesc("hr");
//                List<Employee> data= Arrays.asList(emp1,emp2,emp3);
//        List<Employee> collect = data.stream().filter(a -> a.getDesc().equalsIgnoreCase("se")).collect(Collectors.toList());
//
//        for (Employee emp: collect) {
//        System.out.println(emp.getName());
//        }


//    List<String> data= Arrays.asList("mike","mithun","mike","stallin","doe","micheal");
//    List<String> collect = data.stream().filter(a -> a.equals("mike")).collect(Collectors.toList());
//        System.out.println(collect.size());


//    List<String> data= Arrays.asList("mike","mithun","stallin","doe","micheal");
//    List<String> collect = data.stream().filter(a -> a.startsWith("m")).collect(Collectors.toList());
//        System.out.println(collect);

//        List<Integer> data= Arrays.asList(10,20,30,40,50);
//    List<Integer> collect = data.stream().filter(a -> a > 20).collect(Collectors.toList());
//        System.out.println(collect);


//    List<String> fruits = Arrays.asList("apple", "banana", "orange", "grape", "kiwi");
//    // Map each fruit to its length and collect the lengths into a new list
//    List<Integer> fruitLengths = fruits.stream()
//            .map(String::length)
//            .collect(Collectors.toList());
//
//        System.out.println("Fruit lengths: " + fruitLengths);



//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
//
//    // Calculate the sum of all numbers using reduce
//    int sum = numbers.stream()
//            .reduce(0, Integer::sum);
//
//        System.out.println("Sum of numbers: " + sum);



//    List<String> words = Arrays.asList("Java", "is", "a", "programming", "language");
//
//    // Chain multiple operations: filter, map, and collect
//    List<String> result = words.stream()
//            .filter(word -> word.length() > 2)
//            .map(String::toUpperCase)
//            .collect(Collectors.toList());
//
//                System.out.println("Result: " + result);

//        Predicate<String> condition = x->x.equals("mike");
//        boolean val = condition.test("mike");
//        System.out.println(val);

