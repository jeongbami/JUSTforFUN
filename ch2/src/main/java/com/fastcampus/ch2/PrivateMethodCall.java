package com.fastcampus.ch2;

import java.lang.reflect.Method;

public class PrivateMethodCall {

	public static void main(String[] args) throws Exception{
		// Hello hello = new Hello();
		// hello.main3();  private Method라 호출이 불가능 
		
		
		/* 
		 * 	그렇다면 Requestmapping으로 호출이 가능한 이유는 ?
		 *  Reflection API 를 사용하기 때문이다.
		 * 	이는 클래스 정보를 얻고 다룰 수 있는 강력한 기능을 제공하는 API이다.
		 *  > java.lang.reflect 패키지를 제공한다.
		 * 
		 * 아래는 reflection API 이용하여 객체를 생성 main method 호출 
		 * 
		 */
		
		
		// Hello의 클래스의 Class객체 (클래스의 정보를 담고 있는 객체)를 얻어오겠다는 뜻이다.
		// 클래스 파일(*.class)이 메모리에 올라갈 떄 클래스 파일마다 class 객체가 하나씩 생성된다.
		// 이 Class 객체는 클래스에 대한 모든 정보를 담고 있으며 클래스당 1개만 존재한다 
		// == class : class 객체 = 1 : 1
		
		Class helloClass = Class.forName("com.fastcampus.ch2.Hello");
										// 정보를 받아 올 class 이름을 package이름과 함께 쓴다 
		//helloClass에는 " "의 클래스 정보가 다 담기게 되며 
		//클래스는 java에서 '설계도'의 역할을 하기에 클래스로 무엇이든 다 할 수 있고 그 중 하나가 객체 생성.
		
		Hello hello = (Hello)helloClass.newInstance();
		// 이 hello는 helloClass를 기반으로 만들어진 객체임.
		
		Method main = helloClass.getDeclaredMethod("main3");
		//helloClass가 가지고 있는 메소드를 호출하기 위한 메소드 작업 처리.
		main.setAccessible(true);
		//private인 main()을 호출 가능하게 한다.
	
		main.invoke(hello);
		//== hello.main()
	}
	
}
