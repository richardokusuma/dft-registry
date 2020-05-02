package id.co.cimb.eureka.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import id.co.cimb.eureka.responses.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/instances")
public class InstancesController {
    @Autowired
    DiscoveryClient discoveryClient;

    private final Logger log = LoggerFactory.getLogger(InstancesController.class);

    @RequestMapping("/")
    public ResponseEntity index() {
        List<HashMap> serviceHashes = new ArrayList<>();
        PeerAwareInstanceRegistry registry = EurekaServerContextHolder.getInstance().getServerContext().getRegistry();
        Applications applications = registry.getApplications();

        applications.getRegisteredApplications().forEach(app->{
            app.getInstances().forEach(inst->{
                HashMap singleHash = new HashMap();
                singleHash.put("inst_name", inst.getAppName().toLowerCase());
                singleHash.put("inst_port", inst.getPort());
                serviceHashes.add(singleHash);
            });
        });
        return ResponseEntity.ok(new CommonResponse("Instances Fetched", serviceHashes));
    }

    public static void main (String... args){
        ArrayList<String> strLst  = new ArrayList<>();
        strLst.add("a");
        strLst.add("j");
        ArrayList<HashMap> strHashes = new ArrayList<>();
        int numStr = 0;
        String str1 = strLst.get(0);
        HashMap singleHash = new HashMap();
        for(int i = 0 ; i < strLst.size(); i++){
            String str = strLst.get(i);
            if(str.equals(str1)){
                singleHash.put("app_str", str);
                singleHash.put("num_str", ++numStr);
                if(i == strLst.size()-1) {
                    singleHash.put("num_str", ++numStr);
                    strHashes.add(singleHash);
                }
            }else{
                strHashes.add(singleHash);
                numStr=0;
                singleHash = new HashMap();
                singleHash.put("app_str", str);
                singleHash.put("num_str", ++numStr);
                if(i == strLst.size()-1){
                    strHashes.add(singleHash);
                }
                str1 = str;
            }
        }

        System.out.println(strHashes);
    }
}
