package saberion_java_project.hansa_java_project.dto;

import lombok.Data;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDTO {

    @NotBlank(message = "Code is required")
    @Size(max = 12, message = "Code must not exceed 12 characters")
    private String code;

    @NotBlank(message = "Category is required")
    @Size(max = 40, message = "Category must not exceed 40 characters")
    private String category;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @Size(max = 400, message = "Description must not exceed 400 characters")
    private String description;

    @NotNull(message = "Selling price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Selling price must be greater than 0")
    @Digits(integer = 11, fraction = 2, message = "Invalid selling price format")
    private BigDecimal sellingPrice;

    @DecimalMin(value = "0.0", inclusive = false, message = "Special price must be greater than 0")
    @Digits(integer = 11, fraction = 2, message = "Invalid special price format")
    private BigDecimal specialPrice;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Delivery availability is required")
    private Boolean isDeliveryAvailable;

    @NotBlank(message = "Image data is required")
    private String base64Image; // Base64 encoded image

    private List<AttributeDTO> attributes; // List of attributes

    @Data
    public static class AttributeDTO {
        @NotBlank(message = "Attribute name is required")
        private String name;

        @NotBlank(message = "Attribute value is required")
        private String attributeValue;
    }
}
