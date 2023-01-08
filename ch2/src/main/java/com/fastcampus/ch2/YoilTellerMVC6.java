 package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Calendar;

@Controller
public class YoilTellerMVC6 {
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		ex.printStackTrace();
		return "yoilError";
	}
  
	@RequestMapping("/getYoilMVC6") 
	// 사실 controller에서 참조변수에 @ModelAttribute가 생략 가능하다. 
    public String main(MyDate date, BindingResult result) {	

    	
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