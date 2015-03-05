
package com.splitsecnd.integration.atp.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "CallbackPhoneNumber",
    "Email",
    "FirstName",
    "Gender",
    "Language",
    "LastName",
    "MemberId"
})
public class Caller {

    @JsonProperty("CallbackPhoneNumber")
    private String CallbackPhoneNumber;
    @JsonProperty("Email")
    private String Email;
    @JsonProperty("FirstName")
    private String FirstName;
    @JsonProperty("Gender")
    private String Gender;
    @JsonProperty("Language")
    private String Language;
    @JsonProperty("LastName")
    private String LastName;
    @JsonProperty("MemberId")
    private String MemberId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The CallbackPhoneNumber
     */
    @JsonProperty("CallbackPhoneNumber")
    public String getCallbackPhoneNumber() {
        return CallbackPhoneNumber;
    }

    /**
     * 
     * @param CallbackPhoneNumber
     *     The CallbackPhoneNumber
     */
    @JsonProperty("CallbackPhoneNumber")
    public void setCallbackPhoneNumber(String CallbackPhoneNumber) {
        this.CallbackPhoneNumber = CallbackPhoneNumber;
    }

    /**
     * 
     * @return
     *     The Email
     */
    @JsonProperty("Email")
    public String getEmail() {
        return Email;
    }

    /**
     * 
     * @param Email
     *     The Email
     */
    @JsonProperty("Email")
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * 
     * @return
     *     The FirstName
     */
    @JsonProperty("FirstName")
    public String getFirstName() {
        return FirstName;
    }

    /**
     * 
     * @param FirstName
     *     The FirstName
     */
    @JsonProperty("FirstName")
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    /**
     * 
     * @return
     *     The Gender
     */
    @JsonProperty("Gender")
    public String getGender() {
        return Gender;
    }

    /**
     * 
     * @param Gender
     *     The Gender
     */
    @JsonProperty("Gender")
    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    /**
     * 
     * @return
     *     The Language
     */
    @JsonProperty("Language")
    public String getLanguage() {
        return Language;
    }

    /**
     * 
     * @param Language
     *     The Language
     */
    @JsonProperty("Language")
    public void setLanguage(String Language) {
        this.Language = Language;
    }

    /**
     * 
     * @return
     *     The LastName
     */
    @JsonProperty("LastName")
    public String getLastName() {
        return LastName;
    }

    /**
     * 
     * @param LastName
     *     The LastName
     */
    @JsonProperty("LastName")
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    /**
     * 
     * @return
     *     The MemberId
     */
    @JsonProperty("MemberId")
    public String getMemberId() {
        return MemberId;
    }

    /**
     * 
     * @param MemberId
     *     The MemberId
     */
    @JsonProperty("MemberId")
    public void setMemberId(String MemberId) {
        this.MemberId = MemberId;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	public static Caller getInstance() {
		return new Caller();
	}

}
