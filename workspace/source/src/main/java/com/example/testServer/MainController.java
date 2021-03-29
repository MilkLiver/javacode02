package com.example.testServer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.zip.CRC32;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	private static final Logger log = LoggerFactory.getLogger(MainController.class);
	// private static final SimpleDateFormat SDF = new
	// SimpleDateFormat("yyyyMMddHHmmss");

	// tts轉語音發話
	@ResponseBody
	@GetMapping(value = "/timeout", produces = "application/json")
	public String say(HttpServletRequest request, HttpServletResponse response) {
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "{\"status\":\"ok\"}";
	}

	@ResponseBody
	@GetMapping(value = "/test", produces = "application/json")
	public String test(HttpServletRequest request, HttpServletResponse response, Model model) {

		StringBuilder testSb = new StringBuilder();

		Date date = new Date();

		testSb.append("mapping /test date: " + date.toGMTString());

		log.info(testSb.toString());

		return testSb.toString();
	}

//	@GetMapping(value = "/test", produces = "application/json")
//	public String test(HttpServletRequest request, HttpServletResponse response, Model model) {
//
//		File file = new File("D:/test/oms-file/cat.html");
//		model.addAttribute("filePath", file.getPath());
//		System.out.println(file.getPath());
//		return "OUO";
//	}

	@ResponseBody
	@GetMapping(value = "/download", produces = "application/json")
	public String downLoad(HttpServletRequest request, HttpServletResponse response) {
		log.info("Start fileDownLoad");

		File file = new File("D:/test/oms-file/cat.html");

		response.reset();
		response.setContentType("application/octet-stream");
		response.setCharacterEncoding("utf-8");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment;filename=" + "cat.html");

		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
			byte[] buff = new byte[1024];
			OutputStream os = response.getOutputStream();
			int i = 0;
			while ((i = bis.read(buff)) != -1) {
				os.write(buff, 0, i);
				os.flush();
			}
		} catch (IOException e) {
			for (StackTraceElement elem : e.getStackTrace()) {
				log.error(elem.toString());
			}
			return "error";
		}
		log.info("run fileDownLoad success");
		return "ok";
	}

}
