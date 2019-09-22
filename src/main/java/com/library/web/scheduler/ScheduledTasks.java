package com.library.web.scheduler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.library.web.model.Borrow;
import com.library.web.service.BorrowService;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
public class ScheduledTasks {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@Autowired
	BorrowService service;
	
	//@Scheduled(fixedRate = 2000)
	public void scheduleTaskWithFixedRate() {
		System.out.println("time:=- "+dateTimeFormatter.format(LocalDateTime.now()) );
		logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
	}

    public void scheduleTaskWithFixedDelay() {}

    public void scheduleTaskWithInitialDelay() {}

    @Scheduled(cron = "	0 0 10 1/1 * ?")
    public void scheduleTaskWithCronExpression() {
    	List<Borrow> records = service.getUsersToNotify();
    	for (Borrow temp : records) {
			
			OkHttpClient client = new OkHttpClient();

			MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
			RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"From\"\r\n\r\nLIBSYS\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"To\"\r\n\r\n"+temp.getMobile()+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"TemplateName\"\r\n\r\nduedate2\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"VAR1\"\r\n\r\n"+temp.getUser_name()+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"VAR2\"\r\n\r\n"+temp.getBook_name()+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
			Request request = new Request.Builder()
			  .url("http://2factor.in/API/V1/80a54edf-eaec-11e8-a895-0200cd936042/ADDON_SERVICES/SEND/TSMS")
			  .post(body)
			  .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
			  .addHeader("cache-control", "no-cache")
			  .build();

			try {
				Response response = client.newCall(request).execute();
				System.out.println(response.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
    }
}
