package com.learn;

import com.learn.file.Files;
import com.learn.printer.BinaryTrees;

public class BinarySearchTreeTest {

    private static Integer data[] = new Integer[]{
            7, 4, 9, 2, 5, 8, 11, 3, 12, 1
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
        // test1();
        // test2();
        // test3();
        // test4();
        // test5();
        // test6();
        // test7();
        // test8();
        // test9();
        // test10();
        // test11();
        // test12();
        test13();
    }

    public static void test1() {
        // 添加功能测试
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        //System.out.println(bst.size());
        //BinaryTrees.println(bst);
        Files.writeToFile("E:\\remove.txt",BinaryTrees.printString(bst));
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

    public static void test8() {
        // 计算树高
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i <30 ; i++) {
            bst.add((int)(Math.random()*100));
        }
        BinaryTrees.print(bst);
        System.out.println();
        System.out.println("递归计算树高: " + bst.height());
        System.out.println("层序遍历计算树高: " + bst.height2());
    }

    public static void test9() {
        // 测试一颗 二叉树 是否是 完全二叉树
        // 测试的关键就是多试些比较特殊的数据
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(7);
        bst.add(4);
        bst.add(2);
        bst.add(1);
        BinaryTrees.println(bst);
        System.out.println("isComplete: "+bst.isComplete());

        BinarySearchTree<Integer> bst2 = new BinarySearchTree<>();
        bst2.add(7);
        bst2.add(9);
        bst2.add(8);
        bst2.add(10);
        bst2.add(4);
        //bst2.add(6); //是否注释结果不同
        bst2.add(2);
        bst2.add(1);
        BinaryTrees.println(bst2);
        System.out.println("isComplete: "+bst2.isComplete());
    }

    public static void test10() {
        // 测试获取前驱结点
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(8);
        bst.add(4);
        bst.add(13);
        bst.add(2);
        bst.add(6);
        bst.add(10);
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(7);
        bst.add(9);
        bst.add(12);
        bst.add(11);

        String printString = BinaryTrees.printString(bst);
        Files.writeToFile("e:\\qianqu.text",printString);
        System.out.println("1的前驱节点: "+bst.precursorNode(bst.node(1)));
        System.out.println("3的前驱节点: "+bst.precursorNode(bst.node(3)).element);
        System.out.println("9的前驱节点: "+bst.precursorNode(bst.node(9)).element);
    }

    public static void test11() {
        // 测试获取后继结点
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(4);
        bst.add(1);
        bst.add(8);
        bst.add(2);
        bst.add(7);
        bst.add(10);
        bst.add(3);
        bst.add(5);
        bst.add(9);
        bst.add(11);
        String printString = BinaryTrees.printString(bst);
        System.out.println(printString);
        //Files.writeToFile("e:\\houji.text",printString);

        System.out.println("11的后继节点: "+bst.successorNode(bst.node(11)));
        System.out.println("3的后继节点: "+bst.successorNode(bst.node(3)).element);
        System.out.println("8的后继节点: "+bst.successorNode(bst.node(8)).element);
        System.out.println("9的后继节点: "+bst.successorNode(bst.node(9)).element);
    }

    public static void test12() {
        // 测试删除节点
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        System.out.println(BinaryTrees.printString(bst));
        bst.remove(12);
        System.out.println(BinaryTrees.printString(bst));
        bst.remove(7);
        System.out.println(BinaryTrees.printString(bst));
        bst.remove(4);
        System.out.println(BinaryTrees.printString(bst));

    }
    public static void test13() {
        // 测试删除节点2
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(1);
        //bst.add(2);
        //bst.add(3);
        System.out.println(BinaryTrees.printString(bst));
        bst.remove(1);
        System.out.println(BinaryTrees.printString(bst));

    }
}
