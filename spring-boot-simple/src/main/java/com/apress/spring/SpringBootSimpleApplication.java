package com.apress.spring;

import java.io.PrintStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringBootSimpleApplication implements CommandLineRunner, ApplicationRunner {

//   public static void main(String[] args) {
////		SpringApplication app = new SpringApplication(SpringBootSimpleApplication.class);
////		app.setBanner(new Banner() {
////			@Override
////			public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
////				out.print("\n\n\tThis is my own banner!\n\n".toUpperCase());
////			}
////		});
////		app.setBannerMode(Banner.Mode.OFF);
////		app.run(args);
//		SpringApplication.run(SpringBootSimpleApplication.class, args);
////      new SpringApplicationBuilder()
////              .bannerMode(Banner.Mode.OFF)
////              .sources(SpringBootSimpleApplication.class)
//////              .child(OtherConfig.class)
////              .logStartupInfo(false)
////              .profiles("prod", "cloud")
////              .run(args);
//
////      Logger log = LoggerFactory.getLogger(SpringBootSimpleApplication.class);
////      new SpringApplicationBuilder(SpringBootSimpleApplication.class)
////              .listeners(new ApplicationListener<ApplicationEvent>() {
////                 @Override
////                 public void onApplicationEvent(ApplicationEvent event) {
////                    log.info("#### > " + event.getClass().getCanonicalName());
////                 }
////              })
////              .web(WebApplicationType.NONE)
////              .run(args);
//   }

   private static final Logger log = LoggerFactory.getLogger(SpringBootSimpleApplication.class);

   public static void main(String[] args) throws IOException {
      SpringApplication.run(SpringBootSimpleApplication.class, args);
   }

   @Bean
   String info() {
      return "Just a simple String bean";
   }

   @Autowired
   String info;

   @Override
   public void run(ApplicationArguments args) throws Exception {
      log.info("## > ApplicationRunner Implementation...");
      log.info("Accessing the Info bean: " + info);
      args.getNonOptionArgs().forEach(file -> log.info(file));
   }

   @Override
   public void run(String... args) throws Exception {
      log.info("## > CommandLineRunner Implementation...");
      log.info("Accessing the Info bean: " + info);
      for (String arg : args)
         log.info(arg);
   }

   @Bean
   CommandLineRunner myMethod(){
      return args -> {
         log.info("## > CommandLineRunner Implementation...");
         log.info("Accessing the Info bean: " + info);
         for(String arg:args)
            log.info(arg);
      };
   }
}

@Component
class MyComponent {
   private static final Logger log = LoggerFactory.getLogger(MyComponent.class);

   @Autowired
   public MyComponent(ApplicationArguments args) {
      boolean enable = args.containsOption("enable");
      if (enable)
         log.info("## > You are enabled!");
      List<String> _args = args.getNonOptionArgs();
      log.info("## > extra args ...");
      if (!_args.isEmpty())
         _args.forEach(file -> log.info(file));
   }
}
