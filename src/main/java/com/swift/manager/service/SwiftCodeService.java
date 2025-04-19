package com.swift.manager.service;

import com.swift.manager.dto.request.SwiftCodeRequestDto;
import com.swift.manager.dto.response.SwiftCodeResponseDto;
import com.swift.manager.dto.response.CountrySwiftCodesResponse;
import java.util.List;

public interface SwiftCodeService {
    Object getSwiftCodeDetails(String swiftCode);
    CountrySwiftCodesResponse findByCountry(String countryISO2);
    String addSwiftCode(SwiftCodeRequestDto request);
    String deleteSwiftCode(String swiftCode);
    List<SwiftCodeResponseDto> findBranches(String headquarterCode);
}