package com.amsidh.mvc.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class SpringBeansConfig {

	@Bean(name = "messageSource")
	public MessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
		reloadableResourceBundleMessageSource.addBasenames("classpath:messages/messages");
		reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
		return reloadableResourceBundleMessageSource;
	}

}
