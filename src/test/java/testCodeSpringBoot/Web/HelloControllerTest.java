package testCodeSpringBoot.Web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//테스트를 진행할 때 Junit에 내장된 실행자 외에 다른 실행자를 실행시킵니다.
// 여기서는 SpringRUnner라는 스프링 실행자를 사용합니다.
//즉, 스프링 부트테스트와 Junit 사이에 연결자 역할을 합니다.
@WebMvcTest(controllers = HelloController.class)
//여러 스프링 테스트 어노테이셪 중 Web에 집중할 수 있는 어노테이션입니다.
//선언할 경우, @Controller, @controllerAdivce등을 사용할 수 있습니다.
//여기서는 컨트롤러만 사용
public class HelloControllerTest {
    @Autowired
//    스프링이 관리하는 빈(Bean)을 주입합니다.
    private MockMvc mvc;
//웹 API를 테스트할 때 사용합니다.
//스프링 MVC 테스트의 시작점입니다.
//  이 클래스를 통해 HTTP,GET,POST등에 대한 API 테스트를 할 수 있습니다.
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello"))
//                MockMvc를 통해 /hello 주소로 HTTP GET 요청을 합니다.
//                체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 할 수 있습니다.
                .andExpect(status().isOk())
    //          mvc.perform의 결과를 검증합니다. HTTP Header의 status를 검증합니다ㅏ.
//                여기선 OK,즉 200인지 아닌지 를검증합니다.(404,500 등의 상태 검증)
                .andExpect(content().string(hello));
//              mvc.perform의 결과를 검증합니다.
//              음답 본문의 내용을 검증하빈다.
//              Controller에서 "hello"를 리턴하기 떄문에 이 값이 맞는지 검증합니다.

    }
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

// param
//        API 테스트할 때 사용될 요청 파라미터를 설정합니다. 단 값은 String만 허용
//        그래서 숫자/날짜 등의 데이터도 등록할 떄는 문자열로 변경해야만 가능합니다.

// jsonPath
//        JSON 응답값을 필드별로 검증할 수 있는 메소드입니다.
//        $를 기준으로 필드명을 제시합니다.
//        여기서는 name과 amount를 검증하니 $.name $.amount로 검증합니다.

        mvc.perform(get("/hello/dto").param("name",name).param("amount",String.valueOf(amount)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name", is(name))).andExpect(jsonPath("$.amount",is(amount)));

    }
}
