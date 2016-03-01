package com.splitsecnd.integration.drivensolutions.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DispatchModel", propOrder = {
    "clientMemberId",
    "clientId",
    "password",
    "serviceType",
    "ServiceTypeClass",
    "namePrefix",
    "firstName",
    "middleInitial",
    "lastName",
    "nameSuffix",
    "breakdownDetailLandmark",
    "breakdownStreetNumber",
    "breakdownAddress1",
    "breakdowncity",
    "breakdownstateCode",
    "breakdowncountryCode",
    "breakdownIntersection",
    "preferredPhoneNumber",
    "alternatePhoneNumber",
    "destinationDetailLandmark",
    "destinationStreetNumber",
    "destinationAddress1",
    "destinationCity",
    "destinationProvinceOrState",
    "destinationIntersection",
    "transportNumber",
    "transportComment",
    "memberAtVehicle",
    "memberAwayFromVehicle",
    "finalDestinationContactName",
    "finalDestinationContactNumber"
})
@XmlRootElement(name = "DispatchModel")
public class DispatchModel {

	protected String clientMemberId;
    @XmlElement(required = true)
    protected String clientId;
    @XmlElement(required = true)
	protected String password;
    @XmlElement(required = true)
	protected String serviceType;
	protected String ServiceTypeClass;
	protected String namePrefix;
    @XmlElement(required = true)
	protected String firstName;
	protected String middleInitial;
    @XmlElement(required = true)
	protected String lastName;
	protected String nameSuffix;
	protected String breakdownDetailLandmark;
	protected String breakdownStreetNumber;
    @XmlElement(required = true)
	protected String breakdownAddress1;
    @XmlElement(required = true)
	protected String breakdowncity;
    @XmlElement(required = true)
	protected String breakdownstateCode;
	protected String breakdowncountryCode;
	protected String breakdownIntersection;
    @XmlElement(required = true)
	protected String preferredPhoneNumber;
	protected String alternatePhoneNumber;
	protected String destinationDetailLandmark;
	protected String destinationStreetNumber;
	protected String destinationAddress1;
	protected String destinationCity;
    @XmlElement(required = true)
	protected String destinationProvinceOrState;
	protected String destinationIntersection;
	protected Integer transportNumber;
	protected String transportComment;
	protected String memberAtVehicle;
	protected String memberAwayFromVehicle;
	protected String finalDestinationContactName;
	protected String finalDestinationContactNumber;
	
	public static DispatchModel getInstance(String clientId, String password) {
		DispatchModel model = new DispatchModel();
		model.setClientId(clientId);
		model.setPassword(password);
		
		return model;
	}
	
	public String getClientMemberId() {
		return clientMemberId;
	}
	public void setClientMemberId(String clientMemberId) {
		this.clientMemberId = clientMemberId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getServiceTypeClass() {
		return ServiceTypeClass;
	}
	public void setServiceTypeClass(String serviceTypeClass) {
		ServiceTypeClass = serviceTypeClass;
	}
	public String getNamePrefix() {
		return namePrefix;
	}
	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleInitial() {
		return middleInitial;
	}
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNameSuffix() {
		return nameSuffix;
	}
	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}
	public String getBreakdownDetailLandmark() {
		return breakdownDetailLandmark;
	}
	public void setBreakdownDetailLandmark(String breakdownDetailLandmark) {
		this.breakdownDetailLandmark = breakdownDetailLandmark;
	}
	public String getBreakdownStreetNumber() {
		return breakdownStreetNumber;
	}
	public void setBreakdownStreetNumber(String breakdownStreetNumber) {
		this.breakdownStreetNumber = breakdownStreetNumber;
	}
	public String getBreakdownAddress1() {
		return breakdownAddress1;
	}
	public void setBreakdownAddress1(String breakdownAddress1) {
		this.breakdownAddress1 = breakdownAddress1;
	}
	public String getBreakdowncity() {
		return breakdowncity;
	}
	public void setBreakdowncity(String breakdowncity) {
		this.breakdowncity = breakdowncity;
	}
	public String getBreakdownstateCode() {
		return breakdownstateCode;
	}
	public void setBreakdownstateCode(String breakdownstateCode) {
		this.breakdownstateCode = breakdownstateCode;
	}
	public String getBreakdowncountryCode() {
		return breakdowncountryCode;
	}
	public void setBreakdowncountryCode(String breakdowncountryCode) {
		this.breakdowncountryCode = breakdowncountryCode;
	}
	public String getBreakdownIntersection() {
		return breakdownIntersection;
	}
	public void setBreakdownIntersection(String breakdownIntersection) {
		this.breakdownIntersection = breakdownIntersection;
	}
	public String getPreferredPhoneNumber() {
		return preferredPhoneNumber;
	}
	public void setPreferredPhoneNumber(String preferredPhoneNumber) {
		this.preferredPhoneNumber = preferredPhoneNumber;
	}
	public String getAlternatePhoneNumber() {
		return alternatePhoneNumber;
	}
	public void setAlternatePhoneNumber(String alternatePhoneNumber) {
		this.alternatePhoneNumber = alternatePhoneNumber;
	}
	public String getDestinationDetailLandmark() {
		return destinationDetailLandmark;
	}
	public void setDestinationDetailLandmark(String destinationDetailLandmark) {
		this.destinationDetailLandmark = destinationDetailLandmark;
	}
	public String getDestinationStreetNumber() {
		return destinationStreetNumber;
	}
	public void setDestinationStreetNumber(String destinationStreetNumber) {
		this.destinationStreetNumber = destinationStreetNumber;
	}
	public String getDestinationAddress1() {
		return destinationAddress1;
	}
	public void setDestinationAddress1(String destinationAddress1) {
		this.destinationAddress1 = destinationAddress1;
	}
	public String getDestinationCity() {
		return destinationCity;
	}
	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}
	public String getDestinationProvinceOrState() {
		return destinationProvinceOrState;
	}
	public void setDestinationProvinceOrState(String destinationProvinceOrState) {
		this.destinationProvinceOrState = destinationProvinceOrState;
	}
	public String getDestinationIntersection() {
		return destinationIntersection;
	}
	public void setDestinationIntersection(String destinationIntersection) {
		this.destinationIntersection = destinationIntersection;
	}
	public Integer getTransportNumber() {
		return transportNumber;
	}
	public void setTransportNumber(Integer transportNumber) {
		this.transportNumber = transportNumber;
	}
	public String getTransportComment() {
		return transportComment;
	}
	public void setTransportComment(String transportComment) {
		this.transportComment = transportComment;
	}
	public String getMemberAtVehicle() {
		return memberAtVehicle;
	}
	public void setMemberAtVehicle(String memberAtVehicle) {
		this.memberAtVehicle = memberAtVehicle;
	}
	public String getMemberAwayFromVehicle() {
		return memberAwayFromVehicle;
	}
	public void setMemberAwayFromVehicle(String memberAwayFromVehicle) {
		this.memberAwayFromVehicle = memberAwayFromVehicle;
	}
	public String getFinalDestinationContactName() {
		return finalDestinationContactName;
	}
	public void setFinalDestinationContactName(String finalDestinationContactName) {
		this.finalDestinationContactName = finalDestinationContactName;
	}
	public String getFinalDestinationContactNumber() {
		return finalDestinationContactNumber;
	}
	public void setFinalDestinationContactNumber(
			String finalDestinationContactNumber) {
		this.finalDestinationContactNumber = finalDestinationContactNumber;
	}
	
}
