package com.example.borrowbook.service;

import java.util.concurrent.atomic.AtomicLong;

public class CounterService {
    private final AtomicLong visits = new AtomicLong(0);
    public long incrementAndGet() { return visits.incrementAndGet(); }
    public long get() { return visits.get(); }
}
