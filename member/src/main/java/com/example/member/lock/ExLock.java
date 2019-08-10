package com.example.member.lock;

public interface ExLock {
    //这个类基于Zookeeper实现分布式锁
    void getLock();
    /**释放锁*/
    void unLock();
}
