package com.SprintTwo.phaseTwo.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DriverRequest {
    protected String source;
    protected double price;
    protected Driver driver;
}
