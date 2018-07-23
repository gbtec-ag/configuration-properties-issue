package com.example.demo.feign;

import com.example.demo.DemoApplication;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.GenericContainer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@ContextConfiguration(initializers = TestcontainersBackedIT.Initializer.class)
public class TestcontainersBackedIT {

    @ClassRule
    public static GenericContainer keycloak = new GenericContainer("jboss/keycloak:4.0.0.Final").withExposedPorts(8080)
            .withEnv("KEYCLOAK_USER", "admin").withEnv("KEYCLOAK_PASSWORD", "secret");


    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            String keycloakHost = "keycloak.host=" + keycloak.getContainerIpAddress();
            String keycloakPort = "keycloak.port=" + keycloak.getMappedPort(8080);
            String accessTokenUri = "security.oauth2.client.accessTokenUri=http://localhost:" + keycloak.getMappedPort(8080)
                    + "/auth/realms/master/protocol/openid-connect/token";
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(configurableApplicationContext, keycloakHost, keycloakPort,
                    accessTokenUri);
        }
    }
}
