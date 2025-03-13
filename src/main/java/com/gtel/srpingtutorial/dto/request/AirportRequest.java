package com.gtel.srpingtutorial.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AirportRequest {
     String iata;
     String name;
     String airportGroupCode;
     String language;
     Integer priority;
}
