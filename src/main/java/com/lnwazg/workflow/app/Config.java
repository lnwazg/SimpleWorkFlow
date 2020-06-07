package com.lnwazg.workflow.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:workflow/placeOrderFlow.xml")
public class Config {
}
