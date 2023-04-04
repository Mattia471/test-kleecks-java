package com.demo.test.demotest.model;

import com.demo.test.demotest.dto.response.TotalSalesResponse;
import lombok.Data;

import javax.persistence.*;

@NamedNativeQuery(name = "TotalSales.findTotalSalesByUserAndYear",
        query = "SELECT id,user as utente, SUM(total) as totaleCorrente, year as annoCorrente, (SUM(total) - (SELECT CASE WHEN SUM(total)>0 THEN SUM(total) ELSE 0 END as totalePrecedente FROM total_sales tsj where tsj.user=ts.user and tsj.year=ts.year-1)) as rendimentoRispettoAnnoPrecedente FROM total_sales ts group by user,year",
        resultSetMapping = "Mapping.TotalSalesResponse")
@SqlResultSetMapping(
        name = "Mapping.TotalSalesResponse",
        classes = @ConstructorResult(
                targetClass = TotalSalesResponse.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "utente", type = String.class),
                        @ColumnResult(name = "totaleCorrente", type = Long.class),
                        @ColumnResult(name = "annoCorrente", type = Long.class),
                        @ColumnResult(name = "rendimentoRispettoAnnoPrecedente", type = Long.class)
                }
        ))
@Entity
@Table(name = "total_sales")
@Data
public class TotalSales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String user;

    @Column
    private Long year;

    @Column
    private Long total;
}
