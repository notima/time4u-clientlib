package se.notima.time4u;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {

		IDBClient service = new DBClient();
		
		context.registerService(IDBClient.class.getName(), service, null);
		System.out.println("IDBClient service registered");
		
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
