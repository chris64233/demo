package com.example.demo.fdfs.config;

import com.github.tobato.fastdfs.domain.fdfs.StorageNode;
import com.github.tobato.fastdfs.domain.fdfs.StorageNodeInfo;
import com.github.tobato.fastdfs.service.DefaultTrackerClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author zengyizeng
 */
@Primary
@Component("UserDefaultTrackerClient")
public class UserDefaultTrackerClient extends DefaultTrackerClient {

    @Override
    public StorageNode getStoreStorage() {
        StorageNode storage = super.getStoreStorage();
        storage.setPort(23000);
        return storage;
    }

    @Override
    public StorageNodeInfo getUpdateStorage(String groupName, String filename) {
        StorageNodeInfo storage = super.getUpdateStorage(groupName, filename);
        storage.setPort(23000);
        return storage;
    }

    @Override
    public StorageNodeInfo getFetchStorage(String groupName, String filename) {
        StorageNodeInfo storage = super.getFetchStorage(groupName, filename);
        storage.setPort(23000);
        return storage;
    }


}