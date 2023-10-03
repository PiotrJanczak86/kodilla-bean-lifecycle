package com.kodilla.bytecode.reflection;

public class Request {
    @Range
    private int n = 20;
    @Range
    private int z = 10;

    public long getN() {
        return n;
    }

    public int getZ() {
        return z;
    }

    public Request() {
    }

    public Request(int n, int z) {
        this.n = n;
        this.z = z;
    }
}