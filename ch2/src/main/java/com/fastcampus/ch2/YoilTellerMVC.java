 package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Calendar;

@Controller
public class YoilTellerMVC {

    // http://localhost:8080/ch2/getYoilMVC?year=2021&month=10&day=1
    // public static void main(String[] args) {
    // public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
	@RequestMapping("/getYoilMVC") 
    public String main(int year, int month, int day, Model model) throws IOException {	
		
    
		//1. 유효성 검사 기능 추가
    	//2. 코드 분리 (method로 추가해주기)
    	//		- 요일 반환 method 
    	//		- 유효성 검사 method	
    	//3. 띄워줄 창 분리 
    	//		- 요일 반환 method == return "yoil";
    	//		- 유효성 검사 method == return "yoilError";
    	
		//1 . 유효성 검사 
    	if(!isValid(year,month,day)) 
      		return "yoilError";
    	//2. 요일 계산     	
    	char yoil = getYoil(year, month, day);
       	
       	// 여기까지 했는데 view에 자꾸 year,month,day가 출력이 안되었다. 
       	// 값이 어딘가 저장되어 있어야 하는게 아닌가 하고 생각했는데 그 이상 진도는 못나갔다.
       	// 원인은 method간에 연결해주는 model객체가 없어서였다.
       	
       	//3. 계산한 결과물을 model 객체에 담아주기 
       	
       	model.addAttribute("year", year);
       	model.addAttribute("month", month);
       	model.addAttribute("day", day);
       	model.addAttribute("yoil",yoil);
       	
       	return "yoil";
    }


	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return "일월화수목금토".charAt(dayOfWeek);
        
        
	}
    private boolean isValid(int year, int month, int day) {    
    	if(year==-1 || month==-1 || day==-1) 
    		return false;
    	
    	return (1<=month && month<=12) && (1<=day && day<=31); // 간단히 체크 
    }
}