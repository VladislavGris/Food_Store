package by.grishkevich.food_store_data.repositories;

import by.grishkevich.food_store_data.models.Category;
import by.grishkevich.food_store_data.models.Country;
import by.grishkevich.food_store_data.models.Product;
import by.grishkevich.food_store_data.models.Trademark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Iterable<Product> findAllByTrademark(Trademark trademark);
    Iterable<Product> findAllByCountry(Country country);
    Iterable<Product> findAllByCategory(Category category);
    @Query("from Product p where p.name like :searchText order by p.price asc ")
    Page<Product> findAll(Pageable pageable,@Param("searchText") String searchText);
    @Query("from Product p " +
            "where p.category.name like :category and " +
            "p.country.name like :country and " +
            "p.trademark.name like :trademark order by p.price asc")
    Page<Product> filter(Pageable pageable, @Param("category") String category,
                         @Param("country") String country, @Param("trademark") String trademark);
}
