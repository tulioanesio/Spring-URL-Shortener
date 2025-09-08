package com.tulioanesio.Spring_URL_Shortener;

import com.tulioanesio.Spring_URL_Shortener.controller.ShortenerController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ ShortenerController.class })
public class SpringUrlShortenerApplication {

    @Value("${logging.level.root:OFF}")
    String message = "";

	public static void main(String[] args) {
        SpringApplication.run(SpringUrlShortenerApplication.class, args);
	}

}
