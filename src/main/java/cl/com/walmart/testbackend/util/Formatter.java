package cl.com.walmart.testbackend.util;

import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.stereotype.Component;

import cl.com.walmart.testbackend.bussines.model.DiscountInfo;

@Component
public class Formatter {

	public DiscountInfo makeDiscountInfo(double percentaje, int amount) {
		DiscountInfo discountInfo = new DiscountInfo();
		discountInfo.setPercentage(formatAmount((percentaje*100),0)+"%");
		discountInfo.setPriceWithDiscount(formatAmount((percentaje*amount),0));
		discountInfo.setOriginalPrice(formatAmount(amount,0));
		return discountInfo;

	}

	public  String formatAmount(double number, int decimal) {
		NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
		currency.setMaximumFractionDigits(decimal);
		if ("-".equals(String.valueOf(number).trim().substring(0, 1)))
			return replacePoint("- " + currency.format(Double.parseDouble(String.valueOf(number))).replace("(", "").replace(")", "")); 
		return replacePoint(currency.format(Double.parseDouble(String.valueOf(number))));
	}


	private  String replacePoint(String number) {
		char[] nums = number.toCharArray();
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == '.') {
				nums[i] = ',';
			} else if (nums[i] == ',') {
				nums[i] = '.';
			} 
		} 
		return new String(nums);
	}

}
