package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class HelloController {
    @GetMapping("hello") //1. 웹 브라우저에서 hello 요청을 받으면 내장톰캣이 받아서 컨테이너로 전달해주고
    //2. hello 에 해당하는 아래 메서드를 실행함(입력을 통해 "hello"를 전달받으므로 "GET"방식)
    public String hello(Model model){ //3. model 을 사용해서 key 와 data 를 전달함(model 에 담아서 전달함)
        model.addAttribute("data", "hello!!"); //4. key 는 "data", 전달할 데이터값은 "hello!!"
        return "hello"; //5. 컨트롤러가 문자값을 리턴하면 그 문자를(hello) 뷰리졸버에 요청
        //6. 뷰 리졸버는 요청된 hello 를 자동으로 "templates/ + {viewName} + .html" 형태로 매핑
        //7. 결과적으로 templates 폴더의 hello.html 을 실행시켜 웹브라우저에 보여줌!
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){ //RequestParam : 외부에서 name 변수를 받아서 입력하기위해 사용
        model.addAttribute("name", name); //"name"은 key 명이고, name 이 변수
        return "hello-template"; //hello-template 를 반환해서 뷰 리졸버를 통해 hello-template.html 을 웹 브라우저에 반환함
    }
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name ) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name; //private 로 선언 = 외부에서 바로 사용할 수 없음.

        // getter, setter 등의 메소드를 이용해 name 값을 외부에서 사용하게끔 하는 것!
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }


}
