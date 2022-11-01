package com.example.payment.model;

import com.example.payment.enums.PaymentStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name="Propertypayments")
public class PaymentProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;
    @ManyToMany
    @JoinColumn(name = "idUser")
    private ArrayList<User> clienteComprador;
    @Column
    private int valor;
    @Column
    @DateTimeFormat()
    private Date datePayment;
    @ManyToOne
    @JoinColumn(name="idProperty")
    private Property propertyPayment;
    @Column
    private PaymentStatusEnum status;

    public String getDatePaymentYear(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(this.datePayment);
    }

    public String getDatePaymentMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        return sdf.format(this.datePayment);
    }

    public String getDatePaymentDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        return sdf.format(this.datePayment);
    }


}
