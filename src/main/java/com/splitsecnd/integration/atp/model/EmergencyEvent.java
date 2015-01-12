package com.splitsecnd.integration.atp.model;

public class EmergencyEvent {
	
	public class Event {
		public class Caller {
			private String CallbackPhoneNumber;
			private String FirstName;
			private String LastName;
			public String getCallbackPhoneNumber() {
				return CallbackPhoneNumber;
			}
			public void setCallbackPhoneNumber(String callbackPhoneNumber) {
				CallbackPhoneNumber = callbackPhoneNumber;
			}
			public String getFirstName() {
				return FirstName;
			}
			public void setFirstName(String firstName) {
				FirstName = firstName;
			}
			public String getLastName() {
				return LastName;
			}
			public void setLastName(String lastName) {
				LastName = lastName;
			}
		}
		public class Location {
			public class Position {
				private Double Latitude;
				private Double Longitude;
				public Double getLatitude() {
					return Latitude;
				}
				public void setLatitude(Double latitude) {
					Latitude = latitude;
				}
				public Double getLongitude() {
					return Longitude;
				}
				public void setLongitude(Double longitude) {
					Longitude = longitude;
				}
			}
			private String CreationTimestamp;
			private Position Position = new Position();
			public String getCreationTimestamp() {
				return CreationTimestamp;
			}
			public void setCreationTimestamp(String creationTimestamp) {
				CreationTimestamp = creationTimestamp;
			}
			public Position getPosition() {
				return Position;
			}
			public void setPosition(Position position) {
				Position = position;
			}
		}
		public class Vehicle {
			public class VIN {
				private String Vds;
				private String Vis;
				private String Wmi;
				public String getVds() {
					return Vds;
				}
				public void setVds(String vds) {
					Vds = vds;
				}
				public String getVis() {
					return Vis;
				}
				public void setVis(String vis) {
					Vis = vis;
				}
				public String getWmi() {
					return Wmi;
				}
				public void setWmi(String wmi) {
					Wmi = wmi;
				}
			}
			private String Color;
			private String LicencePlate;
			private VIN Vin = new VIN();
			public String getColor() {
				return Color;
			}
			public void setColor(String color) {
				Color = color;
			}
			public String getLicencePlate() {
				return LicencePlate;
			}
			public void setLicencePlate(String licencePlate) {
				LicencePlate = licencePlate;
			}
			public VIN getVin() {
				return Vin;
			}
			public void setVin(VIN vin) {
				Vin = vin;
			}
		}
		private String ActivationMethod;
		private String CallerId;
		private Caller Caller = new Caller();
		private Location Location = new Location();
		private Vehicle Vehicle = new Vehicle();
		public String getActivationMethod() {
			return ActivationMethod;
		}
		public void setActivationMethod(String activationMethod) {
			ActivationMethod = activationMethod;
		}
		public String getCallerId() {
			return CallerId;
		}
		public void setCallerId(String callerId) {
			CallerId = callerId;
		}
		public Caller getCaller() {
			return Caller;
		}
		public void setCaller(Caller caller) {
			Caller = caller;
		}
		public Location getLocation() {
			return Location;
		}
		public void setLocation(Location location) {
			Location = location;
		}
		public Vehicle getVehicle() {
			return Vehicle;
		}
		public void setVehicle(Vehicle vehicle) {
			Vehicle = vehicle;
		}
	}
	
	public class Service {
		private String AssistanceType;
		private String BrandCode = "";
		private String ProjectCode;
		private String ServiceIdentifier;
		public String getAssistanceType() {
			return AssistanceType;
		}
		public void setAssistanceType(String assistanceType) {
			AssistanceType = assistanceType;
		}
		public String getBrandCode() {
			return BrandCode;
		}
		public void setBrandCode(String brandCode) {
			BrandCode = brandCode;
		}
		public String getProjectCode() {
			return ProjectCode;
		}
		public void setProjectCode(String projectCode) {
			ProjectCode = projectCode;
		}
		public String getServiceIdentifier() {
			return ServiceIdentifier;
		}
		public void setServiceIdentifier(String serviceIdentifier) {
			ServiceIdentifier = serviceIdentifier;
		}
	}
	
	
	private Event Event = new Event();
	private Service Service = new Service();
	public Event getEvent() {
		return Event;
	}
	public void setEvent(Event event) {
		this.Event = event;
	}
	public Service getService() {
		return Service;
	}
	public void setService(Service service) {
		this.Service = service;
	}
}
