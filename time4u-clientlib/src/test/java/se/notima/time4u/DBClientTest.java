/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.notima.time4u;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import se.notima.time4u.entities.*;

/**
 *
 * @author Daniel Tamm
 */
public class DBClientTest {

	private static IDBClient db;
	private static String	 testProject = "";
	private static Set<String>	rootProjects = new TreeSet<String>();
	
    public DBClientTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    	
    	// Find property file if existing
    	Properties props = new Properties();
    	try {
    		props.load(ClassLoader.getSystemResourceAsStream("database.properties"));
    	} catch (Exception e) {
    		System.out.println("No database.properties found. Tests might fail. Have you renamed database.properties.sample?");
    		props = null;
    	}
    	
    	db = new DBClient();
    	String userHomeDir = System.getProperty("user.home");
    	String defaultDriver = "org.apache.derby.jdbc.EmbeddedDriver";
    	String defaultDbUrl = "jdbc:derby:" + userHomeDir + File.separator + "Time4UData-old" + File.separator +
				".metadata" + File.separator + ".plugins" + File.separator + 
				"de.objectcode.time4u.client.store" + File.separator + "time4u";
    	
    	if (props==null) {
	    	db.setDbDriver(defaultDriver);
	    	db.setDbCredentials("SA", "");
	    	db.setDbUrl(defaultDbUrl);
    	} else {
    		db.setDbDriver(props.getProperty("dbDriver", defaultDriver));
    		db.setDbCredentials(props.getProperty("dbUser", "SA"), props.getProperty("dbPass", ""));
    		db.setDbUrl(props.getProperty("dbUrl", defaultDbUrl));
    	}
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    	
    }

    @After
    public void tearDown() {
    }



    /**
     * Test of getDayInfos method, of class DBClient.
     */
    @Test
    public void testGetDayInfos() {
        System.out.println("getDayInfos");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 18);
        cal.set(Calendar.MONTH, Calendar.NOVEMBER);
        Date from = new java.sql.Date(cal.getTimeInMillis());
        Date to = new java.sql.Date(java.util.Calendar.getInstance().getTimeInMillis());
        List<T4uDayinfos> expResult = null;
        List<T4uDayinfos> result = db.getDayInfos(from, to);
        T4uDayinfos day;
        for (Iterator<T4uDayinfos> it = result.iterator(); it.hasNext(); ) {
            day = it.next();
            System.out.println(day.getDaydate() + " : " + day.getId());
        }
    }

    @Test
    public void testGetRootProjects() {
    	List<T4uProjects> sProjects = db.getRootProjects();
    	System.out.println(sProjects.size() + " root projects in database");
    	for (T4uProjects p : sProjects) {
    		rootProjects.add(p.getId());
    	}
    	
    }
    
    @Test
    public void testGetAllProjects() {
    	List<T4uProjects> sProjects = db.getProjects();
    	System.out.println(sProjects.size() + " projects total in database");
    	// Set static project to test worked time
    	for (T4uProjects p : sProjects) {
    		if (p.getParentKey()!=null && p.getParentKey().contains(":") && rootProjects.contains(p.getParentId().getId()) && p.getActive() && !p.getDeleted()) {
    			testProject=p.getParentId().getId();
    			System.out.println("Testing on project " + p.getParentId().getName());
    			break;
    		}
    	}

    }

    /**
     * Test of getTimeReport method, of class DBClient.
     */
    @Test
    public void testGetSubProjects() {
        System.out.println("getSubProjects");
        String projectId = testProject;
        List<T4uProjects> sProjects = db.getSubProjects(projectId);
        T4uProjects project;
        for (Iterator<T4uProjects> it = sProjects.iterator(); it.hasNext();) {
            project = it.next();
            System.out.println(project.getName() + " : " + project.getId());
        }
    }
    
    
    /**
     * Test of getWorkedTimeForProject method, of class DBClient.
     */
    @Test
    public void testGetWorkedTimeForProject() {
        System.out.println("getWorkedTimeForProject");
        
        String projectId = testProject;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, Calendar.NOVEMBER);
        Date since = new java.sql.Date(cal.getTimeInMillis());
        Date until = new java.sql.Date(Calendar.getInstance().getTimeInMillis()); // Now
        long expResult = 0L;
        long result = db.getWorkedTimeForProject(projectId, since, until);
        System.out.println(result + " seconds.");

    }

    @Test
    public void testGetPersons() {
        System.out.println("getPersons");
        List<T4uPersons> persons = db.getPersons();
        T4uPersons person;
        for (Iterator<T4uPersons> it = persons.iterator(); it.hasNext(); ) {
            person = it.next();
            System.out.println(person.getGivenName() + " " + person.getSurname());
        }
    }

    @Test
    public void testUnbilled() {

    	java.sql.Date from;
    	java.sql.Date until;
    	
    	Calendar cal = Calendar.getInstance();
    	until = new java.sql.Date(cal.getTimeInMillis());
    	cal.add(Calendar.YEAR, -10);	// Ten years
    	from = new java.sql.Date(cal.getTimeInMillis());
    	
    	List<WorkSummary> result = db.getUnbilledWorkSummaryForAllProjects(from, until);
    	for (WorkSummary s : result) {
    		System.out.println(s.toString());
    	}
    }

    @Test
    public void testGetWorkSummaryForProject() {
    	
    	String project = null;
    	String person = null;
    	String task = null;
    	
    	java.sql.Date from;
    	java.sql.Date until;
    	
    	Calendar cal = Calendar.getInstance();
    	until = new java.sql.Date(cal.getTimeInMillis());
    	cal.add(Calendar.YEAR, -1);	// One year
    	from = new java.sql.Date(cal.getTimeInMillis());
    	
    	List<WorkSummary> result = db.getWorkSummaryForProject(
    			project,	// All projects 
    			person, 	// All persons
    			task, 		// All tasks
    			from, 		// From
    			until, 		// Util
    			false		// Don't show billed
    			);

    	 for (WorkSummary s : result) {
    		 System.out.println(s.toString());
    	 }
    	
    }
    
    @Test
    public void testGetLimitTimeForProject() {

    	java.sql.Date from;
    	java.sql.Date until;
    	
    	Calendar cal = Calendar.getInstance();
    	until = new java.sql.Date(cal.getTimeInMillis());
    	cal.add(Calendar.YEAR, -10);	// Ten years
    	from = new java.sql.Date(cal.getTimeInMillis());
    	
    	java.sql.Date firstDate = db.getLimitTimeForProject(testProject, null, null, from, until, true, true);    	
    	java.sql.Date lastDate = db.getLimitTimeForProject(testProject, null, null, from, until, false, true);
    	
    	System.out.println("First date: " + firstDate + " Last date: " + lastDate);
    	
    }
    
}