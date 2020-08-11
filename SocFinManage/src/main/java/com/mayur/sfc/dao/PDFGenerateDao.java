package com.mayur.sfc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.mayur.jdbc.db.configuration.ConnectionDb;
import com.mayur.sfc.model.SendMailModel;

public class PDFGenerateDao {

	private static final String select_data = "select distinct s.houseId, s.memName, s.emailId, s.contact, b.fine from society s join billpayment b on s.houseId = b.houseId where b.fine= true";
	
	public List<SendMailModel> getData()  {
		SendMailModel pdf = null;
		Connection conn = null;
		List<SendMailModel> list = new ArrayList<>();
		
		ResultSet rs = null;
		try {
			
			conn= ConnectionDb.getConnection();
			PreparedStatement st= conn.prepareStatement(select_data);
			
			rs = st.executeQuery();
			while(rs.next()) {
				pdf = new SendMailModel();
				pdf.setHouseId(rs.getLong("houseId"));
				pdf.setMemName(rs.getString("memName"));
				pdf.setEmailId(rs.getString("emailId"));
				pdf.setContact(rs.getLong("contact"));
				pdf.setFine(rs.getBoolean("fine"));
				
				list.add(pdf);

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
