package entities.ex5;

import jakarta.persistence.*;

@Entity
@Table(name = "bank_account")
public class BankAccount extends BillingDetails{
    @Column
    private String name;

    @Column(name = "swift_code")
    private String swiftCode;
}
