package com.gtel.srpingtutorial.api;

import com.gtel.srpingtutorial.dto.request.AirportRequest;
import com.gtel.srpingtutorial.dto.response.AirportResponse;
import com.gtel.srpingtutorial.dto.response.ApiResponse;
import com.gtel.srpingtutorial.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping
    public ApiResponse<List<AirportResponse>> getAirports(@RequestParam int page, @RequestParam int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page must be non-negative and size must be greater than zero.");
        }
        return ApiResponse.<List<AirportResponse>>builder()
                .result(airportService.getAirports(page, size))
                .build();
    }

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity countAirports() {
        int count =  airportService.countAirports();

        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(count)).build();
    }


    @GetMapping("/{iata}")
    public ApiResponse<AirportResponse> getAirport(@PathVariable String iata) {
        return ApiResponse.<AirportResponse>builder()
                .result(airportService.getAirport(iata)).build();
    }

    @PostMapping
    public ApiResponse<AirportResponse> createAirport(@RequestBody AirportRequest airportRequest) {
       return ApiResponse.<AirportResponse>builder()
               .result(airportService.createAirport(airportRequest))
               .build();
    }

    @PutMapping("/{iata}")
    public ApiResponse<AirportResponse> updateAirport(@PathVariable String iata, @RequestBody AirportRequest airportRequest) {
        return ApiResponse.<AirportResponse>builder()
                .result(airportService.updateAirports(iata, airportRequest))
                .build();
    }

    @PatchMapping("/{iata}")
    public ApiResponse<AirportResponse> updatePatchAirport(@PathVariable String iata, @RequestBody AirportRequest airportRequest) {
        return ApiResponse.<AirportResponse>builder()
                .result(airportService.updatePathAirports(iata, airportRequest))
                .build();
    }


    @DeleteMapping("/{iata}")
    public void deleteAirport(@PathVariable String iata) {
        airportService.deleteAirport(iata);
    }

//    @GetMapping
//    public ApiResponse<List<AirportResponse>> getAirports() {
//        return ApiResponse.<List<AirportResponse>>builder()
//                .result(airportService.getAirports())
//                .build();
//    }
}
