package com.openclassrooms.application_reunion.service;

import com.openclassrooms.application_reunion.model.Reunion;
import java.util.List;

public interface ReunionApiService {

    List<Reunion> getReunions();

    void deleteReunion(Reunion reunion);

    void createReunion(Reunion reunion);

    void addToFilteredlist(Reunion reunion);

    void deleteFromFilteredlist(Reunion reunion);

    void deleteAllFromFilteredlist();

    List<Reunion> getFilteredReunions();

}

