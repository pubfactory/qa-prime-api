package com.prime.pojo.searchResults;

import com.google.gson.annotations.SerializedName;

   
public class SortOrder {

   @SerializedName("id")
   String id;

   @SerializedName("fieldId")
   String fieldId;

   @SerializedName("fieldName")
   String fieldName;

   @SerializedName("ascending")
   boolean ascending;

   @SerializedName("supportsJumpTo")
   boolean supportsJumpTo;


    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    
    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }
    public String getFieldId() {
        return fieldId;
    }
    
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldName() {
        return fieldName;
    }
    
    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }
    public boolean getAscending() {
        return ascending;
    }
    
    public void setSupportsJumpTo(boolean supportsJumpTo) {
        this.supportsJumpTo = supportsJumpTo;
    }
    public boolean getSupportsJumpTo() {
        return supportsJumpTo;
    }
    
}