/**
 * Copyright (c) 2010-2015, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.myq.internal;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import org.openhab.binding.myq.MyQBindingProvider;
import org.openhab.binding.myq.client.MyQClient;
import org.openhab.binding.myq.client.MyQDeviceClient;
import org.openhab.binding.myq.client.request.GetDeviceAttributeRequest;
import org.openhab.binding.myq.client.request.GetDevicedRequest;
import org.openhab.binding.myq.client.request.LoginRequest;
import org.openhab.binding.myq.client.request.SetDeviceAttributeRequest;
import org.openhab.binding.myq.client.response.GetDeviceAttributeResponse;
import org.openhab.binding.myq.client.response.GetDevicesResponse;
import org.openhab.binding.myq.client.response.LoginResponse;
import org.openhab.binding.myq.client.response.SetDeviceAttributeResponse;
import org.openhab.binding.myq.garagedoor.Device;
import org.openhab.binding.myq.garagedoor.DoorState;
import org.openhab.core.binding.AbstractActiveBinding;
import org.openhab.core.binding.BindingProvider;
import org.openhab.core.items.Item;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.StringType;
import org.openhab.core.types.Command;
import org.openhab.core.types.State;
import org.osgi.framework.BundleContext;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.security.krb5.Config;
	

/**
 * Implement this class if you are going create an actively polling service
 * like querying a Website/Device.
 * 
 * @author Jeff
 * @since 1.8.0
 */

public class MyQBinding extends AbstractActiveBinding<MyQBindingProvider> implements ManagedService {


	private static final Logger logger = LoggerFactory.getLogger(MyQBinding.class);
	
	private MyQConnectionProperties connectionProps = new MyQConnectionProperties();
	
	private static final String CONFIG_HOSTNAME = "hostname";
	private static final String CONFIG_CULTURE = "culture";
	private static final String CONFIG_USERNAME = "username";
	private static final String CONFIG_PASSWORD = "password";
	private static final String CONFIG_SERVICE_PID = "service.pid";
	private static final String CONFIG_REFRESH = "refresh";

	MyQClient client = null;

	/** 
	 * the refresh interval which is used to poll values from the MyQ
	 * server (optional, defaults to 60000ms)
	 */
	private long refreshInterval = 60000;
	
	/**
	 * Called by the SCR to activate the component with its configuration read
	 * from CAS
	 * 
	 * @param bundleContext
	 *            BundleContext of the Bundle that defines this component
	 * @param configuration
	 *            Configuration properties for this component obtained from the
	 *            ConfigAdmin service
	 */
	public void activate(final BundleContext bundleContext,
			final Map<String, Object> config) {
		logger.debug("MyQ binding activated");
		
		logger.debug("Loading configuration");
	
		String hostname = (String) config.get(CONFIG_HOSTNAME);
		logger.debug("Hostname: " + hostname);
			connectionProps.setHostname(hostname);

		String culture = (String)config.get(CONFIG_CULTURE);
		logger.debug("culture: " + culture);
		connectionProps.setCulture(culture);

		String username = (String) config.get(CONFIG_USERNAME);
		logger.debug("username: " + username);
		connectionProps.setUsername(username);

		String password = (String)config.get(CONFIG_PASSWORD);
		logger.debug("password: "+ password);
		connectionProps.setPassword(password);
		
		String refresh = (String)config.get(CONFIG_REFRESH);
		logger.debug("refresh: "+ refresh);
			
			try {
				refreshInterval = Integer.valueOf(refresh);
			} catch (NumberFormatException e) {
				logger.error("No numeric value for configuration refresh property", e);
			}
	
	}
	
	public void deactivate() {
		logger.debug("MyQ binding deactived");
		// TODO: kill security token?
	}	
	
	@Override
	protected void execute() {
		logger.info("Executing the MyQ binding...");
		
		try {
			
			for (MyQBindingProvider provider : providers) {
				Collection<String> items = provider.getItemNames();
				
				for (String itemName: items) {
					logger.debug("Item: " + itemName);
					MyQBindingConfig bindingConfig = provider.getConfig(itemName);
					if(bindingConfig != null) {
						logger.debug("Item: " + bindingConfig.getItemName());
					}
					
				}
			}				
		} finally {
			logger.info("Finished executing MyQ binding...");			
		}
		
	}

	
	@Override
	protected long getRefreshInterval() {
		return refreshInterval;
	}

	@Override
	protected String getName() {
		return "MyQ Refresh Service";
	}

	
	/**
	 * @{inheritDoc}
	 */	
	@Override
	public void bindingChanged(BindingProvider provider, String itemName) {
		logger.debug("MyQ binding changed for item {}", itemName);
		//TODO: update
//		if (provider instanceof MyQBindingProvider)
//		{	
//			MyQBindingConfig config = ((MyQBindingProvider) provider).getConfig(itemName);
//			if (config != null) {
//				getConnector(config).updateStateFromCache(config.getProperty());
//			}
//		}		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void allBindingsChanged(BindingProvider provider) {
		logger.debug("MyQ all bindings changed");
		
		if (provider instanceof MyQBindingProvider) {
			MyQBindingProvider myQProvider = (MyQBindingProvider) provider;
			setInitialState(myQProvider);
		}
	}

	private void setInitialState(MyQBindingProvider provider) {
		logger.debug("MyQ setting initial state");
		
		if(provider == null || provider.getItemNames() == null || provider.getItemNames().isEmpty()) {
			logger.error("Provider is null!");
		}
		
		for (String itemName : provider.getItemNames()) {
			logger.debug("Item Name: " + itemName);
			MyQBindingConfig config = provider.getConfig(itemName);
			logger.debug("Config: " + config.toString());
			if (config != null) {
				setInitialState(config);
			}
		}
	}		

	private void setInitialState(MyQBindingConfig config) {
//	
//		if (client != null) {
//
//			logger.info("MyQ Updating initial state");
//			
//			logger.debug(config.toString());
//			logger.debug(connectionProps.getAppId() + " " + config.getCulture() + " " + 
//					config.getHostname() + " " + config.getPassword() + " " + 
//						connectionProps.getSecurityToken() + " " + config.getUsername());
//					
//			LoginRequest loginReq = new LoginRequest();
//			LoginResponse loginRes = client.login(loginReq);
//			
//			logger.info("MyQ successfully logged in");
//			logger.debug("MyQ security token" + loginRes.getSecurityToken());
//		}
	}

	/**
	 * @{inheritDoc}
	 */
	@Override
	protected void internalReceiveCommand(String itemName, Command command) {
		logger.debug("MyQ binding received command");
		
		MyQBindingConfig config = getConfig(itemName);
		
		String property = config.getProperty();
		String iteName = config.getItemName();
		
		logger.debug("Property name {}", property);
		logger.debug("Item name {}", iteName);
		if(MyQProperty.STATUS.toString().equals(config.getProperty())) {
			updateStatus();
		}

	}
		
	private void updateStatus() {
		logger.debug("Updating status");
		LoginRequest loginReq = new LoginRequest();
		loginReq.setCulture(connectionProps.getCulture());
		loginReq.setPassword(connectionProps.getPassword());
		loginReq.setUsername(connectionProps.getUsername());
		LoginResponse loginRes = client.login(loginReq);
		
		GetDevicedRequest getDevicesReq = new GetDevicedRequest();
		GetDevicesResponse getDevicesRes = client.getDevices(getDevicesReq);

		logger.debug(getDevicesRes.getDevices().get(0).getDeviceName());
	}

	/**
	 * @{inheritDoc}
	 */
	@Override
	public void updated(Dictionary<String, ?> config) throws ConfigurationException {
		boolean isActiveBinding = false;
		
		logger.info("MyQ binding updated");
		
		if (config != null) {
			logger.debug("Config is not null");
			
			setProperlyConfigured(false);
			
			// TODO: update to query to see if connection is still alive, otherwise login again to get new security token
			if(client == null) {
				logger.debug("Client is null");
				
				connectionProps = new MyQConnectionProperties();
				
				Enumeration<String> keys = config.keys();
				
				while (keys.hasMoreElements()) {
					logger.debug("Iterating through keys");
					String key = keys.nextElement();
					
					logger.debug("Key: " + key);
					
					if (CONFIG_SERVICE_PID.equals(key)) 
						continue;
	
					if (CONFIG_HOSTNAME.equals(key)) {
						String hostname = (String)config.get(key);
						logger.debug("Hostname: " + hostname);
						connectionProps.setHostname(hostname);
					} else if (CONFIG_CULTURE.equals(key)) {
						String culture = (String)config.get(key);
						logger.debug("culture: " + culture);
						connectionProps.setCulture(culture);
					} else if (CONFIG_USERNAME.equals(key)) {
						String username = (String)config.get(key);
						logger.debug("username: " + username);
						connectionProps.setUsername(username);
					} else if (CONFIG_PASSWORD.equals(key)) {
						String password = (String)config.get(key);
						logger.debug("password: "+ password);
						connectionProps.setPassword(password);
					} if (CONFIG_REFRESH.equals(key)) {
						String refresh = (String)config.get(key);
						logger.debug("refresh: "+ refresh);
						
						try {
							refreshInterval = Integer.valueOf(refresh);
						} catch (NumberFormatException e) {
							logger.error("No numeric value for configuration refresh property", e);
						}
					}
				}

			}
			
			client = new MyQDeviceClient(connectionProps);
			
			LoginRequest loginReq = new LoginRequest();
			LoginResponse loginRes = client.login(loginReq);
			
			logger.debug("Got login response");

			if(loginRes.getSecurityToken() != null && !loginRes.getSecurityToken().equals("null")){
				logger.debug("Successfully logged in");
				isActiveBinding = true;
			}
			
			logger.debug("Getting devices");
			GetDevicedRequest getDevicesReq = new GetDevicedRequest();
			GetDevicesResponse getDevicesRes = client.getDevices(getDevicesReq);
			
			if(getDevicesRes != null) {
				List<Device> devices = getDevicesRes.getDevices();
			
				for (Device device : devices) {
					logger.debug("Device name:" + device.getMyQDeviceTypeName());
				}
			}
			
			logger.debug("Getting device attributes");
			GetDeviceAttributeRequest getDeviceReq = new GetDeviceAttributeRequest();
			getDeviceReq.setName("doorstate");
			GetDeviceAttributeResponse getDeviceRes = client.getDeviceAttributes(getDeviceReq);
			
			logger.debug("Door state:" + getDeviceRes.getAttributeValue());
			SetDeviceAttributeRequest setDeviceReq = new SetDeviceAttributeRequest();
			setDeviceReq.setAttributeName("desireddoorstate");
			setDeviceReq.setAttributeValue(DoorState.CLOSED.getStateCode());
			SetDeviceAttributeResponse setDeviceRes = client.setDeviceStatus(setDeviceReq);
			
			setProperlyConfigured(isActiveBinding);
		}
	}
	
	private void setItemValue(Item item, boolean value) {
			eventPublisher.postUpdate(item.getName(), value ? OnOffType.ON : OnOffType.OFF);
	}

	private void setItemValue(Item item, String value) {
			eventPublisher.postUpdate(item.getName(), new StringType(value));
	}
	
	private void setItemValue(Item item, Long value) {
			eventPublisher.postUpdate(item.getName(), new DecimalType(value));					
	}


	private void processPropertyUpdated(String instance, String property, State state) {
		updateIfChanged(instance, property, state);
		
//		if (property.startsWith(SWITCH_INPUT)) {
//			updateInputProperties(instance, property);
//		}
	}

	/**
	 * Update all the different input properties (=properties that start with SI). 
	 * This way, only the currently selected input has state 'ON'.  
	 */
	private void updateInputProperties(String instance, String property) {
//		for (MyQBindingProvider provider : providers) {
//			for (String itemName : provider.getItemNames()) {
//				MyQBindingConfig cfg = provider.getConfig(itemName);
//				if (cfg.getInstance().equals(instance)) {
//					if (cfg.getProperty().startsWith(SWITCH_INPUT) && !cfg.getProperty().equals(property)) {
//						updateIfChanged(cfg.getInstance(), cfg.getProperty(), OnOffType.OFF);
//					}
//				}
//			}
//		}
	}
	
	/**
	 * Only update the property if newState is different than it's current state.   
	 */
	private void updateIfChanged(String instance, String property, State newState) {
//		MyQBindingProvider firstProvider = getFirstMatchingProvider(instance, property);
//		if (firstProvider != null) {
//			MyQBindingConfig config = firstProvider.getConfig(instance, property);
//			try {
//				State oldState = itemRegistry.getItem(config.getItemName()).getState();
//				if (!oldState.equals(newState)) {
//					eventPublisher.postUpdate(config.getItemName(), newState);
//				}
//			} catch (ItemNotFoundException e) {
//				logger.error("Cannot find item " + config.getItemName() + " in the registry", e);
//			}
//		}
	}

	private MyQBindingProvider getFirstMatchingProvider(String instance, String property) {
		for (MyQBindingProvider provider : providers) {
			MyQBindingConfig config = provider.getConfig(instance, property);
			if (config != null)
				return provider;
		}
		return null;
	}
	
	private MyQBindingConfig getConfig(String itemName) {
		for (MyQBindingProvider provider : providers) {
			MyQBindingConfig config = provider.getConfig(itemName);
			 if (config != null)
				 return config;
		}
			 
		return null;
	}
	

	@Override
	public void receiveUpdate(String itemName, State newState) {
		// TODO Auto-generated method stub
		super.receiveUpdate(itemName, newState);
	}
}
