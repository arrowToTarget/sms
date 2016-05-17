package com.lewis.cms.register;

import com.lewis.cms.common.base.RequestParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.List;

/**
 * Created by zhangminghua on 2016/5/17.
 */
@Component
public class ZkRegister {

    private static final String connectString ="192.168.183.129:2181,192.168.183.129:2182,192.168.183.129:2183";

    private CuratorFramework client;

    public ZkRegister() {
        client = CuratorFrameworkFactory.builder().retryPolicy(new ExponentialBackoffRetry(10000,3)).connectString(connectString)
                 .namespace("sms").sessionTimeoutMs(10000).build();
        client.start();
    }

    public void registerService(List<RequestParam> requestParamList) throws Exception {
        InetAddress localHost = InetAddress.getLocalHost();
        if (CollectionUtils.isNotEmpty(requestParamList)) {
            for (RequestParam requestParam : requestParamList) {
                String requestUrl = requestParam.getRequestUrl();
                if (StringUtils.isNotEmpty(requestUrl)) {
                    requestUrl = requestUrl.replace("/",".");
                }
                if (requestUrl.startsWith(".")) {
                    requestUrl = requestUrl.substring(1);
                }
                String parentNodePath = "/"+requestParam.getSysCode()+"/"+requestUrl;
                if (client.checkExists().forPath(parentNodePath) == null) {
                    client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                            .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(parentNodePath,(requestParam.getSysCode()+"提供的服务列表").getBytes());
                }
                String leafNode = parentNodePath+"/"+localHost.getHostAddress()+":8081";
                client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(leafNode,"1".getBytes());
            }
        }
    }




}
