package com.kiela;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class TestProjectApplication {
	/*
	Application 파일의 main 함수위에 아래의 필터를 추가한다.
	추가하는 이유는 백엔드 서버는 8080포트로 운영할것이고 프런트는 80번으로 운영하게되면 브라우저에서는
	보안에 문제가 있는 서로 다른 도메인으로 인식하여 CORS 이슈가 생기게 된다.
	이 이슈를 해결하기 위해서 서버쪽에서 설정한다.
	 */
	@Bean
	public FilterRegistrationBean crosFilter(){
		UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**",config);

		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new CorsFilter(source));
		registrationBean.setOrder(0);

		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(TestProjectApplication.class, args);
	}
}
