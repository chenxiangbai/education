package com.education.controller;

/**
 * @Author admin
 * @Date 2021-02-24 13:35
 * @Version 1.0
 * @Description
 */
public class LinkList<T> {
    Node head = null;

    public void add(T data) {
        if (head == null) {
            head = new Node(data);
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(data);
    }

    public boolean deleteNode(int index) {
        if (index < 1 || index > length()) {
            return false;
        }
        if (index == 1) {
            head = head.next;
            return true;
        }
        int i = 2;
        Node headNode = head;
        Node nextNode = headNode.next;
        while (nextNode != null) {
            if (i == index) {
                headNode.next = nextNode.next;
                return true;
            }
            headNode = nextNode;
            nextNode = nextNode.next;
            i++;
        }
        return false;
    }

    public int length() {
        int length = 0;
        Node tmp = head;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }
        return length;
    }

}
