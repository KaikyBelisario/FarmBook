package com.example.farmbook;

public class Farm {

    public String farm,criador,link, producao;

    public Farm() {
    }

    public Farm(String farm, String criador, String link, String producao) {
        this.farm = farm;
        this.criador = criador;
        this.link = link;
        this.producao = producao;
    }

    public String getFarm() {
        return farm;
    }
    public String getCriador() {
        return criador;
    }

    public String getLink() {
        return link;
    }

    public String getProducao() {
        return producao;
    }

}
