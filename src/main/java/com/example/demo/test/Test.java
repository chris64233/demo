package com.example.demo.test;

import com.example.demo.domain.Student;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Test {
    @RequestMapping("/test")
    public String hello() {
        return "hello world";
    }


    public static void main(String[] args) {
        ListNode n1 = new ListNode();
        n1.val = 1;
        ListNode n2 = new ListNode();
        n2.val = 2;
        ListNode n3 = new ListNode();
        n3.val = 3;
        ListNode n4 = new ListNode();
        n4.val = 4;

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        System.out.print("old: " + n1);

        Solution solution = new Solution();
        ListNode reversed = solution.reverse(n1);

        System.out.print("new: " + reversed);

    }

    @RequestMapping("/dic")
    public Student dic() {
        Student student = new Student();
        student.setSex(0);
        return student;
    }
}
