package io.pivotal.fe.rhardt.healthchecktesttenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.cloud.CloudFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HealthCheck implements HealthIndicator {

    @Autowired
    private AppStatusRepository appStatusRepository;

    @Override
    public Health health() {
        boolean stat = check(); // perform some specific health check
        if (!stat) {
            return Health.down()
                    .withDetail("Custom health", Boolean.FALSE).build();
        }
        return Health.up().build();
    }

    public boolean check() {
        String myGuid = (String)new CloudFactory().getCloud().getApplicationInstanceInfo().getProperties().get("application_id");
        List<AppStatus> ases = appStatusRepository.findByAppGuid(myGuid);
        if (ases != null && ((List) ases).size() > 0){
            return ases.get(0).isUp();
        }
        return true;
    }

}
