package com.scaler.bookmyshowsept23.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "shows") // show is a reserved keyword in mysql
public class Show extends BaseModel{
    @ManyToOne
    private Movie movie;

    private Date startTime;
    private Date endTime;

    @ManyToOne
    private Screen screen;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;
     /*
        1   -  1
      Show - Movie
         m   -  1

        1   -  1
      Show - Screen
         m   -   1
     */

}
