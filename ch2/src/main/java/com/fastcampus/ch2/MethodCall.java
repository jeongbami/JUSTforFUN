package com.fastcampus.ch2;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

class ModelController {
	//작업 결과를 map에 저장
	public String main(HashMap map) {
		map.put("id", "asdf");
		map.put("pwd", "1111");
		
		return "txtView2";//map  자체를 return 할 필요가 없음 이유는 main method에서 객체를 생성하기 때문 
	
	// ModelContrller는 MethodCall main method에서 호출 되어 작업을 수행 한 후 model 객체에 담아 준 후
	// RAM에서 사라진다.
	}
}

public class MethodCall {
	public static void main(String[] args) throws Exception{
		HashMap map = new HashMap();
		System.out.println("before:"+map);

		//ModellController에서 넘겨받은 값을 View name에 담아준다.
		ModelController mc = new ModelController();
		String viewName = mc.main(map);
		
		System.out.println("after :"+map);
		
		//render mathod로 data와 viewname을 전달한다.
		render(map, viewName);
	}
	
	static void render(HashMap map, String viewName) throws IOException {
		String result = "";
		
		// 1. 뷰의 내용을 한줄씩 읽어서 하나의 문자열로 만든다.
		Scanner sc = new Scanner(new File(viewName+".txt"));
		
		while(sc.hasNextLine())
			result += sc.nextLine()+ System.lineSeparator();
		
		// 2. map에 담긴 key를 하나씩 읽어서 template의 ${key}를 value바꾼다.
		Iterator it = map.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String)it.next();

			// 3. replace()로 key를 value 치환한다.
			result = result.replace("${"+key+"}", (String)map.get(key));
		}
		
		// 4.렌더링 결과를 출력한다.
		System.out.println(result);
	}
}

