package com.openclassrooms.application_reunion.service;

import com.openclassrooms.application_reunion.model.Reunion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class DummyReunionGenerator {

    public static List<Reunion> DUMMY_REUNIONS = Arrays.asList(
                new Reunion(1, new Date(2021,6,21), "salle 1", "Developement Durable",Arrays.asList("sup1", "sup2", "sup3")
                        )
        );

        static List<Reunion> generateNeighbours() {
            return new ArrayList<>(DUMMY_REUNIONS);
        }
    }

