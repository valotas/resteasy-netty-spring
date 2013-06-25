package com.valotas.resteasy;

import java.util.Collection;

import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@ComponentScan(basePackages = "com.valotas.resteasy.rs")
public class NettyServer implements Lifecycle {
	private static final Logger LOG = LoggerFactory.getLogger(NettyServer.class);
	
	private final ApplicationContext ctx;
	
	private NettyJaxrsServer netty = null;
	
	@Autowired
	public NettyServer(ApplicationContext ctx) {
		this.ctx = ctx;
	}

	@Override
	public void start() {
		ResteasyDeployment dp = new ResteasyDeployment();
		
		Collection<Object> providers = ctx.getBeansWithAnnotation(Provider.class).values();
		if (providers != null && providers.size() > 0) {
			LOG.info("Adding " + providers.size() + " providers: " + providers);
			dp.getProviders().addAll(providers);
		}
		
		Collection<Object> controllers = ctx.getBeansWithAnnotation(Controller.class).values();
		LOG.info("Adding " + controllers.size() + " controllers to the resources: " + controllers);
		dp.getResources().addAll(controllers);		
		
		netty = new NettyJaxrsServer();
		netty.setDeployment(dp);
		netty.setPort(8080);
		netty.start();
	}

	@Override
	public void stop() {
		netty.stop();
	}

	@Override
	public boolean isRunning() {
		return netty != null;
	}

	
}
