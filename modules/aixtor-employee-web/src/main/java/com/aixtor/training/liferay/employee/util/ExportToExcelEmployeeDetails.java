package com.aixtor.training.liferay.employee.util;

import com.aixtor.training.liferay.employee.constants.AixtorEmployeeWebConstant;
import com.aixtor.training.liferay.employee.service.builder.model.EmployeeAllDetailsModel;
import com.aixtor.training.liferay.employee.service.builder.service.EmployeeLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ContentTypes;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;


import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


public class ExportToExcelEmployeeDetails {

	@Reference
	EmployeeLocalService employeeLocalService;
	
	private static final Log log  =LogFactoryUtil.getLog(ExportToExcelEmployeeDetails.class);
	
	
	public void exportToExcel(List<EmployeeAllDetailsModel> employeeList, ResourceResponse resourceResponse,
			ResourceRequest resourceRequest, HttpServletResponse httpServletResponse)
			throws java.io.IOException, PortletException {

		try (XSSFWorkbook workbook = new XSSFWorkbook()) {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			Sheet sheet = workbook.createSheet();

			Row headerRow = sheet.createRow(0);
			String[] headers = {
								AixtorEmployeeWebConstant.EXPORT_EMPLOYEE_ID,
								AixtorEmployeeWebConstant.EXPORT_FIRST_NAME,
								AixtorEmployeeWebConstant.LAST_NAME,
								AixtorEmployeeWebConstant.EXPORT_EMAIL,
								AixtorEmployeeWebConstant.EXPORT_MOBILE_NUMBER,
								AixtorEmployeeWebConstant.EXPORT_DEPARTMENT_NAME,
								AixtorEmployeeWebConstant.EXPORT_BRANCH_NAME,
								AixtorEmployeeWebConstant.EXPORT_DESIGNATION_NAME,
								AixtorEmployeeWebConstant.EXPORT_ADDRESS
							};
			for (int i = 0; i < headers.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headers[i]);
			}

			Font font = workbook.createFont();
			font.setFontHeightInPoints((short) 12);
			font.setFontName(AixtorEmployeeWebConstant.ARIAL);

			CellStyle style = workbook.createCellStyle();
			style.setFont(font);

			sheet.setColumnWidth(0, 3000);
			sheet.setColumnWidth(1, 3000);
			sheet.setColumnWidth(2, 3500);
			sheet.setColumnWidth(3, 6000);
			sheet.setColumnWidth(4, 5000);
			sheet.setColumnWidth(5, 5000);
			sheet.setColumnWidth(6, 5000);
			sheet.setColumnWidth(7, 5000);
			sheet.setColumnWidth(8, 6000);

			int rowNum = 1;
			for (EmployeeAllDetailsModel employee : employeeList) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(employee.getEmployeeId());
				row.createCell(1).setCellValue(employee.getFirstName());
				row.createCell(2).setCellValue(employee.getLastName());
				row.createCell(3).setCellValue(employee.getEmail());
				row.createCell(4).setCellValue(employee.getMobileNumber());
				row.createCell(5).setCellValue(employee.getDepartmentName());
				row.createCell(6).setCellValue(employee.getBranchName());
				row.createCell(7).setCellValue(employee.getDesignationName());
				row.createCell(8).setCellValue(employee.getAddress());

				for (int j = 0; j < 9; j++) {
					row.getCell(j).setCellStyle(style);
				}
			}
			workbook.write(byteArrayOutputStream);
			byte[] by = byteArrayOutputStream.toByteArray();
			
			String filename = AixtorEmployeeWebConstant.EMPLOYEE_CHEETSHEET_ + System.currentTimeMillis() + AixtorEmployeeWebConstant.XLSX;
			resourceResponse.addProperty(AixtorEmployeeWebConstant.CONTENT_DISPOSITION, AixtorEmployeeWebConstant.ATTACHMENT_FILENAME + filename);
			resourceResponse.setContentType(ContentTypes.APPLICATION_VND_MS_EXCEL);
			OutputStream outputStream = resourceResponse.getPortletOutputStream();
			outputStream.write(by);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			log.info("Error in Catch == > " + e.getMessage());
			throw new PortletException("Error exporting data to Excel", e);
		}
	}
}
