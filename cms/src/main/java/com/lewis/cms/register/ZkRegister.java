package com.lewis.cms.register;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by zhangminghua on 2016/5/17.
 */
public class ZkRegister {

    private static final String connectString ="192.168.183.129:2181,192.168.183.129:2182,192.168.183.129:2183";

    private CuratorFramework client;

    public ZkRegister() {
        client = CuratorFrameworkFactory.builder().retryPolicy(new ExponentialBackoffRetry(10000,3)).connectString(connectString)
                 .namespace("cms").sessionTimeoutMs(10000).build();
        client.start();
    }
}
