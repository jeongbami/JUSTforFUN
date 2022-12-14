package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 1. 원격 호출 가능한 프로그램으로 등록
@Controller
public class Hello {
	
	int iv = 10;			// 인스턴스 변수		 
	static int cv = 20;		// static 변수
	
	
	// 2. URL과 hello가 연결되었음 
	@RequestMapping("/hello")
	public void main() { // 인스턴스 메소드 - iv, cv 둘 다 사용 가능 
		System.out.println("HELLO");
		System.out.println(cv);
		System.out.println(iv);
	}
	
	/*
	 *  < 살펴볼점 > 
	 *	public void main 으로 method를 만들었는데 instance method가 실행 되었다.
	 * 		( static method는 프로그램의 실행과 동시에 생성되는 method)
	 * 	instance method는 객체가 생성 후 실행되는 method인데 실행이 되었다는 것은
	 *	어디선가 main()이라는 객체가 생성되었다고 볼 수 있다.
	 *	이는 url로 원격프로그램을 호출하면 tomcat 내부에서 객체를 생성해준다
	 *
	 *
	 *
	 *	< 그럼 왜 인스턴스 메소드어야 하나? >
	 *	static method는 static 변수만 받을 수 있으므로 유용한 instance method를 사용한다 
	 *
	 *
	 * */
	
	public static void main2() { // static 메소드 - cv만 사용 가능 
		System.out.println(cv);	//OK
	//	System.out.println(iv); //ERROR
		
	}
	
	// private으로 해도 호출이 될까 ? -> 된다.
	@RequestMapping
	private void main3() {
		System.out.println("private method in Hello Class");
	// 이유는 @RequestMapping이라는 것은 외부와의 통신을 허락한 것이므로 private라도 호출을 하는 것.
	// 외부에서는 호출 가능 ,내부에서는 Private로 작동하게됨.
	// 이를 내부에서 호출하는 방법이 있는데 그것이 Reflection API이고 spring framework에서 자주 사용한다
		
	}
}
 