package repositories;

import models.Invoice;

import java.util.Map;
import java.util.HashMap;

public class InvoiceRepositoryImpl implements InvoiceRepository{
    private Map<Long, Invoice> invoiceDb = new HashMap<>();

    public Invoice save(Invoice invoice){
        invoiceDb.put(invoice.getId(), invoice);
        return invoice;
    }
}