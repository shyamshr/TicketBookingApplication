package com.scaler.bookmyshowsept23.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel{
    private String name;
    @ManyToOne
    private Region region;

    @OneToMany
    private List<Screen> screens;

    /*
        1   -  1
    Theatre - Region
        m   -   1


        1   -  m
    Theatre - Screen
        1   -   1
     */

}
