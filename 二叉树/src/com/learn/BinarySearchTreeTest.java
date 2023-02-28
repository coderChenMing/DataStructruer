package com.learn;

import com.learn.BinarySearchTree.Visitor;
import com.learn.printer.BinaryTrees;

public class BinarySearchTreeTest {

    private static Integer data[] = new Integer[]{
            7, 4, 9, 2, 5, 8, 11, 10, 3, 12, 1
    };

    private static Person[] people = {
            new Person(7, "吕布"),
            new Person(4, "赵云"),
            new Person(9, "典韦"),
            new Person(2, "关羽"),
            new Person(5, "马超"),
            new Person(8, "张飞"),
            new Person(11, "黄忠"),
            new Person(3, "许褚"),
            new Person(12, "张郃"),
            new Person(1, "张辽"),
            new Person(12, "徐晃")
    };

    public static void main(String[] args) {
        test1();
        // test2();
        // test3();
        // test4();
        // test5();
        // test6();
        //test7();
    }

    public static void test1() {
        // 添加功能测试
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        // bst.add(1); 查看只有一个根节点的二叉树
        // System.out.println(bst.size());
        BinaryTrees.println(bst);
        System.out.println("递归计算树高: " + bst.height());
        System.out.println("迭代计算树高: " + bst.height2());
    }

    public static void test2() {
        // 比较器测试:通过控制二叉搜索树元素的比较顺序，打印想要的内容
        BinarySearchTree<Person> bst = new BinarySearchTree<>((o1, o2) -> o1.getAge() - o2.getAge());
        BinarySearchTree<Person> bst2 = new BinarySearchTree<>((o1, o2) -> o2.getAge() - o1.getAge());
        for (int i = 0; i < people.length; i++) {
            bst.add(people[i]);
            bst2.add(people[i]);
        }
        BinaryTrees.println(bst);
        System.out.println("=================================================================================================================");
        BinaryTrees.println(bst2);
    }

    public static void test3() {
        // 测试节点元素相等
        //  new Person(12, "张郃"),
        //  new Person(12, "徐晃")
        BinarySearchTree<Person> bst = new BinarySearchTree<>((o1, o2) -> o1.getAge() - o2.getAge());
        for (int i = 0; i < people.length; i++) {
            bst.add(people[i]);
        }
        BinaryTrees.println(bst);
    }

    public static void test4() {
        // 测试前中后序遍历,层序遍历
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        bst.preorderTraversal();
        //bst.inorderTraversal();
        //bst.postorderTraversal();
        //bst.levelOrderTraversal();
    }

    public static void test5() {
        // 测试toString方法
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        System.out.println(bst);
    }

    public static void test6() {
        // 测试visitor接口
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        bst.preorderTraversalExt(new Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
            }
        });
        System.out.println();
        bst.inorderTraversalExt(new Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
            }
        });
        System.out.println();
        bst.postorderTraversalExt(new Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
            }
        });
        System.out.println();
        bst.levelOrderTraversalExt(new Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
            }
        });
        System.out.println();
        bst.levelOrderTraversalExt(null);
    }

    public static void test7() {
        // 测试循环等于具体值中断后续打印
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        bst.preorderTraversalExt(new Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.print(element + " ");
                return 2 == element;
            }
        });
        System.out.println();
        bst.inorderTraversalExt(new Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.print(element + " ");
                return 4 == element;
            }
        });
        System.out.println();
        bst.postorderTraversalExt(new Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.print(element + " ");
                return 8 == element;
            }
        });
        System.out.println();
        bst.levelOrderTraversalExt(new Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.print(element + " ");
                return 10 == element;
            }
        });
    }
}
