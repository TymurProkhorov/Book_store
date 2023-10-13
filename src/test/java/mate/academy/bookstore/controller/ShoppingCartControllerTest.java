package mate.academy.bookstore.controller;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashSet;
import java.util.Set;
import mate.academy.bookstore.config.TestSecurityConfig;
import mate.academy.bookstore.dto.shoppingcart.AddCartItemRequestDto;
import mate.academy.bookstore.dto.shoppingcart.CartItemResponseDto;
import mate.academy.bookstore.dto.shoppingcart.ShoppingCartResponseDto;
import mate.academy.bookstore.dto.shoppingcart.UpdateBookQuantityInCartDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestSecurityConfig.class)
class ShoppingCartControllerTest {
    protected static MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll(@Autowired WebApplicationContext applicationContext) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "test@gmail.com")
    @Sql(scripts = "classpath:database/cart/add-data-for-cart.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:database/cart/delete-all-from-cart.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Get shopping cart by user from @WithMockUser")
    void getCart_Ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/cart"))
                .andExpect(status().isOk())
                .andReturn();
        ShoppingCartResponseDto actual = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), ShoppingCartResponseDto.class);
        ShoppingCartResponseDto expected = getCartResponseDto();
        assertNotNull(actual);
        assertNotEquals(expected, actual);
    }

    @Test
    @WithMockUser(username = "test@gmail.com")
    @Sql(scripts = {"classpath:database/cart/add-data-for-cart.sql",
            "classpath:database/cart/add-book-for-cart.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:database/cart/delete-all-from-cart.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Add book to cart by wrong id")
    void addBookToCart_NotOk() throws Exception {
        AddCartItemRequestDto requestDto = new AddCartItemRequestDto()
                .setBookId(999L)
                .setQuantity(3);
        String request = objectMapper.writeValueAsString(requestDto);
        mockMvc.perform(post("/cart")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "test@gmail.com")
    @Sql(scripts = "classpath:database/cart/add-data-for-cart.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:database/cart/delete-all-from-cart.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Update quantity of books by id 1000, expected status not found")
    void updateQuantityOfBook_NotOk() throws Exception {
        int updatedQuantity = 55;
        UpdateBookQuantityInCartDto requestDto = new UpdateBookQuantityInCartDto(updatedQuantity);
        String request = objectMapper.writeValueAsString(requestDto);
        mockMvc.perform(put("/cart/cart-items/{cartItemId}", 1000L)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "test@gmail.com")
    @Sql(scripts = "classpath:database/cart/add-data-for-cart.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:database/cart/delete-all-from-cart.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Delete mist from cart")
    void deleteCartItemById1_Ok() throws Exception {
        mockMvc.perform(
                        delete("/cart/cart-items/{cartItemId}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "test@gmail.com")
    @Sql(scripts = "classpath:database/cart/add-data-for-cart.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:database/cart/delete-all-from-cart.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Delete book by id 1000")
    void deleteCartItemById999_NotOk() throws Exception {
        mockMvc.perform(
                        delete("/cart/cart-items/{cartItemId}", 1000L))
                .andExpect(status().isNotFound());
    }

    private ShoppingCartResponseDto getCartResponseDto() {
        Set<CartItemResponseDto> cartItems = new HashSet<>();
        cartItems.add(getMistCartItemResponseDto());
        cartItems.add(getPetCemeteryCartItemResponseDto());

        return new ShoppingCartResponseDto()
                .setUserId(1L)
                .setCartItems(cartItems);
    }

    private CartItemResponseDto getMistCartItemResponseDto() {
        return new CartItemResponseDto()
                .setId(1L)
                .setBookId(1L)
                .setBookTitle("Mist")
                .setQuantity(2);
    }

    private CartItemResponseDto getPetCemeteryCartItemResponseDto() {
        return new CartItemResponseDto()
                .setId(2L)
                .setBookId(2L)
                .setBookTitle("Pet cemetery")
                .setQuantity(4);
    }
}
