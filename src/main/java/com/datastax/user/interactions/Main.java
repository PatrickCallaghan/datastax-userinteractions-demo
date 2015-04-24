package com.datastax.user.interactions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.demo.utils.PropertyHelper;
import com.datastax.demo.utils.Timer;
import com.datastax.user.interactions.dao.UserInteractionDao;
import com.datastax.user.interactions.model.UserInteraction;

public class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);
	private DateTime date;	
	private Map<String, Date> userDateMap = new HashMap<String,Date>();
	private static int BATCH = 10000;

	public Main() {

		// Create yesterdays date at midnight
		this.date = new DateTime().minusDays(1).withTimeAtStartOfDay();

		String contactPointsStr = PropertyHelper.getProperty("contactPoints", "localhost");
		String noOfUsersStr = PropertyHelper.getProperty("noOfUsers", "1000");
		String noOfInteractionsStr = PropertyHelper.getProperty("noOfInteractions", "10");

		UserInteractionDao dao = new UserInteractionDao(contactPointsStr.split(","));

		int noOfUsers = Integer.parseInt(noOfUsersStr);
		int noOfInteractions = Integer.parseInt(noOfInteractionsStr);

		Timer timer = new Timer();
		logger.info("Writing " + noOfInteractions + " interactions for " + noOfUsers + " users");
		
		int total = 0;
		
		int totalInteractions = noOfInteractions * noOfUsers;
		
		dao.printMetrics();
		
		for (int i = 0; i < totalInteractions; i++) {

			dao.insertUserInteraction(createRandomUserInteraction(noOfUsers));

			if (i > 0 && i % BATCH == 0) {
				total += BATCH;
				logger.info("Wrote " + total + " groups of interactions");
				dao.printMetrics();
			}
		}
		
		dao.printMetrics();
		
		timer.end();
		logger.info("User Interaction Load took " + timer.getTimeTakenSeconds() + " secs.");

	}
	

	private List<UserInteraction> createRandomUserInteraction(int noOfUsers) {
				
		List<UserInteraction> interactions = new ArrayList<UserInteraction>();
		
		String user = "U" + (new Double(Math.random() * noOfUsers).intValue() + 1);
		String app = apps.get(new Double(Math.random() * apps.size()).intValue());
		
		
		//Need to establish a good date. now or after the user logged out of the last app they were using
		DateTime dateTime = new DateTime();
		
		if (userDateMap.containsKey(user)){
			dateTime = new DateTime(userDateMap.get(user));
			dateTime = dateTime.plusSeconds(new Double(Math.random() * 60).intValue());
		}else{
			dateTime = new DateTime();
		}		
		
		UUID visitId = UUID.randomUUID();
		
		interactions.add(new UserInteraction(visitId, user,app, dateTime.toDate(), "login"));
		
		int items = new Double(Math.random() * 50).intValue() + 1;
		
		for (int i=0; i < items; i ++){		
			
			dateTime = dateTime.plusSeconds(new Double(Math.random() * 60).intValue());			
			String action = actions.get(new Double(Math.random() * actions.size()).intValue());			
			interactions.add(new UserInteraction(visitId, user,app, dateTime.toDate(), action));
		}
				
		interactions.add(new UserInteraction(visitId, user,app, dateTime.toDate(), "logout"));		
		userDateMap.put(user, dateTime.toDate());
		
		return interactions;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();

		System.exit(0);
	}

	private List<String> apps = Arrays.asList("mobilefx", "mobilefx", "mobilefx", "desktop ui", "tablet research", 
			"tablet research", "iphone banking", "android banking",
			"mobile trading");

	private List<String> actions = Arrays.asList("tradeview", "news",
			"portfolio", "p and l", "charting", "advanced charting", "advanced charting", "advanced charting", "news", "news", "landing page",
			"preferences", "tradeview", "tradeview", "tradeview", "tradeview", "tradeview");
}
