package org.example;

import org.example.entities.Client;
import org.example.entities.Planet;
import org.example.services.ClientCrudService;
import org.example.services.PlanetCrudService;

public class Main {
    public static void main(String[] args) {
        ClientCrudService clientCrudService = new ClientCrudService();
        clientCrudService.createClient(new Client("Petro"));
        System.out.println(clientCrudService.readClient(2L));
        clientCrudService.updateClient(14L, "Vlad");
        clientCrudService.deleteClient(17L);
        clientCrudService.getAllClients().forEach(System.out::println);

        PlanetCrudService planetCrudService = new PlanetCrudService();
        planetCrudService.createPlanet(new Planet("VEN", "Venus"));
        System.out.println(planetCrudService.readPlanet("VEN"));
        planetCrudService.updatePlanet("VEN", "Luna");
        planetCrudService.deletePlanet("VEN");
        planetCrudService.getAllPlanets().forEach(System.out::println);
    }
}
