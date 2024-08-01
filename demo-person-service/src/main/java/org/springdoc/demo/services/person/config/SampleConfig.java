package org.springdoc.demo.services.person.config;

import javax.money.MonetaryAmount;

import jakarta.annotation.PostConstruct;
import org.javamoney.moneta.Money;
import org.zalando.jackson.datatype.money.MoneyModule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springdoc.core.utils.SpringDocUtils.getConfig;

@Configuration
public class SampleConfig {

	@PostConstruct
	public void initConfig() {
		getConfig().replaceWithClass(MonetaryAmount.class,
				org.springdoc.core.converters.models.MonetaryAmount.class);
	}

	@Bean
	public MoneyModule moneyModule() {
		return new MoneyModule().withMonetaryAmount(Money::of);
	}
}
