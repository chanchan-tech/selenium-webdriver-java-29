package javaTester;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

// kiểu dữ liệu
public class Topic_01_Data_Type {
	
	//Chứa duy nhất 1 ký tự
	//char
	char a ='c';
	
	//Chứa số nguyên không có dấu
	//byte
	byte bytenumber = 50;
	//short
	short shortnumber = 50;
	//int
	int diemtoan = 10;
	int diemanh = 9;
	int diemvan = 7;
	//long
	long longnumber = 50;
	
	
	//Chứa số thực (có dấu)
	//float
	float floatnumber = 123.45F;
	//double
	double doublenumber = 3213124.123D;
	
	
	//Luận lý true/false
	//boolean
	boolean status = true;
	

	//chứa những chuỗi ký tự
	//string
	String name = "Nguyen Hong Vinh";
	String diachi = "So nha 25, ngo 84 Co Nhue, Bac Tu Liem, HN";
	
	//class
	//class
	Topic_01_Data_Type topic01;
	
	//đối tượng
	//Object
	
	//mảng
	//array
	String [] students = {name, diachi};
	int[] diemmonhoc = {diemtoan, diemanh, diemvan};
	
	//interface
	//interface
	WebDriver driver;

	
	//Kiểu tập hợp (list, set, queue: Interface)
	//List: ArrayList/ LinkedList/...
	//collection
	//List<String> studentName = new ArrayList<String>();
	
	
}

