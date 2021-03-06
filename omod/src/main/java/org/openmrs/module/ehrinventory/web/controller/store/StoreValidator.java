/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.ehrinventory.web.controller.store;

import org.apache.commons.lang.StringUtils;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.model.InventoryStore;
import org.openmrs.module.ehrinventory.InventoryService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class StoreValidator implements Validator {

	/**
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz) {
    	return InventoryStore.class.equals(clazz);
    }

	/**
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object command, Errors error) {
    	InventoryStore store = (InventoryStore) command;
    	
   	if( StringUtils.isBlank(store.getName())){
    		error.reject("ehrinventory.store.name.required");
    	}
    	if(store.getRole() == null){
    		error.reject("ehrinventory.store.role.required");
    	}
    	/*if( StringUtils.isBlank(store.getCode())){
    		error.reject("inventory.store.code.required");
    	}*/
    	InventoryService inventoryService = (InventoryService) Context.getService(InventoryService.class);
    	 
    	InventoryStore storeE = inventoryService.getStoreByName(store.getName());
      
       if(store.getId() != null){
    		if(storeE != null && storeE.getId().intValue() != store.getId().intValue()){
    			
    			error.reject("ehrinventory.store.name.existed");
    		}
    	}else{
    		if(storeE != null){
    	    		error.reject("ehrinventory.store.name.existed");
    		}
    	}
    	
    	
    }

}