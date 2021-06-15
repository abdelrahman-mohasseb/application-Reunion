package com.openclassrooms.application_reunion.service;

import com.openclassrooms.application_reunion.model.Reunion;
import java.util.List;

public interface ReunionApiService {

    List<Reunion> getReunions();

    void deleteNeighbour(Reunion reunion);

    void createNeighbour(Reunion reunion);

}

