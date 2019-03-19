package io.pivotal.fe.rhardt.healthchecktesttenant;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.CloudFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("cloud")
@Log
public class TenantDataPopulator implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private AppStatusRepository appStatusRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.warning("*********** POPULATE");
        String myGuid = new CloudFactory().getCloud().getApplicationInstanceInfo().getProperties().get("application_id").toString();
        List<AppStatus> as = null;
        try{
            as = appStatusRepository.findByAppGuid(myGuid);
        }
        catch (Exception ex){}
        if (as == null || as.size() == 0) {
            log.warning("********* WRITING");
            String myName = new CloudFactory().getCloud().getApplicationInstanceInfo().getProperties().get("application_name").toString();
            appStatusRepository.save(new AppStatus(-1, myGuid, myName, true));
        }

    }
}
