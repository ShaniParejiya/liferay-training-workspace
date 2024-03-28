package com.aixtor.training.liferay.employee.util;

import com.aixtor.training.liferay.employee.action.ExportToPdfMVCServeResourcesCommand;
import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebConstant;
import com.aixtor.training.liferay.employee.service.builder.model.EmployeeAllDetailsModel;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ContentTypes;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Stream;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.ByteArrayOutputStream;

public class ExportToPdfEmployeeDetails {
	private static final Log log = LogFactoryUtil.getLog(ExportToPdfMVCServeResourcesCommand.class);
	
	public void exportToPdf(List<EmployeeAllDetailsModel> employeeList,ResourceResponse resourceResponse,ResourceRequest resourceRequest,HttpServletResponse httpServletResponse,PdfWriter pdfWriter) throws IOException {
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()){        			
        	
        	Document document = new Document();
        	PdfWriter.getInstance(document, byteArrayOutputStream);
            
        	document.open();
     
            document.add(new Paragraph("\n"));
            
            //logo and title create table
            PdfPTable logoandtitle=new PdfPTable(2);            
            addLogoAndTitle(logoandtitle,AixtorEmployeeWebConstant.SITE_LOGO);
            document.add(logoandtitle);
            
        	PdfPTable table = new PdfPTable(9);
        	addTableHeader(table);
        	addRows(table,employeeList);
        	table.setWidthPercentage(100);
        	document.add(table);
        	
        	document.close();
        	
        	byte[] pdfBytes = byteArrayOutputStream.toByteArray();
        	
        	String filename = AixtorEmployeeWebConstant.EMPLOYEE_CHEETSHEET_ + System.currentTimeMillis() + AixtorEmployeeWebConstant.PDF;
			resourceResponse.addProperty(HttpHeaders.CONTENT_DISPOSITION, AixtorEmployeeWebConstant.ATTACHMENT_FILENAME + filename);
			resourceResponse.setContentType(ContentTypes.APPLICATION_PDF);
			
			try(OutputStream outputStream = resourceResponse.getPortletOutputStream()){
				outputStream.write(pdfBytes);
			}
        }catch(Exception e) {
        	log.error("Error msg in exportToPdf =======>>>>" + e.getMessage());
        }
    }

	private void addLogoAndTitle(PdfPTable table, String logoPath) {
	    try {
	        PdfPCell logoCell = new PdfPCell();
	        Image logo = Image.getInstance(logoPath);
	        logo.scaleToFit(60, 40);
	        logo.setAlignment(Element.ALIGN_RIGHT);
	        logoCell.addElement(logo);
	        logoCell.setBorder(Rectangle.NO_BORDER);
	        
	        PdfPCell titleCell = new PdfPCell();
	        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
	        Paragraph title = new Paragraph("Employee Details Report", titleFont);
	        title.setAlignment(Element.ALIGN_CENTER);
	        titleCell.addElement(title);
	        titleCell.setBorder(Rectangle.NO_BORDER);
	        
	        table.addCell(logoCell);
	        table.addCell(titleCell);
	        
	    } catch (BadElementException | IOException e) {
	        log.error("Error adding logo and title to table: " + e.getMessage());
	    }
	}
	 
	private void addTableHeader(PdfPTable table) {
		
		Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, BaseColor.BLACK);
		float[] columnWidths = {10f, 9f, 15f, 18f, 16f, 15f, 10f, 12f,10f};
		try {
	     Stream.of( AixtorEmployeeWebConstant.EXPORT_EMPLOYEE_ID,
					AixtorEmployeeWebConstant.EXPORT_FIRST_NAME,
					AixtorEmployeeWebConstant.LAST_NAME,
					AixtorEmployeeWebConstant.EXPORT_EMAIL,
					AixtorEmployeeWebConstant.EXPORT_MOBILE_NUMBER,
					AixtorEmployeeWebConstant.EXPORT_DEPARTMENT_NAME,
					AixtorEmployeeWebConstant.EXPORT_BRANCH_NAME,
					AixtorEmployeeWebConstant.EXPORT_DESIGNATION_NAME,
					AixtorEmployeeWebConstant.EXPORT_ADDRESS
				)
	      .forEach(title -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(1);
	        header.setPhrase(new Phrase(title, headerFont));
	        header.setBackgroundColor(BaseColor.CYAN);
	        table.addCell(header);
	    });
			table.setWidths(columnWidths);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	private void addRows(PdfPTable table,List<EmployeeAllDetailsModel> employeeList) {
		Font rowFont = FontFactory.getFont(FontFactory.HELVETICA, 8,BaseColor.BLACK);
		for(EmployeeAllDetailsModel employee : employeeList) {
	        table.addCell(new Phrase(employee.getEmployeeId(),rowFont));
	        table.addCell(new Phrase(employee.getFirstName(), rowFont));
	        table.addCell(new Phrase(employee.getLastName(), rowFont));
	        table.addCell(new Phrase(employee.getEmail(), rowFont));
	        table.addCell(new Phrase(employee.getMobileNumber(), rowFont));
	        table.addCell(new Phrase(employee.getDepartmentName(), rowFont));
	        table.addCell(new Phrase(employee.getBranchName(), rowFont));
	        table.addCell(new Phrase(employee.getDesignationName(), rowFont));
	        table.addCell(new Phrase(employee.getAddress(), rowFont));
		}
	}
}
