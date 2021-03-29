package com.example.testServer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Test {

	// log
	private static final Logger log = LoggerFactory.getLogger(Test.class);

//	@Scheduled(cron = "0/1 * * * * *")
//	public void testConnectino() throws Exception {
//		Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 -W " + "1000" + " " + "10.255.78.71");
//		int returnVal = p1.waitFor();
//		boolean reachable = (returnVal == 0);
//		log.info(String.valueOf(reachable));
//
//	}

}
