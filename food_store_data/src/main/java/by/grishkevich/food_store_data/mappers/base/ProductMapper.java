package by.grishkevich.food_store_data.mappers.base;

import by.grishkevich.food_store_data.dto.ProductPutDTO;
import by.grishkevich.food_store_data.models.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product productPutDtoToProduct(ProductPutDTO productDto);
}
