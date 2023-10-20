package com.example.product.app.utilmethods;

public class TestDateUtil {

	public static void main(String[] args) {
		System.out.println("10/14/2012" + " = " + DateUtil.convertToDate("10/14/2012"));
		//System.out.println("10-Jan-2012" + " = " + DateUtil.convertToDate("10-Jan-2012"));
		//System.out.println("01.03.2002" + " = " + DateUtil.convertToDate("01.03.2002"));
		System.out.println("12/03/2010" + " = " + DateUtil.convertToDate("12/03/2010"));
//		System.out.println("19.Feb.2011" + " = " + DateUtil.convertToDate("19.Feb.2011" ));
		System.out.println("4/20/2012" + " = " + DateUtil.convertToDate("4/20/2012"));
		//System.out.println("some string" + " = " + DateUtil.convertToDate("some string"));
		//System.out.println("123456" + " = " + DateUtil.convertToDate("123456"));
		System.out.println("null" + " = " + DateUtil.convertToDate(null));

	}	
}