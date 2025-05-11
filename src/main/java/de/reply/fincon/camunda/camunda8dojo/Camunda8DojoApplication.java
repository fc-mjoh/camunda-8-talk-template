package de.reply.fincon.camunda.camunda8dojo;

import io.camunda.zeebe.client.ZeebeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Camunda8DojoApplication {

    private static final Logger LOG = LoggerFactory.getLogger(Camunda8DojoApplication.class);

    private final ZeebeClient zeebeClient;

    public Camunda8DojoApplication(ZeebeClient zeebeClient) {
        this.zeebeClient = zeebeClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(Camunda8DojoApplication.class, args);
    }

    public void run(final String... args) {
        var bpmnProcessId = "order-process-4"; // or whatever the key is
        var event = zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId(bpmnProcessId)
                .latestVersion()
                .send()
                .join();
        LOG.info(String.format("started a process: %d", event.getProcessInstanceKey()));
    }

}
