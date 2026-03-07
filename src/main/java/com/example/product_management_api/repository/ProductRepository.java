package com.example.product_management_api.repository;
import com.example.product_management_api.Entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface ProductRepository extends JpaRepository<Product, Long> {
    

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    Page<Product> findByNameContainingIgnoreCase(@Param("name")String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category =:category")
    Page<Product> findByCategory(@Param("category") String category, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    Page<Product> findByPriceBetween(@Param("minPrice")Double minPrice,
                                    @Param("maxPrice")Double maxPrice,
                                    Pageable pageable);
    @Query("SELECT p FROM Product p WHERE (p.quantity >:quantity) ")
    Page<Product> findByQuantityGreaterThan(@Param("quantity") Integer quantity, Pageable pageable);
    
    @Query("SELECT COUNT(p) > 0 FROM Product p WHERE LOWER(p.name)=LOWER(:name)")
    boolean existsByNameIgnoreCase(@Param("name")String name);


    @Query("SELECT p FROM Product p WHERE p.category=:category AND p.price BETWEEN :minPrice AND :maxPrice")
    Page<Product> findByCategoryAndPriceRange(@Param("category") String category,
                                            @Param("minPrice")Double minPrice,
                                            @Param("maxPrice")Double maxPrice,
                                            Pageable pageable);


    @Query("SELECT p FROM Product p WHERE p.quantity< :threshold ORDER BY p.quantity ASC")
    Page<Product> findLowStockProducts(@Param("threshold") Integer threshold,Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.price >(SELECT AVG(p2.price) FROM Product p2) ORDER BY p.price DESC")
    Page<Product> findExpensiveProducts(Pageable pageable);
}
