
package com.demo.subbuecsoft_task.Response;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Response {

    @SerializedName("category")
    private String mCategory;
    @SerializedName("created_by")
    private Long mCreatedBy;
    @SerializedName("created_date")
    private String mCreatedDate;
    @SerializedName("id")
    private String mId;
    @SerializedName("key")
    private String mKey;
    @SerializedName("language_code")
    private String mLanguageCode;
    @SerializedName("last_updated_date")
    private String mLastUpdatedDate;
    @SerializedName("updated_by")
    private Long mUpdatedBy;
    @SerializedName("value")
    private String mValue;

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public Long getCreatedBy() {
        return mCreatedBy;
    }

    public void setCreatedBy(Long createdBy) {
        mCreatedBy = createdBy;
    }

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        mCreatedDate = createdDate;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getLanguageCode() {
        return mLanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        mLanguageCode = languageCode;
    }

    public String getLastUpdatedDate() {
        return mLastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        mLastUpdatedDate = lastUpdatedDate;
    }

    public Long getUpdatedBy() {
        return mUpdatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        mUpdatedBy = updatedBy;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

}
