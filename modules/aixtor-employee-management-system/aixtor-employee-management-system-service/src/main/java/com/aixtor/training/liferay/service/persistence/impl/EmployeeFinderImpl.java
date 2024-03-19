package com.aixtor.training.liferay.service.persistence.impl;

import com.aixtor.training.liferay.model.EmployeeAllDetailsModel;

import com.aixtor.training.liferay.service.persistence.EmployeeFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = EmployeeFinder.class)
public class EmployeeFinderImpl extends EmployeeFinderBaseImpl implements EmployeeFinder {

	private static final String GET_EMPLOYEE_ALL_DETAILS = "getEmployeeAllDetails";

	private static final String GET_SEARCH_BY_ALL_DETAILS = "getSearchByAllDetails";

	@Reference
	private CustomSQL customSql;

	private static List<EmployeeAllDetailsModel> employeeFilterList;

	private static EmployeeAllDetailsModel employeeAllDetailsModel;

	public List<EmployeeAllDetailsModel> setEmployeeJoinModelData(List<Object[]> employeeDataList) {
		employeeFilterList = new ArrayList<>();

		for (Object[] row : employeeDataList) {
			employeeAllDetailsModel = new EmployeeAllDetailsModel();
			employeeAllDetailsModel.setEmployeeId((String) row[0]);
			employeeAllDetailsModel.setFirstName((String) row[1]);
			employeeAllDetailsModel.setLastName((String) row[2]);
			employeeAllDetailsModel.setEmail((String) row[3]);
			employeeAllDetailsModel.setMobileNumber((String) row[4]);
			employeeAllDetailsModel.setAddress((String) row[5]);
			employeeAllDetailsModel.setDepartmentName((String) row[6]);
			employeeAllDetailsModel.setDesignationName((String) row[7]);
			employeeAllDetailsModel.setBranchName((String) row[8]);
			employeeFilterList.add(employeeAllDetailsModel);
		}
		return employeeFilterList;
	}

	public List<EmployeeAllDetailsModel> getEmployeeAllDetails(String keyWordValue) {
		Session session = null;
		try {
			session = openSession();
			String sql = customSql.get(getClass(), GET_SEARCH_BY_ALL_DETAILS);
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setCacheable(false);
			QueryPos qPos = QueryPos.getInstance(sqlQuery);
			qPos.add(keyWordValue);
			qPos.add(keyWordValue);
			qPos.add(keyWordValue);
			qPos.add(keyWordValue);
			qPos.add(keyWordValue);
			qPos.add(keyWordValue);
			qPos.add(keyWordValue);
			qPos.add(keyWordValue);
			qPos.add(keyWordValue);
			List<Object[]> objectList = (List<Object[]>) sqlQuery.list();
			employeeFilterList = setEmployeeJoinModelData(objectList);
			return employeeFilterList;
		} catch (Exception e) {
			System.out.println("Error is: " + e.getMessage());
		} finally {
			closeSession(session);
		}
		return null;
	}

	public List<EmployeeAllDetailsModel> getEmployeeDetails() {
		Session session = null;
		try {
			session = openSession();
			String sql = customSql.get(getClass(), GET_EMPLOYEE_ALL_DETAILS);
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setCacheable(false);
			employeeFilterList = setEmployeeJoinModelData((List<Object[]>) sqlQuery.list());
			return employeeFilterList;
		} catch (Exception e) {
			System.out.println("Exception is:" + e.getMessage());
		} finally {
			closeSession(session);
		}
		return null;
	}

}
