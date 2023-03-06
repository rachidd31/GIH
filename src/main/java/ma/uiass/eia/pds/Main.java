package ma.uiass.eia.pds;

import ma.uiass.eia.pds.model.Lit.EtatLit;
import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.Lit.LitEquipe;
import ma.uiass.eia.pds.model.Lit.TypeLit;
import ma.uiass.eia.pds.model.departement.Departement;
import ma.uiass.eia.pds.model.departement.NomDepartement;
import ma.uiass.eia.pds.model.dm.Dm;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.espace.chambre.Chambre;
import ma.uiass.eia.pds.model.espace.chambre.TypeChambre;
import ma.uiass.eia.pds.model.espace.salle.Salle;
import ma.uiass.eia.pds.model.espace.salle.TypeSalle;
import ma.uiass.eia.pds.persistance.GetSessionFactory;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.Session;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8081/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in ma.uiass.eia.pds package
        final ResourceConfig rc = new ResourceConfig().packages("ma.uiass.eia.pds.controller");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {

        // Test Hibernate
        Session session = GetSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        // Cardiologie
        Departement cardiologie = new Departement();
        cardiologie.setCapacity(200);
        cardiologie.setNomDepartement(NomDepartement.CARDIOLOGIE);

        // Chambre et Salle
        Espace salle = new Salle(100, cardiologie, TypeSalle.SALLE_EXAMINATION);
        Espace chambre = new Chambre(200, cardiologie, TypeChambre.DOUBLE);

        // LitEquipe
        LitEquipe litEquipe = new LitEquipe();
        litEquipe.setType(TypeLit.ELECTRIC);
        litEquipe.setEtat(EtatLit.DISPONIBLE);

        // Dm
        Dm dm = new Dm();
        dm.setNom("ciseaux");
        dm.setLit(litEquipe);

        // Save entities
        session.save(cardiologie);
        session.save(salle);
        session.save(chambre);
        session.save(litEquipe);
        session.save(dm);

        // Commit and close session
        session.getTransaction().commit();
        session.close();



        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with endpoints available at "
                + "%s%nHit Ctrl-C to stop it...", BASE_URI));
        System.in.read();
        server.shutdownNow();
    }
}

