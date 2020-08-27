package com.logistic.custom;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator  extends AbstractHealthIndicator {
	
	//Personalizando o actuator, adicionado em components/custom/details
	@Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up().withDetail("app", "Tudo OK");
    }
}
