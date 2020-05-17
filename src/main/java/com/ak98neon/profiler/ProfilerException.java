package com.ak98neon.profiler;

public class ProfilerException extends RuntimeException {
    private final String msg;

    public ProfilerException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
