package com.openclassrooms.application_reunion.service;

import com.openclassrooms.application_reunion.model.Reunion;
import java.util.List;

    public class DummyReunionService implements  ReunionApiService{

        private List<Reunion> reunions = DummyReunionGenerator.generateNeighbours();

        @Override
        public List<Reunion> getReunions() {
            return reunions;
        }

        @Override
        public void deleteReunion(Reunion reunion) {
            reunions.remove(reunion);
        }

        @Override
        public void createReunion(Reunion reunion) {
            reunions.add(reunion);
        }
    }



