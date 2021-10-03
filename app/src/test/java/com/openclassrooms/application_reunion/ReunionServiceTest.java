package com.openclassrooms.application_reunion;

import com.openclassrooms.application_reunion.DI.DI;
import com.openclassrooms.application_reunion.model.Reunion;
import com.openclassrooms.application_reunion.service.DummyReunionGenerator;
import com.openclassrooms.application_reunion.service.ReunionApiService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;

@RunWith(JUnit4.class)
public class ReunionServiceTest {

    private ReunionApiService service;

        @Before
        public void setup() {
            service = DI.getNewInstanceApiService();
        }

        @Test
        public void getReunionsWithSuccess() {
            List<Reunion> reunions = service.getReunions();
            List<Reunion> expectedReunions = DummyReunionGenerator.DUMMY_REUNIONS;
            assertThat(reunions, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedReunions.toArray()));
        }

        @Test
        public void deleteReunionWithSuccess() {
            Reunion reunionToDelete = service.getReunions().get(0);
            service.deleteReunion(reunionToDelete);
            assertFalse(service.getReunions().contains(reunionToDelete));
        }

        @Test
        public void addReunionWithSuccess(){
            Reunion reunionToAdd = new Reunion(1,"14h00","salle 3","developement", Arrays.asList("particip1","particip2","particip3"));
            service.createReunion(reunionToAdd);
            List<Reunion> reunions = service.getReunions();
            assertThat(reunions, IsIterableContainingInAnyOrder.containsInAnyOrder(reunionToAdd));
        }

        @Test
        public void filterreunionWithSuccess(){
            Reunion filteredReunion = service.getReunions().get(0);
            service.addToFilteredlist(filteredReunion);
            List<Reunion> filteredReunions = service.getFilteredReunions();
            assertThat(filteredReunions, IsIterableContainingInAnyOrder.containsInAnyOrder(filteredReunion));
        }

}
