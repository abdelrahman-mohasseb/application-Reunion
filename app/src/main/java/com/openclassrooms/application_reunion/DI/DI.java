package com.openclassrooms.application_reunion.DI;


import com.openclassrooms.application_reunion.service.DummyReunionService;
import com.openclassrooms.application_reunion.service.ReunionApiService;

public class DI {

        private static ReunionApiService service = new DummyReunionService();


        public static ReunionApiService getReunionApiService() {
            return service;
        }


        public static ReunionApiService getNewInstanceApiService() {
            return new  DummyReunionService();
        }
    }
}
