package cl.com.walmart.testbackend.bussines.factory.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cl.com.walmart.testbackend.bussines.factory.IPriceFormaterFactory;
import cl.com.walmart.testbackend.bussines.service.IPriceFormaterService;
import cl.com.walmart.testbackend.bussines.service.impl.PalindromeDiscountService;
import cl.com.walmart.testbackend.bussines.service.impl.PriceOriginalService;
import cl.com.walmart.testbackend.spring.ApplicationContextProvider;

@Component
public class PriceFormaterFactory implements IPriceFormaterFactory {
	
	@Autowired
	private ApplicationContextProvider contextProvider;

	@Override
	public IPriceFormaterService createPriceFormater(String text) {
			if(isPalindrome(text))
				return contextProvider.getApplicationContext().getBean("palindromeDiscountService", PalindromeDiscountService.class);
			else
				return contextProvider.getApplicationContext().getBean("priceOriginalService", PriceOriginalService.class);
		
	}
	
	private boolean isPalindrome(String text) {
	    String clean = text.replaceAll("\\s+", "").toLowerCase();
	    int length = clean.length();
	    int forward = 0;
	    int backward = length - 1;
	    while (backward > forward) {
	        char forwardChar = clean.charAt(forward++);
	        char backwardChar = clean.charAt(backward--);
	        if (forwardChar != backwardChar)
	            return false;
	    }
	    return true;
	}


}
