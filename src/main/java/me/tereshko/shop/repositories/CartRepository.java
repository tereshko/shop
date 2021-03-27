package me.tereshko.shop.repositories;

import me.tereshko.shop.models.Cart1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart1, UUID> {
}

