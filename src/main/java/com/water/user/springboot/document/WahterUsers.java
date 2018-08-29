package com.water.user.springboot.document;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "wahter_users")
public class WahterUsers extends Users {

}
