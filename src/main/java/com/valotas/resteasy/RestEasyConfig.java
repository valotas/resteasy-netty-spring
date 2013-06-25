package com.valotas.resteasy;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(NettyServer.class)
public class RestEasyConfig {

}
