package com.dmedeiros.mybill.bill.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private LocalDate payday = LocalDate.now();
    private boolean estado;
    private boolean repetir;
    @Enumerated(EnumType.STRING)
    private BillType tipoConta;
    private String chaveGrupoContas;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getPayday() {
        return payday;
    }

    public void setPayday(LocalDate payday) {
        this.payday = payday;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isRepetir() {
        return repetir;
    }

    public void setRepetir(boolean repetir) {
        this.repetir = repetir;
    }

    public BillType getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(BillType tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getChaveGrupoContas() {
        return chaveGrupoContas;
    }

    public void setChaveGrupoContas(String chaveGrupoContas) {
        this.chaveGrupoContas = chaveGrupoContas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return estado == bill.estado &&
                repetir == bill.repetir &&
                Objects.equals(id, bill.id) &&
                Objects.equals(name, bill.name) &&
                Objects.equals(price, bill.price) &&
                Objects.equals(payday, bill.payday) &&
                tipoConta == bill.tipoConta &&
                Objects.equals(chaveGrupoContas, bill.chaveGrupoContas);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, price, payday, estado, repetir, tipoConta, chaveGrupoContas);
    }
}
