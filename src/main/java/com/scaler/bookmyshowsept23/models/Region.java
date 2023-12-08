package com.scaler.bookmyshowsept23.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Region extends BaseModel{//city
    private String name;
    // private List<Theatre> theatres; /// depends on the access pattern
}
