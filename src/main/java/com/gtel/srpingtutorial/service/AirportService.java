package com.gtel.srpingtutorial.service;

import com.gtel.srpingtutorial.dto.request.AirportRequest;
import com.gtel.srpingtutorial.dto.response.AirportResponse;
import com.gtel.srpingtutorial.entity.Airport;
import com.gtel.srpingtutorial.exception.AppException;
import com.gtel.srpingtutorial.exception.ErrorCode;
import com.gtel.srpingtutorial.mapper.AirportMapper;
import com.gtel.srpingtutorial.repository.AirportRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AirportService {

    AirportRepository airportRepository;
    AirportMapper airportMapper;

//    public List<AirportResponse> getAirports() {
//        return airportRepository.findAll().stream()
//                .map(airportMapper::toAirportResponse).toList();
//    }
    public List<AirportResponse> getAirports(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("iata").ascending());
        Page<Airport> airportPage = airportRepository.findAll(pageable);

        return airportPage.stream()
                .map(airportMapper::toAirportResponse)
                .toList();
    }

    public int countAirports() {
        return (int) airportRepository.count();
    }

    public AirportResponse getAirport(String iata) {

        return airportMapper.toAirportResponse(airportRepository.findById(iata)
                .orElseThrow(()->new AppException(ErrorCode.AIRPORT_NOT_EXISTED)));
    }

    public AirportResponse createAirport(AirportRequest airportRequest) {
        if (airportRepository.existsByIata(airportRequest.getIata()))
            throw new AppException(ErrorCode.AIRPORT_EXISTED);
        Airport airport = airportMapper.toAirport(airportRequest);
        return airportMapper.toAirportResponse(airportRepository.save(airport));
    }


    public AirportResponse updateAirports(String iata, AirportRequest airportRequest) {

        Airport airport = airportRepository.findById(iata)
                .orElseThrow(()-> new AppException(ErrorCode.AIRPORT_NOT_EXISTED));
        airportMapper.updateAirport(airport,airportRequest);
        return airportMapper.toAirportResponse(airportRepository.save(airport));
    }

    public AirportResponse updatePathAirports(String iata, AirportRequest airportRequest) {
//        Optional<Airport> airport = airportRepository.findById(iata);
//        if (airport.isPresent()){
//            Airport ap = airport.get();
//            airportMapper.updatePatchAirport(ap,airportRequest);
//            return airportMapper.toAirportResponse(airportRepository.save(ap));
//        }else {
//            throw new RuntimeException("Airport not found");
//        }
        Airport airport = airportRepository.findById(iata)
                .orElseThrow(()-> new AppException(ErrorCode.AIRPORT_NOT_EXISTED));
        airportMapper.updatePatchAirport(airport,airportRequest);
        return airportMapper.toAirportResponse(airportRepository.save(airport));

    }

    public void deleteAirport(String iata) {
        Optional<Airport> airport = airportRepository.findById(iata);
        if (airport.isPresent()){
            airportRepository.deleteById(iata);
        }else{
            throw new AppException(ErrorCode.AIRPORT_NOT_EXISTED);
        }
    }

}
