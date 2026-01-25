package com.deep_coding15.GesStockApi.stock.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import com.deep_coding15.GesStockApi.catalogue.entity.Produit;
import com.deep_coding15.GesStockApi.security.entity.Role;
import com.deep_coding15.GesStockApi.security.entity.Utilisateur;
import com.deep_coding15.GesStockApi.stock.entity.Stock;
import com.deep_coding15.GesStockApi.stock.entity.StockMouvement;
import com.deep_coding15.GesStockApi.stock.enums.TypeMouvementStockEnum;

@DataJpaTest
class StockMouvementRepositoryTest {

    @Autowired
    private StockMouvementRepository repository;

    @Autowired
    private TestEntityManager em;

    private Stock stock;
    private Produit produit;
    private Utilisateur utilisateur;

    private Produit createProduit() {
        Produit p = new Produit();
        p.setNom("Produit Test");
        p.setPrixUnitaire(BigDecimal.valueOf(100));
        p.setReference("REF-" + UUID.randomUUID());
        p.setActif(true);
        return p;
    }

    @BeforeEach
    void setUp() {

        // ROLE
        Role role = new Role();
        //role.setId(1L);
        role.setCode("ROLE_ADMIN");
        role.setLibelle("ROLE_LIBELLE");
        em.persist(role);

        produit = createProduit();

        em.persist(produit);

        // UTILISATEUR
        utilisateur = new Utilisateur();
        utilisateur.setEmail("test@test.com");
        utilisateur.setUsername("testuser");
        utilisateur.setMotDePasse("pwd");
        utilisateur.setActif(true);
        utilisateur.setRole(role);
        em.persist(utilisateur);

        // STOCK
        stock = new Stock();
        stock.setProduit(produit);
        stock.setQuantite(10);
        em.persist(stock);
        stock.setMouvements(new ArrayList<>());

        // MOUVEMENT
        StockMouvement mouvement = new StockMouvement();
        mouvement.setTypeMouvement(TypeMouvementStockEnum.ENTREE);
        mouvement.setQuantite(5);
        mouvement.setDateMouvement(LocalDateTime.now());
        mouvement.setProduit(produit);
        mouvement.setUtilisateur(utilisateur);
        mouvement.setStock(stock);

        stock.getMouvements().add(mouvement);

        em.persist(mouvement);
        em.flush();
    }

    @Test
    void shouldFindMovementsByStockId() {
        List<StockMouvement> result = repository.findAllByStockId(stock.getId());

        assertThat(result).hasSize(1);
    }

    @Test
    void shouldFindMovementsByProduitId() {
        List<StockMouvement> result = repository.findAllByProduitId(produit.getId());

        assertThat(result).hasSize(1);
    }

    @Test
    void shouldFindMovementsByUtilisateurId() {
        List<StockMouvement> result = repository.findAllByUtilisateurId(utilisateur.getId());

        assertThat(result).hasSize(1);
    }

    @Test
    void shouldFindMovementsByType() {
        List<StockMouvement> result = repository.findAllByTypeMouvement(TypeMouvementStockEnum.ENTREE);

        assertThat(result).hasSize(1);
    }
}
