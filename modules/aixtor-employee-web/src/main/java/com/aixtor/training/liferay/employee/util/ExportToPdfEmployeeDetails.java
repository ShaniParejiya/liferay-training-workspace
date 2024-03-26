package com.aixtor.training.liferay.employee.util;

import com.aixtor.training.liferay.employee.action.ExportToPdfMVCServeResourcesCommand;
import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebConstant;
import com.aixtor.training.liferay.employee.service.builder.model.EmployeeAllDetailsModel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
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
	
	
	PdfWriter pdfWriter=null;
	public void exportToPdf(List<EmployeeAllDetailsModel> employeeList,ResourceResponse resourceResponse,ResourceRequest resourceRequest,HttpServletResponse httpServletResponse,PdfWriter pdfWriter) throws IOException {
        try {        			
        	Document document = new Document();
        	ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        	
        	PdfWriter.getInstance(document, byteArrayOutputStream);
        	pdfWriter = PdfWriter.getInstance(document, byteArrayOutputStream);
            
        	document.open();
        	addBackgroundImage(document, pdfWriter);
        	
        	PdfPTable table = new PdfPTable(9);
        	addTableHeader(table);
        	addRows(table,employeeList);
        	table.setWidthPercentage(100);
        	document.add(table);
        	document.close();
        	
        	byte[] by = byteArrayOutputStream.toByteArray();
        	
        	String filename = AixtorEmployeeWebConstant.EMPLOYEE_CHEETSHEET_ + System.currentTimeMillis() + AixtorEmployeeWebConstant.PDF;
			resourceResponse.addProperty(HttpHeaders.CONTENT_DISPOSITION, AixtorEmployeeWebConstant.ATTACHMENT_FILENAME + filename);
			resourceResponse.setContentType(ContentTypes.APPLICATION_PDF);
			OutputStream outputStream = resourceResponse.getPortletOutputStream();
			outputStream.write(by);
			outputStream.flush();
			outputStream.close();
        	document.close();
        }catch(Exception e) {
        	log.info("Error msg in Catch =======>>>>" + e.getMessage());
        }
    }

	private void addTableHeader(PdfPTable table) {
		
		try {
			float[] columnWidths = {10f, 9f, 15f, 18f, 16f, 15f, 10f, 12f,10f};
	    Stream.of(AixtorEmployeeWebConstant.EXPORT_EMPLOYEE_ID,
				AixtorEmployeeWebConstant.EXPORT_FIRST_NAME,
				AixtorEmployeeWebConstant.LAST_NAME,
				AixtorEmployeeWebConstant.EXPORT_EMAIL,
				AixtorEmployeeWebConstant.EXPORT_MOBILE_NUMBER,
				AixtorEmployeeWebConstant.EXPORT_DEPARTMENT_NAME,
				AixtorEmployeeWebConstant.EXPORT_BRANCH_NAME,
				AixtorEmployeeWebConstant.EXPORT_DESIGNATION_NAME,
				AixtorEmployeeWebConstant.EXPORT_ADDRESS
				)
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(2);
	        header.setPhrase(new Phrase(columnTitle));
	        header.setBackgroundColor(BaseColor.CYAN);
	        table.addCell(header);
	    });
			table.setWidths(columnWidths);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	private void addRows(PdfPTable table,List<EmployeeAllDetailsModel> employeeList) {
		for(EmployeeAllDetailsModel employee:employeeList) {

		    table.addCell(employee.getEmployeeId());
		    table.addCell(employee.getFirstName());
		    table.addCell(employee.getLastName());
		    table.addCell(employee.getEmail());
		    table.addCell(employee.getMobileNumber());
		    table.addCell(employee.getDepartmentName());
		    table.addCell(employee.getBranchName());
		    table.addCell(employee.getDesignationName());
		    table.addCell(employee.getAddress());
		}
	}
	
	
	private void addBackgroundImage(Document document, PdfWriter pdfWriter) throws IOException, DocumentException {
        // Path to your background image
        String imagePath = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQUO4RhXPa47eQSG6RPZ5GuUczaZWOuuOJiKw&s";
        
        // Create an Image object with the background image
        Image image = Image.getInstance(imagePath);
        
        // Set the position and dimensions of the image
        image.setAbsolutePosition(0, 0); // Position at the top-left corner
        image.scaleAbsolute(document.getPageSize()); // Scale to fit the entire page
        
        // Get the PdfContentByte object from the PdfWriter
        PdfContentByte contentByte = pdfWriter.getDirectContentUnder();
        
        // Add the image to the document
        contentByte.addImage(image);
    }
}
