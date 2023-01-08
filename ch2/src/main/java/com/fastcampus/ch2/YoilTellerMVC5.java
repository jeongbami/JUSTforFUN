 package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Calendar;

@Controller
public class YoilTellerMVC5 {
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		ex.printStackTrace();
		return "yoilError";
	}
  
	@RequestMapping("/getYoilMVC5") 
	//원래는 아래와 같이 Key값을 입력해주는게 원칙이나 생략 가능하다.
	//생략했을 때는 변수의 Type 첫 글자를 소문자로 바꾸어 자동으로 key name을 생성한다.(MyDate=>myDate)
	//== public String main(@ModelAttribute("myDate") MyDate date, Model model){}
    public String main(@ModelAttribute MyDate date, Model model) {	

    	
		//1 . 유효성 검사 
    	if(!isValid(date)) {
      		return "yoilError";
    	}
//    	//2. 요일 계산     	
//    	char yoil = getYoil(date);


//       	model.addAttribute("myDate", date);
//       	model.addAttribute("yoil",yoil);
// 최종적으로 yoil을 호출하는 method, model객체에 저장하는 method 모두 필요없다.       	
       	return "yoil";
    }
// model annotation의 반환타입에 호출한 예 (여기선 key값이 필수)
	private @ModelAttribute("yoil") char getYoil(MyDate date) {
		return getYoil(date.getYear(), date.getMonth(), date.getDay());
	}

	private boolean isValid(MyDate date) {
		return isValid(date.getYear(), date.getMonth(), date.getDay());
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return "일월화수목금토".charAt(dayOfWeek);
		
		}

	private boolean isValid(int year, int month, int day) {
		if(year == -1 || month == -1 || day == -1) 
		return false;
		
		return (1<=month && month<=12) && (1<=day && day<=31); 
	}

}