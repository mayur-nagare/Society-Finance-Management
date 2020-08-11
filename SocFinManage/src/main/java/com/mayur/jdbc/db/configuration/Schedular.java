package com.mayur.jdbc.db.configuration;

import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.itextpdf.text.pdf.codec.TiffWriter.FieldUndefined;
import com.mayur.sfc.dao.PayBillDao;
import com.mayur.sfc.dao.SendMailDao;

public class Schedular {
	
	public void schduleFine() {
		System.out.println("Fine Scheduled");
		PayBillDao payDao= new PayBillDao();
		Date now =  new Date();
		
		int month = now.getMonth() + 1;
		if(month > 12)
			month = 1;
		Date desiredDate = new Date(now.getYear(), now.getMonth()+1, 11); 
		long delay = desiredDate.getTime() - now.getTime();

		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
		ses.schedule(new Runnable(){
		    @Override
		    public void run() {
		    	try {
			        payDao.addFine();
		    	}catch(Exception e) {
		    		e.printStackTrace();
		    	}
		    }
		}, 5000, TimeUnit.MILLISECONDS);
	}
	
	public void schduleMail() {
		System.out.println("Mail scheduled");
		SendMailDao mailDao = new SendMailDao();
		
		Date nowDate = new Date();
		int month = nowDate.getMonth() + 1;
		if(month > 12)
			month = 1;
		Date desiredDate = new Date(nowDate.getYear(), nowDate.getMonth()+1, 5); 
		long delay = desiredDate.getTime() - nowDate.getTime();

		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
		ses.schedule(new Runnable(){
		    @Override
		    public void run() {
		    	try {
		    		mailDao.getAllMembers(); 
		    		System.out.println("Scheduler worked for send mail");
			    }catch(Exception e) {
		    		e.printStackTrace();
		    	}
		    }
		}, delay, TimeUnit.MILLISECONDS);
	}
	
	
}
