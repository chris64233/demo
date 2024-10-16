package com.example.demo.init;

import com.example.demo.config.AdapayConfig;
import com.huifu.adapay.Adapay;
import com.huifu.adapay.model.MerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AdapayConfigInit {

    @Autowired
    private AdapayConfig adapayConfig;

    public void init() throws Exception {
        Adapay.debug = true;
        Adapay.prodMode = true;

        Map<String, MerConfig> configPathMap = new HashMap<>();
        MerConfig merConfigLh = new MerConfig();
        merConfigLh.setApiKey(adapayConfig.getApiKeyLh());
        merConfigLh.setApiMockKey(adapayConfig.getMockApiKeyLh());
        merConfigLh.setRSAPrivateKey(adapayConfig.getRsaPrivateKeyLh());
        configPathMap.put(adapayConfig.getAppIdLh(), merConfigLh);
        Adapay.initWithMerConfigs(configPathMap);
    }
}
