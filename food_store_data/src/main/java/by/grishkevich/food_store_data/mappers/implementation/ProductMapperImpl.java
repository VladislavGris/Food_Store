package by.grishkevich.food_store_data.mappers.implementation;

import by.grishkevich.food_store_data.dto.ProductPutDTO;
import by.grishkevich.food_store_data.mappers.base.ProductMapper;
import by.grishkevich.food_store_data.models.Product;
import by.grishkevich.food_store_data.repositories.CategoryRepository;
import by.grishkevich.food_store_data.repositories.CountryRepository;
import by.grishkevich.food_store_data.repositories.TrademarkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductMapperImpl implements ProductMapper {

    private TrademarkRepository trademarkRepository;
    private CategoryRepository categoryRepository;
    private CountryRepository countryRepository;

    public ProductMapperImpl(TrademarkRepository trademarkRepository, CategoryRepository categoryRepository, CountryRepository countryRepository){
        this.trademarkRepository = trademarkRepository;
        this.categoryRepository = categoryRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Product productPutDtoToProduct(ProductPutDTO productDto) {
        if(productDto == null){
            log.warn("ProductDTO is null");
            return null;
        }

        Product product = new Product();

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageRef(productDto.getImageRef());
        product.setTrademark(trademarkRepository.findByName(productDto.getTrademark()));
        product.setCountry(countryRepository.findByName(productDto.getCountry()));
        product.setCategory(categoryRepository.findByName(productDto.getCategory()));

        return product;
    }
}
