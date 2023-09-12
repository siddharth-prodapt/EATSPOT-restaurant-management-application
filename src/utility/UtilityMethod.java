package utility;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import invoice.Invoice;
import restaurant.loginDashboad.RestaurantDashboard;

public class UtilityMethod {
// number to word converter	
	public static String numberToWord(int num) {

		String words = "";
		String unitarr[] = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
				"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
		String tensarr[] = { "zero", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
				"ninety" };
		if (num == 0) {
			return "zero";
		}

		if (num < 0) {
			// converting the number to a string
			String numberStr = "" + num;
			// removing minus before the number
			numberStr = numberStr.substring(1);
			// add minus before the number and convert the rest of number
			return "minus " + numberToWord(Integer.parseInt(numberStr));
		}
		// cconditon for divisible by 1 million
		if ((num / 1000000) > 0) {
			words += numberToWord(num / 1000000) + " million ";
			num %= 1000000;
		}
		// cconditon for divisible by 1 thousand
		if ((num / 1000) > 0) {
			words += numberToWord(num / 1000) + " thousand ";
			num %= 1000;
		}
		// cconditon for divisible by 1 hundred
		if ((num / 100) > 0) {
			words += numberToWord(num / 100) + " hundred ";
			num %= 100;
		}
		if (num > 0) {
			if (num < 20) {
				words += unitarr[num];
			} else {
				words += tensarr[num / 10];
				if ((num % 10) > 0) {
					words += "-" + unitarr[num % 10];
				}
			}
		}
		return words;
	}

	public static void pdfConverter(String filePath) {

		FileInputStream fis = null;
		DataInputStream in = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		String txtFile = filePath;
		String pdfFile = filePath+".pdf";
		File sourceFile = new File(txtFile);
		File destFile = new File(pdfFile);

		try {
			com.itextpdf.text.Document pdfDoc = new com.itextpdf.text.Document();
			PdfWriter writer = PdfWriter.getInstance(pdfDoc, new FileOutputStream(destFile));
			pdfDoc.open();
			pdfDoc.setMarginMirroring(true);
			pdfDoc.setMargins(36, 72, 108, 180);
			pdfDoc.topMargin();

			BaseFont courier = BaseFont.createFont(BaseFont.COURIER, BaseFont.CP1252, BaseFont.EMBEDDED);

			Font myFont = new Font(courier);

			Font bold_font = new Font();

			bold_font.setStyle(Font.BOLD);
			bold_font.setSize(10);

			myFont.setStyle(Font.NORMAL);
			myFont.setSize(9);

			pdfDoc.add(new com.itextpdf.text.Paragraph("\n"));

			if (sourceFile.exists()) {
				fis = new FileInputStream(sourceFile);
				in = new DataInputStream(fis);
				isr = new InputStreamReader(in);
				br = new BufferedReader(isr);
				String strLine;

				while ((strLine = br.readLine()) != null) {
					com.itextpdf.text.Paragraph para = new com.itextpdf.text.Paragraph(strLine + "\n", myFont);
					para.setAlignment(Element.ALIGN_JUSTIFIED);
					pdfDoc.add(para);
				}

//				System.out.println("InvoicePDF Generated");
			} else {
				System.out.println("No file exists");
			}
			pdfDoc.close();
		} catch (Exception e) {
			System.out.println("Exception in " + e);
		} finally {
			try {
				if (br != null) {
					br.close();
					
				}
				if (fis != null) {
					fis.close();
				}
				if (in != null) {
					in.close();
				}
				if (isr != null) {
					isr.close();
				}
	
			} catch (Exception e) {
				System.out.println("Exception in closing readers" + e);
			}
		}
	}

	public static void writeBillToFile(Invoice bill) {
		String fileName = Integer.toString(bill.getOrder_id())+"_orderId";
		String filePath = "C:\\Users\\siddharth.sp\\Desktop\\Invoices\\"+fileName;
//		File file = null;
		try {
			String txtFilePath = filePath+".txt";
			File file = new File(txtFilePath);
			if(file.exists()) {
//				System.out.println("File already exists therefore deleted");
				file.delete();
			}
			if (file.createNewFile()) {
				System.out.println("Invoice Generated: " + file.getName());
				FileWriter myWriter = new FileWriter(filePath);
//				myWriter.write(text);
				myWriter.write( String.format("%53s \r\n", "INVOICE" ));
				myWriter.append("------------------------------------------------------------------------------------------------\r\n");
				myWriter.append(String.format("%55s \r\n", RestaurantDashboard.getCurrentRestaurant().getRestaurantName()));
				myWriter.append("------------------------------------------------------------------------------------------------\r\n");
				myWriter.append(String.format("%53s \r\n", RestaurantDashboard.getCurrentRestaurant().getState() )+String.format("%60s\r\n", RestaurantDashboard.getCurrentRestaurant().getLocation()));
				myWriter.append("------------------------------------------------------------------------------------------------\r\n");
				myWriter.append("Invoice Dt    : "+bill.getInvoice_dt()+"\r\nInvoiceID     : "+bill.getInvoice_id()+"\r\nRestaurant ID : "+bill.getRestaruant_id()+"\r\nCustomerID    : "+bill.getCustomer_id()+"\r\nOrder ID      :"+bill.getOrder_id()+"\r\n");
				myWriter.append("------------------------------------------------------------------------------------------------\r\n");
				myWriter.append(String.format("| %30s | %10s | %10s | %10s |\r\n", "Item Description","Qty", "Price", "Amount"));
				myWriter.append("------------------------------------------------------------------------------------------------\r\n");
				
				bill.orderedItemsList.forEach(item->{
					try {
						myWriter.append(String.format("| %30s | %10s | %10s | %10s |\r\n", item.getItemName(), item.getQuantity(), item.getItemPrice(), item.getItemAmount()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
				
//				
				myWriter.append("------------------------------------------------------------------------------------------------\r\n");
				myWriter.append(String.format("%58s : %10s   \r\n", "Sub Total",bill.getBill_amount()));
				myWriter.append(String.format("%58s : %10s   \r\n", "Amount",(int)bill.getBill_amount()));
				myWriter.append("\r\n");
				myWriter.append(String.format("%73s ", numberToWord((int)bill.getBill_amount())+"\r\n"));
				myWriter.append("------------------------------------------------------------------------------------------------\r\n");
				myWriter.append("\r\n\r\n");
				myWriter.append("Make all checks payable to Company name\r\n");
				myWriter.append("Thank You very much. Come back again!\r\n");
				myWriter.append("For personal use only");
				
				myWriter.close();
				
			} else {
				System.out.println("File Already exists");
			}
		} catch (IOException e) {
			System.out.println("Exception in creationn of file" + e);
		}
		
		
		pdfConverter(filePath);
		
	}
}