/*
 *
 *  * Copyright 2019-2020 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.springdoc.demo.app2;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public GroupedOpenApi usersGroup(@Value("${springdoc.version}") String appVersion) {
		return GroupedOpenApi.builder().group("users")
				.addOperationCustomizer((operation, handlerMethod) -> {
					operation.addSecurityItem(new SecurityRequirement().addList("basicScheme"));
					return operation;
				})
				.addOpenApiCustomizer(openApi -> openApi.info(new Info().title("Users API").version(appVersion)))
				.packagesToScan("org.springdoc.demo.app2")
				.pathsToMatch("/user/**")
				.build();
	}
}
