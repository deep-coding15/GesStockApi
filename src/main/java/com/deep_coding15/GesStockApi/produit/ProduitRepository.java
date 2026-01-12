package com.deep_coding15.GesStockApi.produit;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    Optional<Produit> findByReference(String reference);

    boolean existsByReference(String reference);
}
