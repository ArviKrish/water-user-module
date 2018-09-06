package com.water.user.springboot.document;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class NotificationData {

    @SerializedName("body")
    private String detail;

    @SerializedName("title")
    private String title;
    
    @SerializedName("channel_id")
    private String channel_id;

    @SerializedName("timestamp")
    private String timestamp;

    public String getChannel_id() {
		return channel_id;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	
}