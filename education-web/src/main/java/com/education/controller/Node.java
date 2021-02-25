package com.education.controller;

/**
 * @Author admin
 * @Date 2021-02-24 11:28
 * @Version 1.0
 * @Description
 */
public class Node<T> {
    Node next = null;
    T data;

    public Node(T data) {
        this.data = data;
    }
}
