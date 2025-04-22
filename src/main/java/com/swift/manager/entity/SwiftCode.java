package com.swift.manager.entity;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(name = "swift_codes",
    indexes = {
        @Index(name = "idx_headquarter_code", columnList = "headquarterCode"),
        @Index(name = "idx_country_iso2", columnList = "countryiso2"),
        @Index(name = "idx_swift_code", columnList = "swiftCode")
    }
)
@Cacheable
public class SwiftCode {
    @Id
    @Column(length = 11)
    private String swiftCode;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String bankName;

    @Column(name = "countryiso2", length = 2, nullable = false)
    private String countryISO2;

    @Column(nullable = false)
    private String countryName;

    @Column(nullable = false)
    private boolean headquarter;

    @Column(length = 8, nullable = false)
    private String headquarterCode;

    public SwiftCode() {}

    public String getSwiftCode() { return swiftCode; }
    public void setSwiftCode(String swiftCode) { this.swiftCode = swiftCode; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }
    public String getCountryISO2() { return countryISO2; }
    public void setCountryISO2(String countryISO2) { this.countryISO2 = countryISO2; }
    public String getCountryName() { return countryName; }
    public void setCountryName(String countryName) { this.countryName = countryName; }
    public boolean isHeadquarter() { return headquarter; }
    public void setHeadquarter(boolean headquarter) { this.headquarter = headquarter; }
    public String getHeadquarterCode() { return headquarterCode; }
    public void setHeadquarterCode(String headquarterCode) { this.headquarterCode = headquarterCode; }
}