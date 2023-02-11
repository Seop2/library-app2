package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMulRequest;
import org.springframework.web.bind.annotation.*;

/**
 * Api 를 개발하기 전에 해야 할 것!
 * API 명세를 정해야함 - Api spec
 * 덧셈 api
 * - HTTP Method: GET
 * - HTTP Path:  /add
 * - 쿼리 (key & value): int number1 / int number2
 * - API 의 반환 결과 : 숫자 - 두 숫자의 덧셈 결과
 */
@RestController // rest api 사용할거야!
/**
 * @RestController : 주어진 클래스를 Controller 로 등록한다!
 * Controller : Api 의 입구
 * 한 컨트롤러 클래스에 여러 api를 만들수있다.
 *
 */
public class CalculatorController {
    //덧셈 api
//    @GetMapping("/add")
    public int addToNumV1(@RequestParam int number1, @RequestParam int number2){
        //@RequestParam : 주어지는 쿼리를 함수 파라미터(매개변수)에 넣는다.
        return number1 + number2;
        //http://localhost:8080/add?number1=13&number2=14
        //27이 출력됨
    }


    @GetMapping("/add")
    public int addToNumV2(CalculatorAddRequest request){//객체를 불러와서 처리(정보전달)
        /**
         * Data Transfer Object , DTO (정보전달객체) ex) CalculatorAddRequest
         */
        int result = request.getNumber1() + request.getNumber2();
        return result;
    }
    /**
     * POST 에서는 데이터를 어떻게 받을까?
     * -> HTTP body를 이용한다!
     * -> 어떤 형식으로 받을까? -? JSON 형식으로 받는다.
     * JSON 안에 JSON도 가능!
     */
    //곱셈 API(post방식 연습)
    @PostMapping("/multiply")
    public int mulToNumV1(@RequestBody CalculatorMulRequest request){//HTTP body인의 json을 객체로 변환
        /**
         * @RequestBody HTTP Body로 들어오는 JSON을 CalculatorMulRequest로 바꾼다!!!
         * - HTTP body 는 CalculatorMulRequest로 매핑된다.
         * - HTTP 메시지 요청과 응답을 주고 받는다.
         */
        int result = request.getNumber1() * request.getNumber2();
        return result;
    }
}
