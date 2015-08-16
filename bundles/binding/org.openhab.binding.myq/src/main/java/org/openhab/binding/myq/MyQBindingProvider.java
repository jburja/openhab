/**
 * Copyright (c) 2010-2015, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.myq;

import org.openhab.binding.myq.internal.MyQBindingConfig;
import org.openhab.core.binding.BindingProvider;

/**
 * @author Jeff
 * @since 1.8.0
 */
public interface MyQBindingProvider extends BindingProvider {
	
	/**
	 * Get configuration by item name 
	 * 
	 * @param itemName The name of the item
	 * @return The binding config for this item 
	 */
	public MyQBindingConfig getConfig(String itemName);
	
	/**
	 * Get configuration by instance and property
	 * 
	 * @param instance Name of the Denon receiver instance
	 * @param property Name of the property
	 * @return The binding config for this item 
	 */
	public MyQBindingConfig getConfig(String instance, String property);

}
