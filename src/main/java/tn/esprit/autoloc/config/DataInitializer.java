package tn.esprit.autoloc.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final IVehiculeRepository vehiculeRepository;

    @Override
    public void run(String... args) {
        if (vehiculeRepository.count() > 0) {
            log.info("Vehicules already present, skipping demo data");
            return;
        }

        vehiculeRepository.saveAll(List.of(
                creer("123 TUN 4567", "Renault", "Clio", CategorieVehicule.CITADINE, "80.00", StatutVehicule.DISPONIBLE),
                creer("234 TUN 8910", "Peugeot", "508", CategorieVehicule.BERLINE, "150.00", StatutVehicule.DISPONIBLE),
                creer("345 TUN 1122", "Dacia", "Duster", CategorieVehicule.SUV, "120.00", StatutVehicule.LOUE)
        ));

        log.info("3 demo vehicules inserted");
    }

    private Vehicule creer(String immat, String marque, String modele,
                           CategorieVehicule categorie, String tarif, StatutVehicule statut) {
        Vehicule v = new Vehicule();
        v.setImmatriculation(immat);
        v.setMarque(marque);
        v.setModele(modele);
        v.setCategorie(categorie);
        v.setTarifJournalier(new BigDecimal(tarif));
        v.setStatut(statut);
        return v;
    }
}