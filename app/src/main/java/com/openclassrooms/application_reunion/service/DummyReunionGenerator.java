package com.openclassrooms.application_reunion.service;

import com.openclassrooms.application_reunion.model.Reunion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class DummyReunionGenerator {

    public static List<Reunion> DUMMY_REUNIONS = Arrays.asList(
                new Reunion(1, "14h00", "salle 1", "Developement Durable",Arrays.asList("sup1","sup2","sup3"))
                        , new Reunion(2, "15h00", "salle 2", "Biologie",Arrays.asList("mec1","mec2","mec3"))
        );

        static List<Reunion> generateNeighbours() {
            return new ArrayList<>(DUMMY_REUNIONS);
        }
    }

