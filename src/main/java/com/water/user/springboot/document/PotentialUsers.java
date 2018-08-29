package com.water.user.springboot.document;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "potential_users")
public class PotentialUsers extends Users {
	
	private Boolean isRekkiCleared;

	public Boolean getIsRekkiCleared() {
		return isRekkiCleared;
	}

	public void setIsRekkiCleared(Boolean isRekkiCleared) {
		this.isRekkiCleared = isRekkiCleared;
	}


}
