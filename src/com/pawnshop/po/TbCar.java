package com.pawnshop.po;

import java.sql.Date;
import java.sql.Timestamp;

public class TbCar {
	private String carId;
	private String brand;
	private String model;
	private Date dateOfManufacture;
	private String color;
	private String drivingLicense;
	private String engineNo;
	private String nakedPrice;
	private String evaluationPrice;
	private String updater;
	private Timestamp updateTime;
	
	@Override
	public String toString() {
		return "TbCar [carId=" + carId + ", brand=" + brand + ", model="
				+ model + ", dateOfManufacture=" + dateOfManufacture
				+ ", color=" + color + ", drivingLicense=" + drivingLicense
				+ ", engineNo=" + engineNo + ", nakedPrice=" + nakedPrice
				+ ", evaluationPrice=" + evaluationPrice + ", updater="
				+ updater + ", updateTime=" + updateTime + "]";
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Date getDateOfManufacture() {
		return dateOfManufacture;
	}

	public void setDateOfManufacture(Date dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public String getNakedPrice() {
		return nakedPrice;
	}

	public void setNakedPrice(String nakedPrice) {
		this.nakedPrice = nakedPrice;
	}

	public String getEvaluationPrice() {
		return evaluationPrice;
	}

	public void setEvaluationPrice(String evaluationPrice) {
		this.evaluationPrice = evaluationPrice;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
}
