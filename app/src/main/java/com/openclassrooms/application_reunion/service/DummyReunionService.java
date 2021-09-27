package com.openclassrooms.application_reunion.service;

import com.openclassrooms.application_reunion.model.Reunion;

import java.util.ArrayList;
import java.util.List;

    public class DummyReunionService implements  ReunionApiService{

        private List<Reunion> reunions = DummyReunionGenerator.generateReunions();
        private List<Reunion> filteredReunions = new ArrayList<>();

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

        @Override
        public void addToFilteredlist(Reunion reunion) {
            filteredReunions.add(reunion);
        }

        @Override
        public void deleteFromFilteredlist(Reunion reunion) { filteredReunions.remove(reunion);
        }
        @Override
        public void deleteAllFromFilteredlist() {
            filteredReunions = new ArrayList<>();
        }

        @Override
        public List<Reunion> getFilteredReunions() {
            return filteredReunions;
        }
    }



