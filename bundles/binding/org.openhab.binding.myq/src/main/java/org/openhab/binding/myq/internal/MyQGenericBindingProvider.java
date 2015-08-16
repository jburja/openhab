/**
 * Copyright (c) 2010-2015, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.myq.internal;

import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.openhab.binding.myq.MyQBindingProvider;
import org.openhab.core.binding.BindingConfig;
import org.openhab.core.items.Item;
import org.openhab.core.library.items.SwitchItem;
import org.openhab.model.item.binding.AbstractGenericBindingProvider;
import org.openhab.model.item.binding.BindingConfigParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class is responsible for parsing the binding configuration.
 * 
 * @author Jeff
 * @since 1.8.0
 */
public class MyQGenericBindingProvider extends AbstractGenericBindingProvider implements MyQBindingProvider {

	private static final Logger logger = LoggerFactory.getLogger(MyQGenericBindingProvider.class);	
	//private static final Pattern CONFIG_PATTERN = Pattern.compile("^(.)+#(.)+$");
	
	/**
	 * {@inheritDoc}
	 */
	public String getBindingType() {
		return "myq";
	}

	/**
	 * @{inheritDoc}
	 */
	@Override
	public void validateItemType(Item item, String bindingConfig) throws BindingConfigParseException {
		if (!(item instanceof SwitchItem)) {
			throw new BindingConfigParseException("item '" + item.getName()
					+ "' is of type '" + item.getClass().getSimpleName()
					+ "', only Switch- and DimmerItems are allowed - please check your *.items configuration");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void processBindingConfiguration(String context, Item item, String bindingConfig) throws BindingConfigParseException {
		logger.debug("Processing Binding Configuration");
		super.processBindingConfiguration(context, item, bindingConfig);

		bindingConfig = bindingConfig.trim();
		
		//if (CONFIG_PATTERN.matcher(bindingConfig).matches()) {
			MyQBindingConfig config = new MyQBindingConfig();

			config.setItemName(item.getName());
			config.setProperty(bindingConfig);
			
			logger.info("MyQ item {} bound to property {}", config.getItemName(), config.getProperty());
			
			addBindingConfig(item, config);

//		} else {
//			logger.error("Item config {} does not match ", bindingConfig);
//		}
//		
	}

	@Override
	public MyQBindingConfig getConfig(String itemName) {
		return (MyQBindingConfig) bindingConfigs.get(itemName);
	}

	@Override
	public MyQBindingConfig getConfig(String instance, String property) {
		for (Entry<String, BindingConfig> entry : bindingConfigs.entrySet()) {
			MyQBindingConfig config = (MyQBindingConfig) entry.getValue();
			if (config.equals(instance) && config.getProperty().equals(property)) {
				return config;
			}
		}
		return null;
	}

	
}
