package repositories;


import models.Invoice;

public interface InvoiceRepository {
    Invoice save(Invoice invoice);
}