package com.prime.pojo.searchResults;

import com.google.gson.annotations.SerializedName;

   
public class Filters {

   @SerializedName("id")
   String id;

   @SerializedName("type")
   String type;

   @SerializedName("defaultLabel")
   String defaultLabel;

   @SerializedName("multiValued")
   boolean multiValued;

   @SerializedName("collapsed")
   boolean collapsed;

   @SerializedName("parameterType")
   String parameterType;

   @SerializedName("displayCounts")
   boolean displayCounts;

   @SerializedName("cutoff")
   int cutoff;

   @SerializedName("advancedValues")
   AdvancedValues advancedValues;


    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    
    public void setDefaultLabel(String defaultLabel) {
        this.defaultLabel = defaultLabel;
    }
    public String getDefaultLabel() {
        return defaultLabel;
    }
    
    public void setMultiValued(boolean multiValued) {
        this.multiValued = multiValued;
    }
    public boolean getMultiValued() {
        return multiValued;
    }
    
    public void setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;
    }
    public boolean getCollapsed() {
        return collapsed;
    }
    
    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }
    public String getParameterType() {
        return parameterType;
    }
    
    public void setDisplayCounts(boolean displayCounts) {
        this.displayCounts = displayCounts;
    }
    public boolean getDisplayCounts() {
        return displayCounts;
    }
    
    public void setCutoff(int cutoff) {
        this.cutoff = cutoff;
    }
    public int getCutoff() {
        return cutoff;
    }
    
    public void setAdvancedValues(AdvancedValues advancedValues) {
        this.advancedValues = advancedValues;
    }
    public AdvancedValues getAdvancedValues() {
        return advancedValues;
    }
    
}