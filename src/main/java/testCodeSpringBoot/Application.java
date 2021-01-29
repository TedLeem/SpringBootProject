package testCodeSpringBoot;
import org.springframework.boot.SpringApplication;
import  org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
//이걸로 인해 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 생성해줌
//항상 @SpringBootApplication이 있는 위치부터 읽어나가므로 이 클래스는 항상 프로그램의 최상단에 위치해야함
public class Application {
//    Application은 Main클래스
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//      내장 WAS를 실행하는 문장 , WAS = Web Application Server
//      톰캣 설치 필요없이 jar로만 해결가능 ,  Jar = 실행가능한 자바 패키지 파일


    }
}
