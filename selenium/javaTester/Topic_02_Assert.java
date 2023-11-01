package javaTester;

import org.testng.Assert;

// kiểu dữ liệu
public class Topic_02_Assert {
	
	public static void main(String[] args) {
		String errorMessage = "Please enter your email";
		boolean status = true;
		
		//kiem tra 2 data co bang nhau hay khong
		Assert.assertEquals("Please enter your email", errorMessage);
		
		//kiem tra 1 dieu kien tra ve la dung
		Assert.assertTrue(status);
		
		//kiem tra 1 dieu kien tra ve la sai
		status = false;
		Assert.assertFalse(status);
		
	}
}

