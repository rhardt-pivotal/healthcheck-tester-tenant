package io.pivotal.fe.rhardt.healthchecktesttenant;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AppStatusRepository extends CrudRepository<AppStatus, Long> {

    List<AppStatus> findByAppName(@Param("appname") String appname);
    List<AppStatus> findByAppGuid(@Param("appguid") String appguid);


}
