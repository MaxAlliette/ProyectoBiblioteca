package cl.bibliotecaproyecto.stock.stock.repository;


import cl.bibliotecaproyecto.stock.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long>{

    List<Stock> findByIdLibro(Long idLibro);
}


