package com.example.member.lock;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**模板方法实现公共部分的编写*/
public abstract class ZookeeperAbstractLock implements  ExLock{

    private static final String CONNECTION="127.0.0.1:2181";
    protected ZkClient zkClient = new ZkClient(CONNECTION);
    public String lockPath="/lockPath";
    protected CountDownLatch countDownLatch = new CountDownLatch(1);
    //获取锁
    public void getLock() {
        //1、连接zkClient 创建一个/lock的临时节点
        // 2、 如果节点创建成果，直接执行业务逻辑，如果节点创建失败，进行等待
        if (tryLock()) {
            System.out.println("#####成功获取锁######");
        }else {
            //进行等待
            waitLock();
        }
        //3、使用事件通知监听该节点是否被删除    ，如果是，重新进入获取锁的资源
    }
    //创建失败 进行等待
    abstract void waitLock();
    abstract boolean tryLock();
    //释放锁
    public void unLock() {
        //执行完毕 直接连接
        if (zkClient != null) {
            zkClient.close();
            System.out.println("######释放锁完毕######");
        }

    }
}
