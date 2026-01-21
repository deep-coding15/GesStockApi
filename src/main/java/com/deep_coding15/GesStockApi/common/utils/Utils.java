package com.deep_coding15.GesStockApi.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.UUID;

/**
 * <p>
 * Classe utilitaire centralisant des méthodes génériques et réutilisables
 * à travers toute l'application GesStockApi.
 * </p>
 *
 * <p>
 * Cette classe :
 * <ul>
 * <li>Ne contient aucune logique métier</li>
 * <li>Est stateless</li>
 * <li>Ne doit jamais être instanciée</li>
 * </ul>
 * </p>
 *
 * <p>
 * Utilisation typique :
 * validation des entrées, sécurisation contre les null,
 * formatage monétaire, génération d'identifiants.
 * </p>
 */
public final class Utils {

    /**
     * Constructeur privé empêchant l'instanciation.
     *
     * @throws UnsupportedOperationException toujours levée
     */
    private Utils() {
        throw new UnsupportedOperationException("Classe utilitaire ne peut pas être instanciée");
    }

    /*
     * =====================================================
     * STRINGS
     * =====================================================
     */

    /**
     * Vérifie si une chaîne est inutilisable.
     *
     * @param str la chaîne à tester
     * @return {@code true} si la chaîne est {@code null}, vide ou composée
     *         uniquement d'espaces,
     *         {@code false} sinon
     */
    public static boolean isStringUseless(String str) {
        return str == null || str.isBlank();
    }

    /**
     * Vérifie qu'une chaîne n'est ni nulle ni vide.
     *
     * @param value     la valeur à vérifier
     * @param fieldName le nom fonctionnel du champ (utilisé dans le message
     *                  d'erreur)
     * @throws IllegalArgumentException si la chaîne est nulle ou vide
     */
    public static void requireNonBlank(String value, String fieldName) {
        if (isStringUseless(value)) {
            throw new IllegalArgumentException(fieldName + " ne doit pas être vide");
        }
    }

    /*
     * =====================================================
     * COLLECTIONS
     * =====================================================
     */

    /**
     * Vérifie si une collection est nulle ou vide.
     *
     * @param collection la collection à tester
     * @return {@code true} si la collection est {@code null} ou vide,
     *         {@code false} sinon
     */
    public static boolean isCollectionEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /*
     * =====================================================
     * MONNAIE / BIGDECIMAL
     * =====================================================
     */

    /**
     * Formate un montant monétaire à deux décimales.
     *
     * <p>
     * Si le montant est {@code null}, la valeur {@code 0.00} est retournée.
     * </p>
     *
     * @param amount le montant à formater
     * @return le montant formaté à deux décimales
     */
    public static BigDecimal formatCurrency(BigDecimal amount) {
        if (amount == null) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Vérifie qu'un montant est strictement positif et le formate.
     *
     * @param amount    le montant à valider
     * @param fieldName le nom fonctionnel du champ
     * @return le montant formaté à deux décimales
     * @throws IllegalArgumentException si le montant est nul ou négatif
     */
    public static BigDecimal requirePositiveAmount(BigDecimal amount, String fieldName) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(fieldName + " doit être positif");
        }
        return formatCurrency(amount);
    }

    /*
     * =====================================================
     * IDENTIFIANTS
     * =====================================================
     */

    /**
     * Génère un identifiant court unique.
     *
     * <p>
     * Exemple d'utilisation :
     * 
     * <pre>
     * produit.setReference("PRD-" + Utils.generateShortId());
     * </pre>
     * </p>
     *
     * @return une chaîne unique de 8 caractères en majuscules
     */
    public static String generateShortId() {
        return UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8)
                .toUpperCase();
    }

    /**
     * Vérifie qu'un identifiant est valide.
     *
     * @param id         l'identifiant à vérifier
     * @param entityName le nom de l'entité concernée
     * @throws IllegalArgumentException si l'identifiant est nul ou inférieur ou
     *                                  égal à zéro
     */
    public static void requireValidId(Long id, String entityName) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID invalide pour " + entityName);
        }
    }

    /*
     * =====================================================
     * NULL SAFETY
     * =====================================================
     */

    /**
     * Retourne une valeur par défaut si la valeur fournie est nulle.
     *
     * @param value        la valeur initiale
     * @param defaultValue la valeur par défaut
     * @param <T>          le type de l'objet
     * @return {@code value} si non nulle, sinon {@code defaultValue}
     */
    public static <T> T getOrDefault(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    /*
     * =====================================================
     * ENUM
     * =====================================================
     */

    /**
     * Vérifie si une valeur correspond à une constante valide d'un enum.
     *
     * @param enumClass la classe de l'enum
     * @param value     la valeur à tester
     * @param <E>       le type de l'enum
     * @return {@code true} si la valeur correspond à une constante de l'enum,
     *         {@code false} sinon
     */
    public static <E extends Enum<E>> boolean isValidEnum(Class<E> enumClass, String value) {
        if (isStringUseless(value))
            return false;
        try {
            Enum.valueOf(enumClass, value.toUpperCase());
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
