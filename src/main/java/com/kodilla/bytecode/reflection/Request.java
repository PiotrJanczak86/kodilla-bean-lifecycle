package com.kodilla.bytecode.reflection;

public class Request {
    private long n = 20;
    private int z = 10;

    public long getN() {
        return n;
    }

    public int getZ() {
        return z;
    }

    public Request(long n) {
        this.n = n;
    }

    public Request(int z) {
        this.z = z;
    }

    public Request() {
    }

    public Request(int n, int z) {
        this.n = n;
        this.z = z;
    }
}